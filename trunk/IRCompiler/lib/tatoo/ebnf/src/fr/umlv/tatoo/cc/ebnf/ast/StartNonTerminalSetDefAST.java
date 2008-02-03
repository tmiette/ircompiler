package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.List;

public class StartNonTerminalSetDefAST extends AbstractNodeAST {
  private final List<UnquotedIdVarAST> startNonTerminalList;
  
  StartNonTerminalSetDefAST(AST ast, List<UnquotedIdVarAST> startNonTerminalList,List<TreeAST> trees) {
    super(ast,trees);
    this.startNonTerminalList=startNonTerminalList;
  }
      
  @Override
  public Kind getKind() {
    return Kind.START_NONTERMINAL_SET_DEF;
  }

  public List<UnquotedIdVarAST> getStartNonTerminalList() {
    return startNonTerminalList;
  }

  public <R,P,E extends Exception> R accept(
    TreeASTVisitor<? extends R,? super P,? extends E> visitor,P parameter) throws E {
    return visitor.visit(this,parameter);
  }
}
