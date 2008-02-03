/*
 * Created on 12 déc. 2005
 */
package fr.umlv.tatoo.runtime.parser;

import java.util.Set;

/**
 * Data associated to a state of the parsing table.
 * This class contains basically to kind of state :
 * <ol>
 *  <li>a set of compatible versions of the state,
 *      see {@link Parser#reset(Object)} or {@link Parser#setVersion(Object)}.
 *  <li>the terminal shifted by or the non-terminal reduced by the state,
 *      this information is used by the
 *      {@link DefaultParserErrorRecoveryPolicy default error recovery}
 *      mechanism if a state must be dropped to recover on error to signal to the
 *      {@link ErrorRecoveryListener} which terminal or non terminal value that must be pop
 *      from the stack.
 * 
 * @param <T> type of terminals.
 * @param <N> type of non-terminals.
 * @param <V> type of versions.
 * 
 * @author Remi Forax
 */
public abstract class StateMetadata<T,N,V> {
  /** Returns true if the state is compatible with the version taken as argument.
   *  This default implementation always return true.
   * @param version the version
   * @return true if the state is compatible with the version.
   */
  public boolean isCompatible(V version) {
    return true;
  }
  
  /** Called by the {@link DefaultParserErrorRecoveryPolicy default error recovery}
   *  mechanism to adjust the stack of value used by the evaluator.
   *  This implementation calls {@link ErrorRecoveryListener#popNonTerminalOnError(Object)}
   *  if the current state is a reduce with a non terminal or
   *  {@link ErrorRecoveryListener#popTerminalOnError(Object)} id the current state is
   *  a shift with a terminal.
   *  
   * @param listener the error recovery listener
   */
  public abstract void popVariableOnError(ErrorRecoveryListener<? super T,? super N> listener);
  
  /** Creates a state data compatible with all versions that shift a terminal.
   * 
   * @param <T> type of terminal
   * @param <N> type of non terminal
   * @param <V> type of version
   * 
   * @param terminal the shifted terminal
   * 
   * @return a new state data.
   */
  public static <T,N,V> StateMetadata<T,N,V> createAllVersionWithTerminal(final T terminal) {
    return new TerminalStateMetadata<T,N,V>(terminal);
  }
  
  /** Creates a state data compatible with all versions that reduce a non terminal.
   * 
   * @param <T> type of terminal
   * @param <N> type of non terminal
   * @param <V> type of version
   * 
   * @param nonTerminal the reduced non terminal
   *
   * @return a new state data.
   */
  public static <T,N,V> StateMetadata<T,N,V> createAllVersionWithNonTerminal(final N nonTerminal) {
    return new NonTerminalStateMetadata<T,N,V>(nonTerminal);
  }
  
  private static class TerminalStateMetadata<T,N,V> extends StateMetadata<T,N,V> {
    TerminalStateMetadata(T terminal) {
      this.terminal=terminal;
    }
    @Override public void popVariableOnError(ErrorRecoveryListener<? super T,? super N> listener) {
      listener.popTerminalOnError(terminal);    
    }
    private final T terminal;
  }
  
  private static class NonTerminalStateMetadata<T,N,V> extends StateMetadata<T,N,V> {
    NonTerminalStateMetadata(N nonTerminal) {
      this.nonTerminal=nonTerminal;
    }
    @Override public void popVariableOnError(ErrorRecoveryListener<? super T,? super N> listener) {
      listener.popNonTerminalOnError(nonTerminal);    
    }
    private final N nonTerminal;
  }
  
  /** Creates a state data compatible with a set of versions that shift a terminal.
   * 
   * @param <T> type of terminal
   * @param <N> type of non terminal
   * @param <V> type of version
   * 
   * @param compatible a set of compatible versions
   * @param terminal the shifted terminal
   * @return a new state data.
   */
  public static <T,N,V> StateMetadata<T,N,V> createWithTerminal(final Set<?> compatible,T terminal) {
    return new TerminalStateMetadata<T,N,V>(terminal) {
      @Override public boolean isCompatible(V version) {
        return compatible.contains(version);
      }
    }; 
  }
  
  /** Creates a state data compatible with a set of versions that reduce a non terminal.
   * 
   * @param <T> type of terminal
   * @param <N> type of non terminal
   * @param <V> type of version
   * 
   * @param compatible a set of compatible versions
   * @param nonTerminal the reduced non terminal
   *
   * @return a new state data.
   */
  public static <T,N,V> StateMetadata<T,N,V> createWithNonTerminal(final Set<?> compatible,N nonTerminal) {
    return new NonTerminalStateMetadata<T,N,V>(nonTerminal) {
      @Override public boolean isCompatible(V version) {
        return compatible.contains(version);
      }
    }; 
  }
}
