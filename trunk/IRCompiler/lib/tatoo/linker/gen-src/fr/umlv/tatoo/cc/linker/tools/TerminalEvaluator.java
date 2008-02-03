package fr.umlv.tatoo.cc.linker.tools;


/** 
 *  @param <D> data type passed by the lexer listener.
 *
 *  This class is generated - please do not edit it 
 */
public interface TerminalEvaluator<D> {
  /** This method is called when the rule <code>identifier</code> is recognized by the lexer.
   *  @param data the data sent by the lexer, in general, the
   *         {@link fr.umlv.tatoo.runtime.buffer.TokenBuffer#view a view of the token buffer} or the buffer itself.
   *  @return the value associated with the terminal spawn for the rule.
 */
  public String identifier(D data);
  /** This method is called when the rule <code>quotedstring</code> is recognized by the lexer.
   *  @param data the data sent by the lexer, in general, the
   *         {@link fr.umlv.tatoo.runtime.buffer.TokenBuffer#view a view of the token buffer} or the buffer itself.
   *  @return the value associated with the terminal spawn for the rule.
 */
  public String quotedstring(D data);
}
