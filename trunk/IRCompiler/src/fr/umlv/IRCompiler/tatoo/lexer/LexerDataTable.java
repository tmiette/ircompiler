package fr.umlv.IRCompiler.tatoo.lexer;

import fr.umlv.IRCompiler.tatoo.lexer.RuleEnum;
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
    initvalMainAccepts();
    initvalMainTransitions();
    CharRegexTable valMain = new CharRegexTable(1, valMainTransitions, valMainAccepts);
    RuleData val = new RuleData(valMain, null, false);
    initorMainAccepts();
    initorMainTransitions();
    CharRegexTable orMain = new CharRegexTable(1, orMainTransitions, orMainAccepts);
    RuleData or = new RuleData(orMain, null, false);
    initandMainAccepts();
    initandMainTransitions();
    CharRegexTable andMain = new CharRegexTable(1, andMainTransitions, andMainAccepts);
    RuleData and = new RuleData(andMain, null, false);
    initnotMainAccepts();
    initnotMainTransitions();
    CharRegexTable notMain = new CharRegexTable(1, notMainTransitions, notMainAccepts);
    RuleData not = new RuleData(notMain, null, false);
    initopen_braceMainAccepts();
    initopen_braceMainTransitions();
    CharRegexTable open_braceMain = new CharRegexTable(1, open_braceMainTransitions, open_braceMainAccepts);
    RuleData open_brace = new RuleData(open_braceMain, null, false);
    initclose_braceMainAccepts();
    initclose_braceMainTransitions();
    CharRegexTable close_braceMain = new CharRegexTable(1, close_braceMainTransitions, close_braceMainAccepts);
    RuleData close_brace = new RuleData(close_braceMain, null, false);
    initspaceMainAccepts();
    initspaceMainTransitions();
    CharRegexTable spaceMain = new CharRegexTable(1, spaceMainTransitions, spaceMainAccepts);
    RuleData space = new RuleData(spaceMain, null, false);
    initletterMainAccepts();
    initletterMainTransitions();
    CharRegexTable letterMain = new CharRegexTable(1, letterMainTransitions, letterMainAccepts);
    RuleData letter = new RuleData(letterMain, null, false);
    initblkMainAccepts();
    initblkMainTransitions();
    CharRegexTable blkMain = new CharRegexTable(1, blkMainTransitions, blkMainAccepts);
    RuleData blk = new RuleData(blkMain, null, false);
    EnumMap<RuleEnum,RuleData> datas = new EnumMap<RuleEnum,RuleData>(RuleEnum.class);
    datas.put(RuleEnum.val, val);
    datas.put(RuleEnum.or, or);
    datas.put(RuleEnum.and, and);
    datas.put(RuleEnum.not, not);
    datas.put(RuleEnum.open_brace, open_brace);
    datas.put(RuleEnum.close_brace, close_brace);
    datas.put(RuleEnum.space, space);
    datas.put(RuleEnum.letter, letter);
    datas.put(RuleEnum.blk, blk);
    table = new LexerTable<RuleEnum>(datas);
  }
  
  private boolean[] valMainAccepts;
  private void initvalMainAccepts() {
    valMainAccepts = new boolean[] {true, false, false, false, false, false, false, false};
  }
    
  private int[][] valMainTransitions;
  private void initvalMainTransitions() {
    valMainTransitions = new int[][] {
        {0,-1},
        {0,-1,102,7,103,-1,116,6,117,-1},
        {0,-1,101,0,102,-1},
        {0,-1,108,4,109,-1},
        {0,-1,115,2,116,-1},
        {0,-1,117,2,118,-1},
        {0,-1,114,5,115,-1},
        {0,-1,97,3,98,-1}};
  }
  private boolean[] orMainAccepts;
  private void initorMainAccepts() {
    orMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] orMainTransitions;
  private void initorMainTransitions() {
    orMainTransitions = new int[][] {
        {0,-1},
        {0,-1,124,0,125,-1}};
  }
  private boolean[] andMainAccepts;
  private void initandMainAccepts() {
    andMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] andMainTransitions;
  private void initandMainTransitions() {
    andMainTransitions = new int[][] {
        {0,-1},
        {0,-1,38,0,39,-1}};
  }
  private boolean[] notMainAccepts;
  private void initnotMainAccepts() {
    notMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] notMainTransitions;
  private void initnotMainTransitions() {
    notMainTransitions = new int[][] {
        {0,-1},
        {0,-1,33,0,34,-1}};
  }
  private boolean[] open_braceMainAccepts;
  private void initopen_braceMainAccepts() {
    open_braceMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] open_braceMainTransitions;
  private void initopen_braceMainTransitions() {
    open_braceMainTransitions = new int[][] {
        {0,-1},
        {0,-1,40,0,41,-1}};
  }
  private boolean[] close_braceMainAccepts;
  private void initclose_braceMainAccepts() {
    close_braceMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] close_braceMainTransitions;
  private void initclose_braceMainTransitions() {
    close_braceMainTransitions = new int[][] {
        {0,-1},
        {0,-1,41,0,42,-1}};
  }
  private boolean[] spaceMainAccepts;
  private void initspaceMainAccepts() {
    spaceMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] spaceMainTransitions;
  private void initspaceMainTransitions() {
    spaceMainTransitions = new int[][] {
        {0,-1},
        {0,-1,9,0,11,-1,13,0,14,-1,32,0,33,-1}};
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
  private boolean[] blkMainAccepts;
  private void initblkMainAccepts() {
    blkMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] blkMainTransitions;
  private void initblkMainTransitions() {
    blkMainTransitions = new int[][] {
        {0,-1},
        {0,-1,9,0,11,-1,13,0,14,-1,32,0,33,-1}};
  }
  
  private final LexerTable<RuleEnum> table;
  
}
