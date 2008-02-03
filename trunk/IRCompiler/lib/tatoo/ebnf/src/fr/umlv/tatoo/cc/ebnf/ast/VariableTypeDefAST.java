package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.List;

public class VariableTypeDefAST extends AbstractNodeAST {
  private final VariableVarAST variable;
  private final TypeVarAST type;
  
  VariableTypeDefAST(AST ast,VariableVarAST variable,TypeVarAST type,List<TreeAST> nodes) {
    super(ast,nodes);
    this.variable=variable;
    this.type=type;
  }
  
  public VariableVarAST getVariable() {
    return variable;
  }
  public TypeVarAST getType() {
    return type;
  }
  
  @Override
  public Kind getKind() {
    return Kind.VARIABLE_TYPE_DEF;
  }
  
  public <R,P,E extends Exception> R accept(
      TreeASTVisitor<? extends R,? super P,? extends E> visitor,P parameter) throws E {
    return visitor.visit(this,parameter);
  }
}
