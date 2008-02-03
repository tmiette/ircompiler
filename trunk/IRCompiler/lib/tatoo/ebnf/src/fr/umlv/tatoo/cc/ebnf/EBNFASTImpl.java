package fr.umlv.tatoo.cc.ebnf;

import java.util.HashMap;

import fr.umlv.tatoo.cc.ebnf.ast.AST;
import fr.umlv.tatoo.cc.ebnf.ast.NodeAST;
import fr.umlv.tatoo.cc.ebnf.ast.TreeAST;
import fr.umlv.tatoo.cc.ebnf.ast.TreeASTVisitor;

public class EBNFASTImpl implements AST {
  private NodeAST root;
  private final HashMap<TreeAST,LineColumnLocation> locationMap=
    new HashMap<TreeAST,LineColumnLocation>();

  public NodeAST getRoot() {
    return root;
  }
  public void setRoot(TreeAST root) {
    this.root=(NodeAST)root;
  }
  
  public boolean isAttributeSupported(Class<?> attributeType) {
    return attributeType==LineColumnLocation.class;
  }

  public <A> void setAttribute(TreeAST node,Class<A> attributeType,A attribute) {
    if (attributeType!=LineColumnLocation.class)
      throw new IllegalArgumentException("attribute can only be LineColumnLocation "+attributeType);
    locationMap.put(node,(LineColumnLocation)attribute);
  }

  @SuppressWarnings("unchecked")
  public <A> A getAttribute(TreeAST node,Class<A> attributeType) {
    if (attributeType!=LineColumnLocation.class)
      throw new IllegalArgumentException("attribute can only be LineColumnLocation "+attributeType);
    return (A)locationMap.get(node);
  }
  
  public <R,P,E extends Exception> R visit(
      TreeASTVisitor<? extends R,? super P,? extends E> visitor,P parameter)
      throws E {
    NodeAST root=this.root;
    if (root==null)
      return null;
    return root.accept(visitor,parameter);
  }
}