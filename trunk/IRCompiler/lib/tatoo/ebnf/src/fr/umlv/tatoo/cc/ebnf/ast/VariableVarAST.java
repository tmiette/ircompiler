package fr.umlv.tatoo.cc.ebnf.ast;

import fr.umlv.tatoo.cc.ebnf.ast.Binding.VariableBinding;

public interface VariableVarAST extends NodeAST, BindingSite {
  public String getName();
  public VariableBinding<?> getBinding();
}
