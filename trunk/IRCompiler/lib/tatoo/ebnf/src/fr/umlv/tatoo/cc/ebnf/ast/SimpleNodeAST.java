package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.List;

public class SimpleNodeAST<V> extends AbstractNodeAST  {
  private final Kind kind;
  private final V value;
  
  protected SimpleNodeAST(AST ast,Kind kind,V value,List<TreeAST> trees) {
    super(ast,trees);
    
    this.kind=kind;
    this.value=value;
  }
  
  @Override
  public Kind getKind() {
    return kind;
  }
  
  public V getValue() {
    return value;
  }
  
  public <R,P,E extends Exception> R accept(
      TreeASTVisitor<? extends R,? super P,? extends E> visitor,P parameter) throws E {
    return visitor.visit(this,parameter);
  }
}
