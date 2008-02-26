package fr.umlv.IRCompiler.exception;

/**
 * This exception is thrown when a symbol is declared two times.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public class SymbolAlreadyDefinedException extends Exception {

  private static final long serialVersionUID = -8506502681301966757L;

  /**
   * Constructor of this exception.
   * 
   * @param message
   *            the message.
   */
  public SymbolAlreadyDefinedException(String message) {
    super(message);
  }

}
