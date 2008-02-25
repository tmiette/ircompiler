package fr.umlv.IRCompiler.main;

public class SymbolAlreadyDefinedException extends Exception {

  private static final long serialVersionUID = -8506502681301966757L;

  public SymbolAlreadyDefinedException(String message) {
    super(message);
  }

}
