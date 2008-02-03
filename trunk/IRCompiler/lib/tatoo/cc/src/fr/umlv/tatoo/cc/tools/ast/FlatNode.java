/*
 * Created on 4 mars 2006
 *
 */
package fr.umlv.tatoo.cc.tools.ast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.umlv.tatoo.cc.common.generator.Type;
import fr.umlv.tatoo.cc.parser.grammar.ProductionDecl;
import fr.umlv.tatoo.cc.tools.generator.Naming;

public class FlatNode extends ElementNode implements ParentNode, ProductionNode {
  FlatNode(String packageName,ProductionDecl production,NonTerminalNode parent,List<? extends ASTNode> nodes) {
    super(packageName,"",production);
    
    // if non terminal is not expressive
    if (parent==null) {
      this.parent=this;
    } else {
      this.parent=parent;
      parent.addNodeImpl(this);
    }
    
    for(ASTNode node:nodes) {
      if (node instanceof ElementNode) {
        ElementNode elementNode=(ElementNode)node;
        elementNode.addNodeParent(parent);
        
        Parameter<ElementNode> parameter=createNodeParameter(elementNode,elementNaming);
        elementParameters.add(parameter);
        nodeParameters.add(parameter);
      } else {
        Parameter<AttributeNode> parameter=createNodeParameter((AttributeNode)node,attrNaming);
        attributeParameters.add(parameter);
        nodeParameters.add(parameter);
      }
    }
    
    elementSuperType=getParameterSuperType(elementParameters);
    attributeSupertype=getParameterSuperType(attributeParameters);
  }
  
  // called by velocity
  public boolean isSingleProduction() {
    return parent==this;
  }
  
  //called by velocity
  public boolean isComposite() {
    return false;
  }
  
  private static <N extends ASTNode> Parameter<N> createNodeParameter(N node,Naming naming) {
    return new Parameter<N>(node,naming.name(node.getId()));
  }
  
  public String getEnumName() {
    return getId().replace('-','_');
  }
  public String getXMLName() {
    return getId().replace('_','-');
  }
  
  public ParentNode getParent() {
    return parent;
  }
  
  public List<? extends Parameter<ElementNode>> getElements() {
    return elementParameters;
  }
  public List<? extends Parameter<AttributeNode>> getAttributes() {
    return attributeParameters;
  }
  
  @Override
  void serialUIDAsStream(DataOutputStream out) throws IOException {
    out.writeUTF(getEnumName());
    for(Parameter<?> parameter:elementParameters) {
      out.writeUTF(parameter.getNode().getType().getName());
    }
    for(Parameter<?> parameter:attributeParameters) {
      out.writeUTF(parameter.getName());
      out.writeUTF(parameter.getNode().getType().getName());
    }
  }
  
  public Type getAttributeSuperType() {
    return attributeSupertype;
  }
  
  public Type getElementSuperType() {
    return elementSuperType;
  }
  
  public List<? extends Parameter<? extends ASTNode>> getNodes() {
    return nodeParameters;
  }
  
  @Override
  public String toString() {
    StringBuilder builder=new StringBuilder();
    builder.append(getType()).append(':').append(parent.getType()).append(" (");
    for(Parameter<ElementNode> param:elementParameters)
      builder.append(param.getNode().getType()).append(param.getName()).append(',');
    if (!elementParameters.isEmpty())
      builder.setLength(builder.length()-1);
    return builder.append(')').toString();
  }
  
  private final Type elementSuperType;
  private final Type attributeSupertype;
  
  private final ParentNode parent;
  private final Naming attrNaming=new Naming();
  private final Naming elementNaming=new Naming();
  private final ArrayList<Parameter<? extends ASTNode>> nodeParameters=
    new ArrayList<Parameter<? extends ASTNode>>();
  private final ArrayList<Parameter<ElementNode>> elementParameters=
    new ArrayList<Parameter<ElementNode>>();
  private final ArrayList<Parameter<AttributeNode>> attributeParameters=
    new ArrayList<Parameter<AttributeNode>>();
  
  private static <N extends ASTNode> Type getParameterSuperType(List<? extends Parameter<N>> parameters) {
    if (parameters.isEmpty())
      // oups, malformed grammar
      return null;
    
    N node=parameters.get(0).getNode(); 
    for(int i=1;i<parameters.size();i++) {
      if (parameters.get(i).getNode()!=node)
        return null;
    }
    return node.getType().boxIfPrimitive();
  }
}
