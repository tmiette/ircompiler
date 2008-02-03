package fr.umlv.tatoo.gui.jit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fr.umlv.tatoo.cc.lexer.charset.encoding.UTF16Encoding;
import fr.umlv.tatoo.cc.lexer.lexer.RuleDecl;
import fr.umlv.tatoo.cc.lexer.lexer.RuleFactory;
import fr.umlv.tatoo.cc.lexer.regex.RegexIntervalTable;
import fr.umlv.tatoo.cc.lexer.xml.LexerXMLDigester;
import fr.umlv.tatoo.runtime.buffer.LexerBuffer;
import fr.umlv.tatoo.runtime.buffer.TokenBuffer;
import fr.umlv.tatoo.runtime.lexer.Lexer;
import fr.umlv.tatoo.runtime.lexer.LexerListener;
import fr.umlv.tatoo.runtime.lexer.LexerTable;
import fr.umlv.tatoo.runtime.lexer.LifecycleHandler;
import fr.umlv.tatoo.runtime.lexer.RuleActivator;
import fr.umlv.tatoo.runtime.lexer.rules.RuleData;
import fr.umlv.tatoo.runtime.regex.CharRegexTable;
import fr.umlv.tatoo.runtime.tools.builder.LexerBuilder;

public class RuntimeLexer<B extends LexerBuffer & TokenBuffer<D>,D> {
  
  private final RuleFactory ruleFactory;
  private final LexerXMLDigester digester;
  private Lexer<B> lexer;
  
  public RuntimeLexer(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
    ruleFactory = new RuleFactory();
    digester = new LexerXMLDigester(ruleFactory,new UTF16Encoding());
    digester.parse(xmlFile, true);
  }
  
  public RuleFactory getRuleFactory() {
    return ruleFactory;
  }
  
  public Lexer<B> getLexer() {
    return lexer;
  }
  
  public void createRuntimeLexer(B buffer, LexerListener<RuleDecl,? super B> listener,
      RuntimeParser parser,RuleActivator<RuleDecl> activator,LifecycleHandler<B> lifeCycleHandler) {
    
    HashMap<RuleDecl,RuleData> ruleMap=new HashMap<RuleDecl,RuleData>();
    for(RuleDecl rule:ruleFactory.getAllRules())
      ruleMap.put(rule,createRuleData(rule));
    
    LexerTable<RuleDecl> lexerTable=new LexerTable<RuleDecl>(ruleMap);
     LexerBuilder<RuleDecl, B> builder =
       LexerBuilder.createBuilder(lexerTable, buffer);

    builder.setParser(parser.getParser());
    builder.setLexerListener(listener);
    builder.setActivator(activator);
    builder.setLifecycleHandler(lifeCycleHandler);
    
    
    lexer = builder.createLexer();
  }
  
  public void resetLexer(B buffer) {
    lexer.reset(buffer);
  }
  
  private static RuleData createRuleData(RuleDecl rule) {
    RegexIntervalTable main=rule.getMainRegex();
    CharRegexTable mainRegex=new CharRegexTable(main.getFirstState(),main.getTransitions(),main.getAccepts());
    RegexIntervalTable follow=rule.getFollowRegex();
    CharRegexTable followRegex;
    if (follow!=null)
      followRegex=new CharRegexTable(follow.getFirstState(),follow.getTransitions(),follow.getAccepts());
    else
      followRegex=null;
    return new RuleData(mainRegex,followRegex,rule.isBeginningOfLineRequired());
  }

}
