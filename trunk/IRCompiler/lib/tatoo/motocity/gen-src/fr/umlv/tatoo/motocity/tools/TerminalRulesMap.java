package fr.umlv.tatoo.motocity.tools;

import fr.umlv.tatoo.motocity.lexer.RuleEnum;
import fr.umlv.tatoo.motocity.parser.TerminalEnum;
import fr.umlv.tatoo.motocity.parser.VersionEnum;
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
    map.put(TerminalEnum.blank,EnumSet.of(RuleEnum.blank));
    map.put(TerminalEnum.headJokerIn,EnumSet.of(RuleEnum.headJokerIn));
    map.put(TerminalEnum.javaJokerOut,EnumSet.of(RuleEnum.javaJokerOut));
    map.put(TerminalEnum.javaLetter,EnumSet.of(RuleEnum.javaLetter));
    map.put(TerminalEnum.name,EnumSet.of(RuleEnum.name));
    map.put(TerminalEnum.paramkw,EnumSet.of(RuleEnum.paramkw));
    map.put(TerminalEnum.javaJokerIn,EnumSet.of(RuleEnum.javaJokerIn));
    map.put(TerminalEnum.semicolon,EnumSet.of(RuleEnum.semicolon));
    map.put(TerminalEnum.jokerOut,EnumSet.of(RuleEnum.jokerOut));
    map.put(TerminalEnum.jokerIn,EnumSet.of(RuleEnum.jokerIn));
    map.put(TerminalEnum.imports,EnumSet.of(RuleEnum.imports));
    map.put(TerminalEnum.colon,EnumSet.of(RuleEnum.colon));
    map.put(TerminalEnum.headJokerOut,EnumSet.of(RuleEnum.headJokerOut));
    map.put(TerminalEnum.type,EnumSet.of(RuleEnum.type));
    map.put(TerminalEnum.letter,EnumSet.of(RuleEnum.letter));
    return map;
  }

  public static EnumSet<RuleEnum> getUnconditionalRules() {
    return   EnumSet.noneOf(RuleEnum.class);
  }

  public static EnumParserLookaheadActivator<RuleEnum,TerminalEnum,VersionEnum> getActivator(Parser<TerminalEnum,?,?,VersionEnum> parser) {
    return new EnumParserLookaheadActivator<RuleEnum,TerminalEnum,VersionEnum>(
      parser,getTerminalRulesMap(),RuleEnum.class,getUnconditionalRules());
  }
}
