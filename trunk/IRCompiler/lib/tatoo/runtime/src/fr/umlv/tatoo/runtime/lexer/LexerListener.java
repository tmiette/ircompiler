/*
 * Created on 4 juil. 2005
 */
package fr.umlv.tatoo.runtime.lexer;

/**
 * Interface defining an observer registered on a lexer that
 * receive notifications of rule recognized.
 *
 * @param <R> type of rules.
 * @param <B> type of buffers.
 *
 * @author <a href="mailto:forax@univ-mlv.fr">Remi Forax</a>
 * @version $Revision$
 * 
 * @see Lexer#createLexer(LexerTable, fr.umlv.tatoo.runtime.buffer.LexerBuffer, LexerListener, RuleActivator, LifecycleHandler, LexerErrorRecoveryPolicy)
 */

public interface LexerListener<R,B> {
  /**
   * This method is called each time a token is recognized by the lexer.
   * @param rule the rule verified
   * @param lastTokenLength the length of the token recognized by the rule
   * @param buffer the buffer containing the token to extract
   * @throws RuntimeException the implementor of this interface could throw a runtime exception
   *  to signal an error.
   */
  public void ruleVerified(R rule, int lastTokenLength, B buffer);
}
