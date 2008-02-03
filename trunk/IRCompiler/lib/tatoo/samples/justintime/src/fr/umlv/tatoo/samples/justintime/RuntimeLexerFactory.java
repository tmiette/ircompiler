package fr.umlv.tatoo.samples.justintime;

import java.util.HashMap;

import fr.umlv.tatoo.cc.lexer.lexer.RuleDecl;
import fr.umlv.tatoo.cc.lexer.lexer.RuleFactory;
import fr.umlv.tatoo.cc.lexer.regex.RegexIntervalTable;
import fr.umlv.tatoo.runtime.buffer.LexerBuffer;
import fr.umlv.tatoo.runtime.lexer.LexerListener;
import fr.umlv.tatoo.runtime.lexer.LexerTable;
import fr.umlv.tatoo.runtime.lexer.SimpleLexer;
import fr.umlv.tatoo.runtime.lexer.rules.RuleData;
import fr.umlv.tatoo.runtime.regex.CharRegexTable;
import fr.umlv.tatoo.runtime.tools.builder.LexerBuilder;

public class RuntimeLexerFactory {
  public static <B extends LexerBuffer> SimpleLexer createRuntimeLexer(RuleFactory ruleFactory,B buffer,LexerListener<RuleDecl,? super B> listener) {
    HashMap<RuleDecl,RuleData> ruleMap=new HashMap<RuleDecl,RuleData>();
    for(RuleDecl rule:ruleFactory.getAllRules())
      ruleMap.put(rule,createRuleData(rule));
    
    LexerTable<RuleDecl> lexerTable=new LexerTable<RuleDecl>(ruleMap);
    return LexerBuilder.createBuilder(lexerTable, buffer).setLexerListener(listener).createSimpleLexer();
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
