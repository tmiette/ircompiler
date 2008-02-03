package fr.umlv.tatoo.cc.parser.grammar;

/** Common interface of objects that own a priority. 
 * 
 * @author Cervelle
 *
 * @see fr.umlv.tatoo.cc.parser.parser.ActionEntry
 */
public interface PriorityOwner {
  
  /** Returns the priority.
   * @return the priority of the current object.
   */
  public Priority getPriority();
}
