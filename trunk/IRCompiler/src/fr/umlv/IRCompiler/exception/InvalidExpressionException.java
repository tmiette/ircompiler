package fr.umlv.IRCompiler.exception;

/**
 * This exception is thrown when an expression cannot be solved.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public class InvalidExpressionException extends Exception {

  private static final long serialVersionUID = 5716647090569727173L;

  /**
   * Constructor of this exception.
   * 
   * @param message
   *            the message.
   */
  public InvalidExpressionException(String message) {
    super(message);
  }

}
