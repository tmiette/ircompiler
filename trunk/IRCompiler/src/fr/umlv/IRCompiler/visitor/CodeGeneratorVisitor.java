package fr.umlv.IRCompiler.visitor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tatoo.tools.Another_Arg;
import tatoo.tools.Another_Arg_Star;
import tatoo.tools.Another_Parameter;
import tatoo.tools.Another_Parameter_Star;
import tatoo.tools.Args_List;
import tatoo.tools.Boolean_Expression;
import tatoo.tools.Boolean_Type;
import tatoo.tools.Class_Type;
import tatoo.tools.Comment_Statement;
import tatoo.tools.Conditional_Statement;
import tatoo.tools.Div_Expression;
import tatoo.tools.Double_Expression;
import tatoo.tools.Double_Type;
import tatoo.tools.Else_Statement;
import tatoo.tools.Empty_Arg_List;
import tatoo.tools.Empty_Else_Statement;
import tatoo.tools.Empty_Parameter_List;
import tatoo.tools.Empty_Statement;
import tatoo.tools.Equal_Expression;
import tatoo.tools.Expression_Statement;
import tatoo.tools.Float_Expression;
import tatoo.tools.Float_Type;
import tatoo.tools.Foreach_Statement;
import tatoo.tools.Foreach_Statement_With_Declaration;
import tatoo.tools.Function_Call_Expression;
import tatoo.tools.Function_Declaration;
import tatoo.tools.IInstruction;
import tatoo.tools.IStatement;
import tatoo.tools.Identifier_Expression;
import tatoo.tools.Import_Statement;
import tatoo.tools.Import_Statement_Star;
import tatoo.tools.Inequal_Expression;
import tatoo.tools.Instruction;
import tatoo.tools.Instruction_Star;
import tatoo.tools.Integer_Type;
import tatoo.tools.Method_Call_Expression;
import tatoo.tools.Minus_Expression;
import tatoo.tools.Minus_Simple_Expression;
import tatoo.tools.Mult_Expression;
import tatoo.tools.Multiple_Package_Name;
import tatoo.tools.Multiple_Statement;
import tatoo.tools.Not_Expression;
import tatoo.tools.Number_Expression;
import tatoo.tools.Par_Expression;
import tatoo.tools.Parameter;
import tatoo.tools.Parameter_List;
import tatoo.tools.Plus_Expression;
import tatoo.tools.Pow_Expression;
import tatoo.tools.Print_Statement;
import tatoo.tools.Return_Statement;
import tatoo.tools.Return_Statement_Empty;
import tatoo.tools.Single_Package_Name;
import tatoo.tools.Start;
import tatoo.tools.Statement;
import tatoo.tools.Statement_Star;
import tatoo.tools.String_Expression;
import tatoo.tools.String_Type;
import tatoo.tools.Variable_Assignment;
import tatoo.tools.Variable_Assignment_Statement;
import tatoo.tools.Variable_Declaration_Statement;
import tatoo.tools.Variable_Declaration_With_Assignment;
import tatoo.tools.Variable_Declaration_Without_Assignment;
import tatoo.tools.Visitor;
import tatoo.tools.Void_Type;
import fr.umlv.IRCompiler.codegenerator.CodeGenerator;
import fr.umlv.IRCompiler.codegenerator.CodeGeneratorBufferedAction;
import fr.umlv.IRCompiler.exception.InvalidExpressionException;
import fr.umlv.IRCompiler.exception.SymbolAlreadyDefinedException;
import fr.umlv.IRCompiler.exception.UnknownSymbolException;
import fr.umlv.IRCompiler.util.Function;
import fr.umlv.IRCompiler.util.FunctionCollection;
import fr.umlv.IRCompiler.util.IRCompilerSymbolTable;
import fr.umlv.IRCompiler.util.Import;
import fr.umlv.IRCompiler.util.JavaClassResolver;
import fr.umlv.IRCompiler.util.Operator;
import fr.umlv.IRCompiler.util.SymbolTable;
import fr.umlv.IRCompiler.util.Variable;

