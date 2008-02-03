/*
 * TokenBuffer.java
 *
 * Created: 13 nov. 2005
 */
package fr.umlv.tatoo.runtime.buffer;

/**
 * A <code>TokenBuffer</code> provides a method used by developer 
 * to access tokens processed by a lexer.
 * 
 * @author Gilles
 * 
 * @see fr.umlv.tatoo.runtime.lexer.LexerListener
 * @param <D> type of the view's items.
 */

public interface TokenBuffer<D> {
  /**
   * Discards all characters already recognized by the lexer.
   */
  public void discard();
  
  /**
   * Provides a view of all available tokens. Tokens are not copied 
   * from the buffer and thus may change during lexer process.
   * 
   * @return a view of all available tokens
   * 
   */
  public D view();
}
