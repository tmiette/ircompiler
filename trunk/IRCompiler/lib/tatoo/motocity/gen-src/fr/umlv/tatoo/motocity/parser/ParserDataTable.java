package fr.umlv.tatoo.motocity.parser;

import fr.umlv.tatoo.motocity.parser.NonTerminalEnum;
import fr.umlv.tatoo.motocity.parser.ProductionEnum;
import fr.umlv.tatoo.motocity.parser.TerminalEnum;
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
    initparamGotoes();
    initmethodStartGotoes();
    initstartGotoes();
    initunitGotoes();
    initheadGotoes();
    initendOutGotoes();
    initparamsGotoes();
    initjavaWordGotoes();
    initstartOutGotoes();
    initbodyGotoes();
    initwordGotoes();
    initclassDefGotoes();
    reducemoreJavaLetter = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.moreJavaLetter,2,javaWordGotoes);
    reducehead = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.head,6,headGotoes);
    reduceparamDef = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.paramDef,6,paramGotoes);
    reducemethodStart = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.methodStart,0,methodStartGotoes);
    reducestart = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.start,3,startGotoes);
    reducestartJavaWord = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.startJavaWord,0,javaWordGotoes);
    reducebegin = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.begin,0,bodyGotoes);
    reducefirstLetter = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.firstLetter,1,wordGotoes);
    reducestartOut = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.startOut,0,startOutGotoes);
    reduceendOut = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.endOut,0,endOutGotoes);
    reducejavaExprOuput = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.javaExprOuput,5,unitGotoes);
    reducemoreParam = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.moreParam,2,paramsGotoes);
    reducewordOutput = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.wordOutput,1,unitGotoes);
    reduceunit = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.unit,2,bodyGotoes);
    reduceclassDef = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.classDef,0,classDefGotoes);
    reduceheadClassDef = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.headClassDef,1,headGotoes);
    reducemoreLetter = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.moreLetter,2,wordGotoes);
    reducejavaCodeOutput = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.javaCodeOutput,3,unitGotoes);
    reduceparamsInit = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.paramsInit,0,paramsGotoes);
    shift7 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(7);
    shift6 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(6);
    shift25 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(25);
    shift9 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(9);
    shift31 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(31);
    shift1 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(1);
    shift27 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(27);
    shift24 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(24);
    shift5 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(5);
    shift14 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(14);
    shift8 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(8);
    shift28 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(28);
    shift13 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(13);
    shift20 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(20);
    shift2 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(2);
    shift4 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(4);
    error0 = new ErrorAction<TerminalEnum,ProductionEnum,VersionEnum>("parse error");
    branch0 = new BranchAction<TerminalEnum,ProductionEnum,VersionEnum>("parse error");
    initblankArray();
    initjavaJokerOutArray();
    initheadJokerInArray();
    initjavaLetterArray();
    init__eof__Array();
    initnameArray();
    initparamkwArray();
    initjavaJokerInArray();
    initsemicolonArray();
    initjokerOutArray();
    initjokerInArray();
    initimportsArray();
    initcolonArray();
    initheadJokerOutArray();
    inittypeArray();
    initletterArray();
    EnumMap<TerminalEnum,Action<TerminalEnum,ProductionEnum,VersionEnum>[]> tableMap =
      new EnumMap<TerminalEnum,Action<TerminalEnum,ProductionEnum,VersionEnum>[]>(TerminalEnum.class);

    tableMap.put(TerminalEnum.blank,blankArray);
    tableMap.put(TerminalEnum.javaJokerOut,javaJokerOutArray);
    tableMap.put(TerminalEnum.headJokerIn,headJokerInArray);
    tableMap.put(TerminalEnum.javaLetter,javaLetterArray);
    tableMap.put(TerminalEnum.__eof__,__eof__Array);
    tableMap.put(TerminalEnum.name,nameArray);
    tableMap.put(TerminalEnum.paramkw,paramkwArray);
    tableMap.put(TerminalEnum.javaJokerIn,javaJokerInArray);
    tableMap.put(TerminalEnum.semicolon,semicolonArray);
    tableMap.put(TerminalEnum.jokerOut,jokerOutArray);
    tableMap.put(TerminalEnum.jokerIn,jokerInArray);
    tableMap.put(TerminalEnum.imports,importsArray);
    tableMap.put(TerminalEnum.colon,colonArray);
    tableMap.put(TerminalEnum.headJokerOut,headJokerOutArray);
    tableMap.put(TerminalEnum.type,typeArray);
    tableMap.put(TerminalEnum.letter,letterArray);

    initBranchArrayTable();

    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0start = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.start);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0methodStart = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.methodStart);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0jokerOut = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.jokerOut);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0javaJokerIn = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.javaJokerIn);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0__eof__ = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.__eof__);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0unit = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.unit);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0javaJokerOut = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.javaJokerOut);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0params = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.params);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0headJokerOut = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.headJokerOut);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0head = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.head);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0startOut = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.startOut);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(null);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0blank = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.blank);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0type = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.type);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0javaLetter = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.javaLetter);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0semicolon = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.semicolon);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0body = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.body);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0letter = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.letter);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0javaWord = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.javaWord);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0colon = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.colon);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0param = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.param);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0jokerIn = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.jokerIn);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0word = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.word);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0endOut = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.endOut);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0classDef = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.classDef);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0name = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.name);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0paramkw = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.paramkw);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0headJokerIn = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.headJokerIn);
    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum> metadata0imports = StateMetadata.<TerminalEnum,NonTerminalEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.imports);

    StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum>[] tableMetadata = (StateMetadata<TerminalEnum,NonTerminalEnum,VersionEnum>[])new StateMetadata<?,?,?>[]{metadata0null, metadata0headJokerIn, metadata0imports, metadata0params, metadata0paramkw, metadata0blank, metadata0name, metadata0colon, metadata0type, metadata0semicolon, metadata0param, metadata0classDef, metadata0javaWord, metadata0javaLetter, metadata0headJokerOut, metadata0start, metadata0__eof__, metadata0head, metadata0methodStart, metadata0body, metadata0jokerIn, metadata0startOut, metadata0javaWord, metadata0endOut, metadata0jokerOut, metadata0javaJokerIn, metadata0javaWord, metadata0javaJokerOut, metadata0letter, metadata0unit, metadata0word, metadata0letter, metadata0classDef};
    
    EnumMap<NonTerminalEnum,Integer> tableStarts =
      new EnumMap<NonTerminalEnum,Integer>(NonTerminalEnum.class);
    tableStarts.put(NonTerminalEnum.start,0);
    table = new ParserTable<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>(tableMap,branchArrayTable,tableMetadata,tableStarts,VersionEnum.values(),33,TerminalEnum.__eof__, null);
  } 
  

  private int[] paramGotoes;

  private void initparamGotoes() {
    paramGotoes = 
      new int[]{-1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] methodStartGotoes;

  private void initmethodStartGotoes() {
    methodStartGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 18, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] startGotoes;

  private void initstartGotoes() {
    startGotoes = 
      new int[]{15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] unitGotoes;

  private void initunitGotoes() {
    unitGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 29, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] headGotoes;

  private void initheadGotoes() {
    headGotoes = 
      new int[]{17, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] endOutGotoes;

  private void initendOutGotoes() {
    endOutGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 23, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] paramsGotoes;

  private void initparamsGotoes() {
    paramsGotoes = 
      new int[]{-1, -1, 3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] javaWordGotoes;

  private void initjavaWordGotoes() {
    javaWordGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, 22, -1, -1, -1, 26, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] startOutGotoes;

  private void initstartOutGotoes() {
    startOutGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 21, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] bodyGotoes;

  private void initbodyGotoes() {
    bodyGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] wordGotoes;

  private void initwordGotoes() {
    wordGotoes = 
      new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private int[] classDefGotoes;

  private void initclassDefGotoes() {
    classDefGotoes = 
      new int[]{32, -1, -1, 11, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
  }

  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] blankArray;
  @SuppressWarnings("unchecked")
  private void initblankArray() {
    blankArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, shift5, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] javaJokerOutArray;
  @SuppressWarnings("unchecked")
  private void initjavaJokerOutArray() {
    javaJokerOutArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestartJavaWord, branch0, reducemoreJavaLetter, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestartJavaWord, branch0, branch0, branch0, reducestartJavaWord, shift27, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] headJokerInArray;
  @SuppressWarnings("unchecked")
  private void initheadJokerInArray() {
    headJokerInArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{shift1, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] javaLetterArray;
  @SuppressWarnings("unchecked")
  private void initjavaLetterArray() {
    javaLetterArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceclassDef, branch0, reduceparamsInit, reduceclassDef, branch0, branch0, branch0, branch0, branch0, reduceparamDef, reducemoreParam, reducestartJavaWord, shift13, reducemoreJavaLetter, branch0, branch0, branch0, branch0, branch0, branch0, reducestartOut, reducestartJavaWord, shift13, branch0, branch0, reducestartJavaWord, shift13, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] __eof__Array;
  @SuppressWarnings("unchecked")
  private void init__eof__Array() {
    __eof__Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceclassDef, branch0, branch0, reduceclassDef, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducehead, accept, accept, reducemethodStart, reducebegin, reducestart, branch0, branch0, branch0, branch0, reducejavaExprOuput, branch0, branch0, reducejavaCodeOutput, reducefirstLetter, reduceunit, reducewordOutput, reducemoreLetter, reduceheadClassDef};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] nameArray;
  @SuppressWarnings("unchecked")
  private void initnameArray() {
    nameArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, shift6, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] paramkwArray;
  @SuppressWarnings("unchecked")
  private void initparamkwArray() {
    paramkwArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, reduceparamsInit, shift4, branch0, branch0, branch0, branch0, branch0, reduceparamDef, reducemoreParam, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] javaJokerInArray;
  @SuppressWarnings("unchecked")
  private void initjavaJokerInArray() {
    javaJokerInArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceclassDef, branch0, branch0, reduceclassDef, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducehead, branch0, branch0, reducemethodStart, reducebegin, shift25, branch0, branch0, branch0, branch0, reducejavaExprOuput, branch0, branch0, reducejavaCodeOutput, reducefirstLetter, reduceunit, reducewordOutput, reducemoreLetter, reduceheadClassDef};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] semicolonArray;
  @SuppressWarnings("unchecked")
  private void initsemicolonArray() {
    semicolonArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift9, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] jokerOutArray;
  @SuppressWarnings("unchecked")
  private void initjokerOutArray() {
    jokerOutArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestartJavaWord, branch0, reducemoreJavaLetter, branch0, branch0, branch0, branch0, branch0, branch0, reducestartOut, reducestartJavaWord, reduceendOut, shift24, branch0, reducestartJavaWord, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] jokerInArray;
  @SuppressWarnings("unchecked")
  private void initjokerInArray() {
    jokerInArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceclassDef, branch0, branch0, reduceclassDef, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducehead, branch0, branch0, reducemethodStart, reducebegin, shift20, branch0, branch0, branch0, branch0, reducejavaExprOuput, branch0, branch0, reducejavaCodeOutput, reducefirstLetter, reduceunit, reducewordOutput, reducemoreLetter, reduceheadClassDef};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] importsArray;
  @SuppressWarnings("unchecked")
  private void initimportsArray() {
    importsArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, shift2, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] colonArray;
  @SuppressWarnings("unchecked")
  private void initcolonArray() {
    colonArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, shift7, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] headJokerOutArray;
  @SuppressWarnings("unchecked")
  private void initheadJokerOutArray() {
    headJokerOutArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceclassDef, branch0, reduceparamsInit, reduceclassDef, branch0, branch0, branch0, branch0, branch0, reduceparamDef, reducemoreParam, reducestartJavaWord, shift14, reducemoreJavaLetter, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducestartJavaWord, branch0, branch0, branch0, reducestartJavaWord, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] typeArray;
  @SuppressWarnings("unchecked")
  private void inittypeArray() {
    typeArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0, branch0, branch0, branch0, branch0, branch0, branch0, shift8, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] letterArray;
  @SuppressWarnings("unchecked")
  private void initletterArray() {
    letterArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceclassDef, branch0, branch0, reduceclassDef, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, branch0, reducehead, branch0, branch0, reducemethodStart, reducebegin, shift28, branch0, branch0, branch0, branch0, reducejavaExprOuput, branch0, branch0, reducejavaCodeOutput, reducefirstLetter, reduceunit, shift31, reducemoreLetter, reduceheadClassDef};
  }

  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] branchArrayTable;
  @SuppressWarnings("unchecked")
  private void initBranchArrayTable() {
    branchArrayTable=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceclassDef, error0, error0, reduceclassDef, error0, error0, error0, error0, error0, error0, error0, error0, error0, error0, reducehead, exit, exit, reducemethodStart, reducebegin, reducestart, error0, error0, error0, error0, reducejavaExprOuput, error0, error0, reducejavaCodeOutput, reducefirstLetter, reduceunit, reducewordOutput, reducemoreLetter, reduceheadClassDef};
  }

  private final ParserTable<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> table;
  
  public static final ParserTable<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> createTable() {
    return new ParserDataTable().table;
  }

  private final AcceptAction<TerminalEnum,ProductionEnum,VersionEnum> accept;
  private final ExitAction<TerminalEnum,ProductionEnum,VersionEnum> exit;

  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducemoreJavaLetter;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducehead;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceparamDef;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducemethodStart;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducestart;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducestartJavaWord;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducebegin;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducefirstLetter;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducestartOut;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceendOut;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducejavaExprOuput;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducemoreParam;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducewordOutput;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceunit;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceclassDef;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceheadClassDef;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducemoreLetter;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducejavaCodeOutput;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceparamsInit;

  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift7;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift6;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift25;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift9;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift31;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift1;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift27;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift24;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift5;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift14;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift8;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift28;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift13;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift20;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift2;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift4;


  private final ErrorAction<TerminalEnum,ProductionEnum,VersionEnum> error0;

  private final BranchAction<TerminalEnum,ProductionEnum,VersionEnum> branch0;

}
