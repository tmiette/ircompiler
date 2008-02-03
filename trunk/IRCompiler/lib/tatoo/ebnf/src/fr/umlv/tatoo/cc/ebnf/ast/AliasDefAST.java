package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.List;

import fr.umlv.tatoo.cc.ebnf.ast.Binding.VersionBinding;

public class AliasDefAST extends AbstractNodeAST implements BindingSite {
  private final TokenAST<String> name;
  
  private VersionBinding binding;
  
  AliasDefAST(AST ast,TokenAST<String> name,List<TreeAST> trees) {
    super(ast,trees);
    this.name=name;
  }
  @Override
  public Kind getKind() {
    return Kind.ALIAS_DEF;
  }
  public TokenAST<String> getTokenId() {
    return name;
  }
  
  @Override
  public VersionBinding getBinding() {
    return binding;
  }
  public void setBinding(VersionBinding binding) {
    if (this.binding!=null)
      throw new AssertionError("binding can only be initialized once");
    this.binding=binding;
  }
  
  public <R,P,E extends Exception> R accept(
      TreeASTVisitor<? extends R,? super P,? extends E> visitor,P parameter) throws E {
    return visitor.visit(this,parameter);
  }
}
