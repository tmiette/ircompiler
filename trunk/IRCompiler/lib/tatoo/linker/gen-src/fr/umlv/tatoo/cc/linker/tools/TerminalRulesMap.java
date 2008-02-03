package fr.umlv.tatoo.cc.linker.tools;

import fr.umlv.tatoo.cc.linker.lexer.RuleEnum;
import fr.umlv.tatoo.cc.linker.parser.TerminalEnum;
import fr.umlv.tatoo.cc.linker.parser.VersionEnum;
import fr.umlv.tatoo.runtime.parser.Parser;
import fr.umlv.tatoo.runtime.tools.EnumParserLookaheadActivator;
import java.util.EnumMap;
import java.util.EnumSet;

/** 
 *  This class is generated - please do not edit it 
 */
public class TerminalRulesMap {
  public static EnumMap<TerminalEnum,EnumSet<RuleEnum>> getTerminalRulesMap() {
    EnumMap<TerminalEnum,EnumSet<RuleEnum>> map=new EnumMap<TerminalEnum,EnumSet<RuleEnum>>(TerminalEnum.class);
    map.put(TerminalEnum.assign,EnumSet.of(RuleEnum.assign));
    map.put(TerminalEnum.quotedstring,EnumSet.of(RuleEnum.quotedstring));
    map.put(TerminalEnum.grammar,EnumSet.of(RuleEnum.grammar));
    map.put(TerminalEnum.lbracket,EnumSet.of(RuleEnum.lbracket));
    map.put(TerminalEnum.colon,EnumSet.of(RuleEnum.colon));
    map.put(TerminalEnum.root,EnumSet.of(RuleEnum.root));
    map.put(TerminalEnum.dot,EnumSet.of(RuleEnum.dot));
    map.put(TerminalEnum.rbracket,EnumSet.of(RuleEnum.rbracket));
    map.put(TerminalEnum.identifier,EnumSet.of(RuleEnum.identifier));
    map.put(TerminalEnum.link,EnumSet.of(RuleEnum.link));
    return map;
  }

  public static EnumSet<RuleEnum> getUnconditionalRules() {
    return  EnumSet.of(RuleEnum.space);
  }

  public static EnumParserLookaheadActivator<RuleEnum,TerminalEnum,VersionEnum> getActivator(Parser<TerminalEnum,?,?,VersionEnum> parser) {
    return new EnumParserLookaheadActivator<RuleEnum,TerminalEnum,VersionEnum>(
      parser,getTerminalRulesMap(),RuleEnum.class,getUnconditionalRules());
  }
}
