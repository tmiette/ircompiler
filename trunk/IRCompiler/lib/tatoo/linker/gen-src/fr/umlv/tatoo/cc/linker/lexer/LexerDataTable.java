package fr.umlv.tatoo.cc.linker.lexer;

import fr.umlv.tatoo.cc.linker.lexer.RuleEnum;
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
    initgrammarMainAccepts();
    initgrammarMainTransitions();
    CharRegexTable grammarMain = new CharRegexTable(4, grammarMainTransitions, grammarMainAccepts);
    RuleData grammar = new RuleData(grammarMain, null, false);
    initrootMainAccepts();
    initrootMainTransitions();
    CharRegexTable rootMain = new CharRegexTable(4, rootMainTransitions, rootMainAccepts);
    RuleData root = new RuleData(rootMain, null, false);
    initlinkMainAccepts();
    initlinkMainTransitions();
    CharRegexTable linkMain = new CharRegexTable(3, linkMainTransitions, linkMainAccepts);
    RuleData link = new RuleData(linkMain, null, false);
    initidentifierMainAccepts();
    initidentifierMainTransitions();
    CharRegexTable identifierMain = new CharRegexTable(1, identifierMainTransitions, identifierMainAccepts);
    RuleData identifier = new RuleData(identifierMain, null, false);
    initquotedstringMainAccepts();
    initquotedstringMainTransitions();
    CharRegexTable quotedstringMain = new CharRegexTable(2, quotedstringMainTransitions, quotedstringMainAccepts);
    RuleData quotedstring = new RuleData(quotedstringMain, null, false);
    initcolonMainAccepts();
    initcolonMainTransitions();
    CharRegexTable colonMain = new CharRegexTable(1, colonMainTransitions, colonMainAccepts);
    RuleData colon = new RuleData(colonMain, null, false);
    initassignMainAccepts();
    initassignMainTransitions();
    CharRegexTable assignMain = new CharRegexTable(1, assignMainTransitions, assignMainAccepts);
    RuleData assign = new RuleData(assignMain, null, false);
    initdotMainAccepts();
    initdotMainTransitions();
    CharRegexTable dotMain = new CharRegexTable(1, dotMainTransitions, dotMainAccepts);
    RuleData dot = new RuleData(dotMain, null, false);
    initlbracketMainAccepts();
    initlbracketMainTransitions();
    CharRegexTable lbracketMain = new CharRegexTable(1, lbracketMainTransitions, lbracketMainAccepts);
    RuleData lbracket = new RuleData(lbracketMain, null, false);
    initrbracketMainAccepts();
    initrbracketMainTransitions();
    CharRegexTable rbracketMain = new CharRegexTable(1, rbracketMainTransitions, rbracketMainAccepts);
    RuleData rbracket = new RuleData(rbracketMain, null, false);
    initspaceMainAccepts();
    initspaceMainTransitions();
    CharRegexTable spaceMain = new CharRegexTable(1, spaceMainTransitions, spaceMainAccepts);
    RuleData space = new RuleData(spaceMain, null, false);
    EnumMap<RuleEnum,RuleData> datas = new EnumMap<RuleEnum,RuleData>(RuleEnum.class);
    datas.put(RuleEnum.grammar, grammar);
    datas.put(RuleEnum.root, root);
    datas.put(RuleEnum.link, link);
    datas.put(RuleEnum.identifier, identifier);
    datas.put(RuleEnum.quotedstring, quotedstring);
    datas.put(RuleEnum.colon, colon);
    datas.put(RuleEnum.assign, assign);
    datas.put(RuleEnum.dot, dot);
    datas.put(RuleEnum.lbracket, lbracket);
    datas.put(RuleEnum.rbracket, rbracket);
    datas.put(RuleEnum.space, space);
    table = new LexerTable<RuleEnum>(datas);
  }
  
  private boolean[] grammarMainAccepts;
  private void initgrammarMainAccepts() {
    grammarMainAccepts = new boolean[] {true, false, false, false, false, false, false, false};
  }
    
  private int[][] grammarMainTransitions;
  private void initgrammarMainTransitions() {
    grammarMainTransitions = new int[][] {
        {0,-1},
        {0,-1,114,0,115,-1},
        {0,-1,109,6,110,-1},
        {0,-1,109,2,110,-1},
        {0,-1,103,7,104,-1},
        {0,-1,97,3,98,-1},
        {0,-1,97,1,98,-1},
        {0,-1,114,5,115,-1}};
  }
  private boolean[] rootMainAccepts;
  private void initrootMainAccepts() {
    rootMainAccepts = new boolean[] {true, false, false, false, false};
  }
    
  private int[][] rootMainTransitions;
  private void initrootMainTransitions() {
    rootMainTransitions = new int[][] {
        {0,-1},
        {0,-1,116,0,117,-1},
        {0,-1,111,3,112,-1},
        {0,-1,111,1,112,-1},
        {0,-1,114,2,115,-1}};
  }
  private boolean[] linkMainAccepts;
  private void initlinkMainAccepts() {
    linkMainAccepts = new boolean[] {true, false, false, false, false};
  }
    
  private int[][] linkMainTransitions;
  private void initlinkMainTransitions() {
    linkMainTransitions = new int[][] {
        {0,-1},
        {0,-1,107,0,108,-1},
        {0,-1,105,4,106,-1},
        {0,-1,108,2,109,-1},
        {0,-1,110,1,111,-1}};
  }
  private boolean[] identifierMainAccepts;
  private void initidentifierMainAccepts() {
    identifierMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] identifierMainTransitions;
  private void initidentifierMainTransitions() {
    identifierMainTransitions = new int[][] {
        {0,-1,48,0,58,-1,65,0,91,-1,97,0,123,-1},
        {0,-1,65,0,91,-1,97,0,123,-1}};
  }
  private boolean[] quotedstringMainAccepts;
  private void initquotedstringMainAccepts() {
    quotedstringMainAccepts = new boolean[] {true, false, false};
  }
    
  private int[][] quotedstringMainTransitions;
  private void initquotedstringMainTransitions() {
    quotedstringMainTransitions = new int[][] {
        {0,-1},
        {0,1,34,0,35,1},
        {0,-1,34,1,35,-1}};
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
  private boolean[] assignMainAccepts;
  private void initassignMainAccepts() {
    assignMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] assignMainTransitions;
  private void initassignMainTransitions() {
    assignMainTransitions = new int[][] {
        {0,-1},
        {0,-1,61,0,62,-1}};
  }
  private boolean[] dotMainAccepts;
  private void initdotMainAccepts() {
    dotMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] dotMainTransitions;
  private void initdotMainTransitions() {
    dotMainTransitions = new int[][] {
        {0,-1},
        {0,0}};
  }
  private boolean[] lbracketMainAccepts;
  private void initlbracketMainAccepts() {
    lbracketMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] lbracketMainTransitions;
  private void initlbracketMainTransitions() {
    lbracketMainTransitions = new int[][] {
        {0,-1},
        {0,-1,123,0,124,-1}};
  }
  private boolean[] rbracketMainAccepts;
  private void initrbracketMainAccepts() {
    rbracketMainAccepts = new boolean[] {true, false};
  }
    
  private int[][] rbracketMainTransitions;
  private void initrbracketMainTransitions() {
    rbracketMainTransitions = new int[][] {
        {0,-1},
        {0,-1,125,0,126,-1}};
  }
  private boolean[] spaceMainAccepts;
  private void initspaceMainAccepts() {
    spaceMainAccepts = new boolean[] {true, false, false};
  }
    
  private int[][] spaceMainTransitions;
  private void initspaceMainTransitions() {
    spaceMainTransitions = new int[][] {
        {0,-1,9,0,11,-1,13,2,14,-1,32,0,33,-1},
        {0,-1,9,0,11,-1,13,2,14,-1,32,0,33,-1},
        {0,-1,10,0,11,-1}};
  }
  
  private final LexerTable<RuleEnum> table;
  
}
