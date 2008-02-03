package fr.umlv.tatoo.cc.ebnf.ast;

public interface TreeAST {
  public boolean isToken();
  public Object getKind();
  
  public NodeAST getParent();
  
  public <A> A getAttribute(Class<A> attributeType);
  public <A> void setAttribute(Class<A> attributeType, A attribute);
  
  public AST getAST();
  
  public <R,P,E extends Exception> R accept(
      TreeASTVisitor<? extends R,? super P,? extends E> visitor,P parameter) throws E;
}
