package tatoo.lexer;

import tatoo.lexer.RuleEnum;
import fr.umlv.tatoo.runtime.lexer.LexerTable;
import fr.umlv.tatoo.runtime.lexer.rules.RuleData;
import fr.umlv.tatoo.runtime.regex.CharRegexTable;
import java.util.EnumMap;

/** 
 *  This class is generated - please do not edit it 
 */
public class LexerDataTable {

  public static LexerTable<RuleEnum> createTable() {
    return new LexerDataTable().table;
  }

  private LexerDataTable() {
    initdot_MainAccepts();
    initdot_MainTransitions();
    CharRegexTable dot_Main = new CharRegexTable(1, dot_MainTransitions, dot_MainAccepts);
    RuleData dot_ = new RuleData(dot_Main, null, false);
    initsemicolon_MainAccepts();
    initsemicolon_MainTransitions();
    CharRegexTable semicolon_Main = new CharRegexTable(1, semicolon_MainTransitions, semicolon_MainAccepts);
    RuleData semicolon_ = new RuleData(semicolon_Main, null, false);
    initlpar_MainAccepts();
    initlpar_MainTransitions();
    CharRegexTable lpar_Main = new CharRegexTable(1, lpar_MainTransitions, lpar_MainAccepts);
    RuleData lpar_ = new RuleData(lpar_Main, null, false);
    initrpar_MainAccepts();
    initrpar_MainTransitions();
    CharRegexTable rpar_Main = new CharRegexTable(1, rpar_MainTransitions, rpar_MainAccepts);
    RuleData rpar_ = new RuleData(rpar_Main, null, false);
    initcomma_MainAccepts();
    initcomma_MainTransitions();
    CharRegexTable comma_Main = new CharRegexTable(1, comma_MainTransitions, comma_MainAccepts);
    RuleData comma_ = new RuleData(comma_Main, null, false);
    initplus_MainAccepts();
    initplus_MainTransitions();
    CharRegexTable plus_Main = new CharRegexTable(1, plus_MainTransitions, plus_MainAccepts);
    RuleData plus_ = new RuleData(plus_Main, null, false);
    initminus_MainAccepts();
    initminus_MainTransitions();
    CharRegexTable minus_Main = new CharRegexTable(1, minus_MainTransitions, minus_MainAccepts);
    RuleData minus_ = new RuleData(minus_Main, null, false);
    initmult_MainAccepts();
    initmult_MainTransitions();
    CharRegexTable mult_Main = new CharRegexTable(1, mult_MainTransitions, mult_MainAccepts);
    RuleData mult_ = new RuleData(mult_Main, null, false);
    initdiv_MainAccepts();
    initdiv_MainTransitions();
    CharRegexTable div_Main = new CharRegexTable(1, div_MainTransitions, div_MainAccepts);
    RuleData div_ = new RuleData(div_Main, null, false);
    initequal_MainAccepts();
    initequal_MainTransitions();
    CharRegexTable equal_Main = new CharRegexTable(2, equal_MainTransitions, equal_MainAccepts);
    RuleData equal_ = new RuleData(equal_Main, null, false);
    initinequal_MainAccepts();
    initinequal_MainTransitions();
    CharRegexTable inequal_Main = new CharRegexTable(2, inequal_MainTransitions, inequal_MainAccepts);
    RuleData inequal_ = new RuleData(inequal_Main, null, false);
    initaffect_MainAccepts();
    initaffect_MainTransitions();
    CharRegexTable affect_Main = new CharRegexTable(1, affect_MainTransitions, affect_MainAccepts);
    RuleData affect_ = new RuleData(affect_Main, null, false);
    initpow_MainAccepts();
    initpow_MainTransitions();
    CharRegexTable pow_Main = new CharRegexTable(1, pow_MainTransitions, pow_MainAccepts);
    RuleData pow_ = new RuleData(pow_Main, null, false);
    initimport_MainAccepts();
    initimport_MainTransitions();
    CharRegexTable import_Main = new CharRegexTable(6, import_MainTransitions, import_MainAccepts);
    RuleData import_ = new RuleData(import_Main, null, false);
    initfunction_MainAccepts();
    initfunction_MainTransitions();
    CharRegexTable function_Main = new CharRegexTable(4, function_MainTransitions, function_MainAccepts);
    RuleData function_ = new RuleData(function_Main, null, false);
    initend_function_MainAccepts();
    initend_function_MainTransitions();
    CharRegexTable end_function_Main = new CharRegexTable(3, end_function_MainTransitions, end_function_MainAccepts);
    RuleData end_function_ = new RuleData(end_function_Main, null, false);
    initif_MainAccepts();
    initif_MainTransitions();
    CharRegexTable if_Main = new CharRegexTable(2, if_MainTransitions, if_MainAccepts);
    RuleData if_ = new RuleData(if_Main, null, false);
    initthen_MainAccepts();
    initthen_MainTransitions();
    CharRegexTable then_Main = new CharRegexTable(4, then_MainTransitions, then_MainAccepts);
    RuleData then_ = new RuleData(then_Main, null, false);
    initelse_MainAccepts();
    initelse_MainTransitions();
    CharRegexTable else_Main = new CharRegexTable(4, else_MainTransitions, else_MainAccepts);
    RuleData else_ = new RuleData(else_Main, null, false);
    initfi_MainAccepts();
    initfi_MainTransitions();
    CharRegexTable fi_Main = new CharRegexTable(2, fi_MainTransitions, fi_MainAccepts);
    RuleData fi_ = new RuleData(fi_Main, null, false);
    initreturn_MainAccepts();
    initreturn_MainTransitions();
    CharRegexTable return_Main = new CharRegexTable(4, return_MainTransitions, return_MainAccepts);
    RuleData return_ = new RuleData(return_Main, null, false);
    initnot_MainAccepts();
    initnot_MainTransitions();
    CharRegexTable not_Main = new CharRegexTable(3, not_MainTransitions, not_MainAccepts);
    RuleData not_ = new RuleData(not_Main, null, false);
    initprint_MainAccepts();
    initprint_MainTransitions();
    CharRegexTable print_Main = new CharRegexTable(4, print_MainTransitions, print_MainAccepts);
    RuleData print_ = new RuleData(print_Main, null, false);
    initforeach_MainAccepts();
    initforeach_MainTransitions();
    CharRegexTable foreach_Main = new CharRegexTable(5, foreach_MainTransitions, foreach_MainAccepts);
    RuleData foreach_ = new RuleData(foreach_Main, null, false);
    initin_MainAccepts();
    initin_MainTransitions();
    CharRegexTable in_Main = new CharRegexTable(2, in_MainTransitions, in_MainAccepts);
    RuleData in_ = new RuleData(in_Main, null, false);
    initdo_MainAccepts();
    initdo_MainTransitions();
    CharRegexTable do_Main = new CharRegexTable(2, do_MainTransitions, do_MainAccepts);
    RuleData do_ = new RuleData(do_Main, null, false);
    initdone_MainAccepts();
    initdone_MainTransitions();
    CharRegexTable done_Main = new CharRegexTable(3, done_MainTransitions, done_MainAccepts);
    RuleData done_ = new RuleData(done_Main, null, false);
    initvoid_type_MainAccepts();
    initvoid_type_MainTransitions();
    CharRegexTable void_type_Main = new CharRegexTable(4, void_type_MainTransitions, void_type_MainAccepts);
    RuleData void_type_ = new RuleData(void_type_Main, null, false);
    initinteger_type_MainAccepts();
    initinteger_type_MainTransitions();
    CharRegexTable integer_type_Main = new CharRegexTable(3, integer_type_MainTransitions, integer_type_MainAccepts);
    RuleData integer_type_ = new RuleData(integer_type_Main, null, false);
    initboolean_type_MainAccepts();
    initboolean_type_MainTransitions();
    CharRegexTable boolean_type_Main = new CharRegexTable(2, boolean_type_MainTransitions, boolean_type_MainAccepts);
    RuleData boolean_type_ = new RuleData(boolean_type_Main, null, false);
    initfloat_type_MainAccepts();
    initfloat_type_MainTransitions();
    CharRegexTable float_type_Main = new CharRegexTable(5, float_type_MainTransitions, float_type_MainAccepts);
    RuleData float_type_ = new RuleData(float_type_Main, null, false);
    initdouble_type_MainAccepts();
    initdouble_type_MainTransitions();
    CharRegexTable double_type_Main = new CharRegexTable(6, double_type_MainTransitions, double_type_MainAccepts);
    RuleData double_type_ = new RuleData(double_type_Main, null, false);
    initstring_type_MainAccepts();
    initstring_type_MainTransitions();
    CharRegexTable string_type_Main = new CharRegexTable(6, string_type_MainTransitions, string_type_MainAccepts);
    RuleData string_type_ = new RuleData(string_type_Main, null, false);
    initcomment_MainAccepts();
    initcomment_MainTransitions();
    CharRegexTable comment_Main = new CharRegexTable(3, comment_MainTransitions, comment_MainAccepts);
    RuleData comment_ = new RuleData(comment_Main, null, false);
    initboolean_MainAccepts();
    initboolean_MainTransitions();
    CharRegexTable boolean_Main = new CharRegexTable(1, boolean_MainTransitions, boolean_MainAccepts);
    RuleData boolean_ = new RuleData(boolean_Main, null, false);
    initnumber_MainAccepts();
    initnumber_MainTransitions();
    CharRegexTable number_Main = new CharRegexTable(1, number_MainTransitions, number_MainAccepts);
    RuleData number_ = new RuleData(number_Main, null, false);
    initfloat_MainAccepts();
    initfloat_MainTransitions();
    CharRegexTable float_Main = new CharRegexTable(4, float_MainTransitions, float_MainAccepts);
    RuleData float_ = new RuleData(float_Main, null, false);
    initdouble_MainAccepts();
    initdouble_MainTransitions();
    CharRegexTable double_Main = new CharRegexTable(3, double_MainTransitions, double_MainAccepts);
    RuleData double_ = new RuleData(double_Main, null, false);
    initidentifier_MainAccepts();
    initidentifier_MainTransitions();
    CharRegexTable identifier_Main = new CharRegexTable(1, identifier_MainTransitions, identifier_MainAccepts);
    RuleData identifier_ = new RuleData(identifier_Main, null, false);
    initstring_MainAccepts();
    initstring_MainTransitions();
    CharRegexTable string_Main = new CharRegexTable(2, string_MainTransitions, string_MainAccepts);
    RuleData string_ = new RuleData(string_Main, null, false);
    initblk_MainAccepts();
    initblk_MainTransitions();
    CharRegexTable blk_Main = new CharRegexTable(1, blk_MainTransitions, blk_MainAccepts);
    RuleData blk_ = new RuleData(blk_Main, null, false);
    EnumMap<RuleEnum,RuleData> datas = new EnumMap<RuleEnum,RuleData>(RuleEnum.class);
    datas.put(RuleEnum.dot_, dot_);
    datas.put(RuleEnum.semicolon_, semicolon_);
    datas.put(RuleEnum.lpar_, lpar_);
    datas.put(RuleEnum.rpar_, rpar_);
    datas.put(RuleEnum.comma_, comma_);
    datas.put(RuleEnum.plus_, plus_);
    datas.put(RuleEnum.minus_, minus_);
    datas.put(RuleEnum.mult_, mult_);
    datas.put(RuleEnum.div_, div_);
    datas.put(RuleEnum.equal_, equal_);
    datas.put(RuleEnum.inequal_, inequal_);
    datas.put(RuleEnum.affect_, affect_);
    datas.put(RuleEnum.pow_, pow_);
    datas.put(RuleEnum.import_, import_);
    datas.put(RuleEnum.function_, function_);
    datas.put(RuleEnum.end_function_, end_function_);
    datas.put(RuleEnum.if_, if_);
    datas.put(RuleEnum.then_, then_);
    datas.put(RuleEnum.else_, else_);
    datas.put(RuleEnum.fi_, fi_);
    datas.put(RuleEnum.return_, return_);
    datas.put(RuleEnum.not_, not_);
    datas.put(RuleEnum.print_, print_);
    datas.put(RuleEnum.foreach_, foreach_);
    datas.put(RuleEnum.in_, in_);
    datas.put(RuleEnum.do_, do_);
    datas.put(RuleEnum.done_, done_);
    datas.put(RuleEnum.void_type_, void_type_);
    datas.put(RuleEnum.integer_type_, integer_type_);
    datas.put(RuleEnum.boolean_type_, boolean_type_);
    datas.put(RuleEnum.float_type_, float_type_);
    datas.put(RuleEnum.double_type_, double_type_);
    datas.put(RuleEnum.string_type_, string_type_);
    datas.put(RuleEnum.comment_, comment_);
    datas.put(RuleEnum.boolean_, boolean_);
    datas.put(RuleEnum.number_, number_);
    datas.put(RuleEnum.float_, float_);
    datas.put(RuleEnum.double_, double_);
    datas.put(RuleEnum.identifier_, identifier_);
    datas.put(RuleEnum.string_, string_);
    datas.put(RuleEnum.blk_, blk_);
    table = new LexerTable<RuleEnum>(datas);
  }
  
