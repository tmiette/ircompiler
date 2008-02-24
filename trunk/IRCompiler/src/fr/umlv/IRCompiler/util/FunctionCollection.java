package fr.umlv.IRCompiler.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FunctionCollection {

  private final HashMap<String, ArrayList<Function>> functions;

  public FunctionCollection() {
    this.functions = new HashMap<String, ArrayList<Function>>();
  }

  public Class<?> isConstructor(String name) {
    if (this.functions.get(name) == null) {
      return null;
    }
    if (this.functions.get(name).get(0).isConstructor()) {
      return this.functions.get(name).get(0).getReturnType();
    }
    return null;
  }

  public Function addFunction(String name, Class<?> returnType,
      List<Class<?>> args) throws SymbolAlreadyUsedException {

    try {
      getFuntion(name, args);
      throw new SymbolAlreadyUsedException("Function " + name
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

  public Function getFuntion(String name, List<Class<?>> args)
      throws UnknownSymbolException {

    ArrayList<Function> functions = this.functions.get(name);
    if (functions == null) {
      throw new UnknownSymbolException("Function " + name + " isn't defined.");
    }

    for (Function f : functions) {
      if (f.getArgs().equals(args)) {
        return f;
      }
    }

    throw new UnknownSymbolException("Function " + name
        + " isn't defined with arguments : " + args + ".");
  }

  public Function validateFunctionCall(String name, List<Class<?>> args)
      throws UnknownSymbolException, UnexpectedTypeException {
    Function f = getFuntion(name, args);

    if (f.getArgsCount() != args.size()) {
      throw new UnknownSymbolException("The function " + name + " expects "
          + f.getArgsCount() + " arguments (find " + args.size() + ").");
    }

    int count = f.getArgsCount();
    for (int i = 0; i < count; i++) {
      if (!args.get(i).equals(f.getArgAtIndex(i))) {
        throw new UnexpectedTypeException("The " + i + 1
            + " argument of function " + name + " expects a"
            + f.getArgAtIndex(i) + " (find " + args.get(i) + ").");
      }
    }
    return f;
  }

}
