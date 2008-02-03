package fr.umlv.tatoo.motocity.lexer;

import fr.umlv.tatoo.motocity.lexer.RuleEnum;
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
    initletterMainAccepts();
    initletterMainTransitions();
    CharRegexTable letterMain = new CharRegexTable(1, letterMainTransitions, letterMainAccepts);
    RuleData letter = new RuleData(letterMain, null, false);
    initjavaLetterMainAccepts();
    initjavaLetterMainTransitions();
    CharRegexTable javaLetterMain = new CharRegexTable(1, javaLetterMainTransitions, javaLetterMainAccepts);
    RuleData javaLetter = new RuleData(javaLetterMain, null, false);
    initjokerInMainAccepts();
    initjokerInMainTransitions();
    CharRegexTable jokerInMain = new CharRegexTable(2, jokerInMainTransitions, jokerInMainAccepts);
    RuleData jokerIn = new RuleData(jokerInMain, null, false);
    initjokerOutMainAccepts();
    initjokerOutMainTransitions();
    CharRegexTable jokerOutMain = new CharRegexTable(2, jokerOutMainTransitions, jokerOutMainAccepts);
    RuleData jokerOut = new RuleData(jokerOutMain, null, false);
    initjavaJokerInMainAccepts();
    initjavaJokerInMainTransitions();
    CharRegexTable javaJokerInMain = new CharRegexTable(2, javaJokerInMainTransitions, javaJokerInMainAccepts);
    RuleData javaJokerIn = new RuleData(javaJokerInMain, null, false);
    initjavaJokerOutMainAccepts();
    initjavaJokerOutMainTransitions();
    CharRegexTable javaJokerOutMain = new CharRegexTable(2, javaJokerOutMainTransitions, javaJokerOutMainAccepts);
    RuleData javaJokerOut = new RuleData(javaJokerOutMain, null, false);
    initheadJokerInMainAccepts();
    initheadJokerInMainTransitions();
    CharRegexTable headJokerInMain = new CharRegexTable(6, headJokerInMainTransitions, headJokerInMainAccepts);
    RuleData headJokerIn = new RuleData(headJokerInMain, null, false);
    initheadJokerOutMainAccepts();
    initheadJokerOutMainTransitions();
    CharRegexTable headJokerOutMain = new CharRegexTable(2, headJokerOutMainTransitions, headJokerOutMainAccepts);
    RuleData headJokerOut = new RuleData(headJokerOutMain, null, false);
    initimportsMainAccepts();
    initimportsMainTransitions();
    CharRegexTable importsMain = new CharRegexTable(0, importsMainTransitions, importsMainAccepts);
    RuleData imports = new RuleData(importsMain, null, false);
    initparamkwMainAccepts();
    initparamkwMainTransitions();
    CharRegexTable paramkwMain = new CharRegexTable(1, paramkwMainTransitions, paramkwMainAccepts);
    RuleData paramkw = new RuleData(paramkwMain, null, false);
    initblankMainAccepts();
    initblankMainTransitions();
    CharRegexTable blankMain = new CharRegexTable(1, blankMainTransitions, blankMainAccepts);
    RuleData blank = new RuleData(blankMain, null, false);
    initnameMainAccepts();
    initnameMainTransitions();
    CharRegexTable nameMain = new CharRegexTable(1, nameMainTransitions, nameMainAccepts);
    RuleData name = new RuleData(nameMain, null, false);
    inittypeMainAccepts();
    inittypeMainTransitions();
    CharRegexTable typeMain = new CharRegexTable(1, typeMainTransitions, typeMainAccepts);
    RuleData type = new RuleData(typeMain, null, false);
    initcolonMainAccepts();
    initcolonMainTransitions();
    CharRegexTable colonMain = new CharRegexTable(1, colonMainTransitions, colonMainAccepts);
    RuleData colon = new RuleData(colonMain, null, false);
    initsemicolonMainAccepts();
    initsemicolonMainTransitions();
    CharRegexTable semicolonMain = new CharRegexTable(1, semicolonMainTransitions, semicolonMainAccepts);
    RuleData semicolon = new RuleData(semicolonMain, null, false);
    EnumMap<RuleEnum,RuleData> datas = new EnumMap<RuleEnum,RuleData>(RuleEnum.class);
    datas.put(RuleEnum.letter, letter);
    datas.put(RuleEnum.javaLetter, javaLetter);
    datas.put(RuleEnum.jokerIn, jokerIn);
    datas.put(RuleEnum.jokerOut, jokerOut);
    datas.put(RuleEnum.javaJokerIn, javaJokerIn);
    datas.put(RuleEnum.javaJokerOut, javaJokerOut);
    datas.put(RuleEnum.headJokerIn, headJokerIn);
    datas.put(RuleEnum.headJokerOut, headJokerOut);
    datas.put(RuleEnum.imports, imports);
    datas.put(RuleEnum.paramkw, paramkw);
    datas.put(RuleEnum.blank, blank);
    datas.put(RuleEnum.name, name);
    datas.put(RuleEnum.type, type);
    datas.put(RuleEnum.colon, colon);
    datas.put(RuleEnum.semicolon, semicolon);
    table = new LexerTable<RuleEnum>(datas);
  }
  
  private boolean[] letterMainAccepts;
  private void initletterMainAccepts() {
    letterMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] letterMainTransitions;
  private void initletterMainTransitions() {
    letterMainTransitions = new int[][] {
        {0,-1},
        {0,0}};
  }
  private boolean[] javaLetterMainAccepts;
  private void initjavaLetterMainAccepts() {
    javaLetterMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] javaLetterMainTransitions;
  private void initjavaLetterMainTransitions() {
    javaLetterMainTransitions = new int[][] {
        {0,-1},
        {0,0}};
  }
  private boolean[] jokerInMainAccepts;
  private void initjokerInMainAccepts() {
    jokerInMainAccepts = new boolean[] {true, false, false, false};
  }
    
  private int[][] jokerInMainTransitions;
  private void initjokerInMainTransitions() {
    jokerInMainTransitions = new int[][] {
        {0,-1},
        {0,-1,124,0,125,-1},
        {0,-1,60,3,61,-1},
        {0,-1,91,1,92,-1}};
  }
  private boolean[] jokerOutMainAccepts;
  private void initjokerOutMainAccepts() {
    jokerOutMainAccepts = new boolean[] {true, false, false, false};
  }
    
  private int[][] jokerOutMainTransitions;
  private void initjokerOutMainTransitions() {
    jokerOutMainTransitions = new int[][] {
        {0,-1},
        {0,-1,62,0,63,-1},
        {0,-1,124,3,125,-1},
        {0,-1,93,1,94,-1}};
  }
  private boolean[] javaJokerInMainAccepts;
  private void initjavaJokerInMainAccepts() {
    javaJokerInMainAccepts = new boolean[] {true, false, false, false, false, false, false, false, false};
  }
    
  private int[][] javaJokerInMainTransitions;
  private void initjavaJokerInMainTransitions() {
    javaJokerInMainTransitions = new int[][] {
        {0,-1},
        {0,-1,91,0,92,-1},
        {0,-1,60,8,61,-1},
        {0,-1,74,6,75,-1},
        {0,-1,86,7,87,-1},
        {0,-1,91,3,92,-1},
        {0,-1,65,4,66,-1},
        {0,-1,65,1,66,-1},
        {0,-1,33,5,34,-1}};
  }
  private boolean[] javaJokerOutMainAccepts;
  private void initjavaJokerOutMainAccepts() {
    javaJokerOutMainAccepts = new boolean[] {true, false, false, false};
  }
    
  private int[][] javaJokerOutMainTransitions;
  private void initjavaJokerOutMainTransitions() {
    javaJokerOutMainTransitions = new int[][] {
        {0,-1},
        {0,-1,62,0,63,-1},
        {0,-1,93,3,94,-1},
        {0,-1,93,1,94,-1}};
  }
  private boolean[] headJokerInMainAccepts;
  private void initheadJokerInMainAccepts() {
    headJokerInMainAccepts = new boolean[] {true, false, false, false, false, false, false, false, false};
  }
    
  private int[][] headJokerInMainTransitions;
  private void initheadJokerInMainTransitions() {
    headJokerInMainTransitions = new int[][] {
        {0,-1},
        {0,-1,91,0,92,-1},
        {0,-1,72,7,73,-1},
        {0,-1,91,2,92,-1},
        {0,-1,33,3,34,-1},
        {0,-1,68,1,69,-1},
        {0,-1,60,4,61,-1},
        {0,-1,69,8,70,-1},
        {0,-1,65,5,66,-1}};
  }
  private boolean[] headJokerOutMainAccepts;
  private void initheadJokerOutMainAccepts() {
    headJokerOutMainAccepts = new boolean[] {true, false, false, false};
  }
    
  private int[][] headJokerOutMainTransitions;
  private void initheadJokerOutMainTransitions() {
    headJokerOutMainTransitions = new int[][] {
        {0,-1},
        {0,-1,62,0,63,-1},
        {0,-1,93,3,94,-1},
        {0,-1,93,1,94,-1}};
  }
  private boolean[] importsMainAccepts;
  private void initimportsMainAccepts() {
    importsMainAccepts = new boolean[] {true, false, false, false, false, false, false, false};
  }
    
  private int[][] importsMainTransitions;
  private void initimportsMainTransitions() {
    importsMainTransitions = new int[][] {
        {0,-1,9,1,14,-1,28,1,33,-1,105,7,106,-1},
        {0,-1,9,1,14,-1,28,1,33,-1,105,7,106,-1},
        {0,2,59,0,60,2},
        {0,-1,112,6,113,-1},
        {0,-1,114,5,115,-1},
        {0,-1,116,2,117,-1},
        {0,-1,111,4,112,-1},
        {0,-1,109,3,110,-1}};
  }
  private boolean[] paramkwMainAccepts;
  private void initparamkwMainAccepts() {
    paramkwMainAccepts = new boolean[] {true, false, false, false, false, false, false};
  }
    
  private int[][] paramkwMainTransitions;
  private void initparamkwMainTransitions() {
    paramkwMainTransitions = new int[][] {
        {0,-1},
        {0,-1,9,1,14,-1,28,1,33,-1,37,3,38,-1},
        {0,-1,109,0,110,-1},
        {0,-1,112,4,113,-1},
        {0,-1,97,6,98,-1},
        {0,-1,97,2,98,-1},
        {0,-1,114,5,115,-1}};
  }
  private boolean[] blankMainAccepts;
  private void initblankMainAccepts() {
    blankMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] blankMainTransitions;
  private void initblankMainTransitions() {
    blankMainTransitions = new int[][] {
        {0,-1,9,0,14,-1,28,0,33,-1},
        {0,-1,9,0,14,-1,28,0,33,-1}};
  }
  private boolean[] nameMainAccepts;
  private void initnameMainAccepts() {
    nameMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] nameMainTransitions;
  private void initnameMainTransitions() {
    nameMainTransitions = new int[][] {
        {0,-1,48,0,58,-1,65,0,91,-1,95,0,96,-1,97,0,123,-1},
        {0,-1,65,0,91,-1,95,0,96,-1,97,0,123,-1}};
  }
  private boolean[] typeMainAccepts;
  private void inittypeMainAccepts() {
    typeMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] typeMainTransitions;
  private void inittypeMainTransitions() {
    typeMainTransitions = new int[][] {
        {0,0,59,-1,60,0},
        {0,0,59,-1,60,0}};
  }
  private boolean[] colonMainAccepts;
  private void initcolonMainAccepts() {
    colonMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] colonMainTransitions;
  private void initcolonMainTransitions() {
    colonMainTransitions = new int[][] {
        {0,-1},
        {0,-1,58,0,59,-1}};
  }
  private boolean[] semicolonMainAccepts;
  private void initsemicolonMainAccepts() {
    semicolonMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] semicolonMainTransitions;
  private void initsemicolonMainTransitions() {
    semicolonMainTransitions = new int[][] {
        {0,-1},
        {0,-1,59,0,60,-1}};
  }
  
  private final LexerTable<RuleEnum> table;
  
}
