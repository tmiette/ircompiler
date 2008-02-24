package fr.umlv.IRCompiler.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.umlv.IRCompiler.tatoo.tools.Another_Arg;
import fr.umlv.IRCompiler.tatoo.tools.Another_Arg_Star;
import fr.umlv.IRCompiler.tatoo.tools.Another_Parameter;
import fr.umlv.IRCompiler.tatoo.tools.Another_Parameter_Star;
import fr.umlv.IRCompiler.tatoo.tools.Args_List;
import fr.umlv.IRCompiler.tatoo.tools.Boolean_Expression;
import fr.umlv.IRCompiler.tatoo.tools.Boolean_Type;
import fr.umlv.IRCompiler.tatoo.tools.Class_Type;
import fr.umlv.IRCompiler.tatoo.tools.Comment_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Conditional_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Div_Expression;
import fr.umlv.IRCompiler.tatoo.tools.Double_Expression;
import fr.umlv.IRCompiler.tatoo.tools.Double_Type;
import fr.umlv.IRCompiler.tatoo.tools.Else_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Empty_Arg_List;
import fr.umlv.IRCompiler.tatoo.tools.Empty_Else_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Empty_Parameter_List;
import fr.umlv.IRCompiler.tatoo.tools.Empty_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Equal_Expression;
import fr.umlv.IRCompiler.tatoo.tools.Expression_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Float_Expression;
import fr.umlv.IRCompiler.tatoo.tools.Float_Type;
import fr.umlv.IRCompiler.tatoo.tools.Foreach_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Foreach_Statement_With_Declaration;
import fr.umlv.IRCompiler.tatoo.tools.Function_Call_Expression;
import fr.umlv.IRCompiler.tatoo.tools.Function_Declaration;
import fr.umlv.IRCompiler.tatoo.tools.IInstruction;
import fr.umlv.IRCompiler.tatoo.tools.IStatement;
import fr.umlv.IRCompiler.tatoo.tools.Identifier_Expression;
import fr.umlv.IRCompiler.tatoo.tools.Import_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Import_Statement_Star;
import fr.umlv.IRCompiler.tatoo.tools.Inequal_Expression;
import fr.umlv.IRCompiler.tatoo.tools.Instruction;
import fr.umlv.IRCompiler.tatoo.tools.Instruction_Star;
import fr.umlv.IRCompiler.tatoo.tools.Integer_Type;
import fr.umlv.IRCompiler.tatoo.tools.Method_Call_Expression;
import fr.umlv.IRCompiler.tatoo.tools.Minus_Expression;
import fr.umlv.IRCompiler.tatoo.tools.Minus_Simple_Expression;
import fr.umlv.IRCompiler.tatoo.tools.Mult_Expression;
import fr.umlv.IRCompiler.tatoo.tools.Multiple_Package_Name;
import fr.umlv.IRCompiler.tatoo.tools.Multiple_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Not_Expression;
import fr.umlv.IRCompiler.tatoo.tools.Number_Expression;
import fr.umlv.IRCompiler.tatoo.tools.Par_Expression;
import fr.umlv.IRCompiler.tatoo.tools.Parameter;
import fr.umlv.IRCompiler.tatoo.tools.Parameter_List;
import fr.umlv.IRCompiler.tatoo.tools.Plus_Expression;
import fr.umlv.IRCompiler.tatoo.tools.Print_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Return_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Single_Package_Name;
import fr.umlv.IRCompiler.tatoo.tools.Start;
import fr.umlv.IRCompiler.tatoo.tools.Statement;
import fr.umlv.IRCompiler.tatoo.tools.Statement_Star;
import fr.umlv.IRCompiler.tatoo.tools.Variable_Assignment;
import fr.umlv.IRCompiler.tatoo.tools.Variable_Assignment_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Variable_Declaration_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Variable_Declaration_With_Assignment;
import fr.umlv.IRCompiler.tatoo.tools.Variable_Declaration_Without_Assignment;
import fr.umlv.IRCompiler.tatoo.tools.Visitor;
import fr.umlv.IRCompiler.tatoo.tools.Void_Type;

public class SemanticVisitor extends Visitor<Class<?>, Void, Void, Throwable> {

  private final SymbolTable<Variable> symbolsTable;

  private final ArrayList<String> packageList;

  private final ArrayList<ArrayList<Class<?>>> argsStack;

  private final ArrayList<ArrayList<String>> usedVariables;

  private final ArrayList<Integer> globalVariables;

  private final Import imports;

  private final FunctionCollection functions;

  private Function currentFunction;

  private boolean lessOneReturn = false;

