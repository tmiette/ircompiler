package fr.umlv.IRCompiler.exception;

/**
 * This exception is thrown when a wrong type is found when expecting antoher
 * type.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public class UnexpectedTypeException extends Exception {

  private static final long serialVersionUID = 2473201912981276842L;

  /**
   * Constructor of this exception.
   * 
   * @param message
   *            the message.
   */
  public UnexpectedTypeException(String message) {
    super(message);
  }

}
