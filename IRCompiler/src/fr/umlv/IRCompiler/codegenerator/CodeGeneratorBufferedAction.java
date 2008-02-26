package fr.umlv.IRCompiler.codegenerator;

/**
 * This interface defines an buffered action. This enable to store instructions
 * which can be called later.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public interface CodeGeneratorBufferedAction {

  /**
   * Perform the buffered action.
   */
  public void doBufferedAction();

}
