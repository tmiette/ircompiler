package fr.umlv.tatoo.cc.ebnf.ast;

public interface AnnotationComputer {
  public void computeTokenAnnotation(TokenAST<?> token);
  
  public void computeNodeAnnotation(NodeAST node,TreeAST first,TreeAST last);
  
  public void computeEmptyNodeAnnotation(NodeAST node);
}
