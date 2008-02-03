package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.List;

public class RootDefAST extends AbstractNodeAST {
  private final StartNonTerminalSetDefAST startNonTerminalDef;
  
  RootDefAST(AST ast,StartNonTerminalSetDefAST startNonTerminalDef,List<TreeAST> trees) {
    super(ast,trees);
    this.startNonTerminalDef=startNonTerminalDef;
  }
  @Override
  public Kind getKind() {
    return Kind.ROOT_DEF;
  }
  public StartNonTerminalSetDefAST getStartNonTerminalSetDef() {
    return startNonTerminalDef;
  }
  
  public <R,P,E extends Exception> R accept(
      TreeASTVisitor<? extends R,? super P,? extends E> visitor,P parameter) throws E {
    return visitor.visit(this,parameter);
  }
}
