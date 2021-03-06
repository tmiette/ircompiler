package fr.umlv.IRCompiler.visitor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

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
import fr.umlv.IRCompiler.exception.InvalidExpressionException;
import fr.umlv.IRCompiler.exception.SymbolAlreadyDefinedException;
import fr.umlv.IRCompiler.exception.UnexpectedTypeException;
import fr.umlv.IRCompiler.exception.UnknownSymbolException;
import fr.umlv.IRCompiler.exception.UnresolvedClassException;
import fr.umlv.IRCompiler.util.Function;
import fr.umlv.IRCompiler.util.FunctionCollection;
import fr.umlv.IRCompiler.util.IRCompilerSymbolTable;
import fr.umlv.IRCompiler.util.Import;
import fr.umlv.IRCompiler.util.JavaClassResolver;
import fr.umlv.IRCompiler.util.Operator;
import fr.umlv.IRCompiler.util.SymbolTable;
import fr.umlv.IRCompiler.util.Variable;

/**
 * This visitor validate the semantic of the custom language.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public class SemanticVisitor extends Visitor<Class<?>, Void, Void, Throwable> {

  // stack of arguments
  private final ArrayList<ArrayList<Class<?>>> argsStack;

  // function currently checked
  private Function currentFunction;

  // functions manager
  private final FunctionCollection functions;

  // list of global variables
  private final ArrayList<Integer> globalVariables;

  // imports manager
  private final Import imports;

  // return flag
  private boolean lessOneReturn = false;

  // stack of package declaration
  private final ArrayList<String> packageList;

  // symbol table
  private final SymbolTable<Variable> symbolsTable;

  // list of declared variables
  private final ArrayList<ArrayList<String>> usedVariables;

  /**
   * Default constructor.
   */
  public SemanticVisitor() {
    this.symbolsTable = new IRCompilerSymbolTable<Variable>();
    this.packageList = new ArrayList<String>();
    this.imports = new Import();
    this.functions = new FunctionCollection();
    this.usedVariables = new ArrayList<ArrayList<String>>();
    this.argsStack = new ArrayList<ArrayList<Class<?>>>();
    this.globalVariables = new ArrayList<Integer>();
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
    if (this.usedVariables.get(this.usedVariables.size() - 1).contains(symbol)) {
      throw new SymbolAlreadyDefinedException("Symbol " + symbol
          + " is already defined.");
    }

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
   * Returns the stack of all arguments declared in the current arguments
   * context.
   * 
   * @return the stack of arguments.
   */
  private ArrayList<Class<?>> getArgs() {
    return this.argsStack.get(this.argsStack.size() - 1);
  }

  /**
   * Returns the functions manager.
   * 
   * @return the functions manager.
   */
  public FunctionCollection getFunctions() {
    return this.functions;
  }

  /**
   * Returns the imports manager.
   * 
   * @return the imports manager.
   */
  public Import getImports() {
    return this.imports;
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

  /**
   * Manage an expression visit of semantic checkout.
   * 
   * @param op
   *            the operator used.
   * @param type1
   *            the first type.
   * @param type2
   *            the second type.
   * @return the type of the expression.
   * @throws InvalidExpressionException
   *             if the expression cannot be solved.
   * @throws UnknownSymbolException
   *             if a symbol isn't defined.
   */
  private Class<?> manageExpression(Operator op, Class<?> type1, Class<?> type2)
      throws InvalidExpressionException, UnknownSymbolException {

    if (type1.equals(String.class) || type2.equals(String.class)) {
      JavaClassResolver.validateStringExpression(op, type1, type2);
      return String.class;
    } else {
      if (type1.isPrimitive()) {
        if (!type2.isPrimitive()) {
          throw new InvalidExpressionException("Cannot use the operator "
              + op.getName() + " with the types " + type1 + ", " + type2);
        }
        return JavaClassResolver.validateExpression(op, type1, type2);
      } else {
        if (!type1.equals(type2)) {
          throw new InvalidExpressionException("Cannot use the operator "
              + op.getName() + " with the types " + type1 + ", " + type2);
        }
        return JavaClassResolver.validateOperationMethod(op, type1);
      }
    }
  }

  /**
   * Remove the current context of arguments.
   */
  private void removeArgsContext() {
    this.argsStack.remove(this.argsStack.size() - 1);
  }

  /**
   * Remove the current variables context.
   */
  private void removeVariableContext() {
    for (String s : this.usedVariables.get(this.usedVariables.size() - 1)) {
      this.symbolsTable.pop(s);
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
  public Class<?> visit(Boolean_Expression boolean_expression, Void param)
      throws Throwable {
    return boolean.class;
  }

  @Override
  public Class<?> visit(Boolean_Type boolean_type, Void param) throws Throwable {
    return boolean.class;
  }

  @Override
  public Class<?> visit(Class_Type class_type, Void param) throws Throwable {
    if (!this.imports.isImported(class_type.getIdentifier_())) {
      throw new UnresolvedClassException(class_type.getIdentifier_()
          + " cannot be resolved to a type.");
    }
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
    Class<?> type = conditional_statement.getExpression().accept(this, param);
    if (!type.equals(boolean.class)) {
      throw new InvalidExpressionException("Find " + type
          + " in conditional statement. Expected boolean.");
    }
    conditional_statement.getMultiple_statement().accept(this, param);
    conditional_statement.getElse_statement().accept(this, param);
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
  public Class<?> visit(Double_Expression double_expression, Void param)
      throws Throwable {
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
  public Class<?> visit(Float_Expression float_expression, Void param)
      throws Throwable {
    return float.class;
  }

  @Override
  public Class<?> visit(Float_Type float_type, Void param) throws Throwable {
    return float.class;
  }

  @Override
  public Class<?> visit(Foreach_Statement foreach_statement, Void param)
      throws Throwable {
    addVariableContext();
    getSymbol(foreach_statement.getIdentifier_());
    Class<?> t = foreach_statement.getExpression().accept(this, param);
    JavaClassResolver.validateIterable(t);
    foreach_statement.getMultiple_statement().accept(this, param);
    removeVariableContext();
    return null;
  }

  @Override
  public Class<?> visit(
      Foreach_Statement_With_Declaration foreach_statement_with_declaration,
      Void param) throws Throwable {
    addVariableContext();
    Class<?> type = foreach_statement_with_declaration.getType().accept(this,
        param);
    addSymbol(foreach_statement_with_declaration.getIdentifier_(),
        new Variable(type));
    Class<?> type2 = foreach_statement_with_declaration.getExpression().accept(
        this, param);
    JavaClassResolver.validateIterable(type2);
    foreach_statement_with_declaration.getMultiple_statement().accept(this,
        param);
    removeVariableContext();
    return null;
  }

  @Override
  public Class<?> visit(Function_Call_Expression function_call_expression,
      Void param) throws Throwable {
    addNewArgsContext();
    function_call_expression.getArg_list().accept(this, param);
    try {
      Function f = this.functions.validateFunctionCall(function_call_expression
          .getIdentifier_(), getArgs());
      removeArgsContext();
      return f.getReturnType();
    } catch (UnknownSymbolException e) {
      Class<?> type = this.imports.getClass(function_call_expression
          .getIdentifier_());
      Constructor<?> cons = JavaClassResolver.validateConstructor(type,
          getArgs());
      Function f = this.functions.addFunction(function_call_expression
          .getIdentifier_(), type, Arrays.asList(cons.getParameterTypes()));
      f.setConstructor(true);
      removeArgsContext();
      return type;
    }
  }

  @Override
  public Class<?> visit(Function_Declaration function_declaration, Void param)
      throws Throwable {
    addVariableContext();
    addNewArgsContext();

    function_declaration.getParameter_list().accept(this, param);
    this.currentFunction = this.functions.addFunction(function_declaration
        .getIdentifier_(), function_declaration.getType().accept(this, param),
        getArgs());
    removeArgsContext();
    function_declaration.getMultiple_statement().accept(this, param);
    if (!lessOneReturn) {
      throw new InvalidExpressionException(
          "Return statement missing in function "
              + function_declaration.getIdentifier_()
              + ". This function must return a "
              + this.currentFunction.getReturnType() + " value.");
    }
    this.lessOneReturn = false;
    this.currentFunction = null;
    removeVariableContext();
    return null;
  }

  @Override
  public Class<?> visit(Identifier_Expression identifier_expression, Void param)
      throws Throwable {
    final Variable v = getSymbol(identifier_expression.getIdentifier_());
    return v.getDeclaredClass();
  }

  @Override
  public Class<?> visit(Import_Statement import_statement, Void param)
      throws Throwable {
    import_statement.getPackage_name().accept(this, param);
    this.imports.validateImport(this.packageList);
    this.packageList.clear();
    return null;
  }

  @Override
  public Class<?> visit(Import_Statement_Star import_statement_star, Void param)
      throws Throwable {
    for (Import_Statement statement : import_statement_star.nodeList()) {
      statement.accept(this, param);
    }
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
  public Class<?> visit(Method_Call_Expression method_call_expression,
      Void param) throws Throwable {
    addNewArgsContext();
    method_call_expression.getArg_list().accept(this, param);
    Variable v = this.symbolsTable.get(method_call_expression.getIdentifier_());
    Method m = JavaClassResolver.validateMethod(v.getDeclaredClass(),
        method_call_expression.getIdentifier_2(), getArgs());
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
    return JavaClassResolver.validateExpression(Operator.MIN,
        minus_simple_expression.getExpression().accept(this, param), null);
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
    multiple_package_name.getPackage_name().accept(this, param);
    packageList.add(multiple_package_name.getIdentifier_());
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
    JavaClassResolver.validateExpression(Operator.NOT, not_expression
        .getExpression().accept(this, param), null);
    return boolean.class;
  }

  @Override
  public Class<?> visit(Number_Expression number_expression, Void param)
      throws Throwable {
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
    Variable v = new Variable(t);
    addSymbol(parameter.getIdentifier_(), v);
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
    Class<?> type = print_statement.getExpression().accept(this, param);
    if (type.equals(void.class)) {
      throw new UnexpectedTypeException("Cannot print a void expression.");
    }
    return null;
  }

  @Override
  public Class<?> visit(Return_Statement return_statement, Void param)
      throws Throwable {
    if (this.currentFunction == null) {
      throw new InvalidExpressionException(
          "Return statement must be declared in a function body.");
    }
    Class<?> t = return_statement.getExpression().accept(this, param);
    JavaClassResolver.validateCast(this.currentFunction.getReturnType(), t);
    lessOneReturn = true;
    return null;
  }

  @Override
  public Class<?> visit(Return_Statement_Empty return_statement_empty,
      Void param) throws Throwable {
    if (this.currentFunction == null) {
      throw new InvalidExpressionException(
          "Return statement must be declared in a function body.");
    }
    if (!this.currentFunction.getReturnType().equals(void.class)) {
      throw new InvalidExpressionException(
          "Cannot return void type in function " + this.currentFunction
              + ". Expected " + this.currentFunction.getReturnType() + ".");
    }
    lessOneReturn = true;
    return null;
  }

  @Override
  public Class<?> visit(Single_Package_Name single_package_name, Void param)
      throws Throwable {
    packageList.add(single_package_name.getIdentifier_());
    return null;
  }

  @Override
  public Class<?> visit(Start start, Void param) throws Throwable {
    addVariableContext();
    start.getImport_statement_star().accept(this, param);
    start.getInstruction_star().accept(this, param);
    removeVariableContext();
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
  public Class<?> visit(String_Expression string_expression, Void param)
      throws Throwable {
    return String.class;
  }

  @Override
  public Class<?> visit(String_Type string_type, Void param) throws Throwable {
    return String.class;
  }

  @Override
  public Class<?> visit(Variable_Assignment variable_assignment, Void param)
      throws Throwable {
    Variable v = this.symbolsTable.get(variable_assignment.getIdentifier_());
    JavaClassResolver.validateCast(v.getDeclaredClass(), variable_assignment
        .getExpression().accept(this, param));
    return v.getDeclaredClass();
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
      Variable_Declaration_With_Assignment variable_declaration_with_assignment,
      Void param) throws Throwable {
    Class<?> type1 = variable_declaration_with_assignment.getType().accept(
        this, param);
    if (type1.equals(void.class)) {
      throw new UnexpectedTypeException(
          "A variable cannot be declared with type void.");
    }
    Class<?> type2 = variable_declaration_with_assignment.getExpression()
        .accept(this, param);
    JavaClassResolver.validateCast(type1, type2);
    addSymbol(variable_declaration_with_assignment.getIdentifier_(),
        new Variable(type1));
    return null;
  }

  @Override
  public Class<?> visit(
      Variable_Declaration_Without_Assignment variable_declaration_without_assignment,
      Void param) throws Throwable {
    Class<?> type1 = variable_declaration_without_assignment.getType().accept(
        this, param);
    if (type1.equals(void.class)) {
      throw new UnexpectedTypeException(
          "A variable cannot be declared with type void.");
    }
    addSymbol(variable_declaration_without_assignment.getIdentifier_(),
        new Variable(type1));
    return null;
  }

  @Override
  public Class<?> visit(Void_Type void_type, Void param) throws Throwable {
    return void.class;
  }

}
