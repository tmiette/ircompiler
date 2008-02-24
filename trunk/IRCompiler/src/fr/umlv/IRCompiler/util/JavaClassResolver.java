package fr.umlv.IRCompiler.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JavaClassResolver {

  public static Constructor<?> validateConstructor(Class<?> clazz, List<Class<?>> args)
      throws UnknownSymbolException, SymbolAlreadyUsedException {

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
            callArg.asSubclass(arg);
            pair = new Pair<Constructor<?>, Integer>(pair.getFirstElement(),
                pair.getSecondElement() + 1);
          } catch (ClassCastException e) {
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

  public static Class<?> validateMethod(Class<?> clazz, String name,
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
            callArg.asSubclass(arg);
            pair = new Pair<Method, Integer>(pair.getFirstElement(), pair
                .getSecondElement() + 1);
          } catch (ClassCastException e) {
            it.remove();
            continue;
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
      return choice.getReturnType();
    } else {
      throw new UnknownSymbolException("Method " + name + " with arguments "
          + args + " cannot be resolved for the class " + clazz + ".");
    }

  }

  public static Class<?> validateExpression(Operator op, Class<?> type1,
      Class<?> type2) throws InvalidExpressionException {

    switch (op.getType()) {
    case UNARY:
      // invalid operator type
      if (!op.acceptType(type1)) {
        throw InvalidExpressionException.throwNewException(op, type1);
      }
      break;
    case BINARY:
      // invalid operator type
      if (!op.acceptType(type1)) {
        throw InvalidExpressionException.throwNewException(op, type1, type2);
      }

      if (!type1.equals(type2)) {
        try {
          type2.asSubclass(type1);
        } catch (ClassCastException e) {
          throw new InvalidExpressionException("Cannot convert " + type2
              + " to type " + type1 + ".");
        }
      }
      break;
    }

    if (op.isBooleanOperator()) {
      return boolean.class;
    } else {
      return type1;
    }

  }
}
