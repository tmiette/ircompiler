package fr.umlv.IRCompiler.tatoo.tools;

import fr.umlv.IRCompiler.tatoo.lexer.RuleEnum;
import fr.umlv.IRCompiler.tatoo.parser.TerminalEnum;
import fr.umlv.IRCompiler.tatoo.parser.VersionEnum;
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
    map.put(TerminalEnum.val,EnumSet.of(RuleEnum.val));
    map.put(TerminalEnum.open_brace,EnumSet.of(RuleEnum.open_brace));
    map.put(TerminalEnum.letter,EnumSet.of(RuleEnum.letter));
    map.put(TerminalEnum.space,EnumSet.of(RuleEnum.space));
    map.put(TerminalEnum.or,EnumSet.of(RuleEnum.or));
    map.put(TerminalEnum.close_brace,EnumSet.of(RuleEnum.close_brace));
    map.put(TerminalEnum.and,EnumSet.of(RuleEnum.and));
    map.put(TerminalEnum.not,EnumSet.of(RuleEnum.not));
    return map;
  }

  public static EnumSet<RuleEnum> getUnconditionalRules() {
    return  EnumSet.of(RuleEnum.blk);
  }

  public static EnumParserLookaheadActivator<RuleEnum,TerminalEnum,VersionEnum> getActivator(Parser<TerminalEnum,?,?,VersionEnum> parser) {
    return new EnumParserLookaheadActivator<RuleEnum,TerminalEnum,VersionEnum>(
      parser,getTerminalRulesMap(),RuleEnum.class,getUnconditionalRules());
  }
}
