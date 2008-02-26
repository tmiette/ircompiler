package fr.umlv.IRCompiler.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.umlv.IRCompiler.exception.InvalidExpressionException;
import fr.umlv.IRCompiler.exception.UnknownSymbolException;

/**
 * This class owns only static methods to resolve function or method calls and
 * to validate an expression.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public class JavaClassResolver {

  /**
   * Returns the type which can include the other type between two types.
   * 
   * @param type1
   *            the first type.
   * @param type2
   *            the second type.
   * @return the higher type.
   */
  public static Class<?> getHigherType(Class<?> type1, Class<?> type2) {
    try {
      validateCast(type1, type2);
      return type1;
    } catch (InvalidExpressionException e) {
      try {
        validateCast(type2, type1);
        return type2;
      } catch (InvalidExpressionException e1) {
        return null;
      }
    }
  }

  /**
   * Tests if a class can be casted in another class.
   * 
   * @param convert
   *            conversion type.
   * @param toConvert
   *            type to convert.
   * @throws InvalidExpressionException
   *             if the cast operation is forbidden.
   */
  public static void validateCast(Class<?> convert, Class<?> toConvert)
      throws InvalidExpressionException {
    if (!convert.equals(toConvert)) {
      if (toConvert.isPrimitive()) {
        if (!validatePrimitiveCast(convert, toConvert)) {
          throw new InvalidExpressionException("Cannot convert primitive type "
              + toConvert + " to type " + convert + ".");
        }
      } else {
        try {
          toConvert.asSubclass(convert);
        } catch (ClassCastException e) {
          throw new InvalidExpressionException("Cannot convert " + toConvert
              + " to type " + convert + ".");
        }
      }
    }
  }

  /**
   * Tests if a constructor call. This method looks for the best constructor
   * which involves the minimum cast operations.
   * 
   * @param clazz
   *            the type.
   * @param args
   *            the list of arguments expected.
   * @return the constructor if exist.
   * @throws UnknownSymbolException
   *             if a such constructor doesn't exist.
   */
  public static Constructor<?> validateConstructor(Class<?> clazz,
      List<Class<?>> args) throws UnknownSymbolException {

    ArrayList<Pair<Constructor<?>, Integer>> potentialConstructor = new ArrayList<Pair<Constructor<?>, Integer>>();

    for (Constructor<?> c : clazz.getConstructors()) {
      if (c.getParameterTypes().length == args.size()) {
        potentialConstructor.add(new Pair<Constructor<?>, Integer>(c, 0));
      }
    }

    for (Iterator<Pair<Constructor<?>, Integer>> it = potentialConstructor
        .listIterator(); it.hasNext();) {
      int i = 0;
      Pair<Constructor<?>, Integer> pair = it.next();
      for (Class<?> arg : pair.getFirstElement().getParameterTypes()) {
        Class<?> callArg = args.get(i++);
        if (arg.equals(callArg)) {
          continue;
        } else {
          try {
            validateCast(arg, callArg);
            pair = new Pair<Constructor<?>, Integer>(pair.getFirstElement(),
                pair.getSecondElement() + 1);
          } catch (InvalidExpressionException e) {
            it.remove();
            break;
          }
        }
      }
    }

    if (potentialConstructor.size() > 0) {
      int min = potentialConstructor.get(0).getSecondElement();
      Constructor<?> choice = potentialConstructor.get(0).getFirstElement();
      for (int i = 2; i < potentialConstructor.size(); i++) {
        if (potentialConstructor.get(i).getSecondElement() < min) {
          min = potentialConstructor.get(i).getSecondElement();
          choice = potentialConstructor.get(i).getFirstElement();
        }
      }
      return choice;
    } else {
      throw new UnknownSymbolException("Constructor with arguments " + args
          + " cannot be resolved for the class " + clazz + ".");
    }

  }

  /**
   * Tests if an expression can be solved.
   * 
   * @param op
   *            the operator used.
   * @param type1
   *            the first type of the expression.
   * @param type2
   *            the second type of the expression.
   * @return the type of expression.
   * @throws InvalidExpressionException
   *             id the expression cannot be solved.
   */
  public static Class<?> validateExpression(Operator op, Class<?> type1,
      Class<?> type2) throws InvalidExpressionException {

    Class<?> higherClass = null;

    switch (op.getType()) {
    case UNARY:
      // invalid operator type
      if (!op.acceptType(type1)) {
        throw new InvalidExpressionException("Cannot use the operator "
            + op.getName() + " with the type " + type1);
      }
      higherClass = type1;
      break;
    case BINARY:
      // invalid operator type
      if (!op.acceptType(type1)) {
        throw new InvalidExpressionException("Cannot use the operator "
            + op.getName() + " with the types " + type1 + ", " + type2);
      }

      higherClass = getHigherType(type1, type2);
      if (higherClass == null) {
        throw new InvalidExpressionException("Cannot convert primitive type "
            + type2 + " to type " + type1 + ".");
      }
      break;
    }

    return higherClass;

  }

  /**
   * Tests if a class is iterable.
   * 
   * @param clazz
   *            the type.
   * @throws InvalidExpressionException
   *             if the type isn't iterable.
   */
  public static void validateIterable(Class<?> clazz)
      throws InvalidExpressionException {
    try {
      clazz.asSubclass(Iterable.class);
    } catch (ClassCastException e) {
      throw new InvalidExpressionException("Class " + clazz
          + " isn't iterable. Unable to perform a foreach instruction.");
    }
  }

  /**
   * Tests if a method call.This method looks for the best method which involves
   * the minimum cast operations.
   * 
   * @param clazz
   *            the type.
   * @param name
   *            the name of the method expected.
   * @param args
   *            the list of arguments expected
   * @return the constructor if exist.
   * @throws UnknownSymbolException
   *             if a such method doesn't exist.
   */
  public static Method validateMethod(Class<?> clazz, String name,
      List<Class<?>> args) throws UnknownSymbolException {

    ArrayList<Pair<Method, Integer>> potentialMethods = new ArrayList<Pair<Method, Integer>>();

    for (Method m : clazz.getMethods()) {
      if (m.getName().equals(name)
          && m.getParameterTypes().length == args.size()) {
        potentialMethods.add(new Pair<Method, Integer>(m, 0));
      }
    }

    for (Iterator<Pair<Method, Integer>> it = potentialMethods.listIterator(); it
        .hasNext();) {
      int i = 0;
      Pair<Method, Integer> pair = it.next();
      for (Class<?> arg : pair.getFirstElement().getParameterTypes()) {
        Class<?> callArg = args.get(i++);
        if (arg.equals(callArg)) {
          continue;
        } else {
          try {
            validateCast(arg, callArg);
            pair = new Pair<Method, Integer>(pair.getFirstElement(), pair
                .getSecondElement() + 1);
          } catch (InvalidExpressionException e) {
            it.remove();
            break;
          }
        }
      }
    }

    if (potentialMethods.size() > 0) {
      int min = potentialMethods.get(0).getSecondElement();
      Method choice = potentialMethods.get(0).getFirstElement();
      for (int i = 2; i < potentialMethods.size(); i++) {
        if (potentialMethods.get(i).getSecondElement() < min) {
          min = potentialMethods.get(i).getSecondElement();
          choice = potentialMethods.get(i).getFirstElement();
        }
      }
      return choice;
    } else {
      throw new UnknownSymbolException("Method " + name + " with arguments "
          + args + " cannot be resolved for the class " + clazz + ".");
    }

  }

  /**
   * Tests if a redefine method call for basic operators.
   * 
   * @param op
   *            the operator used.
   * @param type
   *            the type
   * @return if a static redefine method exists for the operator.
   * @throws UnknownSymbolException
   *             if the redefine method doesn't exist.
   */
  public static Class<?> validateOperationMethod(Operator op, Class<?> type)
      throws UnknownSymbolException {

    if (op.getMethodName().equals("equals")) {
      return boolean.class;
    }

    ArrayList<Class<?>> args = new ArrayList<Class<?>>();
    args.add(type);
    args.add(type);

    Method m = validateMethod(type, op.getMethodName(), args);

    if (!m.getReturnType().equals(type)) {
      throw new UnknownSymbolException("Method " + op.getMethodName()
          + " with arguments " + args + " cannot be resolved for the class "
          + type + ".");
    }

    return type;
  }

  private static boolean validatePrimitiveCast(Class<?> convert,
      Class<?> toConvert) {
    if (toConvert.equals(int.class)) {
      return !convert.equals(boolean.class);
    } else if (toConvert.equals(float.class)) {
      return convert.equals(double.class);
    } else if (toConvert.equals(double.class)) {
      return false;
    } else if (toConvert.equals(boolean.class)) {
      return false;
    } else {
      return false;
    }
  }

  public static void validateStringExpression(Operator op, Class<?> type1,
      Class<?> type2) throws InvalidExpressionException {
    if (!op.acceptType(String.class)) {
      throw new InvalidExpressionException("Cannot use the operator "
          + op.getName() + " with the type " + type1 + " and " + type2 + ".");
    }
  }
}
