package fr.umlv.tatoo.runtime.ast;

import java.util.List;
import java.util.Map;

/** Represents the interface of all Abstract Syntax Tree.
 *  This generic Node represents a production of the grammar and has
 *  the following properties :
 *  <ul> 
 *   <li>a name.
 *   <li>a parent, automagically calculed when
 *       a node is set, added or removed in {@link #nodeList() nodeList()}.
 *   <li>a list of all its sub nodes {@link #nodeList() nodeList()}.
 *   <li>a {@link #attributeList() list of attribute values} (values of terminal).
 *   <li>a {@link #attributeMap() map of attributes} (association between the attribute name 
 *       and its value).
 *   <li>an {@link #accept(NodeVisitor,Object) accept(nodeVisitor,param)}
 *       that permits to use visitor on a generic node.
 *  </ul>
 *  
 *  The node default implementations of Node, {@link FlatNode}
 *  and {@link CompositeNode} supports :
 *  <ul>
 *   <li>XML serialization using {@link XML}
 *   <li>IO serialization
 *  </ul>
 *  
 * @author Remi
 */
public interface Node {
  /** Returns the name of the current node.
   * @return the name of the current node.
   */
  public String getName();
  
  /** Returns the parent of the current node.
   * @return the parent of the current node or null if the node
   *  has no parent.
   */
  public Node getParent();

  /** A read-only list of all sub nodes of the current node.
   *  This list provides access in constant time to each of its element.
   *  
   *  Subclass methods are free to return a read/write list.
   *  
   * @return a list of all sub nodes.
   */
  public List<? extends Node> nodeList();

  /** A read-only list of all attribute values of the current node.
   *  This list is fixed size and provides access in constant time
   *  to each of its element.
   *  
   *  Subclass methods are free to return a read/write list.
   *  
   * @return a list of attribute values.
   * @see #attributeMap()
   */
  public List<?> attributeList();
  
  /** A read-only map containing couples of attribute name/attribute value.
   *  This map is fixed size and access time could be a function of the number
   *  of attribute.  
   *  
   *  Subclass methods are free to return a read/write map.
   *  
   * @return a map of attribute name/value.
   */
  public Map<String,?> attributeMap();
  
  /** call a visitor method depending of the real type of the current node.
   * 
   * @param <R> type of the return value.
   * @param <P> type of the argument.
   * @param <D> type of the data attached to each node.
   * @param <E> type of the exception possibly raised by a visit method.
   * @param visitor visitor to call.
   * @param param parameter send to the visitor.
   * @return the return value of the selected visitor method.
   * @throws E could propagate a generice exception raised by the visitor.
   */
  public <R,P,D,E extends Throwable> R accept(NodeVisitor<R,P,D,E> visitor,P param) throws E;
  
  /** A node type used as return type of the
   *  method {@link #getParent() getParent()} to indicates
   *  that the node is a root node without parent.
   *  The uniq legal value of {@link NoParent NoParent} is null. 
   *
   * @author Remi
   */
  public interface NoParent extends Node {
    // just a type interface
  }
  
  /** An attribute value type used as return type of the
   *  method {@link #attributeList() attributeList()} and
   *  {@link #attributeMap() attributeMap()} to indicates
   *  that the node has no attribute value.
   *  Even null is not a legal value of {@link NoValue NoValue}. 
   *
   * @author Remi
   */
  public interface NoValue {
    // just a type interface
  }
}