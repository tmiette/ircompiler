package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.Collections;
import java.util.List;

import fr.umlv.tatoo.cc.ebnf.ast.Binding.TypeBinding;

public class ImportDefAST extends AbstractNodeAST implements TypeBinder, BindingSite {
  private final TokenAST<String> qualifiedId;
  
  private TypeBinding definition;
  ImportDefAST(AST ast,TokenAST<String> qualifiedId,List<TreeAST> trees) {
    super(ast,trees);
    this.qualifiedId=qualifiedId;
  }
  @Override
  public Kind getKind() {
    return Kind.IMPORT_DEF;
  }
  @Override
  public List<NodeAST> nodeList() {
    return Collections.emptyList();
  }
  public TokenAST<String> getTokenId() {
    return qualifiedId;
  }
  
  public String getQualifiedId() {
    return qualifiedId.getValue();
  }
  
  @Override
  public TypeBinding getBinding() {
    return definition;
  }
  public void setBinding(TypeBinding binding) {
    if (this.definition!=null)
      throw new AssertionError("binding can only be initialized once");
    this.definition=binding;
  }
  
  public <R,P,E extends Exception> R accept(
      TreeASTVisitor<? extends R,? super P,? extends E> visitor,P parameter) throws E {
    return visitor.visit(this,parameter);
  }
}
