package fr.umlv.IRCompiler.tatoo.parser;

import fr.umlv.IRCompiler.tatoo.parser.NonTerminalEnum;
import fr.umlv.IRCompiler.tatoo.parser.ProductionEnum;
import fr.umlv.IRCompiler.tatoo.parser.TerminalEnum;
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
    initstartGotoes();
    initexprGotoes();
    reduceparenthesis = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.parenthesis,3,exprGotoes);
    reduceor = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.or,3,exprGotoes);
    reducestart__expr = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.start__expr,1,startGotoes);
    reduceand = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.and,3,exprGotoes);
    reducevalue = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.value,1,exprGotoes);
    reducenot = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.not,2,exprGotoes);
    shift2 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(2);
    shift1 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(1);
    shift5 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(5);
    shift7 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(7);
    shift3 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(3);
    shift10 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(10);
    error0 = new ErrorAction<TerminalEnum,ProductionEnum,VersionEnum>("parse error");
    branch0 = new BranchAction<TerminalEnum,ProductionEnum,VersionEnum>("parse error");
    initvalArray();
    init__eof__Array();
    initopen_braceArray();
    initandArray();
    initorArray();
    initclose_braceArray();
    initnotArray();
    EnumMap<TerminalEnum,Action<TerminalEnum,ProductionEnum,VersionEnum>[]> tableMap =
      new EnumMap<TerminalEnum,Action<TerminalEnum,ProductionEnum,VersionEnum>[]>(TerminalEnum.class);

    tableMap.put(TerminalEnum.val,valArray);
    tableMap.put(TerminalEnum.__eof__,__eof__Array);
    tableMap.put(TerminalEnum.open_brace,open_braceArray);
    tableMap.put(TerminalEnum.and,andArray);
    tableMap.put(TerminalEnum.or,orArray);
    tableMap.put(TerminalEnum.close_brace,close_braceArray);
    tableMap.put(TerminalEnum.not,notArray);

    initBranchArrayTable();

    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0close_brace = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.close_brace);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0__eof__ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.__eof__);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0open_brace = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.open_brace);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0or = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.or);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0expr = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.expr);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(null);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0not = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.not);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0val = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.val);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0start = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.start);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0and = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.and);

    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum>[] tableMetadata = (StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum>[])new StateMetadata<?,?,?>[]{metadata0null, metadata0val, metadata0open_brace, metadata0not, metadata0expr, metadata0and, metadata0expr, metadata0or, metadata0expr, metadata0expr, metadata0close_brace, metadata0start, metadata0__eof__, metadata0expr};
    
    EnumMap<NonTerminalEnum,Integer> tableStarts =
      new EnumMap<NonTerminalEnum,Integer>(NonTerminalEnum.class);
    tableStarts.put(NonTerminalEnum.start,0);
    table = new ParserTable<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>(tableMap,branchArrayTable,tableMetadata,tableStarts,VersionEnum.values(),14,TerminalEnum.__eof__, null);
  } 
  

  private int[] startGotoes;

  private void initstartGotoes() {
    startGotoes = 
      new int[]{11, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] exprGotoes;

  private void initexprGotoes() {
    exprGotoes = 
      new int[]{13, -1, 9, 4, -1, 6, -1, 8, -1, -1, -1, -1, -1, -1};
  }

  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] valArray;
  @SuppressWarnings("unchecked")
  private void initvalArray() {
    valArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{shift1, branch0, shift1, shift1, branch0, shift1, branch0, shift1, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] __eof__Array;
  @SuppressWarnings("unchecked")
  private void init__eof__Array() {
    __eof__Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, reducevalue, branch0, branch0, reducenot, branch0, reduceand, branch0, reduceor, branch0, reduceparenthesis, accept, accept, reducestart__expr};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] open_braceArray;
  @SuppressWarnings("unchecked")
  private void initopen_braceArray() {
    open_braceArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{shift2, branch0, shift2, shift2, branch0, shift2, branch0, shift2, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] andArray;
  @SuppressWarnings("unchecked")
  private void initandArray() {
    andArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, reducevalue, branch0, branch0, reducenot, branch0, reduceand, branch0, shift5, shift5, reduceparenthesis, branch0, branch0, shift5};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] orArray;
  @SuppressWarnings("unchecked")
  private void initorArray() {
    orArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, reducevalue, branch0, branch0, reducenot, branch0, reduceand, branch0, reduceor, shift7, reduceparenthesis, branch0, branch0, shift7};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] close_braceArray;
  @SuppressWarnings("unchecked")
  private void initclose_braceArray() {
    close_braceArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, reducevalue, branch0, branch0, reducenot, branch0, reduceand, branch0, reduceor, shift10, reduceparenthesis, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] notArray;
  @SuppressWarnings("unchecked")
  private void initnotArray() {
    notArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{shift3, branch0, shift3, shift3, branch0, shift3, branch0, shift3, branch0, branch0, branch0, branch0, branch0, branch0};
  }

  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] branchArrayTable;
  @SuppressWarnings("unchecked")
  private void initBranchArrayTable() {
    branchArrayTable=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{error0, reducevalue, error0, error0, reducenot, error0, reduceand, error0, reduceor, error0, reduceparenthesis, exit, exit, reducestart__expr};
  }

  private final ParserTable<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> table;
  
  public static final ParserTable<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> createTable() {
    return new ParserDataTable().table;
  }

  private final AcceptAction<TerminalEnum,ProductionEnum,VersionEnum> accept;
  private final ExitAction<TerminalEnum,ProductionEnum,VersionEnum> exit;

  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceparenthesis;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceor;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducestart__expr;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceand;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducevalue;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducenot;

  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift2;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift1;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift5;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift7;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift3;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift10;


  private final ErrorAction<TerminalEnum,ProductionEnum,VersionEnum> error0;

  private final BranchAction<TerminalEnum,ProductionEnum,VersionEnum> branch0;

}
