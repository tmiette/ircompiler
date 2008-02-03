package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.List;

public class ProductionIdAndVersionDefAST extends AbstractNodeAST implements BindingSite {
  private final TokenAST<String> name;
  private final VersionVarAST version;
  
  ProductionIdAndVersionDefAST(AST ast,TokenAST<String> name,VersionVarAST version,List<TreeAST> trees) {
    super(ast,trees);
    this.name=name;
    this.version=version;
  }
  
  @Override
  public Kind getKind() {
    return Kind.PRODUCTION_ID_AND_VERSION;
  }
  
  public TokenAST<String> getTokenId() {
    return name;
  }
  public VersionVarAST getVersion() {
    return version;
  }
  
  public <R,P,E extends Exception> R accept(
      TreeASTVisitor<? extends R,? super P,? extends E> visitor,P parameter) throws E {
    return visitor.visit(this,parameter);
  }
}
