package fr.umlv.tatoo.motocity.tools;


/** 
 *  @param <D> data type passed by the lexer listener.
 *
 *  This class is generated - please do not edit it 
 */
public interface TerminalEvaluator<D> {
  /** This method is called when the rule <code>letter</code> is recognized by the lexer.
   *  @param data the data sent by the lexer, in general, the
   *         {@link fr.umlv.tatoo.runtime.buffer.TokenBuffer#view a view of the token buffer} or the buffer itself.
   *  @return the value associated with the terminal spawn for the rule.
 */
  public char letter(D data);
  /** This method is called when the rule <code>javaLetter</code> is recognized by the lexer.
   *  @param data the data sent by the lexer, in general, the
   *         {@link fr.umlv.tatoo.runtime.buffer.TokenBuffer#view a view of the token buffer} or the buffer itself.
   *  @return the value associated with the terminal spawn for the rule.
 */
  public char javaLetter(D data);
  /** This method is called when the rule <code>imports</code> is recognized by the lexer.
   *  @param data the data sent by the lexer, in general, the
   *         {@link fr.umlv.tatoo.runtime.buffer.TokenBuffer#view a view of the token buffer} or the buffer itself.
 */
  public void imports(D data);
  /** This method is called when the rule <code>name</code> is recognized by the lexer.
   *  @param data the data sent by the lexer, in general, the
   *         {@link fr.umlv.tatoo.runtime.buffer.TokenBuffer#view a view of the token buffer} or the buffer itself.
   *  @return the value associated with the terminal spawn for the rule.
 */
  public String name(D data);
  /** This method is called when the rule <code>type</code> is recognized by the lexer.
   *  @param data the data sent by the lexer, in general, the
   *         {@link fr.umlv.tatoo.runtime.buffer.TokenBuffer#view a view of the token buffer} or the buffer itself.
   *  @return the value associated with the terminal spawn for the rule.
 */
  public String type(D data);
}
