package fr.umlv.tatoo.cc.ebnf;

import fr.umlv.tatoo.cc.ebnf.ast.AnnotationComputer;
import fr.umlv.tatoo.cc.ebnf.ast.NodeAST;
import fr.umlv.tatoo.cc.ebnf.ast.TokenAST;
import fr.umlv.tatoo.cc.ebnf.ast.TreeAST;
import fr.umlv.tatoo.runtime.buffer.LocationProvider;

public class EBNFAnnotationComputer implements AnnotationComputer {
  private final LocationProvider locationTracker;
  public EBNFAnnotationComputer(LocationProvider locationTracker) {
    this.locationTracker=locationTracker;
  }
  
  public void computeTokenAnnotation(TokenAST<?> token) {
    token.setAttribute(LineColumnLocation.class,
      new LineColumnLocation(
        locationTracker.getLineNumber(),locationTracker.getColumnNumber()));
  }
  
  public void computeNodeAnnotation(NodeAST node,TreeAST first,TreeAST last) {
    node.setAttribute(LineColumnLocation.class,
      first.getAttribute(LineColumnLocation.class));
  }
  
  public void computeEmptyNodeAnnotation(NodeAST node) {
    node.setAttribute(LineColumnLocation.class,
      new LineColumnLocation(
        locationTracker.getLineNumber(),locationTracker.getColumnNumber()));
  }
}
