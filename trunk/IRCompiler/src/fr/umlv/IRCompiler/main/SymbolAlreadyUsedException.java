package fr.umlv.IRCompiler.main;

public class SymbolAlreadyUsedException extends Exception {

  private static final long serialVersionUID = 5877168164597571828L;

  public SymbolAlreadyUsedException(String message) {
    super(message);
  }
}
