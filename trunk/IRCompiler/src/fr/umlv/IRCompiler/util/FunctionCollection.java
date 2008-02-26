package fr.umlv.IRCompiler.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.umlv.IRCompiler.exception.InvalidExpressionException;
import fr.umlv.IRCompiler.exception.SymbolAlreadyDefinedException;
import fr.umlv.IRCompiler.exception.UnknownSymbolException;

/**
 * This class represents all the functions used during the compilation. This
 * class enables to add a new function and to get a function depending on
 * parameters.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public class FunctionCollection {

  // list of funtions
  private final HashMap<String, ArrayList<Function>> functions;

  /**
   * Default constructor.
   */
  public FunctionCollection() {
    this.functions = new HashMap<String, ArrayList<Function>>();
  }

  /**
   * Adds a new function to the collection. If the function has the same name
   * but different arguments than another function already declared, the
   * function is added.
   * 
   * @param name
   *            the name of the function.
   * @param returnType
   *            the return type of the function.
   * @param args
   *            the list of arguments of the function.
   * @return the function added
   * @throws SymbolAlreadyDefinedException
   *             if a function is already defined with the same name and the
   *             same arguments list.
   */
  public Function addFunction(String name, Class<?> returnType,
      List<Class<?>> args) throws SymbolAlreadyDefinedException {

    try {
      getFuntion(name, args);
      throw new SymbolAlreadyDefinedException("Function " + name
          + " with arguments " + args + " is already defined.");
    } catch (UnknownSymbolException e) {
      // do nothing
    }

    ArrayList<Function> functions = this.functions.get(name);
    if (functions == null) {
      functions = new ArrayList<Function>();
      this.functions.put(name, functions);
    }
    Function f = new Function(returnType, args);
    functions.add(f);
    return f;
  }

  /**
   * Returns a function containing on this collection or null.
   * 
   * @param name
   *            the name of the expected function.
   * @param args
   *            the list of arguments of the expected function.
   * @return the function expected.
   * @throws UnknownSymbolException
   *             if a such function isn't defined.
   */
  private Function getFuntion(String name, List<Class<?>> args)
      throws UnknownSymbolException {

    ArrayList<Function> functions = this.functions.get(name);
    if (functions == null) {
      throw new UnknownSymbolException("Function " + name + " isn't defined.");
    }

    boolean correct = true;
    for (Function f : functions) {
      for (int i = 0; i < f.getArgsCount(); i++) {
        try {
          JavaClassResolver.validateCast(f.getArgAtIndex(i), args.get(i));
        } catch (InvalidExpressionException e) {
          correct = false;
          break;
        }
      }
      if (correct) {
        return f;
      }
      correct = true;
    }

    throw new UnknownSymbolException("Function " + name
        + " isn't defined with arguments : " + args + ".");
  }

  /**
   * Tests if a function is a constructor and return the type corresponding.
   * 
   * @param name
   *            the name of the expected constructor.
   * @return the return type of the constructor or null.
   */
  public Class<?> isConstructor(String name) {
    if (this.functions.get(name) == null) {
      return null;
    }
    if (this.functions.get(name).get(0).isConstructor()) {
      return this.functions.get(name).get(0).getReturnType();
    }
    return null;
  }

  /**
   * Tests if a function is already defined with the specified parameters.
   * 
   * @param name
   *            the name of the expected function.
   * @param args
   *            the list of arguments of the expected function.
   * @return the function expected.
   * @throws UnknownSymbolException
   *             if a such function isn't defined.
   */
  public Function validateFunctionCall(String name, List<Class<?>> args)
      throws UnknownSymbolException {
    Function f = getFuntion(name, args);

    if (f.getArgsCount() != args.size()) {
      throw new UnknownSymbolException("The function " + name + " expects "
          + f.getArgsCount() + " arguments (find " + args.size() + ").");
    }

    return f;
  }

}
