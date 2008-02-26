package fr.umlv.IRCompiler.util;

import java.util.HashMap;
import java.util.Stack;

import fr.umlv.IRCompiler.exception.UnknownSymbolException;

/**
 * Implementation of a symbol table.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 * @param <T>
 */
public class IRCompilerSymbolTable<T> implements SymbolTable<T> {

  // stack of contexts
  private final HashMap<String, Stack<T>> symbols;

  /**
   * Default constructor.
   */
  public IRCompilerSymbolTable() {
    this.symbols = new HashMap<String, Stack<T>>();
  }

  @Override
  public T get(String name) throws UnknownSymbolException {
    Stack<T> stack = this.symbols.get(name);
    if (stack == null || stack.size() == 0) {
      throw new UnknownSymbolException("Symbol " + name + " is unknown.");
    }
    return stack.peek();
  }

  @Override
  public T pop(String name) {
    Stack<T> stack = this.symbols.get(name);
    if (stack == null) {
      return null;
    }
    return stack.pop();
  }

  @Override
  public void push(String name, T data) {
    Stack<T> stack = this.symbols.get(name);
    if (stack == null) {
      stack = new Stack<T>();
      this.symbols.put(name, stack);
    }
    stack.push(data);
  }

}
