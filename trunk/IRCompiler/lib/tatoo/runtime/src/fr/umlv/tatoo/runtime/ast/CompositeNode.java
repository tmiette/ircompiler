package fr.umlv.tatoo.runtime.ast;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

import fr.umlv.tatoo.runtime.ast.Node.NoValue;

/** Represents a node with a variable number of nodes.
 * 
 * @param <N> type of child nodes.
 * @param <P> type of parent node.
 * 
 * @author Remi
 * 
 * @see FlatNode
 */
public abstract class CompositeNode<N extends Node,P extends Node> extends AbstractNode<N,P,NoValue> {
  private static final long serialVersionUID = 2979537802634841741L;
  
  /** Only subclass can call this constructor
   */
  protected CompositeNode() {
    // just protected
  }
  
  /** {@inheritDoc}
   */
  @Override final boolean isFixedNodeSize() {
    return false;
  }
  
  /** 
   *  {@inheritDoc}
   *  
   *  Futhermore, this list checks at runtime if the type of
   *  an inserted node is a subtype of the declared children type.
   *  
   * @return a list of all sub nodes.
   * 
   * @see java.util.Collections#checkedList(List, Class)
   */
  @Override public List<N> nodeList() {
    return nodes;
  }
  
  // used by XML serialization
  @Override N nodeAt(int index,N node) {
    // ensure that node exists
    assert index==nodes.size()-1;
    nodes.add(null);
    return nodes.set(index,node);
  }
  
  private final NodeList nodes=new NodeList();
  class NodeList extends AbstractList<N> implements RandomAccess {
    @Override public int size() {
      return list.size();
    }
    @Override public N get(int index) {
      return list.get(index);
    }
    @Override public N set(int index, N element) {
      if (!getElementType().isInstance(element))
        throw new ClassCastException(element.getClass().toString());
      N oldElement=list.get(index);
      list.set(index,reparent(oldElement,element));
      return oldElement;
    }
    @Override public void add(int index, N element) {
      if (!getElementType().isInstance(element))
        throw new ClassCastException(element.getClass().toString());
      reparent(null,element);
      list.add(index,element);
    }
    @Override public N remove(int index) {
      N node=list.remove(index);
      reparent(node,null);
      return node;
    }
    private final ArrayList<N> list=new ArrayList<N>();
  }
}
