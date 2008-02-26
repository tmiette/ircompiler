package tatoo.parser;

import tatoo.parser.NonTerminalEnum;
import tatoo.parser.ProductionEnum;
import tatoo.parser.TerminalEnum;
import fr.umlv.tatoo.runtime.parser.AcceptAction;
import fr.umlv.tatoo.runtime.parser.Action;
import fr.umlv.tatoo.runtime.parser.BranchAction;
import fr.umlv.tatoo.runtime.parser.ErrorAction;
import fr.umlv.tatoo.runtime.parser.ExitAction;
import fr.umlv.tatoo.runtime.parser.ParserTable;
import fr.umlv.tatoo.runtime.parser.ReduceAction;
import fr.umlv.tatoo.runtime.parser.ShiftAction;
import fr.umlv.tatoo.runtime.parser.StateMetadata;
import java.util.EnumMap;

/** 
 *  This class is generated - please do not edit it 
 */
public class ParserDataTable {
  @SuppressWarnings("unchecked")
  private ParserDataTable() {
    accept = AcceptAction.<TerminalEnum,ProductionEnum,VersionEnum>getInstance();
    exit = ExitAction.<TerminalEnum,ProductionEnum,VersionEnum>getInstance();
    initmultiple_statementGotoes();
    initfunction_declarationGotoes();
    initstatementGotoes();
    initparameterGotoes();
    initstatement_starGotoes();
    initanother_parameterGotoes();
    initexpressionGotoes();
    initimport_statementGotoes();
    initinstruction_starGotoes();
    initpackage_nameGotoes();
    initstartGotoes();
    initimport_statement_starGotoes();
    initanother_argGotoes();
    initvariable_declarationGotoes();
    initanother_parameter_starGotoes();
    initinstructionGotoes();
    initvariable_assignmentGotoes();
    inittypeGotoes();
    initanother_arg_starGotoes();
    initparameter_listGotoes();
    initelse_statementGotoes();
    initarg_listGotoes();
    reducereturn_statement = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.return_statement,3,statementGotoes);
    reduceexpression_statement = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expression_statement,2,statementGotoes);
    reducefloat_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.float_expression,1,expressionGotoes);
    reduceimport_statement_star_rec = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.import_statement_star_rec,2,import_statement_starGotoes);
    reducedouble_type = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.double_type,1,typeGotoes);
    reducemult_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.mult_expression,3,expressionGotoes);
    reduceidentifier_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.identifier_expression,1,expressionGotoes);
    reduceplus_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.plus_expression,3,expressionGotoes);
    reducenot_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.not_expression,2,expressionGotoes);
    reduceempty_else_statement = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.empty_else_statement,0,else_statementGotoes);
    reduceinstruction_star_empty = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.instruction_star_empty,0,instruction_starGotoes);
    reduceforeach_statement = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.foreach_statement,7,statementGotoes);
    reducevariable_assignment = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.variable_assignment,4,variable_assignmentGotoes);
    reducestart = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.start,2,startGotoes);
    reducefunction_declaration = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.function_declaration,8,function_declarationGotoes);
    reducevariable_declaration_with_assignment = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.variable_declaration_with_assignment,5,variable_declarationGotoes);
    reducesingle_package_name = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.single_package_name,1,package_nameGotoes);
    reduceforeach_statement_with_declaration = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.foreach_statement_with_declaration,8,statementGotoes);
    reduceinteger_type = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.integer_type,1,typeGotoes);
    reduceanother_arg = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.another_arg,2,another_argGotoes);
    reducepar_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.par_expression,3,expressionGotoes);
    reducenumber_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.number_expression,1,expressionGotoes);
    reduceminus_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.minus_expression,3,expressionGotoes);
    reduceanother_parameter_star_rec = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.another_parameter_star_rec,2,another_parameter_starGotoes);
    reducemethod_call_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.method_call_expression,6,expressionGotoes);
    reducepow_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.pow_expression,3,expressionGotoes);
    reducefunction_call_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.function_call_expression,4,expressionGotoes);
    reducedouble_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.double_expression,1,expressionGotoes);
    reduceargs_list = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.args_list,2,arg_listGotoes);
    reduceempty_arg_list = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.empty_arg_list,0,arg_listGotoes);
    reducevoid_type = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.void_type,1,typeGotoes);
    reduceimport_statement = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.import_statement,3,import_statementGotoes);
    reducevariable_declaration_statement = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.variable_declaration_statement,1,statementGotoes);
    reducestatement = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.statement,1,instructionGotoes);
    reducestatement_star_empty = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.statement_star_empty,0,statement_starGotoes);
    reducemultiple_statement = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.multiple_statement,1,multiple_statementGotoes);
    reduceempty_statement = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.empty_statement,1,statementGotoes);
    reduceinequal_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.inequal_expression,3,expressionGotoes);
    reducevariable_assignment_statement = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.variable_assignment_statement,1,statementGotoes);
    reducevariable_declaration_without_assignment = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.variable_declaration_without_assignment,3,variable_declarationGotoes);
    reduceequal_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.equal_expression,3,expressionGotoes);
    reduceinstruction_star_rec = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.instruction_star_rec,2,instruction_starGotoes);
    reducereturn_statement_empty = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.return_statement_empty,2,statementGotoes);
    reducestring_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.string_expression,1,expressionGotoes);
    reduceboolean_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.boolean_expression,1,expressionGotoes);
    reduceprint_statement = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.print_statement,3,statementGotoes);
    reduceanother_arg_star_rec = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.another_arg_star_rec,2,another_arg_starGotoes);
    reduceparameter = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.parameter,2,parameterGotoes);
    reduceimport_statement_star_empty = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.import_statement_star_empty,0,import_statement_starGotoes);
    reducemultiple_package_name = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.multiple_package_name,3,package_nameGotoes);
    reducecomment_statement = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.comment_statement,1,statementGotoes);
    reducefloat_type = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.float_type,1,typeGotoes);
    reduceelse_statement = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.else_statement,2,else_statementGotoes);
    reduceinstruction = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.instruction,1,instructionGotoes);
    reduceempty_parameter_list = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.empty_parameter_list,0,parameter_listGotoes);
    reduceparameter_list = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.parameter_list,2,parameter_listGotoes);
    reducestatement_star_rec = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.statement_star_rec,2,statement_starGotoes);
    reducestring_type = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.string_type,1,typeGotoes);
    reduceminus_simple_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.minus_simple_expression,2,expressionGotoes);
    reduceanother_parameter_star_empty = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.another_parameter_star_empty,0,another_parameter_starGotoes);
    reduceboolean_type = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.boolean_type,1,typeGotoes);
    reduceanother_arg_star_empty = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.another_arg_star_empty,0,another_arg_starGotoes);
    reduceclass_type = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.class_type,1,typeGotoes);
    reduceconditional_statement = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.conditional_statement,6,statementGotoes);
    reducediv_expression = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.div_expression,3,expressionGotoes);
    reduceanother_parameter = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.another_parameter,2,another_parameterGotoes);
    shift112 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(112);
    shift96 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(96);
    shift20 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(20);
    shift82 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(82);
    shift49 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(49);
    shift55 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(55);
    shift24 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(24);
    shift16 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(16);
    shift74 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(74);
    shift115 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(115);
    shift92 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(92);
    shift34 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(34);
    shift80 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(80);
    shift23 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(23);
    shift61 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(61);
    shift19 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(19);
    shift5 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(5);
    shift56 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(56);
    shift45 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(45);
    shift58 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(58);
    shift114 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(114);
    shift79 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(79);
    shift15 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(15);
    shift94 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(94);
    shift18 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(18);
    shift68 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(68);
    shift22 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(22);
    shift99 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(99);
    shift13 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(13);
    shift30 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(30);
    shift36 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(36);
    shift62 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(62);
    shift21 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(21);
    shift38 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(38);
    shift17 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(17);
    shift98 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(98);
    shift14 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(14);
    shift86 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(86);
    shift26 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(26);
    shift71 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(71);
    shift107 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(107);
    shift57 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(57);
    shift2 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(2);
    shift7 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(7);
    shift100 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(100);
    shift64 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(64);
    shift28 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(28);
    shift102 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(102);
    shift50 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(50);
    shift65 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(65);
    shift89 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(89);
    shift78 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(78);
    shift84 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(84);
    shift52 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(52);
    shift104 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(104);
    shift12 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(12);
    shift87 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(87);
    shift6 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(6);
    shift32 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(32);
    shift76 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(76);
    shift11 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(11);
    shift117 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(117);
    shift41 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(41);
    shift54 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(54);
    shift59 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(59);
    shift95 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(95);
    shift91 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(91);
    shift118 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(118);
    shift60 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(60);
    shift10 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(10);
    shift3 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(3);
    errornonassoc = new ErrorAction<TerminalEnum,ProductionEnum,VersionEnum>("non associative operator");
    error0 = new ErrorAction<TerminalEnum,ProductionEnum,VersionEnum>("parse error");
    branch0 = new BranchAction<TerminalEnum,ProductionEnum,VersionEnum>("parse error");
    initcomment_Array();
    initnumber_Array();
    initdouble_type_Array();
    initrpar_Array();
    initfunction_Array();
    initforeach_Array();
    initin_Array();
    initprint_Array();
    initimport_Array();
    init__eof__Array();
    initdo_Array();
    initinequal_Array();
    initfloat_type_Array();
    initaffect_Array();
    initdot_Array();
    initend_function_Array();
    initplus_Array();
    initdouble_Array();
    initidentifier_Array();
    initmult_Array();
    initfloat_Array();
    initlpar_Array();
    initdone_Array();
    initdiv_Array();
    initfi_Array();
    initstring_Array();
    initboolean_type_Array();
    initvoid_type_Array();
    initboolean_Array();
    initcomma_Array();
    initreturn_Array();
    initminus_Array();
    initinteger_type_Array();
    initsemicolon_Array();
    initequal_Array();
    initnot_Array();
    initstring_type_Array();
    initelse_Array();
    initif_Array();
    initthen_Array();
    initpow_Array();
    EnumMap<TerminalEnum,Action<TerminalEnum,ProductionEnum,VersionEnum>[]> tableMap =
      new EnumMap<TerminalEnum,Action<TerminalEnum,ProductionEnum,VersionEnum>[]>(TerminalEnum.class);

    tableMap.put(TerminalEnum.comment_,comment_Array);
    tableMap.put(TerminalEnum.number_,number_Array);
    tableMap.put(TerminalEnum.double_type_,double_type_Array);
    tableMap.put(TerminalEnum.rpar_,rpar_Array);
    tableMap.put(TerminalEnum.function_,function_Array);
    tableMap.put(TerminalEnum.foreach_,foreach_Array);
    tableMap.put(TerminalEnum.in_,in_Array);
    tableMap.put(TerminalEnum.print_,print_Array);
    tableMap.put(TerminalEnum.import_,import_Array);
    tableMap.put(TerminalEnum.__eof__,__eof__Array);
    tableMap.put(TerminalEnum.do_,do_Array);
    tableMap.put(TerminalEnum.inequal_,inequal_Array);
    tableMap.put(TerminalEnum.float_type_,float_type_Array);
    tableMap.put(TerminalEnum.affect_,affect_Array);
    tableMap.put(TerminalEnum.dot_,dot_Array);
    tableMap.put(TerminalEnum.end_function_,end_function_Array);
    tableMap.put(TerminalEnum.plus_,plus_Array);
    tableMap.put(TerminalEnum.double_,double_Array);
    tableMap.put(TerminalEnum.identifier_,identifier_Array);
    tableMap.put(TerminalEnum.mult_,mult_Array);
    tableMap.put(TerminalEnum.float_,float_Array);
    tableMap.put(TerminalEnum.lpar_,lpar_Array);
    tableMap.put(TerminalEnum.done_,done_Array);
    tableMap.put(TerminalEnum.div_,div_Array);
    tableMap.put(TerminalEnum.fi_,fi_Array);
    tableMap.put(TerminalEnum.string_,string_Array);
    tableMap.put(TerminalEnum.boolean_type_,boolean_type_Array);
    tableMap.put(TerminalEnum.void_type_,void_type_Array);
    tableMap.put(TerminalEnum.boolean_,boolean_Array);
    tableMap.put(TerminalEnum.comma_,comma_Array);
    tableMap.put(TerminalEnum.return_,return_Array);
    tableMap.put(TerminalEnum.minus_,minus_Array);
    tableMap.put(TerminalEnum.integer_type_,integer_type_Array);
    tableMap.put(TerminalEnum.semicolon_,semicolon_Array);
    tableMap.put(TerminalEnum.equal_,equal_Array);
    tableMap.put(TerminalEnum.not_,not_Array);
    tableMap.put(TerminalEnum.string_type_,string_type_Array);
    tableMap.put(TerminalEnum.else_,else_Array);
    tableMap.put(TerminalEnum.if_,if_Array);
    tableMap.put(TerminalEnum.then_,then_Array);
    tableMap.put(TerminalEnum.pow_,pow_Array);

    initBranchArrayTable();

    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0instruction_star = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.instruction_star);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0integer_type_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.integer_type_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0function_declaration = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.function_declaration);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0else_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.else_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0fi_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.fi_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0package_name = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.package_name);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0expression = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.expression);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0if_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.if_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0identifier_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.identifier_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0boolean_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.boolean_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0pow_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.pow_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0float_type_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.float_type_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0in_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.in_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0boolean_type_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.boolean_type_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0comma_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.comma_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0foreach_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.foreach_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0done_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.done_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0start = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.start);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0not_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.not_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0statement_star = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.statement_star);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0__eof__ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.__eof__);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0do_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.do_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0function_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.function_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0print_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.print_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0instruction = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.instruction);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0statement = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.statement);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0import_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.import_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0string_type_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.string_type_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0variable_declaration = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.variable_declaration);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0dot_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.dot_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0type = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.type);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0affect_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.affect_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0minus_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.minus_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(null);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0variable_assignment = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.variable_assignment);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0mult_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.mult_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0number_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.number_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0end_function_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.end_function_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0double_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.double_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0plus_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.plus_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0double_type_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.double_type_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0parameter = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.parameter);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0then_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.then_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0import_statement_star = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.import_statement_star);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0inequal_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.inequal_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0rpar_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.rpar_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0another_arg_star = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.another_arg_star);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0lpar_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.lpar_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0import_statement = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.import_statement);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0return_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.return_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0arg_list = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.arg_list);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0void_type_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.void_type_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0another_parameter = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.another_parameter);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0another_parameter_star = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.another_parameter_star);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0multiple_statement = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.multiple_statement);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0parameter_list = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.parameter_list);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0div_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.div_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0else_statement = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.else_statement);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0string_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.string_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0semicolon_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.semicolon_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0float_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.float_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0another_arg = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.another_arg);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0comment_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.comment_);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0equal_ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.equal_);

    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum>[] tableMetadata = (StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum>[])new StateMetadata<?,?,?>[]{metadata0null, metadata0import_statement_star, metadata0import_, metadata0identifier_, metadata0package_name, metadata0semicolon_, metadata0dot_, metadata0identifier_, metadata0import_statement, metadata0instruction_star, metadata0comment_, metadata0identifier_, metadata0affect_, metadata0boolean_, metadata0identifier_, metadata0dot_, metadata0identifier_, metadata0lpar_, metadata0number_, metadata0minus_, metadata0float_, metadata0lpar_, metadata0not_, metadata0string_, metadata0double_, metadata0expression, metadata0mult_, metadata0expression, metadata0minus_, metadata0expression, metadata0inequal_, metadata0expression, metadata0equal_, metadata0expression, metadata0div_, metadata0expression, metadata0plus_, metadata0expression, metadata0pow_, metadata0expression, metadata0expression, metadata0rpar_, metadata0expression, metadata0expression, metadata0another_arg_star, metadata0comma_, metadata0expression, metadata0another_arg, metadata0arg_list, metadata0rpar_, metadata0lpar_, metadata0arg_list, metadata0rpar_, metadata0expression, metadata0semicolon_, metadata0double_type_, metadata0function_, metadata0identifier_, metadata0integer_type_, metadata0float_type_, metadata0string_type_, metadata0boolean_type_, metadata0void_type_, metadata0type, metadata0identifier_, metadata0lpar_, metadata0parameter, metadata0another_parameter_star, metadata0comma_, metadata0parameter, metadata0type, metadata0identifier_, metadata0another_parameter, metadata0parameter_list, metadata0rpar_, metadata0multiple_statement, metadata0end_function_, metadata0statement_star, metadata0foreach_, metadata0identifier_, metadata0in_, metadata0expression, metadata0do_, metadata0multiple_statement, metadata0done_, metadata0type, metadata0identifier_, metadata0in_, metadata0expression, metadata0do_, metadata0multiple_statement, metadata0done_, metadata0print_, metadata0expression, metadata0semicolon_, metadata0return_, metadata0semicolon_, metadata0expression, metadata0semicolon_, metadata0semicolon_, metadata0if_, metadata0expression, metadata0then_, metadata0multiple_statement, metadata0else_, metadata0multiple_statement, metadata0else_statement, metadata0fi_, metadata0statement, metadata0variable_declaration, metadata0variable_assignment, metadata0expression, metadata0semicolon_, metadata0type, metadata0identifier_, metadata0affect_, metadata0expression, metadata0semicolon_, metadata0semicolon_, metadata0function_declaration, metadata0statement, metadata0instruction, metadata0start, metadata0__eof__};
    
    EnumMap<NonTerminalEnum,Integer> tableStarts =
      new EnumMap<NonTerminalEnum,Integer>(NonTerminalEnum.class);
    tableStarts.put(NonTerminalEnum.start,0);
    table = new ParserTable<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>(tableMap,branchArrayTable,tableMetadata,tableStarts,VersionEnum.values(),124,TerminalEnum.__eof__, null);
  } 
  

  private int[] multiple_statementGotoes;

  private void initmultiple_statementGotoes() {
    multiple_statementGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 75, -1, -1, -1, -1, -1, -1, -1, 83, -1, -1, -1, -1, -1, -1, 90, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 103, -1, 105, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] function_declarationGotoes;

  private void initfunction_declarationGotoes() {
    function_declarationGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, 119, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] statementGotoes;

  private void initstatementGotoes() {
    statementGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, 120, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 108, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] parameterGotoes;

  private void initparameterGotoes() {
    parameterGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 66, -1, -1, 69, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] statement_starGotoes;

  private void initstatement_starGotoes() {
    statement_starGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 77, -1, -1, -1, -1, -1, -1, -1, 77, -1, -1, -1, -1, -1, -1, 77, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 77, -1, 77, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] another_parameterGotoes;

  private void initanother_parameterGotoes() {
    another_parameterGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 72, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] expressionGotoes;

  private void initexpressionGotoes() {
    expressionGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, 111, -1, -1, 53, -1, -1, -1, -1, 43, -1, 42, -1, 40, 25, -1, -1, -1, 27, -1, 29, -1, 31, -1, 33, -1, 35, -1, 37, -1, 39, -1, -1, -1, -1, -1, -1, 46, -1, -1, -1, -1, 43, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 111, -1, -1, 81, -1, -1, -1, -1, -1, -1, 88, -1, -1, -1, -1, 93, -1, -1, 97, -1, -1, -1, -1, 101, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 116, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] import_statementGotoes;

  private void initimport_statementGotoes() {
    import_statementGotoes = 
      new int[]{-1, 8, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] instruction_starGotoes;

  private void initinstruction_starGotoes() {
    instruction_starGotoes = 
      new int[]{-1, 9, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] package_nameGotoes;

  private void initpackage_nameGotoes() {
    package_nameGotoes = 
      new int[]{-1, -1, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] startGotoes;

  private void initstartGotoes() {
    startGotoes = 
      new int[]{122, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] import_statement_starGotoes;

  private void initimport_statement_starGotoes() {
    import_statement_starGotoes = 
      new int[]{1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] another_argGotoes;

  private void initanother_argGotoes() {
    another_argGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 47, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] variable_declarationGotoes;

  private void initvariable_declarationGotoes() {
    variable_declarationGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, 109, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 109, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] another_parameter_starGotoes;

  private void initanother_parameter_starGotoes() {
    another_parameter_starGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 67, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] instructionGotoes;

  private void initinstructionGotoes() {
    instructionGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, 121, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] variable_assignmentGotoes;

  private void initvariable_assignmentGotoes() {
    variable_assignmentGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, 110, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 110, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] typeGotoes;

  private void inittypeGotoes() {
    typeGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, 113, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 63, -1, -1, -1, -1, -1, -1, -1, -1, 70, -1, -1, 70, -1, -1, -1, -1, -1, -1, -1, -1, 113, 85, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] another_arg_starGotoes;

  private void initanother_arg_starGotoes() {
    another_arg_starGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 44, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] parameter_listGotoes;

  private void initparameter_listGotoes() {
    parameter_listGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 73, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] else_statementGotoes;

  private void initelse_statementGotoes() {
    else_statementGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 106, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] arg_listGotoes;

  private void initarg_listGotoes() {
    arg_listGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 48, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] comment_Array;
  @SuppressWarnings("unchecked")
  private void initcomment_Array() {
    comment_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift10, reducecomment_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift10, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, branch0, branch0, reduceprint_statement, branch0, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, branch0, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, branch0, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] number_Array;
  @SuppressWarnings("unchecked")
  private void initnumber_Array() {
    number_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift18, reducecomment_statement, branch0, shift18, branch0, branch0, branch0, branch0, shift18, branch0, shift18, branch0, shift18, shift18, branch0, branch0, branch0, shift18, branch0, shift18, branch0, shift18, branch0, shift18, branch0, shift18, branch0, shift18, branch0, shift18, branch0, branch0, branch0, branch0, branch0, branch0, shift18, branch0, branch0, branch0, branch0, shift18, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift18, branch0, branch0, shift18, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, shift18, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, shift18, branch0, reduceprint_statement, shift18, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, shift18, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, shift18, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] double_type_Array;
  @SuppressWarnings("unchecked")
  private void initdouble_type_Array() {
    double_type_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift55, reducecomment_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducevariable_assignment, branch0, shift55, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift55, branch0, branch0, shift55, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift55, shift55, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, branch0, branch0, reduceprint_statement, branch0, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, branch0, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, branch0, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] rpar_Array;
  @SuppressWarnings("unchecked")
  private void initrpar_Array() {
    rpar_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reduceidentifier_expression, branch0, reduceboolean_expression, reduceidentifier_expression, branch0, branch0, reduceempty_arg_list, reducenumber_expression, branch0, reducefloat_expression, branch0, branch0, reducestring_expression, reducedouble_expression, reducenot_expression, branch0, reducemult_expression, branch0, reduceminus_expression, branch0, reduceinequal_expression, branch0, reduceequal_expression, branch0, reducediv_expression, branch0, reduceplus_expression, branch0, reducepow_expression, shift41, reducepar_expression, reduceminus_simple_expression, reduceanother_arg_star_empty, reduceargs_list, branch0, reduceanother_arg, reduceanother_arg_star_rec, shift49, reducemethod_call_expression, reduceempty_arg_list, shift52, reducefunction_call_expression, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reduceempty_parameter_list, reduceanother_parameter_star_empty, reduceparameter_list, branch0, reduceanother_parameter, branch0, reduceparameter, reduceanother_parameter_star_rec, shift74, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] function_Array;
  @SuppressWarnings("unchecked")
  private void initfunction_Array() {
    function_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift56, reducecomment_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducefunction_declaration, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reduceforeach_statement, branch0, branch0, branch0, branch0, branch0, branch0, reduceforeach_statement_with_declaration, branch0, branch0, reduceprint_statement, branch0, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reduceconditional_statement, branch0, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, branch0, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] foreach_Array;
  @SuppressWarnings("unchecked")
  private void initforeach_Array() {
    foreach_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift78, reducecomment_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift78, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, branch0, branch0, reduceprint_statement, branch0, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, branch0, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, branch0, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] in_Array;
  @SuppressWarnings("unchecked")
  private void initin_Array() {
    in_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift80, branch0, branch0, branch0, branch0, branch0, branch0, shift87, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] print_Array;
  @SuppressWarnings("unchecked")
  private void initprint_Array() {
    print_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift92, reducecomment_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift92, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, branch0, branch0, reduceprint_statement, branch0, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, branch0, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, branch0, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] import_Array;
  @SuppressWarnings("unchecked")
  private void initimport_Array() {
    import_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, shift2, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] __eof__Array;
  @SuppressWarnings("unchecked")
  private void init__eof__Array() {
    __eof__Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, reducestart, reducecomment_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducefunction_declaration, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reduceforeach_statement, branch0, branch0, branch0, branch0, branch0, branch0, reduceforeach_statement_with_declaration, branch0, branch0, reduceprint_statement, branch0, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reduceconditional_statement, branch0, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, branch0, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, accept, accept};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] do_Array;
  @SuppressWarnings("unchecked")
  private void initdo_Array() {
    do_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reduceidentifier_expression, branch0, reduceboolean_expression, reduceidentifier_expression, branch0, branch0, branch0, reducenumber_expression, branch0, reducefloat_expression, branch0, branch0, reducestring_expression, reducedouble_expression, reducenot_expression, branch0, reducemult_expression, branch0, reduceminus_expression, branch0, reduceinequal_expression, branch0, reduceequal_expression, branch0, reducediv_expression, branch0, reduceplus_expression, branch0, reducepow_expression, branch0, reducepar_expression, reduceminus_simple_expression, branch0, branch0, branch0, branch0, branch0, branch0, reducemethod_call_expression, branch0, branch0, reducefunction_call_expression, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift82, branch0, branch0, branch0, branch0, branch0, branch0, shift89, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] inequal_Array;
  @SuppressWarnings("unchecked")
  private void initinequal_Array() {
    inequal_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reduceidentifier_expression, branch0, reduceboolean_expression, reduceidentifier_expression, branch0, branch0, branch0, reducenumber_expression, branch0, reducefloat_expression, branch0, branch0, reducestring_expression, reducedouble_expression, reducenot_expression, branch0, reducemult_expression, branch0, reduceminus_expression, branch0, errornonassoc, branch0, errornonassoc, branch0, reducediv_expression, branch0, reduceplus_expression, branch0, reducepow_expression, shift30, reducepar_expression, reduceminus_simple_expression, shift30, branch0, branch0, shift30, branch0, branch0, reducemethod_call_expression, branch0, branch0, reducefunction_call_expression, shift30, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift30, branch0, branch0, branch0, branch0, branch0, branch0, shift30, branch0, branch0, branch0, branch0, shift30, branch0, branch0, branch0, shift30, branch0, branch0, branch0, shift30, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift30, branch0, branch0, branch0, branch0, shift30, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] float_type_Array;
  @SuppressWarnings("unchecked")
  private void initfloat_type_Array() {
    float_type_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift59, reducecomment_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducevariable_assignment, branch0, shift59, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift59, branch0, branch0, shift59, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift59, shift59, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, branch0, branch0, reduceprint_statement, branch0, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, branch0, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, branch0, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] affect_Array;
  @SuppressWarnings("unchecked")
  private void initaffect_Array() {
    affect_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift12, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift115, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] dot_Array;
  @SuppressWarnings("unchecked")
  private void initdot_Array() {
    dot_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, reducesingle_package_name, shift6, branch0, branch0, reducemultiple_package_name, branch0, branch0, branch0, shift15, branch0, branch0, shift15, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] end_function_Array;
  @SuppressWarnings("unchecked")
  private void initend_function_Array() {
    end_function_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducecomment_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, shift76, branch0, reducemultiple_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, branch0, branch0, reduceprint_statement, branch0, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, branch0, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, branch0, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] plus_Array;
  @SuppressWarnings("unchecked")
  private void initplus_Array() {
    plus_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reduceidentifier_expression, branch0, reduceboolean_expression, reduceidentifier_expression, branch0, branch0, branch0, reducenumber_expression, branch0, reducefloat_expression, branch0, branch0, reducestring_expression, reducedouble_expression, reducenot_expression, branch0, reducemult_expression, branch0, reduceminus_expression, branch0, shift36, branch0, shift36, branch0, reducediv_expression, branch0, reduceplus_expression, branch0, reducepow_expression, shift36, reducepar_expression, reduceminus_simple_expression, shift36, branch0, branch0, shift36, branch0, branch0, reducemethod_call_expression, branch0, branch0, reducefunction_call_expression, shift36, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift36, branch0, branch0, branch0, branch0, branch0, branch0, shift36, branch0, branch0, branch0, branch0, shift36, branch0, branch0, branch0, shift36, branch0, branch0, branch0, shift36, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift36, branch0, branch0, branch0, branch0, shift36, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] double_Array;
  @SuppressWarnings("unchecked")
  private void initdouble_Array() {
    double_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift24, reducecomment_statement, branch0, shift24, branch0, branch0, branch0, branch0, shift24, branch0, shift24, branch0, shift24, shift24, branch0, branch0, branch0, shift24, branch0, shift24, branch0, shift24, branch0, shift24, branch0, shift24, branch0, shift24, branch0, shift24, branch0, branch0, branch0, branch0, branch0, branch0, shift24, branch0, branch0, branch0, branch0, shift24, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift24, branch0, branch0, shift24, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, shift24, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, shift24, branch0, reduceprint_statement, shift24, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, shift24, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, shift24, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] identifier_Array;
  @SuppressWarnings("unchecked")
  private void initidentifier_Array() {
    identifier_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, shift3, branch0, branch0, reduceimport_statement, shift7, branch0, reduceimport_statement_star_rec, shift11, reducecomment_statement, reduceclass_type, shift14, branch0, branch0, shift16, branch0, shift14, branch0, shift14, branch0, shift14, shift14, branch0, branch0, branch0, shift14, branch0, shift14, branch0, shift14, branch0, shift14, branch0, shift14, branch0, shift14, branch0, shift14, branch0, branch0, branch0, branch0, branch0, branch0, shift14, branch0, branch0, branch0, branch0, shift14, branch0, branch0, branch0, reducevariable_assignment, reducedouble_type, shift57, reduceclass_type, reduceinteger_type, reducefloat_type, reducestring_type, reduceboolean_type, reducevoid_type, shift64, branch0, shift57, branch0, branch0, shift57, branch0, shift71, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift11, shift79, reduceclass_type, shift14, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, shift86, branch0, shift14, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, shift14, branch0, reduceprint_statement, shift14, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, shift14, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, shift114, branch0, shift14, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] mult_Array;
  @SuppressWarnings("unchecked")
  private void initmult_Array() {
    mult_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reduceidentifier_expression, branch0, reduceboolean_expression, reduceidentifier_expression, branch0, branch0, branch0, reducenumber_expression, branch0, reducefloat_expression, branch0, branch0, reducestring_expression, reducedouble_expression, reducenot_expression, branch0, reducemult_expression, branch0, shift26, branch0, shift26, branch0, shift26, branch0, reducediv_expression, branch0, shift26, branch0, reducepow_expression, shift26, reducepar_expression, shift26, shift26, branch0, branch0, shift26, branch0, branch0, reducemethod_call_expression, branch0, branch0, reducefunction_call_expression, shift26, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift26, branch0, branch0, branch0, branch0, branch0, branch0, shift26, branch0, branch0, branch0, branch0, shift26, branch0, branch0, branch0, shift26, branch0, branch0, branch0, shift26, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift26, branch0, branch0, branch0, branch0, shift26, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] float_Array;
  @SuppressWarnings("unchecked")
  private void initfloat_Array() {
    float_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift20, reducecomment_statement, branch0, shift20, branch0, branch0, branch0, branch0, shift20, branch0, shift20, branch0, shift20, shift20, branch0, branch0, branch0, shift20, branch0, shift20, branch0, shift20, branch0, shift20, branch0, shift20, branch0, shift20, branch0, shift20, branch0, branch0, branch0, branch0, branch0, branch0, shift20, branch0, branch0, branch0, branch0, shift20, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift20, branch0, branch0, shift20, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, shift20, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, shift20, branch0, reduceprint_statement, shift20, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, shift20, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, shift20, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] lpar_Array;
  @SuppressWarnings("unchecked")
  private void initlpar_Array() {
    lpar_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift21, reducecomment_statement, shift50, shift21, branch0, shift50, branch0, shift17, shift21, branch0, shift21, branch0, shift21, shift21, branch0, branch0, branch0, shift21, branch0, shift21, branch0, shift21, branch0, shift21, branch0, shift21, branch0, shift21, branch0, shift21, branch0, branch0, branch0, branch0, branch0, branch0, shift21, branch0, branch0, branch0, branch0, shift21, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift65, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift21, branch0, branch0, shift21, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, shift21, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, shift21, branch0, reduceprint_statement, shift21, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, shift21, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, shift21, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] done_Array;
  @SuppressWarnings("unchecked")
  private void initdone_Array() {
    done_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducecomment_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, branch0, reducemultiple_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, shift84, reduceforeach_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, shift91, reduceforeach_statement_with_declaration, branch0, branch0, reduceprint_statement, branch0, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, branch0, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, branch0, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] div_Array;
  @SuppressWarnings("unchecked")
  private void initdiv_Array() {
    div_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reduceidentifier_expression, branch0, reduceboolean_expression, reduceidentifier_expression, branch0, branch0, branch0, reducenumber_expression, branch0, reducefloat_expression, branch0, branch0, reducestring_expression, reducedouble_expression, reducenot_expression, branch0, reducemult_expression, branch0, shift34, branch0, shift34, branch0, shift34, branch0, reducediv_expression, branch0, shift34, branch0, reducepow_expression, shift34, reducepar_expression, shift34, shift34, branch0, branch0, shift34, branch0, branch0, reducemethod_call_expression, branch0, branch0, reducefunction_call_expression, shift34, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift34, branch0, branch0, branch0, branch0, branch0, branch0, shift34, branch0, branch0, branch0, branch0, shift34, branch0, branch0, branch0, shift34, branch0, branch0, branch0, shift34, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift34, branch0, branch0, branch0, branch0, shift34, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] fi_Array;
  @SuppressWarnings("unchecked")
  private void initfi_Array() {
    fi_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducecomment_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, branch0, reducemultiple_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, branch0, branch0, reduceprint_statement, branch0, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, branch0, branch0, reducestatement_star_empty, reduceempty_else_statement, reducestatement_star_empty, reduceelse_statement, shift107, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, branch0, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] string_Array;
  @SuppressWarnings("unchecked")
  private void initstring_Array() {
    string_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift23, reducecomment_statement, branch0, shift23, branch0, branch0, branch0, branch0, shift23, branch0, shift23, branch0, shift23, shift23, branch0, branch0, branch0, shift23, branch0, shift23, branch0, shift23, branch0, shift23, branch0, shift23, branch0, shift23, branch0, shift23, branch0, branch0, branch0, branch0, branch0, branch0, shift23, branch0, branch0, branch0, branch0, shift23, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift23, branch0, branch0, shift23, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, shift23, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, shift23, branch0, reduceprint_statement, shift23, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, shift23, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, shift23, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] boolean_type_Array;
  @SuppressWarnings("unchecked")
  private void initboolean_type_Array() {
    boolean_type_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift61, reducecomment_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducevariable_assignment, branch0, shift61, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift61, branch0, branch0, shift61, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift61, shift61, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, branch0, branch0, reduceprint_statement, branch0, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, branch0, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, branch0, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] void_type_Array;
  @SuppressWarnings("unchecked")
  private void initvoid_type_Array() {
    void_type_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift62, reducecomment_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducevariable_assignment, branch0, shift62, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift62, branch0, branch0, shift62, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift62, shift62, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, branch0, branch0, reduceprint_statement, branch0, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, branch0, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, branch0, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] boolean_Array;
  @SuppressWarnings("unchecked")
  private void initboolean_Array() {
    boolean_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift13, reducecomment_statement, branch0, shift13, branch0, branch0, branch0, branch0, shift13, branch0, shift13, branch0, shift13, shift13, branch0, branch0, branch0, shift13, branch0, shift13, branch0, shift13, branch0, shift13, branch0, shift13, branch0, shift13, branch0, shift13, branch0, branch0, branch0, branch0, branch0, branch0, shift13, branch0, branch0, branch0, branch0, shift13, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift13, branch0, branch0, shift13, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, shift13, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, shift13, branch0, reduceprint_statement, shift13, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, shift13, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, shift13, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] comma_Array;
  @SuppressWarnings("unchecked")
  private void initcomma_Array() {
    comma_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reduceidentifier_expression, branch0, reduceboolean_expression, reduceidentifier_expression, branch0, branch0, branch0, reducenumber_expression, branch0, reducefloat_expression, branch0, branch0, reducestring_expression, reducedouble_expression, reducenot_expression, branch0, reducemult_expression, branch0, reduceminus_expression, branch0, reduceinequal_expression, branch0, reduceequal_expression, branch0, reducediv_expression, branch0, reduceplus_expression, branch0, reducepow_expression, branch0, reducepar_expression, reduceminus_simple_expression, reduceanother_arg_star_empty, shift45, branch0, reduceanother_arg, reduceanother_arg_star_rec, branch0, reducemethod_call_expression, branch0, branch0, reducefunction_call_expression, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reduceanother_parameter_star_empty, shift68, branch0, reduceanother_parameter, branch0, reduceparameter, reduceanother_parameter_star_rec, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] return_Array;
  @SuppressWarnings("unchecked")
  private void initreturn_Array() {
    return_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift95, reducecomment_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift95, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, branch0, branch0, reduceprint_statement, branch0, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, branch0, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, branch0, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] minus_Array;
  @SuppressWarnings("unchecked")
  private void initminus_Array() {
    minus_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift19, reducecomment_statement, reduceidentifier_expression, shift19, reduceboolean_expression, reduceidentifier_expression, branch0, branch0, shift19, reducenumber_expression, shift19, reducefloat_expression, shift19, shift19, reducestring_expression, reducedouble_expression, reducenot_expression, shift19, reducemult_expression, shift19, reduceminus_expression, shift19, shift28, shift19, shift28, shift19, reducediv_expression, shift19, reduceplus_expression, shift19, reducepow_expression, shift28, reducepar_expression, reduceminus_simple_expression, shift28, branch0, shift19, shift28, branch0, branch0, reducemethod_call_expression, shift19, branch0, reducefunction_call_expression, shift28, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift19, branch0, branch0, shift19, shift28, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, shift19, shift28, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, shift19, shift28, reduceprint_statement, shift19, reducereturn_statement_empty, shift28, reducereturn_statement, reduceempty_statement, shift19, shift28, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, shift28, reduceexpression_statement, branch0, branch0, shift19, shift28, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] integer_type_Array;
  @SuppressWarnings("unchecked")
  private void initinteger_type_Array() {
    integer_type_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift58, reducecomment_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducevariable_assignment, branch0, shift58, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift58, branch0, branch0, shift58, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift58, shift58, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, branch0, branch0, reduceprint_statement, branch0, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, branch0, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, branch0, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] semicolon_Array;
  @SuppressWarnings("unchecked")
  private void initsemicolon_Array() {
    semicolon_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, reducesingle_package_name, shift5, reduceimport_statement, branch0, reducemultiple_package_name, reduceimport_statement_star_rec, shift99, reducecomment_statement, reduceidentifier_expression, branch0, reduceboolean_expression, reduceidentifier_expression, branch0, branch0, branch0, reducenumber_expression, branch0, reducefloat_expression, branch0, branch0, reducestring_expression, reducedouble_expression, reducenot_expression, branch0, reducemult_expression, branch0, reduceminus_expression, branch0, reduceinequal_expression, branch0, reduceequal_expression, branch0, reducediv_expression, branch0, reduceplus_expression, branch0, reducepow_expression, branch0, reducepar_expression, reduceminus_simple_expression, branch0, branch0, branch0, branch0, branch0, branch0, reducemethod_call_expression, branch0, branch0, reducefunction_call_expression, shift54, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift99, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, branch0, shift94, reduceprint_statement, shift96, reducereturn_statement_empty, shift98, reducereturn_statement, reduceempty_statement, branch0, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, shift112, reduceexpression_statement, branch0, shift118, branch0, shift117, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] equal_Array;
  @SuppressWarnings("unchecked")
  private void initequal_Array() {
    equal_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reduceidentifier_expression, branch0, reduceboolean_expression, reduceidentifier_expression, branch0, branch0, branch0, reducenumber_expression, branch0, reducefloat_expression, branch0, branch0, reducestring_expression, reducedouble_expression, reducenot_expression, branch0, reducemult_expression, branch0, reduceminus_expression, branch0, errornonassoc, branch0, errornonassoc, branch0, reducediv_expression, branch0, reduceplus_expression, branch0, reducepow_expression, shift32, reducepar_expression, reduceminus_simple_expression, shift32, branch0, branch0, shift32, branch0, branch0, reducemethod_call_expression, branch0, branch0, reducefunction_call_expression, shift32, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift32, branch0, branch0, branch0, branch0, branch0, branch0, shift32, branch0, branch0, branch0, branch0, shift32, branch0, branch0, branch0, shift32, branch0, branch0, branch0, shift32, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift32, branch0, branch0, branch0, branch0, shift32, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] not_Array;
  @SuppressWarnings("unchecked")
  private void initnot_Array() {
    not_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift22, reducecomment_statement, branch0, shift22, branch0, branch0, branch0, branch0, shift22, branch0, shift22, branch0, shift22, shift22, branch0, branch0, branch0, shift22, branch0, shift22, branch0, shift22, branch0, shift22, branch0, shift22, branch0, shift22, branch0, shift22, branch0, branch0, branch0, branch0, branch0, branch0, shift22, branch0, branch0, branch0, branch0, shift22, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift22, branch0, branch0, shift22, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, shift22, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, shift22, branch0, reduceprint_statement, shift22, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, shift22, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, shift22, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] string_type_Array;
  @SuppressWarnings("unchecked")
  private void initstring_type_Array() {
    string_type_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift60, reducecomment_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducevariable_assignment, branch0, shift60, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift60, branch0, branch0, shift60, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift60, shift60, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, branch0, branch0, reduceprint_statement, branch0, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, branch0, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, branch0, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] else_Array;
  @SuppressWarnings("unchecked")
  private void initelse_Array() {
    else_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducecomment_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, branch0, reducemultiple_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, branch0, branch0, reduceprint_statement, branch0, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, branch0, branch0, reducestatement_star_empty, shift104, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, branch0, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] if_Array;
  @SuppressWarnings("unchecked")
  private void initif_Array() {
    if_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, branch0, branch0, branch0, reduceimport_statement, branch0, branch0, reduceimport_statement_star_rec, shift100, reducecomment_statement, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducevariable_assignment, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reducefunction_declaration, shift100, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement, branch0, branch0, branch0, branch0, reducestatement_star_empty, branch0, reduceforeach_statement_with_declaration, branch0, branch0, reduceprint_statement, branch0, reducereturn_statement_empty, branch0, reducereturn_statement, reduceempty_statement, branch0, branch0, reducestatement_star_empty, branch0, reducestatement_star_empty, branch0, branch0, reduceconditional_statement, reducestatement_star_rec, reducevariable_declaration_statement, reducevariable_assignment_statement, branch0, reduceexpression_statement, branch0, branch0, branch0, branch0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] then_Array;
  @SuppressWarnings("unchecked")
  private void initthen_Array() {
    then_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reduceidentifier_expression, branch0, reduceboolean_expression, reduceidentifier_expression, branch0, branch0, branch0, reducenumber_expression, branch0, reducefloat_expression, branch0, branch0, reducestring_expression, reducedouble_expression, reducenot_expression, branch0, reducemult_expression, branch0, reduceminus_expression, branch0, reduceinequal_expression, branch0, reduceequal_expression, branch0, reducediv_expression, branch0, reduceplus_expression, branch0, reducepow_expression, branch0, reducepar_expression, reduceminus_simple_expression, branch0, branch0, branch0, branch0, branch0, branch0, reducemethod_call_expression, branch0, branch0, reducefunction_call_expression, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift102, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] pow_Array;
  @SuppressWarnings("unchecked")
  private void initpow_Array() {
    pow_Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reduceidentifier_expression, branch0, reduceboolean_expression, reduceidentifier_expression, branch0, branch0, branch0, reducenumber_expression, branch0, reducefloat_expression, branch0, branch0, reducestring_expression, reducedouble_expression, reducenot_expression, branch0, reducemult_expression, branch0, shift38, branch0, shift38, branch0, shift38, branch0, reducediv_expression, branch0, shift38, branch0, reducepow_expression, shift38, reducepar_expression, shift38, shift38, branch0, branch0, shift38, branch0, branch0, reducemethod_call_expression, branch0, branch0, reducefunction_call_expression, shift38, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift38, branch0, branch0, branch0, branch0, branch0, branch0, shift38, branch0, branch0, branch0, branch0, shift38, branch0, branch0, branch0, shift38, branch0, branch0, branch0, shift38, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift38, branch0, branch0, branch0, branch0, shift38, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }

  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] branchArrayTable;
  @SuppressWarnings("unchecked")
  private void initBranchArrayTable() {
    branchArrayTable=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceimport_statement_star_empty, reduceinstruction_star_empty, error0, error0, error0, reduceimport_statement, error0, error0, reduceimport_statement_star_rec, reducestart, reducecomment_statement, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, reducevariable_assignment, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, reducefunction_declaration, error0, error0, error0, error0, error0, error0, error0, reduceforeach_statement, error0, error0, error0, error0, error0, error0, reduceforeach_statement_with_declaration, error0, error0, reduceprint_statement, error0, reducereturn_statement_empty, error0, reducereturn_statement, reduceempty_statement, error0, error0, error0, error0, error0, error0, error0, reduceconditional_statement, error0, reducevariable_declaration_statement, reducevariable_assignment_statement, error0, reduceexpression_statement, error0, error0, error0, error0, reducevariable_declaration_with_assignment, reducevariable_declaration_without_assignment, reduceinstruction, reducestatement, reduceinstruction_star_rec, exit, exit};
  }

  private final ParserTable<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> table;
  
  public static final ParserTable<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> createTable() {
    return new ParserDataTable().table;
  }

  private final AcceptAction<TerminalEnum,ProductionEnum,VersionEnum> accept;
  private final ExitAction<TerminalEnum,ProductionEnum,VersionEnum> exit;

  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducereturn_statement;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpression_statement;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducefloat_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceimport_statement_star_rec;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducedouble_type;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducemult_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceidentifier_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceplus_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducenot_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceempty_else_statement;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinstruction_star_empty;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceforeach_statement;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducevariable_assignment;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducestart;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducefunction_declaration;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducevariable_declaration_with_assignment;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducesingle_package_name;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceforeach_statement_with_declaration;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinteger_type;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceanother_arg;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducepar_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducenumber_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceminus_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceanother_parameter_star_rec;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducemethod_call_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducepow_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducefunction_call_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducedouble_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceargs_list;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceempty_arg_list;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducevoid_type;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceimport_statement;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducevariable_declaration_statement;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducestatement;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducestatement_star_empty;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducemultiple_statement;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceempty_statement;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinequal_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducevariable_assignment_statement;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducevariable_declaration_without_assignment;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceequal_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinstruction_star_rec;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducereturn_statement_empty;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducestring_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceboolean_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceprint_statement;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceanother_arg_star_rec;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceparameter;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceimport_statement_star_empty;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducemultiple_package_name;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducecomment_statement;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducefloat_type;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceelse_statement;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinstruction;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceempty_parameter_list;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceparameter_list;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducestatement_star_rec;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducestring_type;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceminus_simple_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceanother_parameter_star_empty;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceboolean_type;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceanother_arg_star_empty;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceclass_type;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceconditional_statement;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducediv_expression;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceanother_parameter;

  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift112;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift96;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift20;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift82;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift49;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift55;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift24;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift16;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift74;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift115;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift92;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift34;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift80;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift23;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift61;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift19;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift5;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift56;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift45;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift58;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift114;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift79;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift15;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift94;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift18;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift68;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift22;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift99;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift13;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift30;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift36;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift62;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift21;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift38;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift17;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift98;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift14;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift86;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift26;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift71;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift107;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift57;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift2;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift7;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift100;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift64;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift28;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift102;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift50;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift65;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift89;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift78;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift84;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift52;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift104;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift12;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift87;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift6;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift32;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift76;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift11;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift117;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift41;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift54;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift59;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift95;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift91;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift118;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift60;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift10;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift3;


  private final ErrorAction<TerminalEnum,ProductionEnum,VersionEnum> errornonassoc;
  private final ErrorAction<TerminalEnum,ProductionEnum,VersionEnum> error0;

  private final BranchAction<TerminalEnum,ProductionEnum,VersionEnum> branch0;

}
