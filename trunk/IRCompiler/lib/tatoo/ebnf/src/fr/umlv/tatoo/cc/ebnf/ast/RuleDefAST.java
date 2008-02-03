package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.Collections;
import java.util.List;

import fr.umlv.tatoo.cc.ebnf.ast.Binding.RuleBinding;

public class RuleDefAST extends AbstractNodeAST implements BindingSite {
  private final TokenAST<String> id;
  private final String regex;
  
  private RuleBinding binding;
  RuleDefAST(AST ast,TokenAST<String> id,String regex,List<TreeAST> trees) {
    super(ast,trees);
    this.id=id;
    this.regex=regex;
  }
  @Override
  public Kind getKind() {
    return Kind.RULE_DEF;
  }
  @Override
  public List<NodeAST> nodeList() {
    return Collections.emptyList();
  }
  public TokenAST<String> getTokenId() {
    return id;
  }
  
  public String getName() {
    return id.getValue();
  }
  public String getRegex() {
    return regex;
  }
  
  @Override
  public RuleBinding getBinding() {
    return binding;
  }
  public void setBinding(RuleBinding binding) {
    if (this.binding!=null)
      throw new AssertionError("binding can only be initialized once");
    this.binding=binding;
  }
  
  public <R,P,E extends Exception> R accept(
      TreeASTVisitor<? extends R,? super P,? extends E> visitor,P parameter) throws E {
    return visitor.visit(this,parameter);
  }
}
