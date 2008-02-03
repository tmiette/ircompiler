package fr.umlv.tatoo.cc.ebnf.ast.analysis;

public enum Directive {
  AUTOTOKEN;
  
  public static Directive parse(String directive) {
    return Directive.valueOf(directive.toUpperCase());
  }
}
