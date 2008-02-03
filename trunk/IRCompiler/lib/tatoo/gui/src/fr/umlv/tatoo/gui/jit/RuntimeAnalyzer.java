package fr.umlv.tatoo.gui.jit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fr.umlv.tatoo.cc.lexer.lexer.RuleDecl;
import fr.umlv.tatoo.cc.tools.tools.RuleInfo;
import fr.umlv.tatoo.cc.tools.tools.ToolsFactory;
import fr.umlv.tatoo.cc.tools.xml.ToolXMLDigester;
import fr.umlv.tatoo.gui.util.Mapper;
import fr.umlv.tatoo.runtime.buffer.LexerBuffer;
import fr.umlv.tatoo.runtime.buffer.TokenBuffer;
import fr.umlv.tatoo.runtime.lexer.Lexer;
import fr.umlv.tatoo.runtime.lexer.LexerListener;
import fr.umlv.tatoo.runtime.lexer.LifecycleHandler;
import fr.umlv.tatoo.runtime.parser.ErrorRecoveryListener;
import fr.umlv.tatoo.runtime.parser.ParserListener;
import fr.umlv.tatoo.runtime.tools.HashParserLookaheadActivator;

public class RuntimeAnalyzer<B extends TokenBuffer<D>&LexerBuffer,D> implements Runnable {
  
  final RuntimeParser parser;
  private final ToolsFactory factory;
  private final RuntimeLexer<B,D> lexer;
  
  public  RuntimeAnalyzer(File xmlLexer, File xmlParser, File xmlTools, 
      B buffer,
      ParserListener<NamedObject, NamedObject, Production> parserListener,
      ErrorRecoveryListener<NamedObject, NamedObject> errorRecovery,
      AttributeHolder holder,ParserSettings settings) throws ParserConfigurationException, SAXException, IOException {
    lexer = new RuntimeLexer<B,D>(xmlLexer);
    parser = new RuntimeParser(xmlParser,
        parserListener,errorRecovery,
        settings);
    NamedObjectFactory objectFactory = parser.getObjectFactory();
    
    factory = new ToolsFactory();
    ToolXMLDigester digester = new ToolXMLDigester(lexer.getRuleFactory().getRuleMap(),
        parser.getFactory().getVariableMap(),factory); 
    digester.parse(xmlTools,true);
    
    HashMap<NamedObject,HashSet<RuleDecl>> terminalMap =
      Mapper.apply(factory.getTerminalRulesMap(),
          objectFactory.terminalFunction(),
          Mapper.<RuleDecl>setToHashSet());
    
    HashSet<RuleDecl> unconditionalRules = terminalMap.remove(null);

    if (unconditionalRules==null)
      unconditionalRules=new HashSet<RuleDecl>();

    HashParserLookaheadActivator<RuleDecl, NamedObject> activator =
      new HashParserLookaheadActivator<RuleDecl, NamedObject>(parser.getParser(),terminalMap,unconditionalRules);
    lexer.createRuntimeLexer(buffer, createLexerListener(holder) , parser, activator,getLifecycleHandler(settings));
  }
  
  public void run() {
    lexer.getLexer().run();
  }
  
  public void reset(B buffer)  {
    lexer.resetLexer(buffer);
  }
  
  private LexerListener<RuleDecl, TokenBuffer<D>> createLexerListener(final AttributeHolder holder) {
    final HashMap<RuleDecl,NamedObject> map = new HashMap<RuleDecl, NamedObject>();
    Map<RuleDecl, RuleInfo> infoMap = factory.getRuleInfoMap();
    NamedObjectFactory objectFactory = parser.getObjectFactory();
    for(Map.Entry<RuleDecl,RuleInfo> entry : infoMap.entrySet())
      map.put(entry.getKey(),objectFactory.get(entry.getValue().getTerminal()));
    
    return new LexerListener<RuleDecl,TokenBuffer<D>>() {
      
      public void ruleVerified(RuleDecl rule, int size, TokenBuffer<D> buffer) {
        NamedObject terminal = map.get(rule);
        holder.setAttribute(buffer.view().toString());
          parser.getParser().step(terminal);

        buffer.discard();
      }
    };
  }
  
  private LifecycleHandler<B> getLifecycleHandler(final ParserSettings settings) {
    return new LifecycleHandler<B>(){
      public void handleClose(Lexer<B> lexer) {
        parser.close();
      }
      public void handleReset(Lexer<B> lexer) {
        parser.resetParser(settings);
      }
    };
  }

  public NamedObject[] getStarts() {
    return parser.getStarts();
  }

  public NamedObject[] getVersions() {
    return parser.getVersions();
  }

}
