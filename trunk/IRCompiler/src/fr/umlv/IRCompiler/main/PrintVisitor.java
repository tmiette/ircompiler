package fr.umlv.IRCompiler.main;

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
import fr.umlv.IRCompiler.tatoo.tools.Pow_Expression;
import fr.umlv.IRCompiler.tatoo.tools.Print_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Return_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Return_Statement_Empty;
import fr.umlv.IRCompiler.tatoo.tools.Single_Package_Name;
import fr.umlv.IRCompiler.tatoo.tools.Start;
import fr.umlv.IRCompiler.tatoo.tools.Statement;
import fr.umlv.IRCompiler.tatoo.tools.Statement_Star;
import fr.umlv.IRCompiler.tatoo.tools.String_Expression;
import fr.umlv.IRCompiler.tatoo.tools.String_Type;
import fr.umlv.IRCompiler.tatoo.tools.Variable_Assignment;
import fr.umlv.IRCompiler.tatoo.tools.Variable_Assignment_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Variable_Declaration_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Variable_Declaration_With_Assignment;
import fr.umlv.IRCompiler.tatoo.tools.Variable_Declaration_Without_Assignment;
import fr.umlv.IRCompiler.tatoo.tools.Visitor;
import fr.umlv.IRCompiler.tatoo.tools.Void_Type;