  public SemanticVisitor() {
    this.symbolsTable = new IRCompilerSymbolTable<Variable>();
    this.packageList = new ArrayList<String>();
    this.imports = new Import();
    this.functions = new FunctionCollection();
    this.usedVariables = new ArrayList<ArrayList<String>>();
    this.argsStack = new ArrayList<ArrayList<Class<?>>>();
    this.globalVariables = new ArrayList<Integer>();
  }

  private void addNewArgsContext() {
    this.argsStack.add(new ArrayList<Class<?>>());
  }

  private void removeArgsContext() {
    this.argsStack.remove(this.argsStack.size() - 1);
  }

  private void addArgs(Class<?> arg) {
    this.argsStack.get(this.argsStack.size() - 1).add(arg);
  }

  private ArrayList<Class<?>> getArgs() {
    return this.argsStack.get(this.argsStack.size() - 1);
  }

  private void addVariableContext() {
    this.usedVariables.add(new ArrayList<String>());
  }

  private void removeVariableContext() {
    for (String s : this.usedVariables.get(this.usedVariables.size() - 1)) {
      this.symbolsTable.pop(s);
    }
    this.usedVariables.remove(this.usedVariables.size() - 1);
  }

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

  private Variable getSymbol(String symbol) throws UnknownSymbolException {
    return this.symbolsTable.get(symbol);
  }

  public Import getImports() {
    return this.imports;
  }

  public List<Integer> getGlobalVariables() {
    return this.globalVariables;
  }

