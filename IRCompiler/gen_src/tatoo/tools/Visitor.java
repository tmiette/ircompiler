package tatoo.tools;

import java.util.Map;

import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;

public class Visitor<_R,_P,_D,_E extends Throwable> extends NodeVisitor<_R,_P,_D,_E>{
  public Visitor() {
    super();
  }
  public Visitor(Map<Node,_D> dataMap) {
    super(dataMap);
  }
  public _R visit(Import_Statement import_statement,_P param) throws _E {
       return visit((Node)import_statement,param);
     }
  public _R visit(Number_Expression number_expression,_P param) throws _E {
       return visit((IExpression)number_expression,param);
     }
  public _R visit(Boolean_Type boolean_type,_P param) throws _E {
       return visit((IType)boolean_type,param);
     }
  public _R visit(Args_List args_list,_P param) throws _E {
       return visit((IArg_List)args_list,param);
     }
  public _R visit(Statement statement,_P param) throws _E {
       return visit((IInstruction)statement,param);
     }
  public _R visit(Comment_Statement comment_statement,_P param) throws _E {
       return visit((IStatement)comment_statement,param);
     }
  public _R visit(Div_Expression div_expression,_P param) throws _E {
       return visit((IExpression)div_expression,param);
     }
  public _R visit(Class_Type class_type,_P param) throws _E {
       return visit((IType)class_type,param);
     }
  public _R visit(Parameter_List parameter_list,_P param) throws _E {
       return visit((IParameter_List)parameter_list,param);
     }
  public _R visit(Start start,_P param) throws _E {
       return visit((Node)start,param);
     }
  public _R visit(Statement_Star statement_star,_P param) throws _E {
       return visit((Node)statement_star,param);
     }
  public _R visit(Plus_Expression plus_expression,_P param) throws _E {
       return visit((IExpression)plus_expression,param);
     }
  public _R visit(Multiple_Package_Name multiple_package_name,_P param) throws _E {
       return visit((IPackage_Name)multiple_package_name,param);
     }
  public _R visit(Multiple_Statement multiple_statement,_P param) throws _E {
       return visit((Node)multiple_statement,param);
     }
  public _R visit(Double_Type double_type,_P param) throws _E {
       return visit((IType)double_type,param);
     }
  public _R visit(Function_Declaration function_declaration,_P param) throws _E {
       return visit((Node)function_declaration,param);
     }
  public _R visit(Another_Parameter another_parameter,_P param) throws _E {
       return visit((Node)another_parameter,param);
     }
  public _R visit(Variable_Declaration_Without_Assignment variable_declaration_without_assignment,_P param) throws _E {
       return visit((IVariable_Declaration)variable_declaration_without_assignment,param);
     }
  public _R visit(Method_Call_Expression method_call_expression,_P param) throws _E {
       return visit((IExpression)method_call_expression,param);
     }
  public _R visit(Double_Expression double_expression,_P param) throws _E {
       return visit((IExpression)double_expression,param);
     }
  public _R visit(Empty_Else_Statement empty_else_statement,_P param) throws _E {
       return visit((IElse_Statement)empty_else_statement,param);
     }
  public _R visit(Variable_Declaration_Statement variable_declaration_statement,_P param) throws _E {
       return visit((IStatement)variable_declaration_statement,param);
     }
  public _R visit(Variable_Assignment variable_assignment,_P param) throws _E {
       return visit((Node)variable_assignment,param);
     }
  public _R visit(Foreach_Statement foreach_statement,_P param) throws _E {
       return visit((IStatement)foreach_statement,param);
     }
  public _R visit(String_Expression string_expression,_P param) throws _E {
       return visit((IExpression)string_expression,param);
     }
  public _R visit(Empty_Parameter_List empty_parameter_list,_P param) throws _E {
       return visit((IParameter_List)empty_parameter_list,param);
     }
  public _R visit(Mult_Expression mult_expression,_P param) throws _E {
       return visit((IExpression)mult_expression,param);
     }
  public _R visit(Void_Type void_type,_P param) throws _E {
       return visit((IType)void_type,param);
     }
  public _R visit(String_Type string_type,_P param) throws _E {
       return visit((IType)string_type,param);
     }
  public _R visit(Equal_Expression equal_expression,_P param) throws _E {
       return visit((IExpression)equal_expression,param);
     }
  public _R visit(Expression_Statement expression_statement,_P param) throws _E {
       return visit((IStatement)expression_statement,param);
     }
  public _R visit(Float_Expression float_expression,_P param) throws _E {
       return visit((IExpression)float_expression,param);
     }
  public _R visit(Return_Statement_Empty return_statement_empty,_P param) throws _E {
       return visit((IStatement)return_statement_empty,param);
     }
  public _R visit(Print_Statement print_statement,_P param) throws _E {
       return visit((IStatement)print_statement,param);
     }
  public _R visit(Float_Type float_type,_P param) throws _E {
       return visit((IType)float_type,param);
     }
  public _R visit(Not_Expression not_expression,_P param) throws _E {
       return visit((IExpression)not_expression,param);
     }
  public _R visit(Another_Arg another_arg,_P param) throws _E {
       return visit((Node)another_arg,param);
     }
  public _R visit(Instruction_Star instruction_star,_P param) throws _E {
       return visit((Node)instruction_star,param);
     }
  public _R visit(Minus_Simple_Expression minus_simple_expression,_P param) throws _E {
       return visit((IExpression)minus_simple_expression,param);
     }
  public _R visit(Instruction instruction,_P param) throws _E {
       return visit((IInstruction)instruction,param);
     }
  public _R visit(Parameter parameter,_P param) throws _E {
       return visit((Node)parameter,param);
     }
  public _R visit(Inequal_Expression inequal_expression,_P param) throws _E {
       return visit((IExpression)inequal_expression,param);
     }
  public _R visit(Variable_Assignment_Statement variable_assignment_statement,_P param) throws _E {
       return visit((IStatement)variable_assignment_statement,param);
     }
  public _R visit(Minus_Expression minus_expression,_P param) throws _E {
       return visit((IExpression)minus_expression,param);
     }
  public _R visit(Another_Arg_Star another_arg_star,_P param) throws _E {
       return visit((Node)another_arg_star,param);
     }
  public _R visit(Return_Statement return_statement,_P param) throws _E {
       return visit((IStatement)return_statement,param);
     }
  public _R visit(Foreach_Statement_With_Declaration foreach_statement_with_declaration,_P param) throws _E {
       return visit((IStatement)foreach_statement_with_declaration,param);
     }
  public _R visit(Empty_Statement empty_statement,_P param) throws _E {
       return visit((IStatement)empty_statement,param);
     }
  public _R visit(Else_Statement else_statement,_P param) throws _E {
       return visit((IElse_Statement)else_statement,param);
     }
  public _R visit(Import_Statement_Star import_statement_star,_P param) throws _E {
       return visit((Node)import_statement_star,param);
     }
  public _R visit(Single_Package_Name single_package_name,_P param) throws _E {
       return visit((IPackage_Name)single_package_name,param);
     }
  public _R visit(Integer_Type integer_type,_P param) throws _E {
       return visit((IType)integer_type,param);
     }
  public _R visit(Boolean_Expression boolean_expression,_P param) throws _E {
       return visit((IExpression)boolean_expression,param);
     }
  public _R visit(Empty_Arg_List empty_arg_list,_P param) throws _E {
       return visit((IArg_List)empty_arg_list,param);
     }
  public _R visit(Pow_Expression pow_expression,_P param) throws _E {
       return visit((IExpression)pow_expression,param);
     }
  public _R visit(Conditional_Statement conditional_statement,_P param) throws _E {
       return visit((IStatement)conditional_statement,param);
     }
  public _R visit(Function_Call_Expression function_call_expression,_P param) throws _E {
       return visit((IExpression)function_call_expression,param);
     }
  public _R visit(Identifier_Expression identifier_expression,_P param) throws _E {
       return visit((IExpression)identifier_expression,param);
     }
  public _R visit(Par_Expression par_expression,_P param) throws _E {
       return visit((IExpression)par_expression,param);
     }
  public _R visit(Another_Parameter_Star another_parameter_star,_P param) throws _E {
       return visit((Node)another_parameter_star,param);
     }
  public _R visit(Variable_Declaration_With_Assignment variable_declaration_with_assignment,_P param) throws _E {
       return visit((IVariable_Declaration)variable_declaration_with_assignment,param);
     }
   protected _R visit(IInstruction instruction,_P param) throws _E {
    return visit((Node)instruction,param);
  }
    protected _R visit(IExpression expression,_P param) throws _E {
    return visit((Node)expression,param);
  }
    protected _R visit(IElse_Statement else_statement,_P param) throws _E {
    return visit((Node)else_statement,param);
  }
    protected _R visit(IPackage_Name package_name,_P param) throws _E {
    return visit((Node)package_name,param);
  }
    protected _R visit(IType type,_P param) throws _E {
    return visit((Node)type,param);
  }
    protected _R visit(IArg_List arg_list,_P param) throws _E {
    return visit((Node)arg_list,param);
  }
    protected _R visit(IVariable_Declaration variable_declaration,_P param) throws _E {
    return visit((Node)variable_declaration,param);
  }
    protected _R visit(IStatement statement,_P param) throws _E {
    return visit((Node)statement,param);
  }
    protected _R visit(IParameter_List parameter_list,_P param) throws _E {
    return visit((Node)parameter_list,param);
  }
 }
