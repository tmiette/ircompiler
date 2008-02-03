package fr.umlv.tatoo.runtime.ast;

import java.util.HashMap;

/** Factory used by XML deserialization
 *  in order to create empty node.
 * 
 * @author Remi
 */
public abstract class XMLNodeFactory {
  /** Part of factory that knows how to create an empty
   *  no of a specific XML element name.
   *  
   * @author Remi
   */
  protected interface AbstractNodeFactory {
    /** Returns the XML element name of a node.
     * @return the XML element name of a node.
     * @see Node#getName()
     */
    public String getName();
    
    /** Creates an empty node corresponding to the
     *  XML element name.
     * @return an empty node, i.e. no children, no attribute.
     * @see #getName()
     */
    public Node createEmptyNode();
  }
  
  /** Creates a factory that know how to create an empty nodes
   *  corresponding to selement names.
   * @param factories an array of all abstract factories needed for create the AST.
   *  Each abstract factory corresponds to an XML element and knows how to create
   *  the correspoding AST node.
   */
  protected XMLNodeFactory(AbstractNodeFactory[] factories) {
    HashMap<String,AbstractNodeFactory> map=
      new HashMap<String,AbstractNodeFactory>();
    for(AbstractNodeFactory factory:factories)
      map.put(factory.getName(),factory);
    factoryMap=map;
  }
  
  /** Returns an empty node associated with an element name.
   *  This method must not be called directy,
   *  to deserailize an AST use
   *  {@link XML#unserialize(java.io.Reader, XMLNodeFactory) unserialize(reader,nodeFactory)}
   *  instead.
   * @param name of the node
   * @return an empty node corresponding to the name.
   * 
   * @see Node#getName()
   */
  Node createEmptyNode(String name) {
    AbstractNodeFactory factory=factoryMap.get(name);
    if (factory==null)
      return null;
    return factory.createEmptyNode();
  }
  private final HashMap<String,AbstractNodeFactory> factoryMap;
}