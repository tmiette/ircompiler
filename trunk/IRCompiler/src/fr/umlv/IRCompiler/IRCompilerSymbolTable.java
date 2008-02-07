package fr.umlv.IRCompiler;

import java.util.HashMap;
import java.util.Stack;



public class IRCompilerSymbolTable<T> implements SymbolTable<T> {

  private final HashMap<String, Stack<T>> symbols;

  public IRCompilerSymbolTable() {
    this.symbols = new HashMap<String, Stack<T>>();
  }

  @Override
  public T get(String s) throws UnknownSymbolException {
    Stack<T> stack = this.symbols.get(s);
    if (stack == null || stack.size() == 0) {
      throw new UnknownSymbolException("");
    }
    return stack.peek();
  }

  @Override
  public T pop(String s) {
    Stack<T> stack = this.symbols.get(s);
    if (stack == null) {
      return null;
    }
    return stack.pop();
  }

  @Override
  public void push(String s, T data) {
    Stack<T> stack = this.symbols.get(s);
    if (stack == null) {
      stack = new Stack<T>();
      this.symbols.put(s, stack);
    }
    stack.push(data);
  }

}