/**
 * This visitor print the code which will be interpreted by the compiler.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public class PrintVisitor extends Visitor<Void, Void, Void, Throwable> {

  @Override
  public Void visit(Another_Arg another_arg, Void param) throws Throwable {
    System.out.print(", ");
    another_arg.getExpression().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Another_Arg_Star another_arg_star, Void param)
      throws Throwable {
    for (Another_Arg arg : another_arg_star.nodeList()) {
      arg.accept(this, param);
    }
    return null;
  }

  @Override
  public Void visit(Another_Parameter another_parameter, Void param)
      throws Throwable {
    System.out.print(", ");
    another_parameter.getParameter().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Another_Parameter_Star another_parameter_star, Void param)
      throws Throwable {
    for (Another_Parameter parameter : another_parameter_star.nodeList()) {
      parameter.accept(this, param);
    }
    return null;
  }

  @Override
  public Void visit(Args_List args_list, Void param) throws Throwable {
    args_list.getExpression().accept(this, param);
    args_list.getAnother_arg_star().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Boolean_Expression boolean_expression, Void param)
      throws Throwable {
    System.out.print(boolean_expression.getBoolean_());
    return null;
  }

  @Override
  public Void visit(Boolean_Type boolean_type, Void param) throws Throwable {
    System.out.print("bool");
    return null;
  }

  @Override
  public Void visit(Class_Type class_type, Void param) throws Throwable {
    System.out.print(class_type.getIdentifier_());
    return null;
  }

  @Override
  public Void visit(Comment_Statement comment_statement, Void param)
      throws Throwable {
    return null;
  }

  @Override
  public Void visit(Conditional_Statement conditional_statement, Void param)
      throws Throwable {
    System.out.print("if ");
    conditional_statement.getExpression().accept(this, param);
    System.out.println(" then");
    conditional_statement.getMultiple_statement().accept(this, param);
    conditional_statement.getElse_statement().accept(this, param);
    System.out.println("fi");
    return null;
  }

  @Override
  public Void visit(Div_Expression div_expression, Void param) throws Throwable {
    div_expression.getExpression().accept(this, param);
    System.out.print(" / ");
    div_expression.getExpression2().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Double_Expression double_expression, Void param)
      throws Throwable {
    System.out.println(double_expression.getDouble_());
    return null;
  }

  @Override
  public Void visit(Double_Type double_type, Void param) throws Throwable {
    System.out.println("double");
    return null;
  }

  @Override
  public Void visit(Else_Statement else_statement, Void param) throws Throwable {
    System.out.println("else ");
    else_statement.getMultiple_statement().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Empty_Arg_List empty_arg_list, Void param) throws Throwable {
    return null;
  }

  @Override
  public Void visit(Empty_Else_Statement empty_else_statement, Void param)
      throws Throwable {
    return null;
  }

  @Override
  public Void visit(Empty_Parameter_List empty_parameter_list, Void param)
      throws Throwable {
    System.out.print("void");
    return null;
  }

  @Override
  public Void visit(Empty_Statement empty_statement, Void param)
      throws Throwable {
    return null;
  }

  @Override
  public Void visit(Equal_Expression equal_expression, Void param)
      throws Throwable {
    equal_expression.getExpression().accept(this, param);
    System.out.print(" == ");
    equal_expression.getExpression2().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Expression_Statement expression_statement, Void param)
      throws Throwable {
    expression_statement.getExpression().accept(this, param);
    System.out.println(";");
    return null;
  }

  @Override
  public Void visit(Float_Expression float_expression, Void param)
      throws Throwable {
    System.out.println(float_expression.getFloat_());
    return null;
  }

  @Override
  public Void visit(Float_Type float_type, Void param) throws Throwable {
    System.out.println("float");
    return null;
  }

  @Override
  public Void visit(Foreach_Statement foreach_statement, Void param)
      throws Throwable {
    System.out.print("foreach ");
    System.out.print(foreach_statement.getIdentifier_());
    System.out.print(" in ");
    foreach_statement.getExpression().accept(this, param);
    System.out.println(" do");
    foreach_statement.getMultiple_statement().accept(this, param);
    System.out.println("done");
    return null;
  }

  @Override
  public Void visit(
      Foreach_Statement_With_Declaration foreach_statement_with_declaration,
      Void param) throws Throwable {
    System.out.print("foreach ");
    foreach_statement_with_declaration.getType().accept(this, param);
    System.out.print(foreach_statement_with_declaration.getIdentifier_());
    System.out.print(" in ");
    foreach_statement_with_declaration.getExpression().accept(this, param);
    System.out.println(" do");
    foreach_statement_with_declaration.getMultiple_statement().accept(this,
        param);
    System.out.println("done");
    return null;
  }

  @Override
  public Void visit(Function_Call_Expression function_call_expression,
      Void param) throws Throwable {
    System.out.print(function_call_expression.getIdentifier_());
    System.out.print("(");
    function_call_expression.getArg_list().accept(this, param);
    System.out.print(")");
    return null;
  }

  @Override
  public Void visit(Function_Declaration function_declaration, Void param)
      throws Throwable {
    System.out.println();
    System.out.print("function ");
    function_declaration.getType().accept(this, param);
    System.out.print(" ");
    System.out.print(function_declaration.getIdentifier_());
    System.out.print("(");
    function_declaration.getParameter_list().accept(this, param);
    System.out.print(")");
    System.out.println("");
    function_declaration.getMultiple_statement().accept(this, param);
    System.out.println("end");
    System.out.println();
    return null;
  }

  @Override
  public Void visit(Identifier_Expression identifier_expression, Void param)
      throws Throwable {
    System.out.print(identifier_expression.getIdentifier_());
    return null;
  }

  @Override
  public Void visit(Import_Statement import_statement, Void param)
      throws Throwable {
    System.out.print("import ");
    import_statement.getPackage_name().accept(this, param);
    System.out.println(";");
    return null;
  }

  @Override
  public Void visit(Import_Statement_Star import_statement_star, Void param)
      throws Throwable {
    for (Import_Statement statement : import_statement_star.nodeList()) {
      statement.accept(this, param);
    }
    return null;
  }

  @Override
  public Void visit(Inequal_Expression inequal_expression, Void param)
      throws Throwable {
    inequal_expression.getExpression().accept(this, param);
    System.out.print(" != ");
    inequal_expression.getExpression2().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Instruction instruction, Void param) throws Throwable {
    instruction.getFunction_declaration().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Instruction_Star instruction_star, Void param)
      throws Throwable {
    for (IInstruction instruction : instruction_star.nodeList()) {
      instruction.accept(this, param);
    }
    return null;
  }

  @Override
  public Void visit(Integer_Type integer_type, Void param) throws Throwable {
    System.out.print("int");
    return null;
  }

  @Override
  public Void visit(Method_Call_Expression method_call_expression, Void param)
      throws Throwable {
    System.out.print(method_call_expression.getIdentifier_());
    System.out.print(".");
    System.out.print(method_call_expression.getIdentifier_2());
    System.out.print("(");
    method_call_expression.getArg_list().accept(this, param);
    System.out.print(")");
    return null;
  }

  @Override
  public Void visit(Minus_Expression minus_expression, Void param)
      throws Throwable {
    minus_expression.getExpression().accept(this, param);
    System.out.print(" - ");
    minus_expression.getExpression2().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Minus_Simple_Expression minus_simple_expression, Void param)
      throws Throwable {
    System.out.print("-");
    minus_simple_expression.getExpression().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Mult_Expression mult_expression, Void param)
      throws Throwable {
    mult_expression.getExpression().accept(this, param);
    System.out.print(" * ");
    mult_expression.getExpression2().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Multiple_Package_Name multiple_package_name, Void param)
      throws Throwable {
    multiple_package_name.getPackage_name().accept(this, param);
    System.out.print(".");
    System.out.print(multiple_package_name.getIdentifier_());
    return null;
  }

  @Override
  public Void visit(Multiple_Statement multiple_statement, Void param)
      throws Throwable {
    multiple_statement.getStatement_star().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Not_Expression not_expression, Void param) throws Throwable {
    System.out.print("not ");
    not_expression.getExpression().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Number_Expression number_expression, Void param)
      throws Throwable {
    System.out.print(number_expression.getNumber_());
    return null;
  }

  @Override
  public Void visit(Par_Expression par_expression, Void param) throws Throwable {
    System.out.print("(");
    par_expression.getExpression().accept(this, param);
    System.out.print(")");
    return null;
  }

  @Override
  public Void visit(Parameter parameter, Void param) throws Throwable {
    parameter.getType().accept(this, param);
    System.out.print(" ");
    System.out.print(parameter.getIdentifier_());
    return null;
  }

  @Override
  public Void visit(Parameter_List parameter_list, Void param) throws Throwable {
    parameter_list.getParameter().accept(this, param);
    parameter_list.getAnother_parameter_star().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Plus_Expression plus_expression, Void param)
      throws Throwable {
    plus_expression.getExpression().accept(this, param);
    System.out.print(" + ");
    plus_expression.getExpression2().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Pow_Expression pow_expression, Void param) throws Throwable {
    pow_expression.getExpression().accept(this, param);
    System.out.print(" ^ ");
    pow_expression.getExpression2().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Print_Statement print_statement, Void param)
      throws Throwable {
    System.out.print("print ");
    print_statement.getExpression().accept(this, param);
    System.out.println(";");
    return null;
  }

  @Override
  public Void visit(Return_Statement return_statement, Void param)
      throws Throwable {
    System.out.print("return ");
    return_statement.getExpression().accept(this, param);
    System.out.println(";");
    return null;
  }

  @Override
  public Void visit(Return_Statement_Empty return_statement_empty, Void param)
      throws Throwable {
    System.out.println("return;");
    return null;
  }

  @Override
  public Void visit(Single_Package_Name single_package_name, Void param)
      throws Throwable {
    System.out.print(single_package_name.getIdentifier_());
    return null;
  }

  @Override
  public Void visit(Start start, Void param) throws Throwable {
    start.getImport_statement_star().accept(this, param);
    System.out.println("");
    start.getInstruction_star().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Statement statement, Void param) throws Throwable {
    statement.getStatement().accept(this, param);
    return null;
  }

  @Override
  public Void visit(Statement_Star statement_star, Void param) throws Throwable {
    for (IStatement statement : statement_star.nodeList()) {
      statement.accept(this, param);
    }
    return null;
  }

  @Override
  public Void visit(Variable_Assignment variable_assignment, Void param)
      throws Throwable {
    System.out.println(variable_assignment.getIdentifier_());
    System.out.println(" = ");
    variable_assignment.getExpression().accept(this, param);
    return null;
  }

  @Override
  public Void visit(
      Variable_Assignment_Statement variable_assignment_statement, Void param)
      throws Throwable {
    variable_assignment_statement.getVariable_assignment().accept(this, param);
    return null;
  }

  @Override
  public Void visit(
      Variable_Declaration_Statement variable_declaration_statement, Void param)
      throws Throwable {
    variable_declaration_statement.getVariable_declaration()
        .accept(this, param);
    return null;
  }

  @Override
  public Void visit(
      Variable_Declaration_With_Assignment variable_declaration_with_assignment,
      Void param) throws Throwable {
    variable_declaration_with_assignment.getType().accept(this, param);
    System.out.print(" ");
    System.out.print(variable_declaration_with_assignment.getIdentifier_());
    System.out.print(" = ");
    variable_declaration_with_assignment.getExpression().accept(this, param);
    System.out.println(";");
    return null;
  }

  @Override
  public Void visit(
      Variable_Declaration_Without_Assignment variable_declaration_without_assignment,
      Void param) throws Throwable {
    variable_declaration_without_assignment.getType().accept(this, param);
    System.out.print(" ");
    System.out.print(variable_declaration_without_assignment.getIdentifier_());
    System.out.println(";");
    return null;
  }

  @Override
  public Void visit(Void_Type void_type, Void param) throws Throwable {
    System.out.println("void");
    return null;
  }

  @Override
  public Void visit(String_Expression string_expression, Void param)
      throws Throwable {
    System.out.print(string_expression.getString_());
    return null;
  }

  @Override
  public Void visit(String_Type string_type, Void param) throws Throwable {
    System.out.print("string");
    return null;
  }

}
