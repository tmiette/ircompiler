package fr.umlv.tatoo.runtime.ast;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

/** Abstract class for all nodes.
 * Provides :
 *  - generic services like equals(), hashCode() and toString().
 *  - auto re-parenting with @link {@link #reparent(Node, Node)}, {@link #getParent()}.
 *  - implements Node and provide default implementation.
 *  - support for XML serialization.
 *  - support for IO serialization.
 *  
 * @author Remi 
 * 
 * @param <N> common type of all children Node.
 * @param <P> type of the parent Node.
 * @param <V> common type of all terminal value.
 */
abstract class AbstractNode<N extends Node,P extends Node,V> implements Serializable, Node {
  private static final long serialVersionUID = -4994599841088570804L;
  
  AbstractNode() {
    // just package private
  }
  
  @Override public boolean equals(Object o) {
    if (o==null || getClass()!=o.getClass())
      return false;
    AbstractNode<?,?,?> node=(AbstractNode<?,?,?>)o;
    return attributeList().equals(node.attributeList()) &&
      nodeList().equals(node.nodeList());
  }
  @Override public int hashCode() {
    return attributeList().hashCode()^nodeList().hashCode();
  }
  @Override public String toString() {
    StringBuilder builder=new StringBuilder();
    builder.append(getName()).append('(');
    
    List<V> attributes=attributeList();
    if (!attributes.isEmpty()) {
      for(int i=0;i<attributes.size();i++) {
        builder.append(attributeNameAt(i)).append('=').append(attributes.get(i)).append(',');
      }
      builder.setLength(builder.length()-1);
    }
    for(N node:nodeList())
      builder.append(node).append(',');
    if (builder.charAt(builder.length()-1)==',')
      builder.setLength(builder.length()-1);
    return builder.append(')').toString();
  }
  
  /** {@inheritDoc}
   */
  public P getParent() {
    return parent;
  }
  
  /** This method changes the parent of the current node.
   *  This method must not be called directly, but used by
   *  {@link Node#nodeList() nodeList().set()},
   *  {@link Node#nodeList() nodeList().add()},
   *  {@link Node#nodeList() nodeList().remove()} instead.
   *  
   * @param oldNode the old child node.
   * @param newNode the new child node.
   * @return the new child node.
   * 
   * @param <T> a subtype of N 
   */
  protected <T extends N> T reparent(T oldNode,T newNode) {
    if (newNode!=null && newNode.getParent()!=null)
      throw new IllegalStateException("the node has already a parent");
      
    if (oldNode!=null)
      ((AbstractNode<?,?,?>)oldNode).parent=null;
    
    if (newNode!=null) 
      ((AbstractNode<?,AbstractNode<?,?,?>,?>)newNode).parent=this;
    
    return newNode;
  }
  
  /** Returns a read-write list of all sub nodes of the current node.
   *  This list provides access in constant time to each of its element.
   *  This list checks at runtime if the type of
   *  an inserted node is a subtype of the declared children type.
   *  
   *  The method {@link List#add(int, Object)} or {@link List#set(int, Object)}
   *  raises an {@link IllegalStateException} if the node taken as argument
   *  has already a parent.
   *  
   * @return a list of all sub nodes of the current node.
   * 
   * @see #replaceAllNodes(Collection)
   */
  public abstract List<N> nodeList();
  
  /** Returns if the current node has a fixed number of subnodes.
   *  Helper method used by {@link #replaceAllNodes(Collection)}.
   * @return true if the number of subnodes is fixed. 
   */
  abstract boolean isFixedNodeSize();
  
  /** Replaces all sub-nodes of the current node by new ones taken as parameter.
   *  This method is roughly equivalent this code :
   *  <pre>
   *   nodeList().clear();
       nodeList().addAll(nodes);
   *  </pre>
   *  
   *  Each node in {@code nodes} must have no parent.
   *  Each newly added node will have the current node has parent.
   *  Each removed node will have no parent.
   *  
   * @param nodes a collection of nodes that will be used as new subnodes,
   *        
   * @throws IllegalArgumentException if the {@link #nodeList()} is a fixed size list
   *         and if its number of nodes is not the same that the number of nodes
   *         of the collection taken as parameter.
   * @throws IllegalStateException if one node of {@code nodes} has a parent.
   *        
   * @see #nodeList()
   */
  public void replaceAllNodes(Collection<? extends N> nodes) {
    List<N> nodeList=nodeList();
    if (isFixedNodeSize()) {
      if (nodeList.size()!=nodes.size())
        throw new IllegalArgumentException("the number of children ("+nodeList.size()+
            ") of the current node and size collection ("+nodes.size()+") is not the same");

      int i=0;
      for(N node:nodes) {
        nodeList.set(i,node);
        i++;
      }
    } else {
      nodeList.clear();
      nodeList.addAll(nodes);
    }
  }
  
  // used by XML serialization
  abstract N nodeAt(int index,N node);
  
  /** Returns the real type of the child node.
   *  Service method implemented by concrete node,
   *  this method if used by XML deserialization.
   * @return the real type of the children node.
   */
  protected abstract Class<N> getElementType();
  
