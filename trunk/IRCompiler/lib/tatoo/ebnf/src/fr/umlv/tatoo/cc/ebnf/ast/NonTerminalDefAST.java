package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.List;

import fr.umlv.tatoo.cc.ebnf.ast.Binding.NonTerminalBinding;

public class NonTerminalDefAST extends AbstractNodeAST implements NonTerminalBinder, BindingSite {
  private final TokenAST<String> id;
  private final TypeVarAST type; // may be null
  private final List<ProductionDefAST> productions;
  
  private NonTerminalBinding binding;
  NonTerminalDefAST(AST ast,TokenAST<String> id,final TypeVarAST type,final List<ProductionDefAST> productions,List<TreeAST> trees) {
    super(ast,trees);
    this.id=id;
    this.type=type;
    this.productions=productions;
  }

  @Override
  public Kind getKind() {
    return Kind.NONTERMINAL_DEF;
  }
  public TokenAST<String> getTokenId() {
    return id;
  }
  
  public String getName() {
    return id.getValue();
  }
  public TypeVarAST getType() {
    return type;
  }
  public List<ProductionDefAST> getProductions() {
    return productions;
  }
  
  public void setBinding(NonTerminalBinding binding) {
    if (this.binding!=null)
      throw new AssertionError("binding can only be initialized once");
    this.binding=binding;
  }
  @Override
  public NonTerminalBinding getBinding() {
    return binding;
  }
  
  public <R,P,E extends Exception> R accept(
      TreeASTVisitor<? extends R,? super P,? extends E> visitor,P parameter) throws E {
    return visitor.visit(this,parameter);
  }
}
