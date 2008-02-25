package fr.umlv.IRCompiler.main;

import java.util.HashMap;
import java.util.Stack;

public class IRCompilerSymbolTable<T> implements SymbolTable<T> {

  private final HashMap<String, Stack<T>> symbols;

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
