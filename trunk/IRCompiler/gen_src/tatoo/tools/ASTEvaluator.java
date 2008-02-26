package tatoo.tools;

import tatoo.tools.GrammarEvaluator;

public class ASTEvaluator implements GrammarEvaluator {
  public Import_Statement import_statement( IPackage_Name package_name) {    
    return new Import_Statement( package_name);
  }
  public IExpression number_expression( Integer number_) {    
    return new Number_Expression( number_);
  }
  public IType boolean_type() {    
    return new Boolean_Type();
  }
  public IArg_List args_list( IExpression expression, Another_Arg_Star another_arg_star) {    
    return new Args_List( expression, another_arg_star);
  }
  public IInstruction statement( IStatement statement) {    
    return new Statement( statement);
  }
  public IStatement comment_statement() {    
    return new Comment_Statement();
  }
  public IExpression div_expression( IExpression expression, IExpression expression2) {    
    return new Div_Expression( expression, expression2);
  }
  public IType class_type( String identifier_) {    
    return new Class_Type( identifier_);
  }
  public IParameter_List parameter_list( Parameter parameter, Another_Parameter_Star another_parameter_star) {    
    return new Parameter_List( parameter, another_parameter_star);
  }
  public Start start( Import_Statement_Star import_statement_star, Instruction_Star instruction_star) {    
    return new Start( import_statement_star, instruction_star);
  }
  public IExpression plus_expression( IExpression expression, IExpression expression2) {    
    return new Plus_Expression( expression, expression2);
  }
  public IPackage_Name multiple_package_name( IPackage_Name package_name, String identifier_) {    
    return new Multiple_Package_Name( package_name, identifier_);
  }
  public Multiple_Statement multiple_statement( Statement_Star statement_star) {    
    return new Multiple_Statement( statement_star);
  }
  public IType double_type() {    
    return new Double_Type();
  }
  public Function_Declaration function_declaration( IType type, String identifier_, IParameter_List parameter_list, Multiple_Statement multiple_statement) {    
    return new Function_Declaration( type, identifier_, parameter_list, multiple_statement);
  }
  public Another_Parameter another_parameter( Parameter parameter) {    
    return new Another_Parameter( parameter);
  }
  public IVariable_Declaration variable_declaration_without_assignment( IType type, String identifier_) {    
    return new Variable_Declaration_Without_Assignment( type, identifier_);
  }
  public IExpression method_call_expression( String identifier_, String identifier_2, IArg_List arg_list) {    
    return new Method_Call_Expression( identifier_, identifier_2, arg_list);
  }
  public IExpression double_expression( Double double_) {    
    return new Double_Expression( double_);
  }
  public IElse_Statement empty_else_statement() {    
    return new Empty_Else_Statement();
  }
  public IStatement variable_declaration_statement( IVariable_Declaration variable_declaration) {    
    return new Variable_Declaration_Statement( variable_declaration);
  }
  public Variable_Assignment variable_assignment( String identifier_, IExpression expression) {    
    return new Variable_Assignment( identifier_, expression);
  }
  public IStatement foreach_statement( String identifier_, IExpression expression, Multiple_Statement multiple_statement) {    
    return new Foreach_Statement( identifier_, expression, multiple_statement);
  }
  public IExpression string_expression( String string_) {    
    return new String_Expression( string_);
  }
  public IParameter_List empty_parameter_list() {    
    return new Empty_Parameter_List();
  }
  public IExpression mult_expression( IExpression expression, IExpression expression2) {    
    return new Mult_Expression( expression, expression2);
  }
  public IType void_type() {    
    return new Void_Type();
  }
  public IType string_type() {    
    return new String_Type();
  }
  public IExpression equal_expression( IExpression expression, IExpression expression2) {    
    return new Equal_Expression( expression, expression2);
  }
  public IStatement expression_statement( IExpression expression) {    
    return new Expression_Statement( expression);
  }
  public IExpression float_expression( Float float_) {    
    return new Float_Expression( float_);
  }
  public IStatement return_statement_empty() {    
    return new Return_Statement_Empty();
  }
  public IStatement print_statement( IExpression expression) {    
    return new Print_Statement( expression);
  }
  public IExpression not_expression( IExpression expression) {    
    return new Not_Expression( expression);
  }
  public IType float_type() {    
    return new Float_Type();
  }
  public Another_Arg another_arg( IExpression expression) {    
    return new Another_Arg( expression);
  }
  public IExpression minus_simple_expression( IExpression expression) {    
    return new Minus_Simple_Expression( expression);
  }
  public IInstruction instruction( Function_Declaration function_declaration) {    
    return new Instruction( function_declaration);
  }
  public Parameter parameter( IType type, String identifier_) {    
    return new Parameter( type, identifier_);
  }
  public IExpression inequal_expression( IExpression expression, IExpression expression2) {    
    return new Inequal_Expression( expression, expression2);
  }
  public IStatement variable_assignment_statement( Variable_Assignment variable_assignment) {    
    return new Variable_Assignment_Statement( variable_assignment);
  }
  public IExpression minus_expression( IExpression expression, IExpression expression2) {    
    return new Minus_Expression( expression, expression2);
  }
  public IStatement return_statement( IExpression expression) {    
    return new Return_Statement( expression);
  }
  public IStatement foreach_statement_with_declaration( IType type, String identifier_, IExpression expression, Multiple_Statement multiple_statement) {    
    return new Foreach_Statement_With_Declaration( type, identifier_, expression, multiple_statement);
  }
  public IStatement empty_statement() {    
    return new Empty_Statement();
  }
  public IElse_Statement else_statement( Multiple_Statement multiple_statement) {    
    return new Else_Statement( multiple_statement);
  }
  public IPackage_Name single_package_name( String identifier_) {    
    return new Single_Package_Name( identifier_);
  }
  public IType integer_type() {    
    return new Integer_Type();
  }
  public IExpression boolean_expression( Boolean boolean_) {    
    return new Boolean_Expression( boolean_);
  }
  public IArg_List empty_arg_list() {    
    return new Empty_Arg_List();
  }
  public IExpression pow_expression( IExpression expression, IExpression expression2) {    
    return new Pow_Expression( expression, expression2);
  }
  public IStatement conditional_statement( IExpression expression, Multiple_Statement multiple_statement, IElse_Statement else_statement) {    
    return new Conditional_Statement( expression, multiple_statement, else_statement);
  }
  public IExpression function_call_expression( String identifier_, IArg_List arg_list) {    
    return new Function_Call_Expression( identifier_, arg_list);
  }
  public IExpression identifier_expression( String identifier_) {    
    return new Identifier_Expression( identifier_);
  }
  public IExpression par_expression( IExpression expression) {    
    return new Par_Expression( expression);
  }
  public IVariable_Declaration variable_declaration_with_assignment( IType type, String identifier_, IExpression expression) {    
    return new Variable_Declaration_With_Assignment( type, identifier_, expression);
  }
    
  public Start getStart() {
    return start;
  }
  
  public void acceptStart(Start start) {
    this.start=start;
  }
  private Start start;
}
