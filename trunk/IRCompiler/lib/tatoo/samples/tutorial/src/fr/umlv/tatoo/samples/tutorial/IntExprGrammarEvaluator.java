package fr.umlv.tatoo.samples.tutorial;

import fr.umlv.tatoo.samples.tutorial.tools.GrammarEvaluator;

public class IntExprGrammarEvaluator implements GrammarEvaluator {
  public void calculus(int expr) {
    System.out.println(expr);
  }
  public int expr_value(int value) {
    return value;
  }
  public int expr_plus(int expr, int expr2) {
    return expr+expr2;
  }
  public int expr_minus(int expr, int expr2) {
    return expr-expr2;
  }
  public int expr_star(int expr, int expr2) {
    return expr*expr2;
  }
  public int expr_div(int expr, int expr2) {
    return expr/expr2;
  }
  public int expr_par(int expr) {
    return expr;
  }
  public void calculus_error() {
    System.out.println("Syntax error");
  }
  public void acceptStart() { /* nothing to do */ }
  public void start_list() { /* nothing to do */ }
  public void start_expr() { /* nothing to do */ }
  public void start_empty() { /* nothing to do */ }
  public void line() { /* nothing to do */ }
}