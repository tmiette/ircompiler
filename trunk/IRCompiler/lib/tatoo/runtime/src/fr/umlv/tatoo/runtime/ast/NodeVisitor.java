package fr.umlv.tatoo.runtime.ast;

import java.util.HashMap;
import java.util.Map;

/** Abstract implementation of a visitor on the AST node.
 *  This class provide a mechanism to access to the
 *  data associated with a node.
 *  
 *  The methods {@link #getData(Node) getData(Node)} and
 *  {@link #setData(Node, Object) setData(node,data)} get/set
 *  the data of a specified node.
 *  
 * @param <R> return type of the visitor methods.
 * @param <P> parameter type of the visitor methods.
 * @param <D> type of the data associated with the node hold by the visitor.
 * @param <E> type of the exception throws by the visitor,
 *        if no exception thrown, E could be typed using a RuntimeException.
 *  
 * @author Remi
 *
 */
public abstract class NodeVisitor<R,P,D,E extends Throwable> {
  /** Creates a visitor with an hashmap to store data associated
   * to each node.
   */
  protected NodeVisitor() {
    this(new HashMap<Node,D>());
  }
  /** Creates a visitor that use the argument as map to
   *  store data associated with a node.
   * @param dataMap
   */
  protected NodeVisitor(Map<Node,D> dataMap) {
    this.dataMap=dataMap;
  }
  
  /** Returns the map that contains the data for each node. 
   * @return the map containing couples node, data. 
   */
  public Map<Node,D> getDataMap() {
    return dataMap;
  }
  
  /** Returns the data associated to a node.
   * @param node the node.
   * @return the data associated to the node.
   * @see #getDataMap()
   */
  protected D getData(Node node) {
    return dataMap.get(node);
  }
  
  /** Set the data associated to a node and returns the old value.
   * @param node the node.
   * @param data the new data associated with the node.
   * @return the old data associated with the node.
   */
  protected D setData(Node node,D data) {
    return dataMap.put(node,data);
  }
  
  /** Defined a visit method called by default if no visit method is overridden.
   *  The current implementation throws an UnsupportedOperationException
   *  
   * @param node
   * @param param
   * @return no returns from that method.
   * @throws E this exception is never thrown.
   * @throws UnsupportedOperationException always
   */
  @SuppressWarnings("unused")
  protected R visit(Node node,P param) throws E, UnsupportedOperationException {
    throw new UnsupportedOperationException("node:"+node+" param:"+param+" data:"+getData(node));
  }
  
  private final Map<Node,D> dataMap;
}
