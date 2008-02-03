/*
 * Created on 13 nov. 2005
 *
 */
package fr.umlv.tatoo.cc.main.main;

import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fr.umlv.tatoo.cc.common.extension.ExtensionBus;
import fr.umlv.tatoo.cc.common.generator.IdMap;
import fr.umlv.tatoo.cc.common.log.Info;
import fr.umlv.tatoo.cc.common.log.ReporterFactory;
import fr.umlv.tatoo.cc.common.main.Unit;
import fr.umlv.tatoo.cc.common.xml.AbstractXMLDigester;
import fr.umlv.tatoo.cc.lexer.charset.encoding.Encoding;
import fr.umlv.tatoo.cc.lexer.generator.LexerExtension;
import fr.umlv.tatoo.cc.lexer.lexer.RuleDecl;
import fr.umlv.tatoo.cc.lexer.lexer.RuleFactory;
import fr.umlv.tatoo.cc.lexer.main.LexerDataKeys;
import fr.umlv.tatoo.cc.lexer.xml.LexerXMLDigester;
import fr.umlv.tatoo.cc.main.xml.DispatchXMLDigester;
import fr.umlv.tatoo.cc.parser.generator.ParserExtension;
import fr.umlv.tatoo.cc.parser.grammar.GrammarFactory;
import fr.umlv.tatoo.cc.parser.grammar.ParserTableBuilder;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.VariableDecl;
import fr.umlv.tatoo.cc.parser.main.ParserDataKeys;
import fr.umlv.tatoo.cc.parser.parser.ActionDeclFactory;
import fr.umlv.tatoo.cc.parser.table.LogInfoConflictDiagnosticReporter;
import fr.umlv.tatoo.cc.parser.table.ParserTableDecl;
import fr.umlv.tatoo.cc.parser.xml.ParserXMLDigester;
import fr.umlv.tatoo.cc.tools.ast.generator.ToolsASTExtension;
import fr.umlv.tatoo.cc.tools.generator.ToolsExtension;
import fr.umlv.tatoo.cc.tools.main.ToolsDataKeys;
import fr.umlv.tatoo.cc.tools.tools.RuleInfo;
import fr.umlv.tatoo.cc.tools.tools.ToolsFactory;
import fr.umlv.tatoo.cc.tools.xml.ToolXMLDigester;

public class MainBatch {
  public void execute(MainBean bean)
    throws IOException, ParserConfigurationException, SAXException {
    
    List<? extends File> files=bean.getAllInputFiles();
    
    if (bean.getLogLevel()!=null)
      ReporterFactory.setLogLevel(bean.getLogLevel());
    
    // get extensions
    ExtensionBus extensionBus = bean.getExtensionBus();
    if (bean.isRegisterDefaultExtensions()) {
      extensionBus.register(new LexerExtension());
      extensionBus.register(new ParserExtension());
      if (bean.isGenerateAST())
        extensionBus.register(new ToolsASTExtension());
      else
        extensionBus.register(new ToolsExtension());
    }
    
    //  lexer
    Encoding encoding=bean.getLexerType().getEncoding();
    RuleFactory ruleFactory=new RuleFactory();
    LexerXMLDigester lexerDigester=new LexerXMLDigester(ruleFactory,encoding);
    
    // parser
    GrammarFactory grammarFactory=new GrammarFactory();
    ParserXMLDigester parserDigester=new ParserXMLDigester(grammarFactory);
    
    // tools
    ToolsFactory toolsFactory=new ToolsFactory();
    IdMap<RuleDecl> ruleMap=ruleFactory.getRuleMap();
    IdMap<VariableDecl> variableMap=grammarFactory.getVariableMap();
    ToolXMLDigester toolDigester=new ToolXMLDigester(
        ruleMap,variableMap,toolsFactory);
    
    // ebnf
    EBNFParser parser=EBNFParser.createEBNFParser(ruleFactory,encoding,grammarFactory,toolsFactory);
    
    ParserTableBuilder parserTableBuilder=null;
    
    boolean validating=bean.isValidating();
    EnumMap<Unit,Object> map=new EnumMap<Unit,Object>(Unit.class);
    for(File file:files) {
      if (file.getName().endsWith(".ebnf")) {
        if (parser==null)
          throw new IllegalStateException("EBNF parser is not available");
        
        ReporterFactory.setAndSealDefaultInfo(new Info().file(file));
        parserTableBuilder=parser.parse(file);
        
        boolean ruleExists=!ruleFactory.getAllRules().isEmpty();
        boolean productionExists=!grammarFactory.getAllProductions().isEmpty();
        
        if (ruleExists) {
          map.put(Unit.lexer,file);
        }
        if (productionExists) {
          map.put(Unit.parser,file);
        }
        if (ruleExists && productionExists) {
          map.put(Unit.tools,file);
        }
        
        //System.out.println(grammarFactory.getAllProductions());
      }
      else {
        DispatchXMLDigester dispatcher=new DispatchXMLDigester(lexerDigester,parserDigester,toolDigester);
        
        dispatcher.parse(file,validating);
        AbstractXMLDigester delegate = dispatcher.getDelegate();
        if (delegate==parserDigester) {
          parserTableBuilder=parserDigester;
        }
        map.put(delegate.getUnit(),file);
      }
    }
    
    if (parserTableBuilder!=null) {
      if (parserTableBuilder.isFatalError()) {
        throw new IllegalStateException("error grammar or ebnf is ill formed");
      }

      Set<TerminalDecl> unused = grammarFactory.checkUnusedTerminal();
      if (!unused.isEmpty())
        Info.warning("unused though declared terminal", unused);
    }
    
    // publish all
    for(Map.Entry<Unit,Object> entry:map.entrySet()) {
      Object source=entry.getValue();
      ReporterFactory.setAndSealDefaultInfo(new Info().file(source));
      switch(entry.getKey()) {
        case lexer:
          extensionBus.publish(LexerDataKeys.ruleFactory,ruleFactory);
          break;
          
        case parser:
          // if no version, add a default one
          if (grammarFactory.getAllVersions().isEmpty())
            grammarFactory.createVersion("DEFAULT",null);
          
          extensionBus.publish(ParserDataKeys.grammarRepository,grammarFactory);
          ParserTableDecl table=parserTableBuilder.createParserTableDecl(new ActionDeclFactory(),
              bean.getParserType().getMethod(),
              bean.getConflictResolverType().getConflictResolver(),
              new LogInfoConflictDiagnosticReporter(null),
              bean.getLogFile());
          extensionBus.publish(ParserDataKeys.parserTable,table);
          extensionBus.publish(ParserDataKeys.ebnfSupport,parserTableBuilder.getEBNFSupport());
          break;
          
        case tools:
          Map<RuleDecl,RuleInfo> ruleInfoMap = toolsFactory.getRuleInfoMap();
          toolsFactory.checkUndefinedRules(ruleFactory,ruleInfoMap);
          toolsFactory.checkUnspawnTerminals(grammarFactory,ruleInfoMap);
          extensionBus.publish(ToolsDataKeys.toolsFactory,toolsFactory);
          break;
          
        case ast:
        case ebnf:
        case linker:
          throw new AssertionError();
      }
    }
  }
}
