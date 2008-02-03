package fr.umlv.tatoo.runtime.tools;

import fr.umlv.tatoo.runtime.buffer.LexerBuffer;
import fr.umlv.tatoo.runtime.lexer.LexerListener;
import fr.umlv.tatoo.runtime.parser.ErrorRecoveryListener;
import fr.umlv.tatoo.runtime.parser.ParserListener;
import fr.umlv.tatoo.runtime.parser.SimpleParser;

/** Abstract base class for all processors produced by the Tatoo Java backend.
 * 
 * @param <R> type of rule.
 * @param <T> type of terminal.
 * @param <N> type of non terminal.
 * @param <P> type of production.
 * 
 * @author Remi Forax
 */
public abstract class AbstractToolsProcessor<B extends LexerBuffer,R,T,N,P>
  implements ParserListener<T,N,P>,
             ErrorRecoveryListener<T,N> {
  
  /** Possible operations when a token is recognized.
   */
  public enum BufferOperation {
    
    /** Continue processing normally
     */
    NEXT,
    
    /**
     * Restart the lexing process for the current terminal (unwind must not has been called for
     * it and semantics action not has been executed)
     */
    RESTART
  }
  
  /** Default constructor that does nothing.
   */
  protected AbstractToolsProcessor() {
    // do nothing
  }
  
  /** Creates a lexer listener that forwards recognized rule to the parser.
   * @param parser a parser
   * @return a lexer listener.
   */
  public abstract LexerListener<R,B> createLexerListener(SimpleParser<? super T> parser);
}
