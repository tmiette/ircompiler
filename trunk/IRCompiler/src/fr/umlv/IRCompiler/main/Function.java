package fr.umlv.IRCompiler.main;

import java.util.ArrayList;
import java.util.List;

public class Function {
  private Class<?> returnType;

  private final ArrayList<Class<?>> args;

  private boolean isConstructor;
  
  public Function(Class<?> returnType, List<Class<?>> args) {
    this.returnType = returnType;
    this.args = new ArrayList<Class<?>>();
    for (Class<?> t : args) {
      this.args.add(t);
    }
  }
  
  public boolean isConstructor() {
    return this.isConstructor;
  }
  
  public void setConstructor(boolean isConstructor) {
    this.isConstructor = isConstructor;
  }

  public Class<?> getReturnType() {
    return this.returnType;
  }

  public List<Class<?>> getArgs() {
    return this.args;
  }

  public int getArgsCount() {
    return this.args.size();
  }

  public Class<?> getArgAtIndex(int index) {
    return this.args.get(index);
  }

  public void setReturnType(Class<?> returnType) {
    this.returnType = returnType;
  }

  public void addArguments(Class<?>... args) {
    for (Class<?> t : args) {
      this.args.add(t);
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.returnType);
    sb.append(":");
    for (Class<?> t : this.args) {
      sb.append(t);
      sb.append(",");
    }
    return sb.toString();
  }

}
