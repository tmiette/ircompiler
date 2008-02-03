package fr.umlv.tatoo.samples.tutorial;

import java.util.*;

import fr.umlv.tatoo.samples.tutorial.ast.*;

public class IntExprVisitor extends Visitor<Integer,List<Line>,Void,NoException> {
  @Override
  public Integer visit(Expr_Value v, List<Line> l) {
    return v.getValue();
  }
  @Override
  public Integer visit(Expr_Plus e, List<Line> l) {
    return e.getExpr().accept(this, l) + e.getExpr2().accept(this, l);
  }
  @Override
  public Integer visit(Expr_Minus e, List<Line> l) {
    return e.getExpr().accept(this, l) - e.getExpr2().accept(this, l);
  }
  @Override
  public Integer visit(Expr_Star e, List<Line> l) {
    return e.getExpr().accept(this, l) * e.getExpr2().accept(this, l);
  }
  @Override
  public Integer visit(Expr_Div e, List<Line> l) {
    return e.getExpr().accept(this, l) / e.getExpr2().accept(this, l);
  }
  @Override
  public Integer visit(Expr_Par e, List<Line> l) {
    return e.getExpr().accept(this, l);
  }
  @Override
  public Integer visit(Calculus c, List<Line> l) {
    l.add(new Line(new Calculus(new Expr_Value(c.getExpr().accept(this, null)))));
    return null;
  }
  @Override
  public Integer visit(Calculus_Error c, List<Line> l) {
    l.add(new Line(new Calculus(new Expr_Value(-1))));
    return null;
  }
  @Override
  public Integer visit(Slist list, List<Line> l) throws NoException {
    for (Line c : list.nodeList()) {
      c.accept(this, l);
    }
    list.replaceAllNodes(l);
    return null;
  }
  @Override
  public Integer visit(Start_List s, List<Line> l) {
    List<Line> newlist = new ArrayList<Line>();
    s.getSlist().accept(this,newlist);
    return null;
  }
  @Override
  public Integer visit(Line line, List<Line> param) {
    line.getCalculus().accept(this, param);
    return null;
  }
}
