/*
 * Created on 13 juil. 2005
 */
package fr.umlv.tatoo.runtime.tools;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.umlv.tatoo.runtime.lexer.RuleActivator;
import fr.umlv.tatoo.runtime.parser.SimpleParser;

/** A  generic hash table based {@link RuleActivator rule activator} that
 *  use parser lookaheads.
 *  For each parser state, the implementation used the set of possible terminal
 *  given by the parser to find the set corresponding rules to activate. 
 * 
 * @param <R> type of rule.
 * @param <T> type of terminal.
 * 
 * @author Julien Cervelle
 * 
 * @see EnumParserLookaheadActivator
 */
public class HashParserLookaheadActivator<R,T>
  implements RuleActivator<R> {
  
  private final Map<T,? extends Set<? extends R>> terminalMap;
  private final SimpleParser<? extends T> parser;
  private final HashSet<R> container;
  private final Set<? extends R> unconditionalRules;
  private final HashSet<R> allRules;
  
  /** Creates a lookeahead activator 
   * 
   * @param parser
   * @param terminalMap
   * @param unconditionalRules
   */
  // FIXME Remi, change the algorithm
  public HashParserLookaheadActivator(SimpleParser<? extends T> parser,
      Map<T,? extends Set<? extends R>> terminalMap,
      Set<? extends R> unconditionalRules) {
    
    if (parser.getLookaheadMap()==null)
      throw new IllegalArgumentException("The parser must be construct with a lookahead map");
    
    HashSet<R> all = new HashSet<R>();
    all.addAll(unconditionalRules);
    for(Set<? extends R> value:terminalMap.values())
      all.addAll(value);
    this.allRules = all;
    this.terminalMap = terminalMap;
    this.container = new HashSet<R>();
    this.unconditionalRules = unconditionalRules;
    this.parser = parser;
  }

  public Iterable<? extends R> activeRules() {
    container.clear();
    container.addAll(unconditionalRules);
    
    Set<? extends T> lookahead = parser.getLookahead();
    if (lookahead==null)
      lookahead=terminalMap.keySet();
    
    for(T terminal : lookahead) {
      Set<? extends R> toAdd = terminalMap.get(terminal);
      if (toAdd != null)
       container.addAll(toAdd);
    }
    return container; 
  }

  public Iterable<? extends R> allRules() {
    return allRules;
  }
}
