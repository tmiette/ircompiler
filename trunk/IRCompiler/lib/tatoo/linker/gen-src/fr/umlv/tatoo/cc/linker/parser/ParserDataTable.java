package fr.umlv.tatoo.cc.linker.parser;

import fr.umlv.tatoo.cc.linker.parser.NonTerminalEnum;
import fr.umlv.tatoo.cc.linker.parser.ProductionEnum;
import fr.umlv.tatoo.cc.linker.parser.TerminalEnum;
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
    initlinkStartGotoes();
    initlinkStart_opt_optional0Gotoes();
    initstartGotoes();
    initlinkDef_plusGotoes();
    initgrammarDef_plusGotoes();
    initlinkDefGotoes();
    initlinkVersion_opt_optional1Gotoes();
    initlinkVersionGotoes();
    initgrammarDefGotoes();
    reducelinkVersion_opt_optional1_linkVersion = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.linkVersion_opt_optional1_linkVersion,1,linkVersion_opt_optional1Gotoes);
    reducegrammarDef_plus_element = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.grammarDef_plus_element,1,grammarDef_plusGotoes);
    reducelinkStart_opt_optional0_linkStart = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.linkStart_opt_optional0_linkStart,1,linkStart_opt_optional0Gotoes);
    reducelinkDef_plus_element = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.linkDef_plus_element,1,linkDef_plusGotoes);
    reducegrammarDef_plus_rec = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.grammarDef_plus_rec,2,grammarDef_plusGotoes);
    reducelink_def = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.link_def,7,linkDefGotoes);
    reducelinkStart_opt_optional0_empty = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.linkStart_opt_optional0_empty,0,linkStart_opt_optional0Gotoes);
    reducestart_def = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.start_def,9,startGotoes);
    reducelinkStart_def = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.linkStart_def,2,linkStartGotoes);
    reducegrammar_def = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.grammar_def,3,grammarDefGotoes);
    reducelinkVersion_def = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.linkVersion_def,3,linkVersionGotoes);
    reducelinkVersion_opt_optional1_empty = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.linkVersion_opt_optional1_empty,0,linkVersion_opt_optional1Gotoes);
    reducelinkDef_plus_rec = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.linkDef_plus_rec,2,linkDef_plusGotoes);
    shift21 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(21);
    shift23 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(23);
    shift22 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(22);
    shift3 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(3);
    shift4 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(4);
    shift9 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(9);
    shift2 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(2);
    shift7 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(7);
    shift14 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(14);
    shift1 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(1);
    shift11 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(11);
    shift16 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(16);
    shift15 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(15);
    shift12 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(12);
    shift5 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(5);
    shift17 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(17);
    shift18 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(18);
    shift8 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(8);
    shift10 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(10);
    shift13 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(13);
    error0 = new ErrorAction<TerminalEnum,ProductionEnum,VersionEnum>("parse error");
    branch0 = new BranchAction<TerminalEnum,ProductionEnum,VersionEnum>("parse error");
    initassignArray();
    initquotedstringArray();
    initgrammarArray();
    initcolonArray();
    initrootArray();
    initlbracketArray();
    initdotArray();
    initrbracketArray();
    initidentifierArray();
    init__eof__Array();
    initlinkArray();
    EnumMap<TerminalEnum,Action<TerminalEnum,ProductionEnum,VersionEnum>[]> tableMap =
      new EnumMap<TerminalEnum,Action<TerminalEnum,ProductionEnum,VersionEnum>[]>(TerminalEnum.class);

    tableMap.put(TerminalEnum.assign,assignArray);
    tableMap.put(TerminalEnum.quotedstring,quotedstringArray);
    tableMap.put(TerminalEnum.grammar,grammarArray);
    tableMap.put(TerminalEnum.colon,colonArray);
    tableMap.put(TerminalEnum.root,rootArray);
    tableMap.put(TerminalEnum.lbracket,lbracketArray);
    tableMap.put(TerminalEnum.dot,dotArray);
    tableMap.put(TerminalEnum.rbracket,rbracketArray);
    tableMap.put(TerminalEnum.identifier,identifierArray);
    tableMap.put(TerminalEnum.__eof__,__eof__Array);
    tableMap.put(TerminalEnum.link,linkArray);

    initBranchArrayTable();

    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0grammar = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.grammar);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0linkStart = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.linkStart);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0rbracket = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.rbracket);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0lbracket = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.lbracket);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0linkVersion = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.linkVersion);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0grammarDef_plus = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.grammarDef_plus);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0linkVersion_opt_optional1 = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.linkVersion_opt_optional1);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(null);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0__eof__ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.__eof__);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0dot = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.dot);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0grammarDef = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.grammarDef);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0identifier = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.identifier);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0assign = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.assign);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0start = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.start);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0root = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.root);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0linkDef_plus = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.linkDef_plus);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0quotedstring = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.quotedstring);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0link = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.link);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0linkStart_opt_optional0 = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.linkStart_opt_optional0);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0colon = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.colon);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0linkDef = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.linkDef);

    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum>[] tableMetadata = (StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum>[])new StateMetadata<?,?,?>[]{metadata0null, metadata0grammar, metadata0colon, metadata0identifier, metadata0assign, metadata0quotedstring, metadata0grammarDef_plus, metadata0root, metadata0colon, metadata0identifier, metadata0link, metadata0colon, metadata0identifier, metadata0dot, metadata0identifier, metadata0assign, metadata0identifier, metadata0dot, metadata0identifier, metadata0linkStart, metadata0linkStart_opt_optional0, metadata0lbracket, metadata0identifier, metadata0rbracket, metadata0linkVersion_opt_optional1, metadata0linkVersion, metadata0linkDef_plus, metadata0linkDef, metadata0linkDef, metadata0grammarDef, metadata0grammarDef, metadata0start, metadata0__eof__};
    
    EnumMap<NonTerminalEnum,Integer> tableStarts =
      new EnumMap<NonTerminalEnum,Integer>(NonTerminalEnum.class);
    tableStarts.put(NonTerminalEnum.start,0);
    table = new ParserTable<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>(tableMap,branchArrayTable,tableMetadata,tableStarts,VersionEnum.values(),33,TerminalEnum.__eof__, null);
  } 
  

  private int[] linkStartGotoes;

  private void initlinkStartGotoes() {
    linkStartGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] linkStart_opt_optional0Gotoes;

  private void initlinkStart_opt_optional0Gotoes() {
    linkStart_opt_optional0Gotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] startGotoes;

  private void initstartGotoes() {
    startGotoes = 
      new int[]{31, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] linkDef_plusGotoes;

  private void initlinkDef_plusGotoes() {
    linkDef_plusGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] grammarDef_plusGotoes;

  private void initgrammarDef_plusGotoes() {
    grammarDef_plusGotoes = 
      new int[]{-1, -1, 6, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] linkDefGotoes;

  private void initlinkDefGotoes() {
    linkDefGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 28, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 27, -1, -1, -1, -1, -1, -1};
  }

  private int[] linkVersion_opt_optional1Gotoes;

  private void initlinkVersion_opt_optional1Gotoes() {
    linkVersion_opt_optional1Gotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 24, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] linkVersionGotoes;

  private void initlinkVersionGotoes() {
    linkVersionGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 25, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] grammarDefGotoes;

  private void initgrammarDefGotoes() {
    grammarDefGotoes = 
      new int[]{-1, -1, 30, -1, -1, -1, 29, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] assignArray;
  @SuppressWarnings("unchecked")
  private void initassignArray() {
    assignArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, shift4, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift15, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] quotedstringArray;
  @SuppressWarnings("unchecked")
  private void initquotedstringArray() {
    quotedstringArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, shift5, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] grammarArray;
  @SuppressWarnings("unchecked")
  private void initgrammarArray() {
    grammarArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{shift1, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] colonArray;
  @SuppressWarnings("unchecked")
  private void initcolonArray() {
    colonArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, shift2, branch0, branch0, branch0, branch0, branch0, shift8, branch0, branch0, shift11, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] rootArray;
  @SuppressWarnings("unchecked")
  private void initrootArray() {
    rootArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, reducegrammar_def, shift7, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducegrammarDef_plus_rec, reducegrammarDef_plus_element, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] lbracketArray;
  @SuppressWarnings("unchecked")
  private void initlbracketArray() {
    lbracketArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducelinkStart_opt_optional0_empty, branch0, reducelinkStart_def, reducelinkStart_opt_optional0_linkStart, shift21, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] dotArray;
  @SuppressWarnings("unchecked")
  private void initdotArray() {
    dotArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift13, branch0, branch0, branch0, shift17, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] rbracketArray;
  @SuppressWarnings("unchecked")
  private void initrbracketArray() {
    rbracketArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift23, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] identifierArray;
  @SuppressWarnings("unchecked")
  private void initidentifierArray() {
    identifierArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, shift3, branch0, branch0, reducegrammar_def, shift3, branch0, shift9, branch0, branch0, shift12, branch0, shift14, branch0, shift16, reducelinkStart_opt_optional0_empty, shift18, reducelinkStart_def, reducelinkStart_opt_optional0_linkStart, reducelinkVersion_opt_optional1_empty, shift22, branch0, reducelinkVersion_def, reducelink_def, reducelinkVersion_opt_optional1_linkVersion, shift12, reducelinkDef_plus_rec, reducelinkDef_plus_element, reducegrammarDef_plus_rec, reducegrammarDef_plus_element, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] __eof__Array;
  @SuppressWarnings("unchecked")
  private void init__eof__Array() {
    __eof__Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducelinkStart_opt_optional0_empty, branch0, reducelinkStart_def, reducelinkStart_opt_optional0_linkStart, reducelinkVersion_opt_optional1_empty, branch0, branch0, reducelinkVersion_def, reducelink_def, reducelinkVersion_opt_optional1_linkVersion, reducestart_def, reducelinkDef_plus_rec, reducelinkDef_plus_element, branch0, branch0, accept, accept};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] linkArray;
  @SuppressWarnings("unchecked")
  private void initlinkArray() {
    linkArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift10, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }

  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] branchArrayTable;
  @SuppressWarnings("unchecked")
  private void initBranchArrayTable() {
    branchArrayTable=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, reducelinkStart_opt_optional0_empty, error0, reducelinkStart_def, reducelinkStart_opt_optional0_linkStart, reducelinkVersion_opt_optional1_empty, error0, error0, reducelinkVersion_def, reducelink_def, reducelinkVersion_opt_optional1_linkVersion, reducestart_def, reducelinkDef_plus_rec, reducelinkDef_plus_element, error0, error0, exit, exit};
  }

  private final ParserTable<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> table;
  
  public static final ParserTable<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> createTable() {
    return new ParserDataTable().table;
  }

  private final AcceptAction<TerminalEnum,ProductionEnum,VersionEnum> accept;
  private final ExitAction<TerminalEnum,ProductionEnum,VersionEnum> exit;

  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducelinkVersion_opt_optional1_linkVersion;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducegrammarDef_plus_element;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducelinkStart_opt_optional0_linkStart;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducelinkDef_plus_element;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducegrammarDef_plus_rec;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducelink_def;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducelinkStart_opt_optional0_empty;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducestart_def;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducelinkStart_def;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducegrammar_def;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducelinkVersion_def;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducelinkVersion_opt_optional1_empty;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducelinkDef_plus_rec;

  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift21;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift23;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift22;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift3;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift4;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift9;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift2;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift7;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift14;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift1;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift11;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift16;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift15;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift12;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift5;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift17;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift18;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift8;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift10;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift13;


  private final ErrorAction<TerminalEnum,ProductionEnum,VersionEnum> error0;

  private final BranchAction<TerminalEnum,ProductionEnum,VersionEnum> branch0;

}
