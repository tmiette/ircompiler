/*
 * Created on 8 mars 2006
 *
 */
package fr.umlv.tatoo.cc.tools.ast;

public class Parameter<N extends ASTNode> {
  public Parameter(N node,String name) {
    this.node=node;
    this.name=name;
  }
  public N getNode() {
    return node;
  }
  public String getName() {
    return ASTModel.uncapitalize(name);
  }
  
  public String getGetter() {
    return ASTModel.getter(name,node.getType());
  }
  public String getSetter() {
    return ASTModel.setter(name);
  }
  
  @Override
  public String toString() {
    return node.getType().getSimpleName()+' '+getName();
  }
  
  private final N node;
  private final String name;
}
