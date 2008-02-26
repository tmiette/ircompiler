package tatoo.tools;

import tatoo.lexer.RuleEnum;
import tatoo.parser.TerminalEnum;
import tatoo.parser.VersionEnum;
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
    map.put(TerminalEnum.comment_,EnumSet.of(RuleEnum.comment_));
    map.put(TerminalEnum.number_,EnumSet.of(RuleEnum.number_));
    map.put(TerminalEnum.double_type_,EnumSet.of(RuleEnum.double_type_));
    map.put(TerminalEnum.rpar_,EnumSet.of(RuleEnum.rpar_));
    map.put(TerminalEnum.function_,EnumSet.of(RuleEnum.function_));
    map.put(TerminalEnum.foreach_,EnumSet.of(RuleEnum.foreach_));
    map.put(TerminalEnum.in_,EnumSet.of(RuleEnum.in_));
    map.put(TerminalEnum.print_,EnumSet.of(RuleEnum.print_));
    map.put(TerminalEnum.import_,EnumSet.of(RuleEnum.import_));
    map.put(TerminalEnum.do_,EnumSet.of(RuleEnum.do_));
    map.put(TerminalEnum.inequal_,EnumSet.of(RuleEnum.inequal_));
    map.put(TerminalEnum.float_type_,EnumSet.of(RuleEnum.float_type_));
    map.put(TerminalEnum.affect_,EnumSet.of(RuleEnum.affect_));
    map.put(TerminalEnum.dot_,EnumSet.of(RuleEnum.dot_));
    map.put(TerminalEnum.end_function_,EnumSet.of(RuleEnum.end_function_));
    map.put(TerminalEnum.plus_,EnumSet.of(RuleEnum.plus_));
    map.put(TerminalEnum.double_,EnumSet.of(RuleEnum.double_));
    map.put(TerminalEnum.identifier_,EnumSet.of(RuleEnum.identifier_));
    map.put(TerminalEnum.mult_,EnumSet.of(RuleEnum.mult_));
    map.put(TerminalEnum.float_,EnumSet.of(RuleEnum.float_));
    map.put(TerminalEnum.lpar_,EnumSet.of(RuleEnum.lpar_));
    map.put(TerminalEnum.done_,EnumSet.of(RuleEnum.done_));
    map.put(TerminalEnum.div_,EnumSet.of(RuleEnum.div_));
    map.put(TerminalEnum.boolean_type_,EnumSet.of(RuleEnum.boolean_type_));
    map.put(TerminalEnum.string_,EnumSet.of(RuleEnum.string_));
    map.put(TerminalEnum.fi_,EnumSet.of(RuleEnum.fi_));
    map.put(TerminalEnum.void_type_,EnumSet.of(RuleEnum.void_type_));
    map.put(TerminalEnum.boolean_,EnumSet.of(RuleEnum.boolean_));
    map.put(TerminalEnum.comma_,EnumSet.of(RuleEnum.comma_));
    map.put(TerminalEnum.return_,EnumSet.of(RuleEnum.return_));
    map.put(TerminalEnum.minus_,EnumSet.of(RuleEnum.minus_));
    map.put(TerminalEnum.integer_type_,EnumSet.of(RuleEnum.integer_type_));
    map.put(TerminalEnum.semicolon_,EnumSet.of(RuleEnum.semicolon_));
    map.put(TerminalEnum.equal_,EnumSet.of(RuleEnum.equal_));
    map.put(TerminalEnum.not_,EnumSet.of(RuleEnum.not_));
    map.put(TerminalEnum.string_type_,EnumSet.of(RuleEnum.string_type_));
    map.put(TerminalEnum.else_,EnumSet.of(RuleEnum.else_));
    map.put(TerminalEnum.if_,EnumSet.of(RuleEnum.if_));
    map.put(TerminalEnum.then_,EnumSet.of(RuleEnum.then_));
    map.put(TerminalEnum.pow_,EnumSet.of(RuleEnum.pow_));
    return map;
  }

  public static EnumSet<RuleEnum> getUnconditionalRules() {
    return  EnumSet.of(RuleEnum.blk_);
  }

  public static EnumParserLookaheadActivator<RuleEnum,TerminalEnum,VersionEnum> getActivator(Parser<TerminalEnum,?,?,VersionEnum> parser) {
    return new EnumParserLookaheadActivator<RuleEnum,TerminalEnum,VersionEnum>(
      parser,getTerminalRulesMap(),RuleEnum.class,getUnconditionalRules());
  }
}
