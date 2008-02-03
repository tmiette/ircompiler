package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.Collections;
import java.util.List;

public class MacroDefAST extends AbstractNodeAST implements BindingSite {
  private final TokenAST<String> id;
  private final String regex;
  
  MacroDefAST(AST ast,TokenAST<String> id,String regex,List<TreeAST> nodes) {
    super(ast,nodes);
    this.id=id;
    this.regex=regex;
  }
  
  @Override
  public Kind getKind() {
    return Kind.MACRO_DEF;
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
  
  public <R,P,E extends Exception> R accept(
      TreeASTVisitor<? extends R,? super P,? extends E> visitor,P parameter) throws E {
    return visitor.visit(this,parameter);
  }
}
