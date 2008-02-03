package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.List;

import fr.umlv.tatoo.cc.ebnf.ast.Binding.ProductionBinding;

public class ProductionDefAST extends AbstractNodeAST implements BindingSite {
               
  private final List<NodeAST> variableList; // may be null (optional)
  private final ProductionIdAndVersionDefAST idAndversion; // may be null
  private final PriorityVarAST priority; // may be null
  
  private TokenAST<String> id; // set by enter pass 2, may be null because optional
  private ProductionBinding binding;
  ProductionDefAST(AST ast,List<NodeAST> variableList,PriorityVarAST priority,ProductionIdAndVersionDefAST idAndVersion,List<TreeAST> trees) {
    super(ast,trees);
    this.variableList=variableList;
    this.priority=priority;
    this.idAndversion=idAndVersion;
  }
  @Override
  public Kind getKind() {
    return Kind.PRODUCTION_DEF;
  }
  
  public TokenAST<String> getTokenId() {
    return id;
  }
  public void setTokenId(TokenAST<String> id) {
    this.id=id;
  }
  public List<NodeAST> getVariableList() {
    return variableList;
  }
  public ProductionIdAndVersionDefAST getIdAndVersion() {
    return idAndversion;
  }
  public PriorityVarAST getPriority() {
    return priority;
  }
  
  @Override
  public ProductionBinding getBinding() {
    return binding;
  }
  public void setBinding(ProductionBinding binding) {
    if (this.binding!=null)
      throw new AssertionError("binding can only be initialized once");
    this.binding=binding;
  }
  
  public <R,P,E extends Exception> R accept(
      TreeASTVisitor<? extends R,? super P,? extends E> visitor,P parameter) throws E {
    return visitor.visit(this,parameter);
  }
}
