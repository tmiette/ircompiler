package fr.umlv.IRCompiler.util;

import fr.umlv.IRCompiler.exception.UnknownSymbolException;

/**
 * This class manage the successive contexts of a symbol during the compilation.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 * @param <T>
 *            symbol table data.
 */
public interface SymbolTable<T> {

  /**
   * Get a context for a symbol.
   * 
   * @param name
   *            the symbol.
   * @return the context.
   * @throws UnknownSymbolException
   *             if a such symbol isn't defined.
   */
  public T get(String name) throws UnknownSymbolException;

  /**
   * Remove a context for the symbol.
   * 
   * @param name
   *            the symbol.
   * @return the context.
   */
  public T pop(String name);

  /**
   * Add a new context for the symbol.
   * 
   * @param name
   *            the symbol.
   * @param data
   *            the new context.
   */
  public void push(String name, T data);

}
