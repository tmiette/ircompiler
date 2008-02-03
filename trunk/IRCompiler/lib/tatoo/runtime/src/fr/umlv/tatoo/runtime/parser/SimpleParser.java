package fr.umlv.tatoo.runtime.parser;

import java.util.Set;

import fr.umlv.tatoo.runtime.util.IntArrayList;
import fr.umlv.tatoo.runtime.util.ReadOnlyIntStack;

/**
 * @author Remi
 *
 * @param <T> type of terminal.
 */
public interface SimpleParser<T> {
  /** Returns true if the current parser is a branching parser
   * @return true if the current parser is a branching parser
   */
  public boolean isBranchingParser();
  
  /**
   * Performs the actions induced by a particular terminal. Return RELEX if the expected lookahead has changed during a reduce
   * and that the lexer should reconsider its active rule set and NEXT otherwise. If this feature is used
   * lexical semantic actions must not have already been performed.
   * if NEXT token is required
   * @param next the new terminal
   * @return RELEX if the expected lookahead has changed during a reduce
   * and that the lexer should reconsider its active rule set and NEXT
   * if NEXT token is required
   */
  public SmartStepReturn smartStep(T next);
  
  /**
   * Performs the actions induced by a particular terminal. 
   * 
   * @param next the terminal.
   */
  public void step(T next);

  /**
   * Resets the parser and clears the state stack.
   */
  public void reset();
  
  /**
   * Indicates to the parser that there is no more terminals.
   */
  public void close();
  
  /** 
   * Returns the set of terminals which don't lead to an error for the current state.
   * 
   * This is roughly equivalent to a call to
   * <tt>getLookaheadMap().get(getStateStack().last())</tt>.
   * 
   * @return a set of terminals or <code>null</code> if no lookahead states was provided
   *  at construction of the parser.
   */
  public Set<? extends T> getLookahead();

  /** 
   * Returns the lookahead map.
   * @return the lookahead map or <code>null</code> if no lookahead
   *  map was provided at construction of the parser.
   */
  public LookaheadMap<? extends T,?> getLookaheadMap();
 
  /** 
   * Returns a view on the current parser state stack.
   * @return an unmodifiable list of states.
   */
  public ReadOnlyIntStack getStateStack();
  
  /** 
   * Returns the table associated with the parser.
   * @return the parser table.
   */
  public ParserTable<T,?,?,?> getTable();
  
  /** 
   * Signals to the parser an external error, (by example a lexer error) has occurred
   * allowing the parser to try to branch (enter or exit from the current grammar).
   *  
   * @see #isBranchingParser()
   * @see ParserErrorRecoveryPolicy#recoverOnError(Parser, IntArrayList, Object, String)
   */
  public ActionReturn branchOnError(T terminal, String message);
}