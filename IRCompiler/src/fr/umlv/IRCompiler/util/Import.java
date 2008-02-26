package fr.umlv.IRCompiler.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import fr.umlv.IRCompiler.exception.UnresolvedClassException;

/**
 * This class manages a list of imported classes and interfaces.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public class Import {

  // map the simple class name with the complete type
  private final HashMap<String, Class<?>> identifiers;

  // list of imported types
  private final ArrayList<Class<?>> imports;

  /**
   * Default constructor.
   */
  public Import() {
    this.imports = new ArrayList<Class<?>>();
    this.identifiers = new HashMap<String, Class<?>>();
  }

  /**
   * Tests if a simple class name is imported and returns it.
   * 
   * @param identifier
   *            the simple class name.
   * @return the class.
   * @throws UnresolvedClassException
   *             if a such class isn't already imported.
   */
  public Class<?> getClass(String identifier) throws UnresolvedClassException {
    if (!isImported(identifier)) {
      throw new UnresolvedClassException("Class " + identifier
          + " cannot be resolved.");
    }
    return this.identifiers.get(identifier);
  }

  /**
   * Tests if a simple class name is imported.
   * 
   * @param identifier
   *            the simple class name
   * @return if the simple class name is imported.
   */
  public boolean isImported(String identifier) {
    return this.identifiers.get(identifier) != null;
  }

  /**
   * Tests if a class exist in the standard java API with the specified package
   * name.
   * 
   * @param packageDeclaration
   *            list of the packages of the class.
   * @throws UnresolvedClassException
   *             if a such type doesn't exist.
   */
  public void validateImport(List<String> packageDeclaration)
      throws UnresolvedClassException {

    final StringBuilder sb = new StringBuilder();
    for (final Iterator<String> it = packageDeclaration.iterator(); it
        .hasNext();) {
      sb.append(it.next());
      if (it.hasNext()) {
        sb.append(".");
      }
    }

    try {
      String className = sb.toString();
      Class<?> clazz = Class.forName(className);
      this.imports.add(clazz);
      this.identifiers.put(className.substring(className.lastIndexOf(".") + 1,
          className.length()), clazz);
    } catch (ClassNotFoundException e) {
      throw new UnresolvedClassException("Class " + sb.toString()
          + " cannot be resolved to a type.");
    }
  }

}
