package fr.umlv.IRCompiler.codegenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import fr.umlv.IRCompiler.util.Function;
import fr.umlv.IRCompiler.util.Operator;
import fr.umlv.IRCompiler.util.Variable;

/**
 * This class is an implementation of a code generator and generates byte code
 * interpreted by the java virtual machine.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public class AsmCodeGenerator implements CodeGenerator, Opcodes {

  // map to store operation codes
  private final static HashMap<Class<?>, String> classesOpCodes = new HashMap<Class<?>, String>();
  static {
    classesOpCodes.put(int.class, "I");
    classesOpCodes.put(boolean.class, "Z");
    classesOpCodes.put(void.class, "V");
    classesOpCodes.put(double.class, "D");
    classesOpCodes.put(float.class, "F");
  }

  /**
   * Returns a list of operation codes.
   * 
   * @param args
   *            the types.
   * @return the list of operation codes.
   */
  private static String getArgsOpCode(List<Class<?>> args) {
    StringBuilder sb = new StringBuilder();
    for (Class<?> c : args) {
      sb.append(getClassOpCode(c));
    }
    return sb.toString();
  }

  /**
   * Returns a byte code class name.
   * 
   * @param clazz
   *            the type.
   * @return the byte code class name
   */
  private static String getClassName(Class<?> clazz) {
    return clazz.getName().replaceAll("\\.", "/");
  }

  /**
   * Returns a operation code for a class.
   * 
   * @param clazz
   *            the type.
   * @return the operation code.
   */
  private static String getClassOpCode(Class<?> clazz) {
    String opCode = classesOpCodes.get(clazz);
    if (opCode == null) {
      String className = getClassName(clazz);
      opCode = "L" + className + ";";
    }
    return opCode;
  }

  /**
   * Returns the simple operation code for a class.
   * 
   * @param clazz
   *            the type.
   * @return the simple operation code.
   */
  private static String getLoadStoreOpCode(Class<?> clazz) {
    String opCode = classesOpCodes.get(clazz);
    if (opCode == null) {
      opCode = "A";
    }
    if (clazz.equals(boolean.class)) {
      opCode = "I";
    }
    return opCode;
  }

  // stack of conditional jumps labels
  private final ArrayList<Label> conditionalJumps;
  // class writer
  private final ClassWriter cw;

  // static fields visitor
  private FieldVisitor fv;

  // stack of loop jumps labels
  private final ArrayList<Label> loopJumps;

  // main class name
  private final String mainClassName;

  // method visitor
  private MethodVisitor mv;

  /**
   * Constructor.
   * 
   * @param outputFileName
   *            the file in which byte code will be put.
   */
  public AsmCodeGenerator(String outputFileName) {
    this.mainClassName = outputFileName.substring(outputFileName
        .lastIndexOf("/") + 1, outputFileName.lastIndexOf('.'));
    this.conditionalJumps = new ArrayList<Label>();
    this.loopJumps = new ArrayList<Label>();
    this.cw = new ClassWriter(1);
  }

  @Override
  public byte[] getByteArray() {
    return cw.toByteArray();
  }

  @Override
  public void visitArgsCast(List<Class<?>> exceptedClasses,
      List<Class<?>> classes, List<Integer> registers) {

    for (int i = exceptedClasses.size() - 1; i >= 0; i--) {
      Class<?> c = classes.get(i);
      try {
        int code = getClass().getField(getLoadStoreOpCode(c) + "STORE").getInt(
            getClass());
        mv.visitVarInsn(code, registers.get(i));
      } catch (SecurityException e) {
        throw new AssertionError("Operation code doesn't exist.");
      } catch (NoSuchFieldException e) {
        throw new AssertionError("Operation code doesn't exist.");
      } catch (IllegalAccessException e) {
        throw new AssertionError("Operation code doesn't exist.");
      }
    }

    for (int i = 0; i < exceptedClasses.size(); i++) {
      Class<?> c = classes.get(i);
      Class<?> e = exceptedClasses.get(i);
      try {
        int code = getClass().getField(getLoadStoreOpCode(c) + "LOAD").getInt(
            getClass());
        mv.visitVarInsn(code, registers.get(i));
        if (!e.equals(c) && c.isPrimitive()) {
          visitPrimitiveCast(e, c);
        }
      } catch (SecurityException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      } catch (NoSuchFieldException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      } catch (IllegalAccessException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      }
    }

  }

  @Override
  public void visitBooleanValue(boolean value) {
    if (value == false) {
      mv.visitInsn(ICONST_0);
    } else {
      mv.visitInsn(ICONST_1);
    }
  }

  /**
   * Generates code for a compare.
   * 
   * @param code
   *            operation code.
   */
  private void visitCompare(int code) {
    Label l0 = new Label();
    Label l1 = new Label();
    mv.visitJumpInsn(code, l0);
    mv.visitInsn(ICONST_1);
    mv.visitJumpInsn(GOTO, l1);
    mv.visitLabel(l0);
    mv.visitInsn(ICONST_0);
    mv.visitLabel(l1);
  }

  @Override
  public void visitConstructorEnd(Function cons) {
    mv.visitMethodInsn(INVOKESPECIAL, getClassName(cons.getReturnType()),
        "<init>", "(" + getArgsOpCode(cons.getArgs()) + ")"
            + getClassOpCode(void.class));
  }

  @Override
  public void visitConstructorStart(Class<?> clazz) {
    mv.visitTypeInsn(NEW, getClassName(clazz));
    mv.visitInsn(DUP);
  }

  @Override
  public void visitDoubleValue(double value) {
    mv.visitLdcInsn(new Double(value));
  }

  @Override
  public void visitElseConditionalEnd() {
    mv.visitLabel(this.conditionalJumps
        .remove(this.conditionalJumps.size() - 1));
  }

  @Override
  public void visitElseConditionalStart() {
  }

  @Override
  public void visitEnd() {
    visitMainMethodEnd();
    cw.visitEnd();
  }

  @Override
  public void visitEqualsMethod(Class<?> type) {
    mv.visitMethodInsn(INVOKEVIRTUAL, getClassName(type), "equals",
        "(Ljava/lang/Object;)Z");
  }

  @Override
  public void visitFloatValue(float value) {
    mv.visitLdcInsn(new Float(value));
  }

  @Override
  public void visitFunction(String name, Function function) {
    mv.visitMethodInsn(INVOKESTATIC, this.mainClassName, name, "("
        + getArgsOpCode(function.getArgs()) + ")"
        + getClassOpCode(function.getReturnType()));
  }

  @Override
  public void visitFunctionEnd(String name, Function function) {
    mv.visitMaxs(0, 0);
    mv.visitEnd();
  }

  @Override
  public void visitFunctionStart(String name, Function function) {
    mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, name, "("
        + getArgsOpCode(function.getArgs()) + ")"
        + getClassOpCode(function.getReturnType()), null, null);
    mv.visitCode();
  }

  @Override
  public void visitIfConditionalEnd() {
    Label l0 = this.conditionalJumps.remove(this.conditionalJumps.size() - 1);
    Label l1 = new Label();
    this.conditionalJumps.add(l1);
    mv.visitJumpInsn(GOTO, l1);
    mv.visitLabel(l0);
  }

  @Override
  public void visitIfConditionalStart() {
    Label l0 = new Label();
    this.conditionalJumps.add(l0);
    mv.visitInsn(ICONST_0);
    mv.visitJumpInsn(IF_ICMPEQ, l0);
  }

  @Override
  public void visitIntegerValue(int value) {
    if (value >= 0 && value <= 5) {
      try {
        int code = getClass().getField("ICONST_" + value).getInt(getClass());
        mv.visitInsn(code);
      } catch (SecurityException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      } catch (NoSuchFieldException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      } catch (IllegalAccessException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      }
    } else if (value == -1) {
      mv.visitInsn(ICONST_M1);
    } else {
      mv.visitIntInsn(BIPUSH, value);
    }
  }

  @Override
  public void visitLoopEnd(Class<?> iterableClass, Class<?> iteratedClass,
      int iteratorRegister, int iteratedRegister) {
    mv.visitLabel(this.loopJumps.remove(this.loopJumps.size() - 1));
    mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
    mv.visitVarInsn(ALOAD, iteratorRegister);
    mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z");
    mv.visitJumpInsn(IFNE, this.loopJumps.remove(this.loopJumps.size() - 1));
  }

  @Override
  public void visitLoopStart(Class<?> iterableClass, Class<?> iteratedClass,
      int iteratorRegister, int iteratedRegister) {
    Label l0 = new Label();
    Label l1 = new Label();
    this.loopJumps.add(l1);
    this.loopJumps.add(l0);
    mv.visitMethodInsn(INVOKEVIRTUAL, getClassName(iterableClass), "iterator",
        "()Ljava/util/Iterator;");
    mv.visitVarInsn(ASTORE, iteratorRegister);
    mv.visitJumpInsn(GOTO, l0);
    mv.visitLabel(l1);
    mv.visitFrame(Opcodes.F_FULL, 3, new Object[] { "[Ljava/lang/String;",
        Opcodes.TOP, "java/util/Iterator" }, 0, new Object[] {});
    mv.visitVarInsn(ALOAD, iteratorRegister);
    mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next",
        "()Ljava/lang/Object;");
    mv.visitTypeInsn(CHECKCAST, getClassName(iteratedClass));

  }

  /**
   * Generates code of the constructor for main class.
   */
  private void visitMainClassDefaultConstructor() {
    mv = cw.visitMethod(ACC_PUBLIC, "<init>",
        "()" + getClassOpCode(void.class), null, null);
    mv.visitCode();
    mv.visitVarInsn(ALOAD, 0);
    mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()"
        + getClassOpCode(void.class));
    mv.visitInsn(RETURN);
    mv.visitMaxs(1, 1);
    mv.visitEnd();
  }

  /**
   * Generates the code for main method end.
   */
  private void visitMainMethodEnd() {
    mv.visitInsn(RETURN);
    mv.visitMaxs(0, 0);
    mv.visitEnd();
  }

  /**
   * Generates the code for main method start.
   */
  private void visitMainMethodStart() {
    mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main",
        "([Ljava/lang/String;)V", null, null);
    mv.visitCode();
  }

  @Override
  public void visitMethod(Class<?> type, String name, List<Class<?>> args,
      Class<?> returnType) {
    mv.visitMethodInsn(INVOKEVIRTUAL, getClassName(type), name, "("
        + getArgsOpCode(args) + ")" + getClassOpCode(returnType));
  }

  @Override
  public void visitNullValue() {
    mv.visitInsn(ACONST_NULL);
  }

  @Override
  public void visitOperation(Operator op, Class<?> operationType) {
    try {
      int code = 0;
      switch (op) {
      case ADD:
        code = getClass().getField(getClassOpCode(operationType) + "ADD")
            .getInt(getClass());
        mv.visitInsn(code);
        break;
      case SUB:
        code = getClass().getField(getClassOpCode(operationType) + "SUB")
            .getInt(getClass());
        mv.visitInsn(code);
        break;
      case MUL:
        code = getClass().getField(getClassOpCode(operationType) + "MUL")
            .getInt(getClass());
        mv.visitInsn(code);
        break;
      case DIV:
        code = getClass().getField(getClassOpCode(operationType) + "DIV")
            .getInt(getClass());
        mv.visitInsn(code);
        break;
      case MIN:
        code = getClass().getField(getClassOpCode(operationType) + "NEG")
            .getInt(getClass());
        mv.visitInsn(code);
        break;
      case NOT:
        mv.visitInsn(ICONST_1);
        mv.visitInsn(ISUB);
        break;
      case EQU:
        visitCompare(IF_ICMPNE);
        break;
      case INE:
        visitCompare(IF_ICMPEQ);
        break;
      default:
        throw new AssertionError("Unable to perform operation code.");
      }
    } catch (SecurityException e) {
      throw new AssertionError("Unable to perform operation code.");
    } catch (IllegalArgumentException e) {
      throw new AssertionError("Unable to perform operation code.");
    } catch (IllegalAccessException e) {
      throw new AssertionError("Unable to perform operation code.");
    } catch (NoSuchFieldException e) {
      throw new AssertionError("Unable to perform operation code.");
    }
  }

  @Override
  public void visitPrimitiveCast(Class<?> cast, Class<?> toCast) {
    try {
      int code = getClass().getField(
          getLoadStoreOpCode(toCast) + "2" + getLoadStoreOpCode(cast)).getInt(
          getClass());
      mv.visitInsn(code);
    } catch (SecurityException e1) {
      throw new AssertionError("Operation code doesn't exist.");
    } catch (NoSuchFieldException e1) {
      throw new AssertionError("Operation code doesn't exist.");
    } catch (IllegalAccessException e1) {
      throw new AssertionError("Operation code doesn't exist.");
    }
  }

  @Override
  public void visitPrintEnd(Class<?> typeToPrint) {
    String typeToInvoke = "Ljava/lang/Object;";
    if (typeToPrint.isPrimitive()) {
      typeToInvoke = getClassOpCode(typeToPrint);
    }
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "("
        + typeToInvoke + ")" + getClassOpCode(void.class));
  }

  @Override
  public void visitPrintStart() {
    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
        "Ljava/io/PrintStream;");
  }

  @Override
  public void visitReturn(Class<?> returnType) {
    if (returnType.equals(void.class)) {
      mv.visitInsn(RETURN);
    } else {
      try {
        int code = getClass().getField(
            getLoadStoreOpCode(returnType) + "RETURN").getInt(getClass());
        mv.visitInsn(code);
      } catch (SecurityException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      } catch (NoSuchFieldException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      } catch (IllegalAccessException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      }
    }
  }

  @Override
  public void visitStart() {
    cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, mainClassName, null,
        "java/lang/Object", null);
    visitMainClassDefaultConstructor();
    visitMainMethodStart();
  }

  @Override
  public void visitStaticMethod(Class<?> type, String name,
      List<Class<?>> args, Class<?> returnType) {
    mv.visitMethodInsn(INVOKESTATIC, getClassName(type), name, "("
        + getArgsOpCode(args) + ")" + getClassOpCode(returnType));
  }

  @Override
  public void visitStringConcatenation(List<Class<?>> classes,
      List<Integer> registers) {

    for (int i = classes.size() - 1; i >= 0; i--) {
      Class<?> c = classes.get(i);
      try {
        int code = getClass().getField(getLoadStoreOpCode(c) + "STORE").getInt(
            getClass());
        mv.visitVarInsn(code, registers.get(i));
      } catch (SecurityException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      } catch (NoSuchFieldException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      } catch (IllegalAccessException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      }
    }

    mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
    mv.visitInsn(DUP);

    Class<?> c = classes.get(0);
    try {
      int code = getClass().getField(getLoadStoreOpCode(c) + "LOAD").getInt(
          getClass());
      mv.visitVarInsn(code, registers.get(0));
    } catch (SecurityException e1) {
      throw new AssertionError("Operation code doesn't exist.");
    } catch (NoSuchFieldException e1) {
      throw new AssertionError("Operation code doesn't exist.");
    } catch (IllegalAccessException e1) {
      throw new AssertionError("Operation code doesn't exist.");
    }

    if (getLoadStoreOpCode(c) == "A") {
      mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf",
          "(Ljava/lang/Object;)Ljava/lang/String;");
    } else {
      mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "("
          + getLoadStoreOpCode(c) + ")Ljava/lang/String;");
    }

    mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>",
        "(Ljava/lang/String;)V");

    c = classes.get(1);
    try {
      int code = getClass().getField(getLoadStoreOpCode(c) + "LOAD").getInt(
          getClass());
      mv.visitVarInsn(code, registers.get(1));
    } catch (SecurityException e1) {
      throw new AssertionError("Operation code doesn't exist.");
    } catch (NoSuchFieldException e1) {
      throw new AssertionError("Operation code doesn't exist.");
    } catch (IllegalAccessException e1) {
      throw new AssertionError("Operation code doesn't exist.");
    }

    if (getLoadStoreOpCode(c) == "A") {
      mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf",
          "(Ljava/lang/Object;)Ljava/lang/String;");
    } else {
      mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "("
          + getLoadStoreOpCode(c) + ")Ljava/lang/String;");
    }

    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
        "(Ljava/lang/Object;)Ljava/lang/StringBuilder;");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString",
        "()Ljava/lang/String;");
  }

  @Override
  public void visitStringValue(String value) {
    mv.visitTypeInsn(NEW, "java/lang/String");
    mv.visitInsn(DUP);
    mv.visitLdcInsn(value);
    mv.visitMethodInsn(INVOKESPECIAL, "java/lang/String", "<init>",
        "(Ljava/lang/String;)V");
  }

  @Override
  public void visitVariableAssignment(String name, Variable var) {
    if (var.isGlobal()) {
      mv.visitFieldInsn(PUTSTATIC, this.mainClassName, name, getClassOpCode(var
          .getDeclaredClass()));
    } else {
      try {
        int code = getClass().getField(
            getLoadStoreOpCode(var.getDeclaredClass()) + "STORE").getInt(
            getClass());
        mv.visitVarInsn(code, var.getRegister());
      } catch (SecurityException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      } catch (NoSuchFieldException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      } catch (IllegalAccessException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      }
    }
  }

  @Override
  public void visitVariableDeclaration(String name, Variable var) {
    if (var.isGlobal()) {
      fv = cw.visitField(ACC_PUBLIC + ACC_STATIC, name, getClassOpCode(var
          .getDeclaredClass()), null, null);
      fv.visitEnd();
      mv.visitFieldInsn(PUTSTATIC, this.mainClassName, name, getClassOpCode(var
          .getDeclaredClass()));
    } else {
      try {
        int code = getClass().getField(
            getLoadStoreOpCode(var.getDeclaredClass()) + "STORE").getInt(
            getClass());
        mv.visitVarInsn(code, var.getRegister());
      } catch (SecurityException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      } catch (NoSuchFieldException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      } catch (IllegalAccessException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      }
    }
  }

  @Override
  public void visitVariableUsage(String name, Variable var) {
    if (var.isGlobal()) {
      mv.visitFieldInsn(GETSTATIC, this.mainClassName, name, getClassOpCode(var
          .getDeclaredClass()));
    } else {
      try {
        int code = getClass().getField(
            getLoadStoreOpCode(var.getDeclaredClass()) + "LOAD").getInt(
            getClass());
        mv.visitVarInsn(code, var.getRegister());
      } catch (SecurityException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      } catch (NoSuchFieldException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      } catch (IllegalAccessException e1) {
        throw new AssertionError("Operation code doesn't exist.");
      }
    }
  }

}
