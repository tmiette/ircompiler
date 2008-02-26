package tatoo.tools;


/** 
 *  @param <D> data type passed by the lexer listener.
 *
 *  This class is generated - please do not edit it 
 */
public interface TerminalEvaluator<D> {
  /** This method is called when the rule <code>boolean_</code> is recognized by the lexer.
   *  @param data the data sent by the lexer, in general, the
   *         {@link fr.umlv.tatoo.runtime.buffer.TokenBuffer#view a view of the token buffer} or the buffer itself.
   *  @return the value associated with the terminal spawn for the rule.
 */
  public Boolean boolean_(D data);
  /** This method is called when the rule <code>number_</code> is recognized by the lexer.
   *  @param data the data sent by the lexer, in general, the
   *         {@link fr.umlv.tatoo.runtime.buffer.TokenBuffer#view a view of the token buffer} or the buffer itself.
   *  @return the value associated with the terminal spawn for the rule.
 */
  public Integer number_(D data);
  /** This method is called when the rule <code>float_</code> is recognized by the lexer.
   *  @param data the data sent by the lexer, in general, the
   *         {@link fr.umlv.tatoo.runtime.buffer.TokenBuffer#view a view of the token buffer} or the buffer itself.
   *  @return the value associated with the terminal spawn for the rule.
 */
  public Float float_(D data);
  /** This method is called when the rule <code>double_</code> is recognized by the lexer.
   *  @param data the data sent by the lexer, in general, the
   *         {@link fr.umlv.tatoo.runtime.buffer.TokenBuffer#view a view of the token buffer} or the buffer itself.
   *  @return the value associated with the terminal spawn for the rule.
 */
  public Double double_(D data);
  /** This method is called when the rule <code>identifier_</code> is recognized by the lexer.
   *  @param data the data sent by the lexer, in general, the
   *         {@link fr.umlv.tatoo.runtime.buffer.TokenBuffer#view a view of the token buffer} or the buffer itself.
   *  @return the value associated with the terminal spawn for the rule.
 */
  public String identifier_(D data);
  /** This method is called when the rule <code>string_</code> is recognized by the lexer.
   *  @param data the data sent by the lexer, in general, the
   *         {@link fr.umlv.tatoo.runtime.buffer.TokenBuffer#view a view of the token buffer} or the buffer itself.
   *  @return the value associated with the terminal spawn for the rule.
 */
  public String string_(D data);
}