  public FunctionCollection getFunctions() {
    return this.functions;
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
  public Class<?> visit(Import_Statement_Star import_statement_star, Void param)
      throws Throwable {
    for (Import_Statement statement : import_statement_star.nodeList()) {
      statement.accept(this, param);
    }
    return null;
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
  public Class<?> visit(Single_Package_Name single_package_name, Void param)
      throws Throwable {
    packageList.add(single_package_name.getIdentifier_());
    return null;
  }

  @Override
  public Class<?> visit(Multiple_Package_Name multiple_package_name, Void param)
      throws Throwable {
    multiple_package_name.getPackage_name().accept(this, param);
    packageList.add(multiple_package_name.getIdentifier_());
    return null;
  }

  @Override
  public Class<?> visit(Integer_Type integer_type, Void param) throws Throwable {
    return int.class;
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
  public Class<?> visit(Empty_Statement empty_statement, Void param)
      throws Throwable {
    return null;
  }

  @Override
  public Class<?> visit(Div_Expression div_expression, Void param)
      throws Throwable {
    return JavaClassResolver.validateExpression(Operator.DIV, div_expression
        .getExpression().accept(this, param), div_expression.getExpression2()
        .accept(this, param));
  }

  @Override
  public Class<?> visit(Equal_Expression equal_expression, Void param)
      throws Throwable {
    return JavaClassResolver.validateExpression(Operator.EQU, equal_expression
        .getExpression().accept(this, param), equal_expression.getExpression2()
        .accept(this, param));
  }

  @Override
  public Class<?> visit(Inequal_Expression inequal_expression, Void param)
      throws Throwable {
    return JavaClassResolver.validateExpression(Operator.INE,
        inequal_expression.getExpression().accept(this, param),
        inequal_expression.getExpression2().accept(this, param));
  }

  @Override
  public Class<?> visit(Minus_Expression minus_expression, Void param)
      throws Throwable {
    return JavaClassResolver.validateExpression(Operator.SUB, minus_expression
        .getExpression().accept(this, param), minus_expression.getExpression2()
        .accept(this, param));
  }

  @Override
  public Class<?> visit(Mult_Expression mult_expression, Void param)
      throws Throwable {
    return JavaClassResolver.validateExpression(Operator.MUL, mult_expression
        .getExpression().accept(this, param), mult_expression.getExpression2()
        .accept(this, param));
  }

  @Override
  public Class<?> visit(Not_Expression not_expression, Void param)
      throws Throwable {
    return JavaClassResolver.validateExpression(Operator.NOT, not_expression
        .getExpression().accept(this, param), null);
  }

  @Override
  public Class<?> visit(Plus_Expression plus_expression, Void param)
      throws Throwable {
    return JavaClassResolver.validateExpression(Operator.ADD, plus_expression
        .getExpression().accept(this, param), plus_expression.getExpression2()
        .accept(this, param));
  }

  @Override
  public Class<?> visit(Minus_Simple_Expression minus_simple_expression,
      Void param) throws Throwable {
    return JavaClassResolver.validateExpression(Operator.MIN,
        minus_simple_expression.getExpression().accept(this, param), null);
  }

  @Override
  public Class<?> visit(Expression_Statement expression_statement, Void param)
      throws Throwable {
    expression_statement.getExpression().accept(this, param);
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
  public Class<?> visit(Number_Expression number_expression, Void param)
      throws Throwable {
    return int.class;
  }

  @Override
  public Class<?> visit(Boolean_Expression boolean_expression, Void param)
      throws Throwable {
    return boolean.class;
  }

  @Override
  public Class<?> visit(Identifier_Expression identifier_expression, Void param)
      throws Throwable {
    Variable v = getSymbol(identifier_expression.getIdentifier_());
    return v.getDeclaredClass();
  }

  @Override
  public Class<?> visit(Conditional_Statement conditional_statement, Void param)
      throws Throwable {
    addVariableContext();
    if (!conditional_statement.getExpression().accept(this, param).equals(
        Boolean.TYPE)) {
      throw new InvalidExpressionException(
          "Expression in conditional statement must return a boolean value.");
    }
    conditional_statement.getMultiple_statement().accept(this, param);
    conditional_statement.getElse_statement().accept(this, param);
    removeVariableContext();
    return null;
  }

  @Override
  public Class<?> visit(Empty_Else_Statement empty_else_statement, Void param)
      throws Throwable {
    return null;
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
  public Class<?> visit(Multiple_Statement multiple_statement, Void param)
      throws Throwable {
    multiple_statement.getStatement_star().accept(this, param);
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
  public Class<?> visit(Par_Expression par_expression, Void param)
      throws Throwable {
    return par_expression.getExpression().accept(this, param);
  }

  @Override
  public Class<?> visit(Print_Statement print_statement, Void param)
      throws Throwable {
    print_statement.getExpression().accept(this, param);
    return null;
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
      throw new InvalidExpressionException("Function "
          + function_declaration.getIdentifier_() + " must return a "
          + this.currentFunction.getReturnType() + " value");
    }
    this.lessOneReturn = false;
    this.currentFunction = null;
    removeVariableContext();
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
  public Class<?> visit(Empty_Parameter_List empty_parameter_list, Void param)
      throws Throwable {
    return null;
  }

  @Override
  public Class<?> visit(Return_Statement return_statement, Void param)
      throws Throwable {
    if (this.currentFunction == null) {
      throw new InvalidExpressionException(
          "The return statement must be in a function body.");
    }
    Class<?> t = return_statement.getExpression().accept(this, param);
    if (!t.equals(this.currentFunction.getReturnType())) {
      throw new InvalidExpressionException(
          "The return statement must return a value of type " + t);
    }
    lessOneReturn = true;
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
  public Class<?> visit(Args_List args_list, Void param) throws Throwable {
    addArgs(args_list.getExpression().accept(this, param));
    args_list.getAnother_arg_star().accept(this, param);
    return null;
  }

  @Override
  public Class<?> visit(Empty_Arg_List empty_arg_list, Void param)
      throws Throwable {
    return null;
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
  public Class<?> visit(
      Variable_Declaration_With_Assignment variable_declaration_with_assignment,
      Void param) throws Throwable {
    Class<?> type1 = variable_declaration_with_assignment.getType().accept(
        this, param);
    Class<?> type2 = variable_declaration_with_assignment.getExpression()
        .accept(this, param);
    JavaClassResolver.validateExpression(Operator.AFF, type1, type2);
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
    addSymbol(variable_declaration_without_assignment.getIdentifier_(),
        new Variable(type1));
    return null;
  }

  @Override
  public Class<?> visit(
      Variable_Assignment_Statement variable_assignment_statement, Void param)
      throws Throwable {
    variable_assignment_statement.getVariable_assignment().accept(this, param);
    return null;
  }

  @Override
  public Class<?> visit(Variable_Assignment variable_assignment, Void param)
      throws Throwable {
    Variable v = this.symbolsTable.get(variable_assignment.getIdentifier_());
    return JavaClassResolver.validateExpression(Operator.AFF, v
        .getDeclaredClass(), variable_assignment.getExpression().accept(this,
        param));
  }

  @Override
  public Class<?> visit(Void_Type void_type, Void param) throws Throwable {
    return void.class;
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

    Variable v = getSymbol(foreach_statement.getIdentifier_());
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

    Class<?> t1 = foreach_statement_with_declaration.getType().accept(this,
        param);
    addSymbol(foreach_statement_with_declaration.getIdentifier_(),
        new Variable(t1));
    Class<?> t2 = foreach_statement_with_declaration.getExpression().accept(
        this, param);
    JavaClassResolver.validateIterable(t2);

    foreach_statement_with_declaration.getMultiple_statement().accept(this,
        param);

    removeVariableContext();
    return null;
  }

}
