package fr.umlv.tatoo.runtime.tools;

import fr.umlv.tatoo.runtime.buffer.LexerBuffer;
import fr.umlv.tatoo.runtime.lexer.ErrorForwarder;
import fr.umlv.tatoo.runtime.lexer.ForwardReturn;
import fr.umlv.tatoo.runtime.lexer.Lexer;
import fr.umlv.tatoo.runtime.lexer.LifecycleHandler;
import fr.umlv.tatoo.runtime.parser.ActionReturn;
import fr.umlv.tatoo.runtime.parser.SimpleParser;

/**
 * This class implements a lexer error handler and a lexer lifecycle handler.
 * The lexer error handler part forwards error to the parser branching mechanism
 * (see {@link SimpleParser#branchOnError(Object, String)}) or
 * error recovery mechanism.
 * 
 * The lifecycle handler part forwards the {@link #handleClose(Lexer) close} (resp.
 * {@link #handleReset(Lexer) reset}) event in order to
 * {@link SimpleParser#close() close} (resp. {@link SimpleParser#reset() reset})
 * the parser.
 * 
 * @author Remi Forax
 */
public class ParserForwarder<T,B extends LexerBuffer> implements ErrorForwarder<B>, LifecycleHandler<B> {
  /**
   * Creates a parser forwarder.
   * 
   * @param parser
   *          the parser that will be notified
   */
  public ParserForwarder(SimpleParser<T> parser) {
    this.parser = parser;
  }

  /**
   * {@inheritDoc}
   * 
   * This implementation tries to {@link SimpleParser#branchOnError(Object, String)}
   * @return {@code false} if lexer must discard input
   */
  public ForwardReturn forwardUnexpectedCharacter(Lexer<B> lexer) {
    lexer.getBuffer().reset();
      ActionReturn ret = parser.branchOnError(null, "lexer forward error");
      assert ret!=ActionReturn.RELEX : "cannot ask for relex except after a reduce";
      if (ret==ActionReturn.KEEP)
        return ForwardReturn.RETRY;
      else
        return ForwardReturn.DISCARD;
  }

  /**
   * {@inheritDoc}
   * 
   * This implementation simply tries to
   * {@link SimpleParser#branchOnError(Object, String)}.
   */
  public void forwardUnexpectedEndOfFile(Lexer<B> lexer) {
    ActionReturn ret;
    ret = parser.branchOnError(parser.getTable().getEof(),"lexer eof forward error");
    if (ret==ActionReturn.KEEP)
      throw new IllegalStateException("policy should not return KEEP on end-of-input");
  }

  /**
   * {@inheritDoc}
   * 
   * This implementation reset the parser.
   */
  public void handleReset(Lexer<B> lexer) {
    parser.reset();
  }

  /**
   * {@inheritDoc}
   * 
   * This implementation close the parser.
   */
  public void handleClose(Lexer<B> lexer) {
    parser.close();
  }

  private final SimpleParser<T> parser;

}