package fr.umlv.IRCompiler.main;

public class UnresolvedClassException extends Exception {

  private static final long serialVersionUID = 8853445502692559210L;

  public UnresolvedClassException(String message) {
    super(message);
  }

  public UnresolvedClassException(String message, Throwable cause) {
    super(message, cause);
  }

}
