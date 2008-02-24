package fr.umlv.IRCompiler.util;

public class Variable {

  private static int variableIds = 0;

  private final int id;

  private final Class<?> declaredClass;

  private boolean isGlobal;

  private int register;

  public Variable(Class<?> declaredClass) {
    this.id = variableIds++;
    this.declaredClass = declaredClass;
    this.isGlobal = false;
  }

  public int getRegister() {
    return this.register;
  }

  public void setRegister(int register) {
    this.register = register;
  }

  public boolean isGlobal() {
    return this.isGlobal;
  }

  public void setGlobal(boolean isGlobal) {
    this.isGlobal = isGlobal;
  }

  public int getId() {
    return this.id;
  }

  public Class<?> getDeclaredClass() {
    return this.declaredClass;
  }

  @Override
  public String toString() {
    return "name" + ":" + declaredClass;
  }

}
