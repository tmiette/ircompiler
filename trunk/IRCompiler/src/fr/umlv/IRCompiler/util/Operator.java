package fr.umlv.IRCompiler.util;

/**
 * This enum lists all operators used in the custom language.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public enum Operator {

  ADD("add(+)", "plus", OperatorType.BINARY, new Class<?>[] { int.class,
      float.class, double.class, String.class }),
  DIV("div(/)", "div", OperatorType.BINARY, new Class<?>[] { int.class,
      float.class, double.class }),
  EQU("equal(==)", "equals", OperatorType.BINARY, new Class<?>[] { int.class,
      boolean.class }),
  INE("inequal(!=)", "equals", OperatorType.BINARY, new Class<?>[] { int.class,
      boolean.class }),
  MIN("min(-)", null, OperatorType.UNARY, new Class<?>[] { int.class,
      float.class, double.class }),
  MUL("mult(*)", "mult", OperatorType.BINARY, new Class<?>[] { int.class,
      float.class, double.class }),
  NOT("not", null, OperatorType.UNARY, new Class<?>[] { boolean.class }),
  POW("pow(^)", "exp", OperatorType.BINARY, new Class<?>[] {}),
  SUB("sub(-)", "minus", OperatorType.BINARY, new Class<?>[] { int.class,
      float.class, double.class });

  /**
   * This enum defines if an operator is binary or unary.
   * 
   * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
   * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
   * 
   */
  public static enum OperatorType {
    BINARY,
    UNARY;
  }

  // types that this operator manages
  private final Class<?>[] acceptableTypes;

  // name of the redefine method of the operator
  private final String methodName;

  // name of the operator
  private final String name;

  // type
  private final OperatorType type;

  /**
   * Constructor of an operator
   * 
   * @param name
   *            the name.
   * @param methodName
   *            the redefine method.
   * @param type
   *            the type.
   * @param acceptableTypes
   *            list of acceptables types.
   */
  private Operator(String name, String methodName, OperatorType type,
      Class<?>[] acceptableTypes) {
    this.name = name;
    this.methodName = methodName;
    this.type = type;
    this.acceptableTypes = acceptableTypes;
  }

  /**
   * Tests if this operator accept a type.
   * 
   * @param type
   *            the type to test.
   * @return if this operator accept this type.
   */
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

  /**
   * Return the redefine method of this operator.
   * 
   * @return the redefine method of this operator.
   */
  public String getMethodName() {
    return this.methodName;
  }

  /**
   * Returns the name of the operator.
   * 
   * @return the name of the operator.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the type of this operator.
   * 
   * @return the type of this operator.
   */
  public OperatorType getType() {
    return this.type;
  }

}