/**
 * This visitor generates the executable code of the custom language. This class
 * used a code generator.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public class CodeGeneratorVisitor extends
    Visitor<Class<?>, Void, Void, Throwable> {

  // number of available registers in a registers context.
  private static final int NUMBER_OF_REGISTERS = 40;

  // stack of arguments
  private final ArrayList<ArrayList<Class<?>>> argsStack;

  // list of buffered actions.
  private final ArrayList<CodeGeneratorBufferedAction> bufferedActions;

  // function currently checked
  private Function currentFunction;

  // list of the free registers in the current registers context.
  private final ArrayList<ArrayList<Integer>> freeRegisters;

  // functions manager
  private final FunctionCollection functions;

  // code generator
  private final CodeGenerator generator;

  // list of global variables
  private final ArrayList<Integer> globalVariables;

  // imports manager
  private final Import imports;

  // semantic checkout functions manager
  private final FunctionCollection semanticFunctions;

  // symbol table
  private final SymbolTable<Variable> symbolsTable;

  // list of declared variables
  private final ArrayList<ArrayList<String>> usedVariables;

  /**
   * Constructor.
   * 
   * @param generator
   *            code generator/
   * @param imports
   *            semantic imports manager.
   * @param functions
   *            semantic functions manager.
   */
  public CodeGeneratorVisitor(CodeGenerator generator, Import imports,
      FunctionCollection functions) {
    this.generator = generator;
    this.imports = imports;
    this.symbolsTable = new IRCompilerSymbolTable<Variable>();
    this.functions = new FunctionCollection();
    this.usedVariables = new ArrayList<ArrayList<String>>();
    this.argsStack = new ArrayList<ArrayList<Class<?>>>();
    this.globalVariables = new ArrayList<Integer>();
    this.semanticFunctions = functions;
    this.bufferedActions = new ArrayList<CodeGeneratorBufferedAction>();
    this.freeRegisters = new ArrayList<ArrayList<Integer>>();
  }

  /**
   * Add an argument in the current arguments context.
   * 
   * @param arg
   *            the argument type.
   */
  private void addArgs(Class<?> arg) {
    this.argsStack.get(this.argsStack.size() - 1).add(arg);
  }

  /**
   * Add a new context for arguments.
   */
  private void addNewArgsContext() {
    this.argsStack.add(new ArrayList<Class<?>>());
  }

  /**
   * Add a new registers context.
   */
  private void addRegisterContext() {
    ArrayList<Integer> registers = new ArrayList<Integer>(NUMBER_OF_REGISTERS);
    for (int i = 0; i < NUMBER_OF_REGISTERS; i++) {
      registers.add(new Integer(i));
    }
    this.freeRegisters.add(registers);
  }

  /**
   * Add a new variable in the current variables context.
   * 
   * @param symbol
   *            the variable symbol.
   * @param variable
   *            the variable.
   * @throws SymbolAlreadyDefinedException
   *             if the symbol is already defined in the current variables
   *             context.
   */
  private void addSymbol(String symbol, Variable variable)
      throws SymbolAlreadyDefinedException {
    variable.setRegister(getNextFreeRegister());

    if (this.usedVariables.size() == 1) {
      variable.setGlobal(true);
      globalVariables.add(variable.getId());
    }

    this.usedVariables.get(this.usedVariables.size() - 1).add(symbol);
    this.symbolsTable.push(symbol, variable);
  }

  /**
   * Add a new variables context.
   */
  private void addVariableContext() {
    this.usedVariables.add(new ArrayList<String>());
  }

  /**
   * Submits all buffered actions.
   */
  private void doAllBufferedActions() {
    for (CodeGeneratorBufferedAction action : this.bufferedActions) {
      action.doBufferedAction();
    }
  }

  /**
   * Free a register of the current registers context.
   * 
   * @param register
   *            the register.
   */
  private void freeRegister(int register) {
    ArrayList<Integer> registers = this.freeRegisters.get(this.freeRegisters
        .size() - 1);
    registers.add(new Integer(register));
    Collections.sort(registers);
  }

  /**
   * Free a list of registers of the current registers context.
   * 
   * @param registers
   */
  private void freeRegisters(List<Integer> registers) {
    for (int i : registers) {
      freeRegister(i);
    }
  }

  private void generateDefaultValue(Class<?> clazz) {
    if (clazz.equals(int.class)) {
      this.generator.visitIntegerValue(0);
    } else if (clazz.equals(boolean.class)) {
      this.generator.visitBooleanValue(false);
    } else if (clazz.equals(double.class)) {
      this.generator.visitDoubleValue(new Double(0));
    } else if (clazz.equals(float.class)) {
      this.generator.visitFloatValue(new Float(0));
    } else {
      this.generator.visitNullValue();
    }
  }

  /**
   * Returns the stack of all arguments declared in the current arguments
   * context.
   * 
   * @return the stack of arguments.
   */
  private ArrayList<Class<?>> getArgs() {
    return this.argsStack.get(this.argsStack.size() - 1);
  }

  /**
   * Returns a stack of types
   * 
   * @param higher
   *            the type.
   * @return the stack.
   */
  private ArrayList<Class<?>> getHigherTypeList(Class<?> higher) {
    ArrayList<Class<?>> l = new ArrayList<Class<?>>();
    l.add(higher);
    l.add(higher);
    return l;
  }

  /**
   * Returns the next free register of the current registers context.
   * 
   * @return the next free register of the current registers context.
   */
  private int getNextFreeRegister() {
    ArrayList<Integer> registers = this.freeRegisters.get(this.freeRegisters
        .size() - 1);
    if (registers.size() == 0) {
      throw new AssertionError("No more registers to allocate variables.");
    }
    return registers.remove(0);
  }

  /**
   * Return a list of the n next free registers of the current registers
   * context.
   * 
   * @param number
   *            number of registers expected.
   * @return the list of regoisters.
   */
  private List<Integer> getNextFreeRegisters(int number) {
    ArrayList<Integer> registers = new ArrayList<Integer>();
    for (int i = 0; i < number; i++) {
      registers.add(getNextFreeRegister());
    }
    return registers;
  }

  /**
   * Returns a stack of types
   * 
   * @param type1
   *            the first type.
   * @param type2
   *            the second type.
   * @return the stack.
   */
  private ArrayList<Class<?>> getStackTypeList(Class<?> type1, Class<?> type2) {
    ArrayList<Class<?>> l = new ArrayList<Class<?>>();
    l.add(type1);
    l.add(type2);
    return l;
  }

  /**
   * Returns a variable in the current variables context corresponding on a
   * symbol.
   * 
   * @param symbol
   *            the symbol.
   * @return the variable if exists.
   * @throws UnknownSymbolException
   *             if a such symbol isn't defined.
   */
  private Variable getSymbol(String symbol) throws UnknownSymbolException {
    return this.symbolsTable.get(symbol);
  }

  private Class<?> manageExpression(final Operator op, final Class<?> type1,
      final Class<?> type2) throws InvalidExpressionException,
      UnknownSymbolException {

    if (type1.equals(String.class) || type2.equals(String.class)) {
      this.manageStringExpression(op, type1, type2);
      return String.class;
    } else {
      if (type1.isPrimitive()) {
        Class<?> t = JavaClassResolver.getHigherType(type1, type2);
        visitOperation(op, type1, type2, t);
        return t;
      } else {
        if (op.getMethodName().equals("equals")) {

          // submit the code generation
          if (this.currentFunction == null) {
            this.generator.visitEqualsMethod(type1);
          } else {
            this.bufferedActions.add(new CodeGeneratorBufferedAction() {
              @Override
              public void doBufferedAction() {
                generator.visitEqualsMethod(type1);
              }
            });
          }

          return boolean.class;
        } else {

          // submit the code generation
          if (this.currentFunction == null) {
            this.generator.visitStaticMethod(type1, op.getMethodName(),
                getHigherTypeList(type1), type1);
          } else {
            this.bufferedActions.add(new CodeGeneratorBufferedAction() {
              @Override
              public void doBufferedAction() {
                generator.visitStaticMethod(type1, op.getMethodName(),
                    getHigherTypeList(type1), type1);
              }
            });
          }

          return type1;
        }
      }
    }
  }

  private void manageStringExpression(final Operator op, final Class<?> type1,
      final Class<?> type2) {

    if (op.equals(Operator.ADD)) {
      final List<Integer> registers = getNextFreeRegisters(2);
      this.generator.visitStringConcatenation(getStackTypeList(type1, type2),
          registers);

      freeRegisters(registers);
    }

  }

  /**
   * Remove the current context of arguments.
   */
  private void removeArgsContext() {
    this.argsStack.remove(this.argsStack.size() - 1);
  }

  /**
   * Remove the current registers context.
   */
  private void removeRegisterContext() {
    this.freeRegisters.remove(this.freeRegisters.size() - 1);
  }

  /**
   * Remove the current variables context.
   */
  private void removeVariableContext() {
    for (String s : this.usedVariables.get(this.usedVariables.size() - 1)) {
      Variable v = this.symbolsTable.pop(s);
      freeRegister(v.getRegister());
    }
    this.usedVariables.remove(this.usedVariables.size() - 1);
  }

  @Override
  public Class<?> visit(Another_Arg another_arg, Void param) throws Throwable {
    addArgs(another_arg.getExpression().accept(this, param));
    return null;
  }

  @Override
  public Class<?> visit(Another_Arg_Star another_arg_star, Void param)
      throws Throwable {
    for (Another_Arg arg : another_arg_star.nodeList()) {
      arg.accept(this, param);
    }
    return null;
  }

  @Override
  public Class<?> visit(Another_Parameter another_parameter, Void param)
      throws Throwable {
    another_parameter.getParameter().accept(this, param);
    return null;
  }

  @Override
  public Class<?> visit(Another_Parameter_Star another_parameter_star,
      Void param) throws Throwable {
    for (Another_Parameter parameter : another_parameter_star.nodeList()) {
      parameter.accept(this, param);
    }
    return null;
  }

  @Override
  public Class<?> visit(Args_List args_list, Void param) throws Throwable {
    addArgs(args_list.getExpression().accept(this, param));
    args_list.getAnother_arg_star().accept(this, param);
    return null;
  }

  @Override
  public Class<?> visit(final Boolean_Expression boolean_expression, Void param)
      throws Throwable {

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitBooleanValue(boolean_expression.getBoolean_());
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitBooleanValue(boolean_expression.getBoolean_());
        }
      });
    }

    return boolean.class;
  }

  @Override
  public Class<?> visit(Boolean_Type boolean_type, Void param) throws Throwable {
    return boolean.class;
  }

  @Override
  public Class<?> visit(Class_Type class_type, Void param) throws Throwable {
    return this.imports.getClass(class_type.getIdentifier_());
  }

  @Override
  public Class<?> visit(Comment_Statement comment_statement, Void param)
      throws Throwable {
    return null;
  }

  @Override
  public Class<?> visit(Conditional_Statement conditional_statement, Void param)
      throws Throwable {
    addVariableContext();
    conditional_statement.getExpression().accept(this, param);

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitIfConditionalStart();
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitIfConditionalStart();
        }
      });
    }

    conditional_statement.getMultiple_statement().accept(this, param);

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitIfConditionalEnd();
      this.generator.visitElseConditionalStart();
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitIfConditionalEnd();
          generator.visitElseConditionalStart();
        }
      });
    }

    conditional_statement.getElse_statement().accept(this, param);

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitElseConditionalEnd();
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitElseConditionalEnd();
        }
      });
    }

    removeVariableContext();
    return null;
  }

  @Override
  public Class<?> visit(Div_Expression div_expression, Void param)
      throws Throwable {
    final Class<?> type1 = div_expression.getExpression().accept(this, param);
    final Class<?> type2 = div_expression.getExpression2().accept(this, param);
    return manageExpression(Operator.DIV, type1, type2);
  }

  @Override
  public Class<?> visit(final Double_Expression double_expression, Void param)
      throws Throwable {

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitDoubleValue(double_expression.getDouble_());
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitDoubleValue(double_expression.getDouble_());
        }
      });
    }

    return double.class;
  }

  @Override
  public Class<?> visit(Double_Type double_type, Void param) throws Throwable {
    return double.class;
  }

  @Override
  public Class<?> visit(Else_Statement else_statement, Void param)
      throws Throwable {
    addVariableContext();
    else_statement.getMultiple_statement().accept(this, param);
    removeVariableContext();
    return null;
  }

  @Override
  public Class<?> visit(Empty_Arg_List empty_arg_list, Void param)
      throws Throwable {
    return null;
  }

  @Override
  public Class<?> visit(Empty_Else_Statement empty_else_statement, Void param)
      throws Throwable {
    return null;
  }

  @Override
  public Class<?> visit(Empty_Parameter_List empty_parameter_list, Void param)
      throws Throwable {
    return null;
  }

  @Override
  public Class<?> visit(Empty_Statement empty_statement, Void param)
      throws Throwable {
    return null;
  }

  @Override
  public Class<?> visit(Equal_Expression equal_expression, Void param)
      throws Throwable {
    final Class<?> type1 = equal_expression.getExpression().accept(this, param);
    final Class<?> type2 = equal_expression.getExpression2()
        .accept(this, param);
    manageExpression(Operator.EQU, type1, type2);
    return boolean.class;
  }

  @Override
  public Class<?> visit(Expression_Statement expression_statement, Void param)
      throws Throwable {
    expression_statement.getExpression().accept(this, param);
    return null;
  }

  @Override
  public Class<?> visit(final Float_Expression float_expression, Void param)
      throws Throwable {

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitFloatValue(float_expression.getFloat_());
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitFloatValue(float_expression.getFloat_());
        }
      });
    }

    return float.class;
  }

  @Override
  public Class<?> visit(Float_Type float_type, Void param) throws Throwable {
    return float.class;
  }

  @Override
  public Class<?> visit(final Foreach_Statement foreach_statement, Void param)
      throws Throwable {
    addVariableContext();

    final Variable v = getSymbol(foreach_statement.getIdentifier_());
    final Class<?> iterable = foreach_statement.getExpression().accept(this,
        param);
    final int iteratorRegister = getNextFreeRegister();

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitLoopStart(iterable, v.getDeclaredClass(),
          iteratorRegister, v.getRegister());
      this.generator.visitVariableAssignment(
          foreach_statement.getIdentifier_(), v);
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitLoopStart(iterable, v.getDeclaredClass(),
              iteratorRegister, v.getRegister());
          generator.visitVariableAssignment(foreach_statement.getIdentifier_(),
              v);
        }
      });
    }

    foreach_statement.getMultiple_statement().accept(this, param);

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitLoopEnd(iterable, v.getDeclaredClass(),
          iteratorRegister, v.getRegister());
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitLoopEnd(iterable, v.getDeclaredClass(),
              iteratorRegister, v.getRegister());
        }
      });
    }

    removeVariableContext();
    return null;
  }

  @Override
  public Class<?> visit(
      final Foreach_Statement_With_Declaration foreach_statement_with_declaration,
      Void param) throws Throwable {
    addVariableContext();

    final Class<?> type = foreach_statement_with_declaration.getType().accept(
        this, param);
    final Variable v = new Variable(type);
    addSymbol(foreach_statement_with_declaration.getIdentifier_(), v);
    final Class<?> iterable = foreach_statement_with_declaration
        .getExpression().accept(this, param);
    final int iteratorRegister = getNextFreeRegister();

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitLoopStart(iterable, v.getDeclaredClass(),
          iteratorRegister, v.getRegister());
      this.generator.visitVariableAssignment(foreach_statement_with_declaration
          .getIdentifier_(), v);
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitLoopStart(iterable, v.getDeclaredClass(),
              iteratorRegister, v.getRegister());
          generator.visitVariableAssignment(foreach_statement_with_declaration
              .getIdentifier_(), v);
        }
      });
    }

    foreach_statement_with_declaration.getMultiple_statement().accept(this,
        param);

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitLoopEnd(iterable, v.getDeclaredClass(),
          iteratorRegister, v.getRegister());
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitLoopEnd(iterable, v.getDeclaredClass(),
              iteratorRegister, v.getRegister());
        }
      });
    }

    removeVariableContext();
    return null;
  }

  @Override
  public Class<?> visit(
      final Function_Call_Expression function_call_expression, Void param)
      throws Throwable {

    final Class<?> t = this.semanticFunctions
        .isConstructor(function_call_expression.getIdentifier_());
    if (t != null) {

      // submit the code generation
      if (this.currentFunction == null) {
        this.generator.visitConstructorStart(t);
      } else {
        this.bufferedActions.add(new CodeGeneratorBufferedAction() {
          @Override
          public void doBufferedAction() {
            generator.visitConstructorStart(t);
          }
        });
      }

    }

    addNewArgsContext();

    function_call_expression.getArg_list().accept(this, param);

    try {

      final List<Class<?>> realArgs = getArgs();
      final Function f = this.functions.validateFunctionCall(
          function_call_expression.getIdentifier_(), getArgs());
      removeArgsContext();
      final List<Class<?>> exceptedArgs = f.getArgs();
      final List<Integer> registers = getNextFreeRegisters(realArgs.size());

      if (f.isConstructor()) {
        // submit the code generation
        if (this.currentFunction == null) {
          this.generator.visitArgsCast(exceptedArgs, realArgs, registers);
          this.generator.visitConstructorEnd(f);
        } else {
          this.bufferedActions.add(new CodeGeneratorBufferedAction() {
            @Override
            public void doBufferedAction() {
              generator.visitArgsCast(exceptedArgs, realArgs, registers);
              generator.visitConstructorEnd(f);
            }
          });
        }
      } else {
        // submit the code generation
        if (this.currentFunction == null) {
          this.generator.visitArgsCast(exceptedArgs, realArgs, registers);
          this.generator.visitFunction(function_call_expression
              .getIdentifier_(), f);
        } else {
          this.bufferedActions.add(new CodeGeneratorBufferedAction() {
            @Override
            public void doBufferedAction() {
              generator.visitArgsCast(exceptedArgs, realArgs, registers);
              generator.visitFunction(
                  function_call_expression.getIdentifier_(), f);
            }
          });
        }
      }
      freeRegisters(registers);
      return f.getReturnType();

    } catch (UnknownSymbolException e) {

      Class<?> type = this.imports.getClass(function_call_expression
          .getIdentifier_());
      final List<Class<?>> realArgs = getArgs();
      Constructor<?> cons = JavaClassResolver.validateConstructor(type,
          realArgs);
      final List<Class<?>> exceptedArgs = Arrays.asList(cons
          .getParameterTypes());
      final Function f = this.functions.addFunction(function_call_expression
          .getIdentifier_(), type, exceptedArgs);
      f.setConstructor(true);
      final List<Integer> registers = getNextFreeRegisters(realArgs.size());

      // submit the code generation
      if (this.currentFunction == null) {
        this.generator.visitArgsCast(exceptedArgs, realArgs, registers);
        this.generator.visitConstructorEnd(f);
      } else {
        this.bufferedActions.add(new CodeGeneratorBufferedAction() {
          @Override
          public void doBufferedAction() {
            generator.visitArgsCast(exceptedArgs, realArgs, registers);
            generator.visitConstructorEnd(f);
          }
        });
      }

      freeRegisters(registers);
      removeArgsContext();
      return type;

    }
  }

  @Override
  public Class<?> visit(final Function_Declaration function_declaration,
      Void param) throws Throwable {

    addRegisterContext();
    addVariableContext();
    addNewArgsContext();

    function_declaration.getParameter_list().accept(this, param);
    final Function f = this.functions.addFunction(function_declaration
        .getIdentifier_(), function_declaration.getType().accept(this, param),
        getArgs());
    this.currentFunction = f;

    removeArgsContext();

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitFunctionStart(function_declaration.getIdentifier_(),
          f);
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator
              .visitFunctionStart(function_declaration.getIdentifier_(), f);
        }
      });
    }

    function_declaration.getMultiple_statement().accept(this, param);

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitFunctionEnd(function_declaration.getIdentifier_(), f);
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitFunctionEnd(function_declaration.getIdentifier_(), f);
        }
      });
    }

    this.currentFunction = null;
    removeVariableContext();
    removeRegisterContext();
    return null;
  }

  @Override
  public Class<?> visit(final Identifier_Expression identifier_expression,
      Void param) throws Throwable {
    final Variable v = getSymbol(identifier_expression.getIdentifier_());

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitVariableUsage(identifier_expression.getIdentifier_(),
          v);
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitVariableUsage(identifier_expression.getIdentifier_(),
              v);
        }
      });
    }

    return v.getDeclaredClass();
  }

  @Override
  public Class<?> visit(Import_Statement import_statement, Void param)
      throws Throwable {
    return null;
  }

  @Override
  public Class<?> visit(Import_Statement_Star import_statement_star, Void param)
      throws Throwable {
    return null;
  }

  @Override
  public Class<?> visit(Inequal_Expression inequal_expression, Void param)
      throws Throwable {
    final Class<?> type1 = inequal_expression.getExpression().accept(this,
        param);
    final Class<?> type2 = inequal_expression.getExpression2().accept(this,
        param);
    manageExpression(Operator.INE, type1, type2);
    return boolean.class;
  }

  @Override
  public Class<?> visit(Instruction instruction, Void param) throws Throwable {
    instruction.getFunction_declaration().accept(this, param);
    return null;
  }

  @Override
  public Class<?> visit(Instruction_Star instruction_star, Void param)
      throws Throwable {
    for (IInstruction instruction : instruction_star.nodeList()) {
      instruction.accept(this, param);
    }
    return null;
  }

  @Override
  public Class<?> visit(Integer_Type integer_type, Void param) throws Throwable {
    return int.class;
  }

  @Override
  public Class<?> visit(final Method_Call_Expression method_call_expression,
      Void param) throws Throwable {

    addNewArgsContext();

    final Variable v = this.symbolsTable.get(method_call_expression
        .getIdentifier_());

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitVariableUsage(
          method_call_expression.getIdentifier_(), v);
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitVariableUsage(method_call_expression.getIdentifier_(),
              v);
        }
      });
    }

    method_call_expression.getArg_list().accept(this, param);
    final ArrayList<Class<?>> args = getArgs();
    final Method m = JavaClassResolver.validateMethod(v.getDeclaredClass(),
        method_call_expression.getIdentifier_2(), args);

    final ArrayList<Class<?>> declaredArgs = new ArrayList<Class<?>>();

    Class<?>[] parameters = m.getParameterTypes();
    int size = m.getParameterTypes().length;
    for (int i = 0; i < size; i++) {
      declaredArgs.add(parameters[i]);
    }

    final List<Integer> registers = getNextFreeRegisters(size);

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitArgsCast(declaredArgs, args, registers);
      this.generator.visitMethod(v.getDeclaredClass(), method_call_expression
          .getIdentifier_2(), declaredArgs, m.getReturnType());
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitArgsCast(declaredArgs, args, registers);
          generator.visitMethod(v.getDeclaredClass(), method_call_expression
              .getIdentifier_2(), declaredArgs, m.getReturnType());
        }
      });
    }

    freeRegisters(registers);
    removeArgsContext();
    return m.getReturnType();
  }

  @Override
  public Class<?> visit(Minus_Expression minus_expression, Void param)
      throws Throwable {
    final Class<?> type1 = minus_expression.getExpression().accept(this, param);
    final Class<?> type2 = minus_expression.getExpression2()
        .accept(this, param);
    return manageExpression(Operator.SUB, type1, type2);
  }

  @Override
  public Class<?> visit(Minus_Simple_Expression minus_simple_expression,
      Void param) throws Throwable {

    final Class<?> type1 = JavaClassResolver.validateExpression(Operator.MIN,
        minus_simple_expression.getExpression().accept(this, param), null);

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitOperation(Operator.MIN, type1);
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitOperation(Operator.MIN, type1);
        }
      });
    }

    return type1;
  }

  @Override
  public Class<?> visit(Mult_Expression mult_expression, Void param)
      throws Throwable {
    final Class<?> type1 = mult_expression.getExpression().accept(this, param);
    final Class<?> type2 = mult_expression.getExpression2().accept(this, param);
    return manageExpression(Operator.MUL, type1, type2);
  }

  @Override
  public Class<?> visit(Multiple_Package_Name multiple_package_name, Void param)
      throws Throwable {
    return null;
  }

  @Override
  public Class<?> visit(Multiple_Statement multiple_statement, Void param)
      throws Throwable {
    multiple_statement.getStatement_star().accept(this, param);
    return null;
  }

  @Override
  public Class<?> visit(Not_Expression not_expression, Void param)
      throws Throwable {
    final Class<?> type = JavaClassResolver.validateExpression(Operator.NOT,
        not_expression.getExpression().accept(this, param), null);

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitOperation(Operator.NOT, type);
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitOperation(Operator.NOT, type);
        }
      });
    }

    return boolean.class;
  }

  @Override
  public Class<?> visit(final Number_Expression number_expression, Void param)
      throws Throwable {

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitIntegerValue(number_expression.getNumber_());
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitIntegerValue(number_expression.getNumber_());
        }
      });
    }

    return int.class;
  }

  @Override
  public Class<?> visit(Par_Expression par_expression, Void param)
      throws Throwable {
    return par_expression.getExpression().accept(this, param);
  }

  @Override
  public Class<?> visit(Parameter parameter, Void param) throws Throwable {
    Class<?> t = parameter.getType().accept(this, param);
    addSymbol(parameter.getIdentifier_(), new Variable(t));
    addArgs(t);
    return null;
  }

  @Override
  public Class<?> visit(Parameter_List parameter_list, Void param)
      throws Throwable {
    parameter_list.getParameter().accept(this, param);
    parameter_list.getAnother_parameter_star().accept(this, param);
    return null;
  }

  @Override
  public Class<?> visit(Plus_Expression plus_expression, Void param)
      throws Throwable {
    final Class<?> type1 = plus_expression.getExpression().accept(this, param);
    final Class<?> type2 = plus_expression.getExpression2().accept(this, param);
    return manageExpression(Operator.ADD, type1, type2);
  }

  @Override
  public Class<?> visit(Pow_Expression pow_expression, Void param)
      throws Throwable {
    final Class<?> type1 = pow_expression.getExpression().accept(this, param);
    final Class<?> type2 = pow_expression.getExpression2().accept(this, param);
    return manageExpression(Operator.POW, type1, type2);
  }

  @Override
  public Class<?> visit(Print_Statement print_statement, Void param)
      throws Throwable {

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitPrintStart();
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitPrintStart();
        }
      });
    }

    final Class<?> t = print_statement.getExpression().accept(this, param);

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitPrintEnd(t);
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitPrintEnd(t);
        }
      });
    }

    return null;
  }

  @Override
  public Class<?> visit(Return_Statement return_statement, Void param)
      throws Throwable {

    final Class<?> t = return_statement.getExpression().accept(this, param);

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitReturn(t);
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitReturn(t);
        }
      });
    }

    return null;
  }

  @Override
  public Class<?> visit(Return_Statement_Empty return_statement_empty,
      Void param) throws Throwable {

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitReturn(void.class);
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitReturn(void.class);
        }
      });
    }

    return null;
  }

  @Override
  public Class<?> visit(Single_Package_Name single_package_name, Void param)
      throws Throwable {
    return null;
  }

  @Override
  public Class<?> visit(Start start, Void param) throws Throwable {
    this.generator.visitStart();
    addRegisterContext();
    addVariableContext();
    start.getImport_statement_star().accept(this, param);
    start.getInstruction_star().accept(this, param);
    removeVariableContext();
    removeRegisterContext();
    this.generator.visitEnd();
    doAllBufferedActions();
    return null;
  }

  @Override
  public Class<?> visit(Statement statement, Void param) throws Throwable {
    statement.getStatement().accept(this, param);
    return null;
  }

  @Override
  public Class<?> visit(Statement_Star statement_star, Void param)
      throws Throwable {
    for (IStatement statement : statement_star.nodeList()) {
      statement.accept(this, param);
    }
    return null;
  }

  @Override
  public Class<?> visit(final String_Expression string_expression, Void param)
      throws Throwable {

    final String subString = string_expression.getString_().substring(1,
        string_expression.getString_().length() - 1);

    // submit the code generation
    if (this.currentFunction == null) {
      this.generator.visitStringValue(subString);
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generator.visitStringValue(subString);
        }
      });
    }

    return String.class;
  }

  @Override
  public Class<?> visit(String_Type string_type, Void param) throws Throwable {
    return String.class;
  }

  @Override
  public Class<?> visit(final Variable_Assignment variable_assignment,
      Void param) throws Throwable {

    final Variable v = this.symbolsTable.get(variable_assignment
        .getIdentifier_());
    final Class<?> type1 = v.getDeclaredClass();
    final Class<?> type2 = variable_assignment.getExpression().accept(this,
        param);

    // submit the code generation
    if (this.currentFunction == null) {
      if (!type1.equals(type2)) {
        this.generator.visitPrimitiveCast(type1, type2);
      }
      this.generator.visitVariableAssignment(variable_assignment
          .getIdentifier_(), v);
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          if (!type1.equals(type2)) {
            generator.visitPrimitiveCast(type1, type2);
          }
          generator.visitVariableAssignment(variable_assignment
              .getIdentifier_(), v);
        }
      });
    }

    return type1;
  }

  @Override
  public Class<?> visit(
      Variable_Assignment_Statement variable_assignment_statement, Void param)
      throws Throwable {
    variable_assignment_statement.getVariable_assignment().accept(this, param);
    return null;
  }

  @Override
  public Class<?> visit(
      Variable_Declaration_Statement variable_declaration_statement, Void param)
      throws Throwable {
    variable_declaration_statement.getVariable_declaration()
        .accept(this, param);
    return null;
  }

  @Override
  public Class<?> visit(
      final Variable_Declaration_With_Assignment variable_declaration_with_assignment,
      Void param) throws Throwable {

    final Class<?> type1 = variable_declaration_with_assignment.getType()
        .accept(this, param);
    final Class<?> type2 = variable_declaration_with_assignment.getExpression()
        .accept(this, param);
    final Variable v = new Variable(type1);
    addSymbol(variable_declaration_with_assignment.getIdentifier_(), v);

    // submit the code generation
    if (this.currentFunction == null) {
      if (type1.isPrimitive() && !type1.equals(type2)) {
        this.generator.visitPrimitiveCast(type1, type2);
      }
      this.generator.visitVariableDeclaration(
          variable_declaration_with_assignment.getIdentifier_(), v);
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          if (type1.isPrimitive() && !type1.equals(type2)) {
            generator.visitPrimitiveCast(type1, type2);
          }
          generator.visitVariableDeclaration(
              variable_declaration_with_assignment.getIdentifier_(), v);
        }
      });
    }

    return null;
  }

  @Override
  public Class<?> visit(
      final Variable_Declaration_Without_Assignment variable_declaration_without_assignment,
      Void param) throws Throwable {

    final Class<?> type1 = variable_declaration_without_assignment.getType()
        .accept(this, param);
    final Variable v = new Variable(type1);
    addSymbol(variable_declaration_without_assignment.getIdentifier_(), v);

    // submit the code generation
    if (this.currentFunction == null) {
      generateDefaultValue(type1);
      this.generator.visitVariableDeclaration(
          variable_declaration_without_assignment.getIdentifier_(), v);
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          generateDefaultValue(type1);
          generator.visitVariableDeclaration(
              variable_declaration_without_assignment.getIdentifier_(), v);
        }
      });
    }

    return null;
  }

  @Override
  public Class<?> visit(Void_Type void_type, Void param) throws Throwable {
    return void.class;
  }

  private void visitOperation(final Operator op, final Class<?> type1,
      final Class<?> type2, final Class<?> higher)
      throws InvalidExpressionException {

    List<Integer> registers = null;
    if (!type1.equals(type2)) {
      registers = getNextFreeRegisters(2);
    }
    final List<Integer> finalRegisters = registers;

    // submit the code generation
    if (this.currentFunction == null) {
      if (!type1.equals(type2)) {
        this.generator.visitArgsCast(getHigherTypeList(higher),
            getStackTypeList(type1, type2), finalRegisters);
      }
      this.generator.visitOperation(op, higher);
    } else {
      this.bufferedActions.add(new CodeGeneratorBufferedAction() {
        @Override
        public void doBufferedAction() {
          if (!type1.equals(type2)) {
            generator.visitArgsCast(getHigherTypeList(higher),
                getStackTypeList(type1, type2), finalRegisters);
          }
          generator.visitOperation(op, higher);
        }
      });
    }

    if (!type1.equals(type2)) {
      freeRegisters(finalRegisters);
    }
  }

}
