package fr.umlv.tatoo.runtime.lexer;

import fr.umlv.tatoo.runtime.buffer.LexerBuffer;


/**
 * This implementation that does nothing.
 * @author Julien Cervelle
 */
public class DefaultErrorForwarder<B extends LexerBuffer> implements ErrorForwarder<B> {

  public ForwardReturn forwardUnexpectedCharacter(Lexer<B> lexer) {
    return ForwardReturn.DISCARD;
  }

  public void forwardUnexpectedEndOfFile(Lexer<B> lexer) {
    //nothing
  }
  
  @SuppressWarnings("unchecked")
  public static <B extends LexerBuffer> DefaultErrorForwarder<B> defaultForwarder() {
    return (DefaultErrorForwarder<B>) defaultForwarder;
  }

  private static final DefaultErrorForwarder<LexerBuffer> defaultForwarder =
    new DefaultErrorForwarder<LexerBuffer>();

}
