/*
 * Created on 13 juil. 2005
 */
package fr.umlv.tatoo.runtime.tools;

import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Set;

import fr.umlv.tatoo.runtime.lexer.RuleActivator;
import fr.umlv.tatoo.runtime.parser.LookaheadMap;
import fr.umlv.tatoo.runtime.parser.Parser;
import fr.umlv.tatoo.runtime.parser.ParserTable;
import fr.umlv.tatoo.runtime.util.ReadOnlyIntStack;

/** A {@link RuleActivator rule activator} that use parser lookaheads. 
 *  For each parser state, the implementation used the set of possible terminal
 *  given by the parser to find the set corresponding rules to activate. 
 * 
 *  This implementation supposes that rules and terminals
 *  are specified using enums. In order to improve speed
 *  and memory usage, this activator internally
 *  uses {@link EnumSet} and {@link EnumMap}.
 *  
 *  This implementation pre-calculate all possibles rule set
 *  for all parser state.
 * 
 * @param <R> type of rule (must be an enum).
 * @param <T> type of terminal (must be an enum).
 * @param <V> type of version (must be an enum).
 * 
 * @author Remi Forax
 * 
 * @see HashParserLookaheadActivator
 */
//TODO Remi try to see if some enum set can be shared
public class EnumParserLookaheadActivator<R extends Enum<R>,T extends Enum<T>,V extends Enum<V>>
  implements RuleActivator<R> {
  
  private final ReadOnlyIntStack stateStack;
  private final EnumMap<V,EnumSet<R>[]> rulesArrayMap;
  private final Parser<T,?,?,V> parser;
  private final Collection<R> allRules;
    /**
   * @param parser a simple parser
   * @param terminalMap a map that associate a set of rules that create a terminal.
   * @param ruleClass the class of the rule.
   * @param unconditionalRules the set of unconditional rules.
   */
  @SuppressWarnings("unchecked")
  public EnumParserLookaheadActivator(Parser<T,?,?,V> parser,
      EnumMap<T,EnumSet<R>> terminalMap,Class<R> ruleClass,EnumSet<R> unconditionalRules) {
    
    LookaheadMap<? extends T,? super V> map=parser.getLookaheadMap();
    if (parser.getLookaheadMap()==null)
      throw new IllegalArgumentException("The parser must be constructed with a lookahead map");
    
    allRules=EnumSet.allOf(ruleClass);
    
    ParserTable<T,?,?,V> table=parser.getTable();
    int stateNb=table.getStateNb();
    
    Class<V> versionClass=table.getDefaultVersion().getDeclaringClass();
    EnumMap<V,EnumSet<R>[]> rulesArrayMap=new EnumMap<V,EnumSet<R>[]>(versionClass);
    
    for(V version:table.getVersions()) {
      EnumSet<R>[] rulesArray=(EnumSet<R>[])new EnumSet<?>[stateNb];
      for(int state=0;state<stateNb;state++) {
        EnumSet<R> rules=EnumSet.noneOf(ruleClass);
        rules.addAll(unconditionalRules);

        for(T terminal:map.getLookahead(state,version)) {
          Set<? extends R> toAdd = terminalMap.get(terminal);
          if (toAdd!=null)
            rules.addAll(toAdd);
        }

        rulesArray[state]=rules;
      }
      rulesArrayMap.put(version,rulesArray);
    }
    
    this.parser=parser;
    this.rulesArrayMap=rulesArrayMap;
    this.stateStack=parser.getStateStack();
  }

  public Iterable<? extends R> activeRules() {
    return rulesArrayMap.get(parser.getVersion())[stateStack.last()];
  }

  public Iterable<? extends R> allRules() {
    return allRules;
  }
}
