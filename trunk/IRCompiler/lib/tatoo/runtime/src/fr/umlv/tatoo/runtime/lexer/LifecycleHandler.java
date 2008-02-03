package fr.umlv.tatoo.runtime.lexer;

import fr.umlv.tatoo.runtime.buffer.LexerBuffer;

/**
 * Interface for a listener called after
 * {@link Lexer#reset(fr.umlv.tatoo.runtime.buffer.LexerBuffer)}
 * or {@link Lexer#close()}.
 * 
 * @author Remi
 */
public interface LifecycleHandler<B extends LexerBuffer> {
  /**
   * Called after the lexer is closed. 
   * @see Lexer#close()
   */
  public void handleClose(Lexer<B> lexer);
  
  /**
   * Called after the lexer is reseted. 
   * @see Lexer#reset(fr.umlv.tatoo.runtime.buffer.LexerBuffer)
   */
  public void handleReset(Lexer<B> lexer);
}
