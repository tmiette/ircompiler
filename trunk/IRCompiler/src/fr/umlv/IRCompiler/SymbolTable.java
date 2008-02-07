package fr.umlv.IRCompiler;


public interface SymbolTable<T> {

  public void push(String env, T data);

  public T pop(String env);

  public T get(String env) throws UnknownSymbolException;

}
