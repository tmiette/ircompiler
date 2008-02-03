package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.Collections;
import java.util.List;

import fr.umlv.tatoo.cc.ebnf.ast.Binding.TypeBinding;

public class TypeVarAST extends AbstractNodeAST implements TypeBinder, BindingSite {
  private final TokenAST<String> qualifiedid;
  
  private TypeBinding binding;
  TypeVarAST(AST ast,TokenAST<String> qualifiedid,List<TreeAST> trees) {
    super(ast,trees);
    this.qualifiedid=qualifiedid;
  }
  @Override
  public Kind getKind() {
    return Kind.TYPE_VAR;
  }
  @Override
  public List<NodeAST> nodeList() {
    return Collections.emptyList();
  }
  public TokenAST<String> getTokenId() {
    return qualifiedid;
  }
  
  public String getQualifiedId() {
    return qualifiedid.getValue();
  }
  
  @Override
  public TypeBinding getBinding() {
    return binding;
  }
  public void setBinding(TypeBinding binding,boolean addReferee) {
    if (this.binding!=null)
      throw new AssertionError("binding can only be initialized once");
    this.binding=binding;
    if (addReferee)
      binding.addReferee(this);
  }
  
  public <R,P,E extends Exception> R accept(
      TreeASTVisitor<? extends R,? super P,? extends E> visitor,P parameter) throws E {
    return visitor.visit(this,parameter);
  }
}
