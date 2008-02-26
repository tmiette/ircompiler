package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.XMLNodeFactory;

public class XMLFactory extends XMLNodeFactory {
  public XMLFactory() {
    super(AbstractXMLFactory.values());
  }
  private static enum AbstractXMLFactory implements AbstractNodeFactory{
        import_statement {
      public Node createEmptyNode() {
        return new Import_Statement();
      }
    }  
    ,    number_expression {
      public Node createEmptyNode() {
        return new Number_Expression();
      }
    }  
    ,    boolean_type {
      public Node createEmptyNode() {
        return new Boolean_Type();
      }
    }  
    ,    args_list {
      public Node createEmptyNode() {
        return new Args_List();
      }
    }  
    ,    statement {
      public Node createEmptyNode() {
        return new Statement();
      }
    }  
    ,    comment_statement {
      public Node createEmptyNode() {
        return new Comment_Statement();
      }
    }  
    ,    div_expression {
      public Node createEmptyNode() {
        return new Div_Expression();
      }
    }  
    ,    class_type {
      public Node createEmptyNode() {
        return new Class_Type();
      }
    }  
    ,    parameter_list {
      public Node createEmptyNode() {
        return new Parameter_List();
      }
    }  
    ,    start {
      public Node createEmptyNode() {
        return new Start();
      }
    }  
    ,    statement_star {
      public Node createEmptyNode() {
        return new Statement_Star();
      }
    }  
    ,    plus_expression {
      public Node createEmptyNode() {
        return new Plus_Expression();
      }
    }  
    ,    multiple_package_name {
      public Node createEmptyNode() {
        return new Multiple_Package_Name();
      }
    }  
    ,    multiple_statement {
      public Node createEmptyNode() {
        return new Multiple_Statement();
      }
    }  
    ,    double_type {
      public Node createEmptyNode() {
        return new Double_Type();
      }
    }  
    ,    function_declaration {
      public Node createEmptyNode() {
        return new Function_Declaration();
      }
    }  
    ,    another_parameter {
      public Node createEmptyNode() {
        return new Another_Parameter();
      }
    }  
    ,    variable_declaration_without_assignment {
      public Node createEmptyNode() {
        return new Variable_Declaration_Without_Assignment();
      }
    }  
    ,    method_call_expression {
      public Node createEmptyNode() {
        return new Method_Call_Expression();
      }
    }  
    ,    double_expression {
      public Node createEmptyNode() {
        return new Double_Expression();
      }
    }  
    ,    empty_else_statement {
      public Node createEmptyNode() {
        return new Empty_Else_Statement();
      }
    }  
    ,    variable_declaration_statement {
      public Node createEmptyNode() {
        return new Variable_Declaration_Statement();
      }
    }  
    ,    variable_assignment {
      public Node createEmptyNode() {
        return new Variable_Assignment();
      }
    }  
    ,    foreach_statement {
      public Node createEmptyNode() {
        return new Foreach_Statement();
      }
    }  
    ,    string_expression {
      public Node createEmptyNode() {
        return new String_Expression();
      }
    }  
    ,    empty_parameter_list {
      public Node createEmptyNode() {
        return new Empty_Parameter_List();
      }
    }  
    ,    mult_expression {
      public Node createEmptyNode() {
        return new Mult_Expression();
      }
    }  
    ,    void_type {
      public Node createEmptyNode() {
        return new Void_Type();
      }
    }  
    ,    string_type {
      public Node createEmptyNode() {
        return new String_Type();
      }
    }  
    ,    equal_expression {
      public Node createEmptyNode() {
        return new Equal_Expression();
      }
    }  
    ,    expression_statement {
      public Node createEmptyNode() {
        return new Expression_Statement();
      }
    }  
    ,    float_expression {
      public Node createEmptyNode() {
        return new Float_Expression();
      }
    }  
    ,    return_statement_empty {
      public Node createEmptyNode() {
        return new Return_Statement_Empty();
      }
    }  
    ,    print_statement {
      public Node createEmptyNode() {
        return new Print_Statement();
      }
    }  
    ,    float_type {
      public Node createEmptyNode() {
        return new Float_Type();
      }
    }  
    ,    not_expression {
      public Node createEmptyNode() {
        return new Not_Expression();
      }
    }  
    ,    another_arg {
      public Node createEmptyNode() {
        return new Another_Arg();
      }
    }  
    ,    instruction_star {
      public Node createEmptyNode() {
        return new Instruction_Star();
      }
    }  
    ,    minus_simple_expression {
      public Node createEmptyNode() {
        return new Minus_Simple_Expression();
      }
    }  
    ,    instruction {
      public Node createEmptyNode() {
        return new Instruction();
      }
    }  
    ,    parameter {
      public Node createEmptyNode() {
        return new Parameter();
      }
    }  
    ,    inequal_expression {
      public Node createEmptyNode() {
        return new Inequal_Expression();
      }
    }  
    ,    variable_assignment_statement {
      public Node createEmptyNode() {
        return new Variable_Assignment_Statement();
      }
    }  
    ,    minus_expression {
      public Node createEmptyNode() {
        return new Minus_Expression();
      }
    }  
    ,    another_arg_star {
      public Node createEmptyNode() {
        return new Another_Arg_Star();
      }
    }  
    ,    return_statement {
      public Node createEmptyNode() {
        return new Return_Statement();
      }
    }  
    ,    foreach_statement_with_declaration {
      public Node createEmptyNode() {
        return new Foreach_Statement_With_Declaration();
      }
    }  
    ,    empty_statement {
      public Node createEmptyNode() {
        return new Empty_Statement();
      }
    }  
    ,    else_statement {
      public Node createEmptyNode() {
        return new Else_Statement();
      }
    }  
    ,    import_statement_star {
      public Node createEmptyNode() {
        return new Import_Statement_Star();
      }
    }  
    ,    single_package_name {
      public Node createEmptyNode() {
        return new Single_Package_Name();
      }
    }  
    ,    integer_type {
      public Node createEmptyNode() {
        return new Integer_Type();
      }
    }  
    ,    boolean_expression {
      public Node createEmptyNode() {
        return new Boolean_Expression();
      }
    }  
    ,    empty_arg_list {
      public Node createEmptyNode() {
        return new Empty_Arg_List();
      }
    }  
    ,    pow_expression {
      public Node createEmptyNode() {
        return new Pow_Expression();
      }
    }  
    ,    conditional_statement {
      public Node createEmptyNode() {
        return new Conditional_Statement();
      }
    }  
    ,    function_call_expression {
      public Node createEmptyNode() {
        return new Function_Call_Expression();
      }
    }  
    ,    identifier_expression {
      public Node createEmptyNode() {
        return new Identifier_Expression();
      }
    }  
    ,    par_expression {
      public Node createEmptyNode() {
        return new Par_Expression();
      }
    }  
    ,    another_parameter_star {
      public Node createEmptyNode() {
        return new Another_Parameter_Star();
      }
    }  
    ,    variable_declaration_with_assignment {
      public Node createEmptyNode() {
        return new Variable_Declaration_With_Assignment();
      }
    }  
    ;
    public String getName() {
      return name().replace('_','-');
    }
  }
}
