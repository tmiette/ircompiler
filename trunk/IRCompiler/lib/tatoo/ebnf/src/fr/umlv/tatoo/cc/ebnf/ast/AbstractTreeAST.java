package fr.umlv.tatoo.cc.ebnf.ast;

abstract class AbstractTreeAST implements TreeAST {
  private final AST ast;
  private NodeAST parent;
  
  protected AbstractTreeAST(AST ast) {
    this.ast=ast;
  }
  
  public AST getAST() {
    return ast;
  }
  
  public NodeAST getParent() {
    return parent;
  }
  protected void setParent(NodeAST parent) {
    this.parent=parent;
  }
  
  public final <A> A getAttribute(Class<A> attributeType) {
    return ast.getAttribute(this,attributeType);
  }
  public final <A> void setAttribute(Class<A> attributeType,A attribute) {
    ast.setAttribute(this,attributeType,attribute);
  }
}