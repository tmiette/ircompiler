package fr.umlv.IRCompiler.util;


public interface SymbolTable<T> {

  public void push(String name, T data);

  public T pop(String name);

  public T get(String name) throws UnknownSymbolException;

}
