package fr.umlv.tatoo.cc.ebnf.ast;

import fr.umlv.tatoo.cc.ebnf.ast.Binding.TypeBinding;

public interface TypeBinder extends NodeAST, BindingSite {
  public TypeBinding getBinding();
}