  private boolean[] dot_MainAccepts;
  private void initdot_MainAccepts() {
    dot_MainAccepts = new boolean[] {true, false};
  }
    
  private int[][] dot_MainTransitions;
  private void initdot_MainTransitions() {
    dot_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,46,0,47,-1}};
  }
  private boolean[] semicolon_MainAccepts;
  private void initsemicolon_MainAccepts() {
    semicolon_MainAccepts = new boolean[] {true, false};
  }
    
  private int[][] semicolon_MainTransitions;
  private void initsemicolon_MainTransitions() {
    semicolon_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,59,0,60,-1}};
  }
  private boolean[] lpar_MainAccepts;
  private void initlpar_MainAccepts() {
    lpar_MainAccepts = new boolean[] {true, false};
  }
    
  private int[][] lpar_MainTransitions;
  private void initlpar_MainTransitions() {
    lpar_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,40,0,41,-1}};
  }
  private boolean[] rpar_MainAccepts;
  private void initrpar_MainAccepts() {
    rpar_MainAccepts = new boolean[] {true, false};
  }
    
  private int[][] rpar_MainTransitions;
  private void initrpar_MainTransitions() {
    rpar_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,41,0,42,-1}};
  }
  private boolean[] comma_MainAccepts;
  private void initcomma_MainAccepts() {
    comma_MainAccepts = new boolean[] {true, false};
  }
    
  private int[][] comma_MainTransitions;
  private void initcomma_MainTransitions() {
    comma_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,44,0,45,-1}};
  }
  private boolean[] plus_MainAccepts;
  private void initplus_MainAccepts() {
    plus_MainAccepts = new boolean[] {true, false};
  }
    
  private int[][] plus_MainTransitions;
  private void initplus_MainTransitions() {
    plus_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,43,0,44,-1}};
  }
  private boolean[] minus_MainAccepts;
  private void initminus_MainAccepts() {
    minus_MainAccepts = new boolean[] {true, false};
  }
    
  private int[][] minus_MainTransitions;
  private void initminus_MainTransitions() {
    minus_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,45,0,46,-1}};
  }
  private boolean[] mult_MainAccepts;
  private void initmult_MainAccepts() {
    mult_MainAccepts = new boolean[] {true, false};
  }
    
  private int[][] mult_MainTransitions;
  private void initmult_MainTransitions() {
    mult_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,42,0,43,-1}};
  }
  private boolean[] div_MainAccepts;
  private void initdiv_MainAccepts() {
    div_MainAccepts = new boolean[] {true, false};
  }
    
  private int[][] div_MainTransitions;
  private void initdiv_MainTransitions() {
    div_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,47,0,48,-1}};
  }
  private boolean[] equal_MainAccepts;
  private void initequal_MainAccepts() {
    equal_MainAccepts = new boolean[] {true, false, false};
  }
    
  private int[][] equal_MainTransitions;
  private void initequal_MainTransitions() {
    equal_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,61,0,62,-1},
        {0,-1,61,1,62,-1}};
  }
  private boolean[] inequal_MainAccepts;
  private void initinequal_MainAccepts() {
    inequal_MainAccepts = new boolean[] {true, false, false};
  }
    
  private int[][] inequal_MainTransitions;
  private void initinequal_MainTransitions() {
    inequal_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,61,0,62,-1},
        {0,-1,33,1,34,-1}};
  }
  private boolean[] affect_MainAccepts;
  private void initaffect_MainAccepts() {
    affect_MainAccepts = new boolean[] {true, false};
  }
    
  private int[][] affect_MainTransitions;
  private void initaffect_MainTransitions() {
    affect_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,61,0,62,-1}};
  }
  private boolean[] pow_MainAccepts;
  private void initpow_MainAccepts() {
    pow_MainAccepts = new boolean[] {true, false};
  }
    
  private int[][] pow_MainTransitions;
  private void initpow_MainTransitions() {
    pow_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,94,0,95,-1}};
  }
  private boolean[] import_MainAccepts;
  private void initimport_MainAccepts() {
    import_MainAccepts = new boolean[] {true, false, false, false, false, false, false};
  }
    
  private int[][] import_MainTransitions;
  private void initimport_MainTransitions() {
    import_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,116,0,117,-1},
        {0,-1,112,5,113,-1},
        {0,-1,109,2,110,-1},
        {0,-1,114,1,115,-1},
        {0,-1,111,4,112,-1},
        {0,-1,105,3,106,-1}};
  }
  private boolean[] function_MainAccepts;
  private void initfunction_MainAccepts() {
    function_MainAccepts = new boolean[] {true, false, false, false, false, false, false, false, false};
  }
    
  private int[][] function_MainTransitions;
  private void initfunction_MainTransitions() {
    function_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,110,0,111,-1},
        {0,-1,99,3,100,-1},
        {0,-1,116,5,117,-1},
        {0,-1,102,6,103,-1},
        {0,-1,105,7,106,-1},
        {0,-1,117,8,118,-1},
        {0,-1,111,1,112,-1},
        {0,-1,110,2,111,-1}};
  }
  private boolean[] end_function_MainAccepts;
  private void initend_function_MainAccepts() {
    end_function_MainAccepts = new boolean[] {true, false, false, false};
  }
    
  private int[][] end_function_MainTransitions;
  private void initend_function_MainTransitions() {
    end_function_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,100,0,101,-1},
        {0,-1,110,1,111,-1},
        {0,-1,101,2,102,-1}};
  }
  private boolean[] if_MainAccepts;
  private void initif_MainAccepts() {
    if_MainAccepts = new boolean[] {true, false, false};
  }
    
  private int[][] if_MainTransitions;
  private void initif_MainTransitions() {
    if_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,102,0,103,-1},
        {0,-1,105,1,106,-1}};
  }
  private boolean[] then_MainAccepts;
  private void initthen_MainAccepts() {
    then_MainAccepts = new boolean[] {true, false, false, false, false};
  }
    
  private int[][] then_MainTransitions;
  private void initthen_MainTransitions() {
    then_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,110,0,111,-1},
        {0,-1,101,1,102,-1},
        {0,-1,104,2,105,-1},
        {0,-1,116,3,117,-1}};
  }
  private boolean[] else_MainAccepts;
  private void initelse_MainAccepts() {
    else_MainAccepts = new boolean[] {true, false, false, false, false};
  }
    
  private int[][] else_MainTransitions;
  private void initelse_MainTransitions() {
    else_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,101,0,102,-1},
        {0,-1,108,3,109,-1},
        {0,-1,115,1,116,-1},
        {0,-1,101,2,102,-1}};
  }
  private boolean[] fi_MainAccepts;
  private void initfi_MainAccepts() {
    fi_MainAccepts = new boolean[] {true, false, false};
  }
    
  private int[][] fi_MainTransitions;
  private void initfi_MainTransitions() {
    fi_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,105,0,106,-1},
        {0,-1,102,1,103,-1}};
  }
  private boolean[] return_MainAccepts;
  private void initreturn_MainAccepts() {
    return_MainAccepts = new boolean[] {true, false, false, false, false, false, false};
  }
    
  private int[][] return_MainTransitions;
  private void initreturn_MainTransitions() {
    return_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,110,0,111,-1},
        {0,-1,101,3,102,-1},
        {0,-1,116,6,117,-1},
        {0,-1,114,2,115,-1},
        {0,-1,114,1,115,-1},
        {0,-1,117,5,118,-1}};
  }
  private boolean[] not_MainAccepts;
  private void initnot_MainAccepts() {
    not_MainAccepts = new boolean[] {true, false, false, false};
  }
    
  private int[][] not_MainTransitions;
  private void initnot_MainTransitions() {
    not_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,116,0,117,-1},
        {0,-1,111,1,112,-1},
        {0,-1,110,2,111,-1}};
  }
  private boolean[] print_MainAccepts;
  private void initprint_MainAccepts() {
    print_MainAccepts = new boolean[] {true, false, false, false, false, false};
  }
    
  private int[][] print_MainTransitions;
  private void initprint_MainTransitions() {
    print_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,116,0,117,-1},
        {0,-1,114,3,115,-1},
        {0,-1,105,5,106,-1},
        {0,-1,112,2,113,-1},
        {0,-1,110,1,111,-1}};
  }
  private boolean[] foreach_MainAccepts;
  private void initforeach_MainAccepts() {
    foreach_MainAccepts = new boolean[] {true, false, false, false, false, false, false, false};
  }
    
  private int[][] foreach_MainTransitions;
  private void initforeach_MainTransitions() {
    foreach_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,104,0,105,-1},
        {0,-1,101,7,102,-1},
        {0,-1,111,4,112,-1},
        {0,-1,114,2,115,-1},
        {0,-1,102,3,103,-1},
        {0,-1,99,1,100,-1},
        {0,-1,97,6,98,-1}};
  }
  private boolean[] in_MainAccepts;
  private void initin_MainAccepts() {
    in_MainAccepts = new boolean[] {true, false, false};
  }
    
  private int[][] in_MainTransitions;
  private void initin_MainTransitions() {
    in_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,110,0,111,-1},
        {0,-1,105,1,106,-1}};
  }
  private boolean[] do_MainAccepts;
  private void initdo_MainAccepts() {
    do_MainAccepts = new boolean[] {true, false, false};
  }
    
  private int[][] do_MainTransitions;
  private void initdo_MainTransitions() {
    do_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,111,0,112,-1},
        {0,-1,100,1,101,-1}};
  }
  private boolean[] done_MainAccepts;
  private void initdone_MainAccepts() {
    done_MainAccepts = new boolean[] {true, false, false, false, false};
  }
    
  private int[][] done_MainTransitions;
  private void initdone_MainTransitions() {
    done_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,101,0,102,-1},
        {0,-1,111,4,112,-1},
        {0,-1,100,2,101,-1},
        {0,-1,110,1,111,-1}};
  }
  private boolean[] void_type_MainAccepts;
  private void initvoid_type_MainAccepts() {
    void_type_MainAccepts = new boolean[] {true, false, false, false, false};
  }
    
  private int[][] void_type_MainTransitions;
  private void initvoid_type_MainTransitions() {
    void_type_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,100,0,101,-1},
        {0,-1,105,1,106,-1},
        {0,-1,111,2,112,-1},
        {0,-1,118,3,119,-1}};
  }
  private boolean[] integer_type_MainAccepts;
  private void initinteger_type_MainAccepts() {
    integer_type_MainAccepts = new boolean[] {true, false, false, false};
  }
    
  private int[][] integer_type_MainTransitions;
  private void initinteger_type_MainTransitions() {
    integer_type_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,116,0,117,-1},
        {0,-1,110,1,111,-1},
        {0,-1,105,2,106,-1}};
  }
  private boolean[] boolean_type_MainAccepts;
  private void initboolean_type_MainAccepts() {
    boolean_type_MainAccepts = new boolean[] {true, false, false, false, false};
  }
    
  private int[][] boolean_type_MainTransitions;
  private void initboolean_type_MainTransitions() {
    boolean_type_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,108,0,109,-1},
        {0,-1,98,3,99,-1},
        {0,-1,111,4,112,-1},
        {0,-1,111,1,112,-1}};
  }
  private boolean[] float_type_MainAccepts;
  private void initfloat_type_MainAccepts() {
    float_type_MainAccepts = new boolean[] {true, false, false, false, false, false};
  }
    
  private int[][] float_type_MainTransitions;
  private void initfloat_type_MainTransitions() {
    float_type_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,116,0,117,-1},
        {0,-1,97,1,98,-1},
        {0,-1,111,2,112,-1},
        {0,-1,108,3,109,-1},
        {0,-1,102,4,103,-1}};
  }
  private boolean[] double_type_MainAccepts;
  private void initdouble_type_MainAccepts() {
    double_type_MainAccepts = new boolean[] {true, false, false, false, false, false, false};
  }
    
  private int[][] double_type_MainTransitions;
  private void initdouble_type_MainTransitions() {
    double_type_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,101,0,102,-1},
        {0,-1,98,3,99,-1},
        {0,-1,108,1,109,-1},
        {0,-1,117,2,118,-1},
        {0,-1,111,4,112,-1},
        {0,-1,100,5,101,-1}};
  }
  private boolean[] string_type_MainAccepts;
  private void initstring_type_MainAccepts() {
    string_type_MainAccepts = new boolean[] {true, false, false, false, false, false, false};
  }
    
  private int[][] string_type_MainTransitions;
  private void initstring_type_MainTransitions() {
    string_type_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,103,0,104,-1},
        {0,-1,105,3,106,-1},
        {0,-1,110,1,111,-1},
        {0,-1,114,2,115,-1},
        {0,-1,116,4,117,-1},
        {0,-1,115,5,116,-1}};
  }
  private boolean[] comment_MainAccepts;
  private void initcomment_MainAccepts() {
    comment_MainAccepts = new boolean[] {true, false, false, false};
  }
    
  private int[][] comment_MainTransitions;
  private void initcomment_MainTransitions() {
    comment_MainTransitions = new int[][] {
        {0,-1},
        {0,1,10,0,11,1,13,2,14,1},
        {0,-1,10,0,11,-1},
        {0,-1,35,1,36,-1}};
  }
  private boolean[] boolean_MainAccepts;
  private void initboolean_MainAccepts() {
    boolean_MainAccepts = new boolean[] {true, false, false, false, false, false, false, false};
  }
    
  private int[][] boolean_MainTransitions;
  private void initboolean_MainTransitions() {
    boolean_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,102,5,103,-1,116,3,117,-1},
        {0,-1,101,0,102,-1},
        {0,-1,114,6,115,-1},
        {0,-1,108,7,109,-1},
        {0,-1,97,4,98,-1},
        {0,-1,117,2,118,-1},
        {0,-1,115,2,116,-1}};
  }
  private boolean[] number_MainAccepts;
  private void initnumber_MainAccepts() {
    number_MainAccepts = new boolean[] {true, false};
  }
    
  private int[][] number_MainTransitions;
  private void initnumber_MainTransitions() {
    number_MainTransitions = new int[][] {
        {0,-1,48,0,58,-1},
        {0,-1,48,0,58,-1}};
  }
  private boolean[] float_MainAccepts;
  private void initfloat_MainAccepts() {
    float_MainAccepts = new boolean[] {true, false, false, false, false};
  }
    
  private int[][] float_MainTransitions;
  private void initfloat_MainTransitions() {
    float_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,46,3,47,-1,48,1,58,-1},
        {0,-1,48,2,58,-1,102,0,103,-1},
        {0,-1,48,2,58,-1},
        {0,-1,48,1,58,-1}};
  }
  private boolean[] double_MainAccepts;
  private void initdouble_MainAccepts() {
    double_MainAccepts = new boolean[] {true, false, false, false};
  }
    
  private int[][] double_MainTransitions;
  private void initdouble_MainTransitions() {
    double_MainTransitions = new int[][] {
        {0,-1,48,0,58,-1},
        {0,-1,46,2,47,-1,48,1,58,-1},
        {0,-1,48,0,58,-1},
        {0,-1,48,1,58,-1}};
  }
  private boolean[] identifier_MainAccepts;
  private void initidentifier_MainAccepts() {
    identifier_MainAccepts = new boolean[] {true, false};
  }
    
  private int[][] identifier_MainTransitions;
  private void initidentifier_MainTransitions() {
    identifier_MainTransitions = new int[][] {
        {0,-1,36,0,37,-1,48,0,58,-1,65,0,91,-1,95,0,96,-1,97,0,123,-1},
        {0,-1,36,0,37,-1,65,0,91,-1,95,0,96,-1,97,0,123,-1}};
  }
  private boolean[] string_MainAccepts;
  private void initstring_MainAccepts() {
    string_MainAccepts = new boolean[] {true, false, false};
  }
    
  private int[][] string_MainTransitions;
  private void initstring_MainTransitions() {
    string_MainTransitions = new int[][] {
        {0,-1},
        {0,1,34,0,35,1},
        {0,-1,34,1,35,-1}};
  }
  private boolean[] blk_MainAccepts;
  private void initblk_MainAccepts() {
    blk_MainAccepts = new boolean[] {true, false};
  }
    
  private int[][] blk_MainTransitions;
  private void initblk_MainTransitions() {
    blk_MainTransitions = new int[][] {
        {0,-1},
        {0,-1,9,0,11,-1,13,0,14,-1,32,0,33,-1}};
  }
  
  private final LexerTable<RuleEnum> table;
  
}
