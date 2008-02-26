package fr.umlv.IRCompiler.exception;

/**
 * This exception is thrown when symbol isn't already defined.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public class UnknownSymbolException extends Exception {

  private static final long serialVersionUID = -8562296063999711451L;

  /**
   * Constructor of this exception.
   * 
   * @param message
   *            the message.
   */
  public UnknownSymbolException(String message) {
    super(message);
  }

}
