package fr.umlv.IRCompiler.codegenerator;

import java.util.List;

import fr.umlv.IRCompiler.util.Function;
import fr.umlv.IRCompiler.util.Operator;
import fr.umlv.IRCompiler.util.Variable;

/**
 * This interface defines a code generator which can be used in the code
 * generation visitor of the custom language.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public interface CodeGenerator {

  /**
   * Returns the bytes of code generation.
   * 
   * @return the bytes of code generation.
   */
  public byte[] getByteArray();

  /**
   * This method generates the code of a list of arguments.
   * 
   * @param exceptedClasses
   *            the types expected.
   * @param classes
   *            the real types.
   * @param registers
   *            the registers which can be used to store variables.
   */
  public void visitArgsCast(List<Class<?>> exceptedClasses,
      List<Class<?>> classes, List<Integer> registers);

  /**
   * This method generates the boolean creation code.
   * 
   * @param value
   *            the boolean.
   */
  public void visitBooleanValue(boolean value);

  /**
   * This method id called after a constructor call.
   * 
   * @param cons
   *            the constructor function.
   */
  public void visitConstructorEnd(Function cons);

  /**
   * This method is called before a constructor call.
   * 
   * @param clazz
   *            the constructor type.
   */
  public void visitConstructorStart(Class<?> clazz);

  /**
   * This method generates the double creation code.
   * 
   * @param value
   *            the double.
   */
  public void visitDoubleValue(double value);

  /**
   * This method is called after a else statement.
   */
  public void visitElseConditionalEnd();

  /**
   * This method is called before a else statement.
   */
  public void visitElseConditionalStart();

  /**
   * This method is called after the code generation.
   */
  public void visitEnd();

  /**
   * This method generates the equals method call code.
   * 
   * @param type
   *            the type calling equals.
   */
  public void visitEqualsMethod(Class<?> type);

  /**
   * This method generates the float creation code.
   * 
   * @param value
   *            the float.
   */
  public void visitFloatValue(float value);

  /**
   * This method generates a function call code.
   * 
   * @param name
   *            the name of the function.
   * @param funtion
   *            the function.
   */
  public void visitFunction(String name, Function funtion);

  /**
   * This method is called after a function declaration.
   * 
   * @param name
   *            the name of the function.
   * @param funtion
   *            the function.
   */
  public void visitFunctionEnd(String name, Function funtion);

  /**
   * This method is called before a function declaration.
   * 
   * @param name
   *            the name of the function.
   * @param funtion
   *            the function.
   */
  public void visitFunctionStart(String name, Function funtion);

  /**
   * This method is called after a condition statement.
   */
  public void visitIfConditionalEnd();

  /**
   * This method is called before a condition statement.
   */
  public void visitIfConditionalStart();

  /**
   * This method generates the integer creation code.
   * 
   * @param value
   *            the integer.
   */
  public void visitIntegerValue(int value);

  /**
   * This method is called after a loop operation.
   * 
   * @param iterableClass
   *            the iterable class.
   * @param iteratedClass
   *            the class iterated.
   * @param iteratorRegister
   *            the register in which store the iterator.
   * @param iteratedRegister
   *            the register in which store the iterated object.
   */
  public void visitLoopEnd(Class<?> iterableClass, Class<?> iteratedClass,
      int iteratorRegister, int iteratedRegister);

  /**
   * This method is called before a loop operation.
   * 
   * @param iterableClass
   *            the iterable class.
   * @param iteratedClass
   *            the class iterated.
   * @param iteratorRegister
   *            the register in which store the iterator.
   * @param iteratedRegister
   *            the register in which store the iterated object.
   */
  public void visitLoopStart(Class<?> iterableClass, Class<?> iteratedClass,
      int iteratorRegister, int iteratedRegister);

  /**
   * This method generates a method call code.
   * 
   * @param type
   *            the type calling the method.
   * @param name
   *            the of the method.
   * @param args
   *            the list of the arguments of the method.
   * @param returnType
   *            the return type of the method.
   */
  public void visitMethod(Class<?> type, String name, List<Class<?>> args,
      Class<?> returnType);

  /**
   * This method generates the null creation code.
   */
  public void visitNullValue();

  /**
   * This method generates a basic operation code.
   * 
   * @param op
   *            the operator.
   * @param operationType
   *            the operation type.
   */
  public void visitOperation(Operator op, Class<?> operationType);

  /**
   * This method generates the code of a primitive cast operation.
   * 
   * @param cast
   *            the cast type.
   * @param toCast
   *            the type to cast.
   */
  public void visitPrimitiveCast(Class<?> cast, Class<?> toCast);

  /**
   * This method is called after a print operation.
   * 
   * @param typeToPrint
   *            type of the print operation.
   */
  public void visitPrintEnd(Class<?> typeToPrint);

  /**
   * This method is called before a print operation.
   */
  public void visitPrintStart();

  /**
   * This method generates a return call code.
   * 
   * @param returnType
   *            the return type.
   */
  public void visitReturn(Class<?> returnType);

  /**
   * This method is called before the code generation.
   */
  public void visitStart();

  /**
   * This method generates a static method call code.
   * 
   * @param type
   *            the type calling the method.
   * @param name
   *            the of the method.
   * @param args
   *            the list of the arguments of the method.
   * @param returnType
   *            the return type of the method.
   */
  public void visitStaticMethod(Class<?> type, String name,
      List<Class<?>> args, Class<?> returnType);

  /**
   * This method generates a string concatenation code.
   * 
   * @param classes
   * @param registers
   */
  public void visitStringConcatenation(List<Class<?>> classes,
      List<Integer> registers);

  /**
   * This method generates the string creation code.
   * 
   * @param value
   *            the string.
   */
  public void visitStringValue(String value);

  /**
   * This method generates a variable assignment code.
   * 
   * @param name
   *            the variable name.
   * @param var
   *            the variable.
   */
  public void visitVariableAssignment(String name, Variable var);

  /**
   * This method generates a variable declaration code.
   * 
   * @param name
   *            the variable name.
   * @param var
   *            the variable.
   */
  public void visitVariableDeclaration(String name, Variable var);

  /**
   * This method generates a variable usage.
   * 
   * @param name
   *            the variable name.
   * @param var
   *            the variable.
   */
  public void visitVariableUsage(String name, Variable var);

}
