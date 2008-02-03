package fr.umlv.tatoo.samples.tutorial;

import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.samples.tutorial.ast.*;

public class IntCounterVisitor extends Visitor<Integer,Void,Void,NoException> {
  @Override
  public Integer visit(Expr_Value v, Void param) {
    return 1;
  }
  @Override
  public Integer visit(Node n, Void param) {
    int result= 0;
    for (Node c : n.nodeList()) {
      result += c.accept(this, null);
    }
    return result;
  }
}
