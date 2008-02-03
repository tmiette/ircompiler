package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.List;

import fr.umlv.tatoo.cc.ebnf.ast.Binding.VersionBinding;

public class VersionDefAST extends AbstractNodeAST implements BindingSite {
  private final TokenAST<String> id;
  private final VersionVarAST parentVersion; // may be null
  
  private VersionBinding binding;
  VersionDefAST(AST ast,TokenAST<String> id,VersionVarAST parentVersion,List<TreeAST> trees) {
    super(ast,trees);
    this.parentVersion=parentVersion;
    this.id=id;
  }
  @Override
  public Kind getKind() {
    return Kind.VERSION_DEF;
  }
  public TokenAST<String> getTokenId() {
    return id;
  }
  
  public String getName() {
    return id.getValue();
  }
  public VersionVarAST getParentVersion() {
    return parentVersion;
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
