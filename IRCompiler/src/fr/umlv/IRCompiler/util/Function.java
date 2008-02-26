package fr.umlv.IRCompiler.util;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines a function which can be declared and used in the custom
 * language.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public class Function {

  // arguments type
  private final ArrayList<Class<?>> args;

  // if the function is a constructor
  private boolean isConstructor;

  // return type
  private Class<?> returnType;

  /**
   * Constructor of a function.
   * 
   * @param returnType
   *            the return type.
   * @param args
   *            the list of arguments types.
   */
  public Function(Class<?> returnType, List<Class<?>> args) {
    this.returnType = returnType;
    this.args = new ArrayList<Class<?>>();
    for (Class<?> t : args) {
      this.args.add(t);
    }
  }

  /**
   * Returns the argument type of the function at the specified index.
   * 
   * @param index
   *            the index.
   * @return the argument type.
   */
  public Class<?> getArgAtIndex(int index) {
    return this.args.get(index);
  }

  /**
   * Returns the list of arguments types of the function.
   * 
   * @return the list of arguments types of the function.
   */
  public List<Class<?>> getArgs() {
    return this.args;
  }

  /**
   * Returns the number of arguments of the function.
   * 
   * @return the number of arguments of the function.
   */
  public int getArgsCount() {
    return this.args.size();
  }

  /**
   * Returns the return type of the function.
   * 
   * @return the return type of the function.
   */
  public Class<?> getReturnType() {
    return this.returnType;
  }

  /**
   * Returns if the function is a constructor.
   * 
   * @return if the function is a constructor.
   */
  public boolean isConstructor() {
    return this.isConstructor;
  }

  /**
   * Sets if the function is a constructor.
   * 
   * @param isConstructor
   *            if the function is a constructor.
   */
  public void setConstructor(boolean isConstructor) {
    this.isConstructor = isConstructor;
  }

  /**
   * Sets the return type of the function.
   * 
   * @param returnType
   *            the return type.
   */
  public void setReturnType(Class<?> returnType) {
    this.returnType = returnType;
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
