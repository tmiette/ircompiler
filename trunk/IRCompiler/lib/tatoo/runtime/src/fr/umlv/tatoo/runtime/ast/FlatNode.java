package fr.umlv.tatoo.runtime.ast;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/** Represents a node with a fixed number of children nodes.
 * 
 * @param <N> type of the child nodes.
 * @param <P> type of the parent node.
 * @param <V> type of the attribute values.
 * 
 * @author Remi
 * 
 * @see CompositeNode
 */
public abstract class FlatNode<N extends Node,P extends Node,V> extends AbstractNode<N,P,V> {
  private static final long serialVersionUID = 7518189319404365377L;

  /** Only subclass can call this constructor
   */
  protected FlatNode() {
    // just protected
  }
  
  /** {@inheritDoc}
   */
  @Override final boolean isFixedNodeSize() {
    return true;
  }
  
  /** 
   * {@inheritDoc}
   *  
   *  Futhermore the size of the list is fixed so
   *   {@link List#add(int, Object)}) and
   *   {@link List#remove(int)}) are not supported.
   *   
   * @return a list of all sub nodes.
   */
  @Override public List<N> nodeList() {
    if (nodes!=null)
      return nodes;
    return nodes=new NodeList();
  }
  class NodeList extends AbstractList<N> implements RandomAccess {
    @Override public int size() {
      return nodeCount();
    }
    @Override public N get(int index) {
      return nodeAt(index);
    }
    @Override
    public N set(int index, N element) {
      return nodeAt(index,element);
    }
  }
  
  /** Returns the number of child nodes.
   *  Service method implemented by concrete node,
   *  this method must not be called directly,
   *  to obtain the number of child nodes use
   *  {@link #nodeList() nodeList().size()} instead.
   * @return the number of child nodes.
   */
  protected int nodeCount() {
    return 0;
  }
  
  /** Returns the child node at the index position.
   *  Service method implemented by concrete node,
   *  this method must not be called directly,
   *  to obtain a child node by its index use
   *  {@link #nodeList() nodeList().get(index)}
   *  instead.
   * @param index the index of the child node.
   * @return the child node at index index.
   * @throws IndexOutOfBoundsException if the index is not
   *  between 0 and the node list size.
   */
  protected N nodeAt(int index) {
    throw new IndexOutOfBoundsException("invalid index "+index);
  }
  
  /** Change the child node at the index position
   *  and returns the old one.
   *  Service method implemented by concrete node,
   *  this method must not be called directly,
   *  to change a child node by its index use
   *  {@link #nodeList() nodeList().set(index)}
   *  instead.
   * @param index the index of the child node.
   * @param node the new child node at index index.
   * @return the old child node at index index.
   * @throws IndexOutOfBoundsException if the index is not
   *  between 0 and the node list size.
   */
  //used by XML deserialization too
  @Override protected N nodeAt(int index,N node) {
    throw new IndexOutOfBoundsException("invalid index"+index+" with node "+node);
  }
  
  private transient NodeList nodes;
}
