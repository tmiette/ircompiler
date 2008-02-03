/*
 * Created on 26 déc. 2005
 *
 */
package fr.umlv.tatoo.runtime.lexer.rules;


/** This class defined the a regular expression associated with a rule
 *  of the lexer.
 *  The regular expression is composed by one {@link #getMainRegex() main} automaton,
 *  one optional {@link #getFollowRegex() follow} automaton
 *  and a property {@link #beginningOfLineRequired()} used to know if the regular expression
 *  is restricted to match beginning of line.
 *  
 * @author Remi
 */
public class RuleData {
  /** Creates a data stratucture that xill be associated with a rule
   *  and that defined the regular expression.
   *  
   * @param main
   * @param follow
   * @param beginningOfLineRequired
   */
  public RuleData(RegexTable main,RegexTable follow,boolean beginningOfLineRequired) {
    this.main=main;
    this.follow=follow;
    this.beginningOfLineRequired=beginningOfLineRequired;
  }
  
  /**
   * Returns the main regular expression associated with this rule.
   * 
   * @return a table implementation of the minimal automaton corresponding to
   *         the main regular expression.
   */
  public RegexTable getMainRegex() {
    return main;
  }

  /**
   * Indicates if he main regular expression associated with the rule must
   * starts at the beginning of the line
   * 
   * @return <code>true</code> if the main regular expression must starts at
   *         the beginning a the line; <code>false</code> otherwise.
   *         
   * @see fr.umlv.tatoo.runtime.buffer.LexerBuffer#previousWasNewLine()
   */
  public boolean beginningOfLineRequired() {
    return beginningOfLineRequired;
  }

  /**
   * Returns the regular expression that must follow the main regular expression
   * associated with this rule.
   * 
   * @return a table implementation of the minimal automaton corresponding to
   *         the following regular expression.
   */
  public RegexTable getFollowRegex() {
    return follow;
  }
  
  private final RegexTable main;
  private final RegexTable follow;
  private final boolean beginningOfLineRequired;
}
