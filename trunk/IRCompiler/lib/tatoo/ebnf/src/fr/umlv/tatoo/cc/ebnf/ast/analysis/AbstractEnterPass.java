package fr.umlv.tatoo.cc.ebnf.ast.analysis;

import fr.umlv.tatoo.cc.ebnf.ast.BindingMap;
import fr.umlv.tatoo.cc.ebnf.ast.NodeAST;
import fr.umlv.tatoo.cc.ebnf.ast.SimpleNodeAST;
import fr.umlv.tatoo.cc.ebnf.ast.TokenAST;
import fr.umlv.tatoo.cc.ebnf.ast.TreeASTVisitor;

public class AbstractEnterPass extends TreeASTVisitor<Object,Object,RuntimeException> {
  private final BindingMap bindingMap;
  protected AbstractEnterPass(BindingMap bindingMap) {
    this.bindingMap=bindingMap;
  }
  protected AbstractEnterPass(AbstractEnterPass pass) {
    this.bindingMap=pass.bindingMap;
  }
  
  protected BindingMap getBindingMap() {
    return bindingMap;
  }
  
  /** Escape null.
   */
  protected Object processOneSubNode(NodeAST subnode,Object parameter) {
    if (subnode==null)
      return null;
    return subnode.accept(this,parameter);
  }
  
  protected void processSubNodes(NodeAST node,Object parameter) {
    for(NodeAST n:node.nodeList()) {
      n.accept(this,parameter);
    }
  }
  
  @Override
  public Object visit(SimpleNodeAST<?> node,Object parameter) {
    processSubNodes(node,parameter);
    return null;
  }
  
  @Override
  protected Object visit(NodeAST node,Object parameter) {
    processSubNodes(node,parameter);
    return null;
  }
  
  @Override
  public Object visit(TokenAST<?> token, Object parameter) {
    return token.getValue();
    //throw new AssertionError("only nodes can be visited");
  }
}
