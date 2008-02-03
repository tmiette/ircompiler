package fr.umlv.tatoo.cc.ebnf.ast;

public interface AST {
  public void setRoot(TreeAST tree);
  //public TreeAST getRoot();
  
  public boolean isAttributeSupported(Class<?> attributeType);
  public <A> A getAttribute(TreeAST node, Class<A> attributeType);
  public <A> void setAttribute(TreeAST node, Class<A> attributeType, A attribute);
  
  public <R,P,E extends Exception> R visit(
      TreeASTVisitor<? extends R,? super P,? extends E> visitor,P parameter) throws E;
}