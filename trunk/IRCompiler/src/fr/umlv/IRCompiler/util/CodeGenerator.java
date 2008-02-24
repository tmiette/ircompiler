package fr.umlv.IRCompiler.util;

import java.util.List;

public interface CodeGenerator {

  public byte[] getByteArray();

  public void visitStart();

  public void visitEnd();

  public void visitVariableDeclaration(String name, Variable var);

  public void visitVariableAssignment(String name, Variable var);
  
  public void visitVariableUsage(String name, Variable var);

  public void visitOperation(Operator op, Class<?> operationType);

  public void visitIntegerValue(int value);

  public void visitBooleanValue(boolean value);
  
  public void visitNullValue();

  public void visitPrintStart();

  public void visitPrintEnd(Class<?> typeToPrint);

  public void visitIfConditionalStart();

  public void visitIfConditionalEnd();

  public void visitElseConditionalStart();

  public void visitElseConditionalEnd();

  public void visitConstructorStart(Class<?> clazz);

  public void visitConstructorEnd(Function cons);

  public void visitMethod(Class<?> type, String name, List<Class<?>> args, Class<?> returnType);
  
  public void visitFunctionStart(String name, Function funtion);
  
  public void visitReturn(Class<?> returnType);
  
  public void visitFunctionEnd(String name, Function funtion);
  
  public void visitFunction(String name, Function funtion);

}
