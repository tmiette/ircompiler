package fr.umlv.tatoo.cc.common.main;

public enum Unit {
  lexer, parser, tools, ast, ebnf, linker;
  
  public String getDefaultSubPackage() {
    return name();
  }
  
  public static Unit parse(String unitName) {
    return Unit.valueOf(unitName.toLowerCase());
  }
}
