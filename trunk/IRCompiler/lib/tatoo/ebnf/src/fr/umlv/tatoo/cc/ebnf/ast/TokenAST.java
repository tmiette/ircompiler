package fr.umlv.tatoo.cc.ebnf.ast;

import fr.umlv.tatoo.cc.lexer.ebnf.parser.TerminalEnum;

public class TokenAST<V> extends AbstractTreeAST implements TreeAST {
  private final TerminalEnum kind;
  private final V value;
  
  TokenAST(AST ast, TerminalEnum kind, V value) {
    super(ast);
    this.kind=kind;
    this.value=value;
  }
  
  public boolean isToken() {
    return true;
  }
  
  @Override
  public String toString() {
    return "("+getKind()+':'+getValue()+')';
  }
  
  public TerminalEnum getKind() {
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
