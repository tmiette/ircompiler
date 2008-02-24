package fr.umlv.IRCompiler.util;

public enum Operator {

  ADD("+", OperatorType.BINARY, false, new Class<?>[] { int.class }),
  SUB("-", OperatorType.BINARY, false, new Class<?>[] { int.class }),
  MUL("*", OperatorType.BINARY, false, new Class<?>[] { int.class }),
  DIV("/", OperatorType.BINARY, false, new Class<?>[] { int.class }),
  MIN("-", OperatorType.UNARY, false, new Class<?>[] { int.class }),
  NOT("not", OperatorType.UNARY, true, new Class<?>[] { boolean.class }),
  EQU("==", OperatorType.BINARY, true, new Class<?>[] { int.class,
      boolean.class }),
  INE("!=", OperatorType.BINARY, true, new Class<?>[] { int.class,
      boolean.class }),
  AFF("=", OperatorType.BINARY, false, new Class<?>[] { Object.class });

  public static enum OperatorType {
    UNARY,
    BINARY;
  }

  private final String name;

  private final OperatorType type;

  private final boolean booleanOperator;

  private final Class<?>[] acceptableTypes;

  private Operator(String name, OperatorType type, boolean booleanOperator,
      Class<?>[] acceptableTypes) {
    this.name = name;
    this.type = type;
    this.booleanOperator = booleanOperator;
    this.acceptableTypes = acceptableTypes;
  }

  public String getName() {
    return this.name;
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
