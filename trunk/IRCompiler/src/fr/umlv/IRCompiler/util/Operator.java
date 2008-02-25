package fr.umlv.IRCompiler.util;

public enum Operator {

  ADD("add(+)", "plus", OperatorType.BINARY, false, new Class<?>[] { int.class,
      float.class, double.class }),
  SUB("sub(-)", "minus", OperatorType.BINARY, false, new Class<?>[] {
      int.class, float.class, double.class }),
  MUL("mult(*)", "mult", OperatorType.BINARY, false, new Class<?>[] {
      int.class, float.class, double.class }),
  DIV("div(/)", "div", OperatorType.BINARY, false, new Class<?>[] { int.class,
      float.class, double.class }),
  MIN("min(-)", null, OperatorType.UNARY, false, new Class<?>[] { int.class,
      float.class, double.class }),
  POW("pow(^)", "exp", OperatorType.BINARY, false, new Class<?>[] {}),
  NOT("not", null, OperatorType.UNARY, true, new Class<?>[] { boolean.class }),
  EQU("equal(==)", "equals", OperatorType.BINARY, true, new Class<?>[] {
      int.class, boolean.class }),
  INE("inequal(!=)", "equals", OperatorType.BINARY, true, new Class<?>[] {
      int.class, boolean.class });

  public static enum OperatorType {
    UNARY,
    BINARY;
  }

  private final String name;

  private final String methodName;

  private final OperatorType type;

  private final boolean booleanOperator;

  private final Class<?>[] acceptableTypes;

  private Operator(String name, String methodName, OperatorType type,
      boolean booleanOperator, Class<?>[] acceptableTypes) {
    this.name = name;
    this.methodName = methodName;
    this.type = type;
    this.booleanOperator = booleanOperator;
    this.acceptableTypes = acceptableTypes;
  }

  public String getName() {
    return this.name;
  }

  public String getMethodName() {
    return this.methodName;
  }

  public boolean isBooleanOperator() {
    return this.booleanOperator;
  }

  public OperatorType getType() {
    return this.type;
  }

  public boolean acceptType(Class<?> type) {
    for (Class<?> t : this.acceptableTypes) {
      if (t.equals(Object.class)) {
        return true;
      }
      if (t.equals(type)) {
        return true;
      }
    }
    return false;
  }

}
