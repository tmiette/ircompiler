package fr.umlv.IRCompiler.exception;

/**
 * This exception is thrown when a class or interface ins't imported or cannot
 * be imported.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public class UnresolvedClassException extends Exception {

  private static final long serialVersionUID = 8853445502692559210L;

  /**
   * Constructor of this exception.
   * 
   * @param message
   *            the message.
   */
  public UnresolvedClassException(String message) {
    super(message);
  }

}
