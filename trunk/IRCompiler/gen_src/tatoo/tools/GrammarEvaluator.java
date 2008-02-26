package tatoo.tools;

import tatoo.tools.Another_Arg;
import tatoo.tools.Another_Arg_Star;
import tatoo.tools.Another_Parameter;
import tatoo.tools.Another_Parameter_Star;
import tatoo.tools.Function_Declaration;
import tatoo.tools.IArg_List;
import tatoo.tools.IElse_Statement;
import tatoo.tools.IExpression;
import tatoo.tools.IInstruction;
import tatoo.tools.IPackage_Name;
import tatoo.tools.IParameter_List;
import tatoo.tools.IStatement;
import tatoo.tools.IType;
import tatoo.tools.IVariable_Declaration;
import tatoo.tools.Import_Statement;
import tatoo.tools.Import_Statement_Star;
import tatoo.tools.Instruction_Star;
import tatoo.tools.Multiple_Statement;
import tatoo.tools.Parameter;
import tatoo.tools.Start;
import tatoo.tools.Statement_Star;
import tatoo.tools.Variable_Assignment;

/** 
 *  This class is generated - please do not edit it 
 */
public interface GrammarEvaluator {
  /** This methods is called after the reduction of the non terminal start
   *  by the grammar production start.
   *  <code>start := import_statement_star instruction_star </code>
   */
  public  Start start(Import_Statement_Star import_statement_star, Instruction_Star instruction_star);
  /** This methods is called after the reduction of the non terminal import_statement
   *  by the grammar production import_statement.
   *  <code>import_statement := 'import_' package_name 'semicolon_' </code>
   */
  public  Import_Statement import_statement(IPackage_Name package_name);
  /** This methods is called after the reduction of the non terminal package_name
   *  by the grammar production single_package_name.
   *  <code>package_name := 'identifier_' </code>
   */
  public  IPackage_Name single_package_name(String identifier_);
  /** This methods is called after the reduction of the non terminal package_name
   *  by the grammar production multiple_package_name.
   *  <code>package_name := package_name 'dot_' 'identifier_' </code>
   */
  public  IPackage_Name multiple_package_name(IPackage_Name package_name, String identifier_);
  /** This methods is called after the reduction of the non terminal instruction
   *  by the grammar production instruction.
   *  <code>instruction := function_declaration </code>
   */
  public  IInstruction instruction(Function_Declaration function_declaration);
  /** This methods is called after the reduction of the non terminal instruction
   *  by the grammar production statement.
   *  <code>instruction := statement </code>
   */
  public  IInstruction statement(IStatement statement);
  /** This methods is called after the reduction of the non terminal function_declaration
   *  by the grammar production function_declaration.
   *  <code>function_declaration := 'function_' type 'identifier_' 'lpar_' parameter_list 'rpar_' multiple_statement 'end_function_' </code>
   */
  public  Function_Declaration function_declaration(IType type, String identifier_, IParameter_List parameter_list, Multiple_Statement multiple_statement);
  /** This methods is called after the reduction of the non terminal parameter_list
   *  by the grammar production empty_parameter_list.
   *  <code>parameter_list := </code>
   */
  public  IParameter_List empty_parameter_list();
  /** This methods is called after the reduction of the non terminal parameter_list
   *  by the grammar production parameter_list.
   *  <code>parameter_list := parameter another_parameter_star </code>
   */
  public  IParameter_List parameter_list(Parameter parameter, Another_Parameter_Star another_parameter_star);
  /** This methods is called after the reduction of the non terminal parameter
   *  by the grammar production parameter.
   *  <code>parameter := type 'identifier_' </code>
   */
  public  Parameter parameter(IType type, String identifier_);
  /** This methods is called after the reduction of the non terminal another_parameter
   *  by the grammar production another_parameter.
   *  <code>another_parameter := 'comma_' parameter </code>
   */
  public  Another_Parameter another_parameter(Parameter parameter);
  /** This methods is called after the reduction of the non terminal multiple_statement
   *  by the grammar production multiple_statement.
   *  <code>multiple_statement := statement_star </code>
   */
  public  Multiple_Statement multiple_statement(Statement_Star statement_star);
  /** This methods is called after the reduction of the non terminal statement
   *  by the grammar production variable_declaration_statement.
   *  <code>statement := variable_declaration </code>
   */
  public  IStatement variable_declaration_statement(IVariable_Declaration variable_declaration);
  /** This methods is called after the reduction of the non terminal statement
   *  by the grammar production variable_assignment_statement.
   *  <code>statement := variable_assignment </code>
   */
  public  IStatement variable_assignment_statement(Variable_Assignment variable_assignment);
  /** This methods is called after the reduction of the non terminal statement
   *  by the grammar production comment_statement.
   *  <code>statement := 'comment_' </code>
   */
  public  IStatement comment_statement();
  /** This methods is called after the reduction of the non terminal statement
   *  by the grammar production print_statement.
   *  <code>statement := 'print_' expression 'semicolon_' </code>
   */
  public  IStatement print_statement(IExpression expression);
  /** This methods is called after the reduction of the non terminal statement
   *  by the grammar production expression_statement.
   *  <code>statement := expression 'semicolon_' </code>
   */
  public  IStatement expression_statement(IExpression expression);
  /** This methods is called after the reduction of the non terminal statement
   *  by the grammar production return_statement.
   *  <code>statement := 'return_' expression 'semicolon_' </code>
   */
  public  IStatement return_statement(IExpression expression);
  /** This methods is called after the reduction of the non terminal statement
   *  by the grammar production return_statement_empty.
   *  <code>statement := 'return_' 'semicolon_' </code>
   */
  public  IStatement return_statement_empty();
  /** This methods is called after the reduction of the non terminal statement
   *  by the grammar production conditional_statement.
   *  <code>statement := 'if_' expression 'then_' multiple_statement else_statement 'fi_' </code>
   */
  public  IStatement conditional_statement(IExpression expression, Multiple_Statement multiple_statement, IElse_Statement else_statement);
  /** This methods is called after the reduction of the non terminal statement
   *  by the grammar production foreach_statement.
   *  <code>statement := 'foreach_' 'identifier_' 'in_' expression 'do_' multiple_statement 'done_' </code>
   */
  public  IStatement foreach_statement(String identifier_, IExpression expression, Multiple_Statement multiple_statement);
  /** This methods is called after the reduction of the non terminal statement
   *  by the grammar production foreach_statement_with_declaration.
   *  <code>statement := 'foreach_' type 'identifier_' 'in_' expression 'do_' multiple_statement 'done_' </code>
   */
  public  IStatement foreach_statement_with_declaration(IType type, String identifier_, IExpression expression, Multiple_Statement multiple_statement);
  /** This methods is called after the reduction of the non terminal statement
   *  by the grammar production empty_statement.
   *  <code>statement := 'semicolon_' </code>
   */
  public  IStatement empty_statement();
  /** This methods is called after the reduction of the non terminal else_statement
   *  by the grammar production empty_else_statement.
   *  <code>else_statement := </code>
   */
  public  IElse_Statement empty_else_statement();
  /** This methods is called after the reduction of the non terminal else_statement
   *  by the grammar production else_statement.
   *  <code>else_statement := 'else_' multiple_statement </code>
   */
  public  IElse_Statement else_statement(Multiple_Statement multiple_statement);
  /** This methods is called after the reduction of the non terminal variable_declaration
   *  by the grammar production variable_declaration_with_assignment.
   *  <code>variable_declaration := type 'identifier_' 'affect_' expression 'semicolon_' </code>
   */
  public  IVariable_Declaration variable_declaration_with_assignment(IType type, String identifier_, IExpression expression);
  /** This methods is called after the reduction of the non terminal variable_declaration
   *  by the grammar production variable_declaration_without_assignment.
   *  <code>variable_declaration := type 'identifier_' 'semicolon_' </code>
   */
  public  IVariable_Declaration variable_declaration_without_assignment(IType type, String identifier_);
  /** This methods is called after the reduction of the non terminal variable_assignment
   *  by the grammar production variable_assignment.
   *  <code>variable_assignment := 'identifier_' 'affect_' expression 'semicolon_' </code>
   */
  public  Variable_Assignment variable_assignment(String identifier_, IExpression expression);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production plus_expression.
   *  <code>expression := expression 'plus_' expression </code>
   */
  public  IExpression plus_expression(IExpression expression, IExpression expression2);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production minus_expression.
   *  <code>expression := expression 'minus_' expression </code>
   */
  public  IExpression minus_expression(IExpression expression, IExpression expression2);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production mult_expression.
   *  <code>expression := expression 'mult_' expression </code>
   */
  public  IExpression mult_expression(IExpression expression, IExpression expression2);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production div_expression.
   *  <code>expression := expression 'div_' expression </code>
   */
  public  IExpression div_expression(IExpression expression, IExpression expression2);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production pow_expression.
   *  <code>expression := expression 'pow_' expression </code>
   */
  public  IExpression pow_expression(IExpression expression, IExpression expression2);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production not_expression.
   *  <code>expression := 'not_' expression </code>
   */
  public  IExpression not_expression(IExpression expression);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production minus_simple_expression.
   *  <code>expression := 'minus_' expression </code>
   */
  public  IExpression minus_simple_expression(IExpression expression);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production equal_expression.
   *  <code>expression := expression 'equal_' expression </code>
   */
  public  IExpression equal_expression(IExpression expression, IExpression expression2);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production inequal_expression.
   *  <code>expression := expression 'inequal_' expression </code>
   */
  public  IExpression inequal_expression(IExpression expression, IExpression expression2);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production par_expression.
   *  <code>expression := 'lpar_' expression 'rpar_' </code>
   */
  public  IExpression par_expression(IExpression expression);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production function_call_expression.
   *  <code>expression := 'identifier_' 'lpar_' arg_list 'rpar_' </code>
   */
  public  IExpression function_call_expression(String identifier_, IArg_List arg_list);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production method_call_expression.
   *  <code>expression := 'identifier_' 'dot_' 'identifier_' 'lpar_' arg_list 'rpar_' </code>
   */
  public  IExpression method_call_expression(String identifier_, String identifier_2, IArg_List arg_list);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production identifier_expression.
   *  <code>expression := 'identifier_' </code>
   */
  public  IExpression identifier_expression(String identifier_);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production number_expression.
   *  <code>expression := 'number_' </code>
   */
  public  IExpression number_expression(Integer number_);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production float_expression.
   *  <code>expression := 'float_' </code>
   */
  public  IExpression float_expression(Float float_);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production double_expression.
   *  <code>expression := 'double_' </code>
   */
  public  IExpression double_expression(Double double_);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production string_expression.
   *  <code>expression := 'string_' </code>
   */
  public  IExpression string_expression(String string_);
  /** This methods is called after the reduction of the non terminal expression
   *  by the grammar production boolean_expression.
   *  <code>expression := 'boolean_' </code>
   */
  public  IExpression boolean_expression(Boolean boolean_);
  /** This methods is called after the reduction of the non terminal arg_list
   *  by the grammar production empty_arg_list.
   *  <code>arg_list := </code>
   */
  public  IArg_List empty_arg_list();
  /** This methods is called after the reduction of the non terminal arg_list
   *  by the grammar production args_list.
   *  <code>arg_list := expression another_arg_star </code>
   */
  public  IArg_List args_list(IExpression expression, Another_Arg_Star another_arg_star);
  /** This methods is called after the reduction of the non terminal another_arg
   *  by the grammar production another_arg.
   *  <code>another_arg := 'comma_' expression </code>
   */
  public  Another_Arg another_arg(IExpression expression);
  /** This methods is called after the reduction of the non terminal type
   *  by the grammar production integer_type.
   *  <code>type := 'integer_type_' </code>
   */
  public  IType integer_type();
  /** This methods is called after the reduction of the non terminal type
   *  by the grammar production float_type.
   *  <code>type := 'float_type_' </code>
   */
  public  IType float_type();
  /** This methods is called after the reduction of the non terminal type
   *  by the grammar production double_type.
   *  <code>type := 'double_type_' </code>
   */
  public  IType double_type();
  /** This methods is called after the reduction of the non terminal type
   *  by the grammar production boolean_type.
   *  <code>type := 'boolean_type_' </code>
   */
  public  IType boolean_type();
  /** This methods is called after the reduction of the non terminal type
   *  by the grammar production string_type.
   *  <code>type := 'string_type_' </code>
   */
  public  IType string_type();
  /** This methods is called after the reduction of the non terminal type
   *  by the grammar production void_type.
   *  <code>type := 'void_type_' </code>
   */
  public  IType void_type();
  /** This methods is called after the reduction of the non terminal type
   *  by the grammar production class_type.
   *  <code>type := 'identifier_' </code>
   */
  public  IType class_type(String identifier_);

  /** Accepts the non-terminal start 
   */
  public void acceptStart(Start start);

}
