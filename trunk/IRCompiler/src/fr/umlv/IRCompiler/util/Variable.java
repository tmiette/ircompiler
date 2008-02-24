package fr.umlv.IRCompiler.util;

public class Variable {

  private static int variableIds = 0;

  private final int id;

  private final Class<?> clazz;

  private boolean isGlobal;

  private int register;

  public Variable(Class<?> clazz) {
    this.id = variableIds++;
    this.clazz = clazz;
    this.isGlobal = false;
    System.out.println("var " + register + " " + clazz);
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

  public Class<?> getClazz() {
    return this.clazz;
  }

  @Override
  public String toString() {
    return "name" + ":" + clazz;
  }

}
