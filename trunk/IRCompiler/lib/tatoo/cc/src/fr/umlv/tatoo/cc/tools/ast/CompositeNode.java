/*
 * Created on 4 mars 2006
 *
 */
package fr.umlv.tatoo.cc.tools.ast;

import java.io.DataOutputStream;
import java.io.IOException;

import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;

public class CompositeNode extends ElementNode implements ProductionNode {
  CompositeNode(String packageName,NonTerminalDecl nonTerminal,ElementNode enclosingElement) {
    super(packageName,"",nonTerminal);
    this.enclosingParameter=new Parameter<ElementNode>(
      enclosingElement,enclosingElement.getId());
  }
  
  public String getEnumName() {
    return getId().replace('-','_');
  }
  
  //called by velocity
  public boolean isComposite() {
    return true;
  }
  
  public Parameter<ElementNode> getEnclosingParameter() {
    return enclosingParameter;
  }
  
  @Override
  void serialUIDAsStream(DataOutputStream out) throws IOException {
    out.writeUTF(getId());
    out.writeUTF(enclosingParameter.getNode().getId());
  }
  
  @Override
  public String toString() {
    return super.toString();
  }
  
  private final Parameter<ElementNode> enclosingParameter;
}