  /** A read-write list of all attribute values of the current node.
   *  This list is fixed size and provides access in constant time
   *  to each of its element.
   * @return a list of attribute values.
   * @see Node#attributeMap()
   */
  public List<V> attributeList() {
    if (attributeList!=null)
      return attributeList;
    return attributeList=new AttributeList();
  }
  class AttributeList extends AbstractList<V> implements RandomAccess {
    @Override public int size() {
      return attributeCount();
    }
    @Override public V get(int index) {
      return attributeAt(index);
    }
    @Override public V set(int index,V element) {
      return attributeAt(index,element);
    }
  }
  
  /** Returns the number of attributes.
   *  Service method implemented by concrete node,
   *  this method must not be called directly,
   *  to obtain the number of attributes use
   *  {@link Node#attributeList() attributeList().size()} or
   *  {@link Node#attributeMap() attributeMap().size()} instead.
   * @return the number of attributes.
   */
  protected int attributeCount() { 
    return 0;
  }
  
  /** Returns the value of the attribute at the index position.
   *  Service method implemented by concrete node,
   *  this method must not be called directly,
   *  to obtain an attribute value by its index use
   *  {@link Node#attributeList() attributeList().get(index)}
   *  instead.
   * @param index the index of the attribute.
   * @return the value of the attribute at index index.
   * @throws IndexOutOfBoundsException if the index is not
   *  between 0 and the attribute list size.
   */
  protected V attributeAt(int index) throws IndexOutOfBoundsException {
    throw new IndexOutOfBoundsException("invalid index "+index);
  }
  
  /** Changes the value of the attribute at the index position
   *  and returns the old value.
   *  Service method implemented by concrete node,
   *  this method must not be called directly,
   *  to change an attribute value by its index use
   *  {@link Node#attributeList() attributeList().set(index)}
   *  instead.
   * @param index the index of the attribute.
   * @param value the new value of the attribute.
   * @return the old value of the attribute at index index.
   * @throws IndexOutOfBoundsException if the index is not
   *  between 0 ant the attribute list size.
   */
  protected V attributeAt(int index,V value) throws IndexOutOfBoundsException {
    throw new IndexOutOfBoundsException("invalid index "+index+" with value "+value);
  }
  
  /** Returns the name of the attribute at the index position.
   *  Service method implemented by concrete node,
   *  this method must not be called directly.
   * @param index index the index of the attribute.
   * @return the name of the attribute at index index.
   * @throws IndexOutOfBoundsException if the index is not
   *  between 0 ant the attribute list size.
   */
  protected String attributeNameAt(int index) throws IndexOutOfBoundsException {
    throw new IndexOutOfBoundsException("invalid index "+index);
  }
  
  /** Returns the real type of the attribute at the index position.
   *  Service method implemented by concrete node
   *  and used by deserialization mecanism,
   *  this method must not be called directly.
   * @param index index the index of the attribute.
   * @return the real type of the attribute at index index.
   * @throws IndexOutOfBoundsException if the index is not
   *  between 0 ant the attribute list size.
   */
  protected Class<? extends V> attributeTypeAt(int index) throws IndexOutOfBoundsException {
    throw new IndexOutOfBoundsException("invalid index "+index);
  }
  
  /** Returns the index of an attribute by its name.
   *  Service method implemented by concrete node
   *  and used by deserialization mecanism,
   *  this method must not be called directly.
   * @param name name of the attribute.
   * @return the real type of the attribute at index index.
   * @throws IllegalArgumentException
   *   if the name of the attribute is not known.
   */
  protected int indexOfAttributeName(String name) {
    int attributeCount=attributeCount();
    for(int i=0;i<attributeCount;i++)
      if (attributeNameAt(i).equals(name))
        return i;
    throw new IllegalArgumentException("invalid attribute name "+name);
  }
  
  /** A read-write map of couples attribute name/attribute value.
   *  This map is fixed size and access time could be a function of the number
   *  of attribute. Futhermore, this map checks the runtime time of the attribute
   *  value before insertion.
   *  
   * @return a map of attribute name/value.
   */
  public Map<String,V> attributeMap() {
    if (attributeMap!=null)
      return attributeMap;
    return attributeMap=new AbstractMap<String,V>() {
      private final Set<Entry<String,V>> entrySet=
        new AbstractSet<Entry<String,V>>() {

        @Override public int size() {
          return attributeCount();
        }
        @Override public Iterator<Entry<String,V>> iterator() {
          // use a fail-fast iterator here
          final ListIterator<V> it=attributeList().listIterator();

          return new Iterator<Map.Entry<String,V>>() {
            public boolean hasNext() {
              return it.hasNext();
            }
            public Map.Entry<String,V> next() {
              final int index=it.nextIndex();
              return new Map.Entry<String,V>() {
                private V value=it.next();
                public String getKey() {
                  return attributeNameAt(index);
                }
                public V getValue() {
                  return value;
                }
                public V setValue(V value) {
                  // may raised an exception
                  V oldValue=attributeAt(index,value);
                  this.value=value;
                  return oldValue;
                }
              };
            }
            public void remove() {
              throw new UnsupportedOperationException();
            }
          };
        }
      };
        
      @Override public int size() {
        return attributeCount();
      }
      @Override
      public Set<Entry<String,V>> entrySet() {
        return entrySet;
      }
    };
  }
  
  private P parent;
  private transient AttributeList attributeList;
  private transient AbstractMap<String,V> attributeMap;
}
