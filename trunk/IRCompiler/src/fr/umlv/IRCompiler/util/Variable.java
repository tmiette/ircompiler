package fr.umlv.IRCompiler.util;

/**
 * This class represents a variable that can be declared and used in the custom
 * language.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public class Variable {

  // counter of variables created
  private static int variableIds = 0;

  // the declared type
  private final Class<?> declaredClass;

  // id
  private final int id;

  // of the variable is global
  private boolean isGlobal;

  // the register to store this variable
  private int register;

  /**
   * Constructor of a variable.
   * 
   * @param declaredClass
   *            the declared type of the variable.
   */
  public Variable(Class<?> declaredClass) {
    this.id = variableIds++;
    this.declaredClass = declaredClass;
    this.isGlobal = false;
  }

  /**
   * Return the declared type of this variable.
   * 
   * @return the declared type of this variable.
   */
  public Class<?> getDeclaredClass() {
    return this.declaredClass;
  }

  /**
   * Return the id of this variable.
   * 
   * @return the id of this variable.
   */
  public int getId() {
    return this.id;
  }

  /**
   * Returns the register in which the variable is stored.
   * 
   * @return the register in which the variable is stored.
   */
  public int getRegister() {
    return this.register;
  }

  /**
   * Tests if this variable is global.
   * 
   * @return if this varible is global.
   */
  public boolean isGlobal() {
    return this.isGlobal;
  }

  /**
   * Sets if this variable is global.
   * 
   * @param isGlobal
   *            if this variable is global.
   */
  public void setGlobal(boolean isGlobal) {
    this.isGlobal = isGlobal;
  }

  /**
   * Sets the register in which the variable is stored.
   * 
   * @param register
   *            the register in which the variable is stored.
   */
  public void setRegister(int register) {
    this.register = register;
  }

  @Override
  public String toString() {
    return "name" + ":" + declaredClass;
  }

}
