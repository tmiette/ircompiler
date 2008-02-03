package fr.umlv.IRCompiler.tatoo.tools;


/** 
 *  This class is generated - please do not edit it 
 */
public interface GrammarEvaluator {
  /** This methods is called after the reduction of the non terminal start
   *  by the grammar production start__expr.
   *  <code>start := expr </code>
   */
  public  void start__expr(boolean expr);
  /** This methods is called after the reduction of the non terminal expr
   *  by the grammar production and.
   *  <code>expr := expr 'and' expr </code>
   */
  public  boolean and(boolean expr, boolean expr2);
  /** This methods is called after the reduction of the non terminal expr
   *  by the grammar production or.
   *  <code>expr := expr 'or' expr </code>
   */
  public  boolean or(boolean expr, boolean expr2);
  /** This methods is called after the reduction of the non terminal expr
   *  by the grammar production not.
   *  <code>expr := 'not' expr </code>
   */
  public  boolean not(boolean expr);
  /** This methods is called after the reduction of the non terminal expr
   *  by the grammar production parenthesis.
   *  <code>expr := 'open_brace' expr 'close_brace' </code>
   */
  public  boolean parenthesis(boolean expr);
  /** This methods is called after the reduction of the non terminal expr
   *  by the grammar production value.
   *  <code>expr := 'val' </code>
   */
  public  boolean value(boolean val);

}
