package fr.umlv.IRCompiler.main;


public class InvalidExpressionException extends Exception {

  private static final long serialVersionUID = 5716647090569727173L;

  public InvalidExpressionException(String message) {
    super(message);
  }

}
