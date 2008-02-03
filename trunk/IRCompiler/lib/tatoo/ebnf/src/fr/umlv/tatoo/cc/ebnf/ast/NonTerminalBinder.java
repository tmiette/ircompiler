package fr.umlv.tatoo.cc.ebnf.ast;

import fr.umlv.tatoo.cc.ebnf.ast.Binding.NonTerminalBinding;

public interface NonTerminalBinder extends NodeAST, BindingSite {
  public NonTerminalBinding getBinding();
}
