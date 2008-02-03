package fr.umlv.tatoo.cc.tools.tools;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.umlv.tatoo.cc.common.generator.Type;
import fr.umlv.tatoo.cc.common.log.Info;
import fr.umlv.tatoo.cc.lexer.lexer.RuleDecl;
import fr.umlv.tatoo.cc.lexer.lexer.RuleFactory;
import fr.umlv.tatoo.cc.parser.grammar.GrammarFactory;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.VariableDecl;

public class ToolsFactory  {
  public Map<RuleDecl,RuleInfo> getRuleInfoMap() {
    return ruleInfoMap; 
  }
  
  public Map<TerminalDecl,Type> getTerminalTypeMap() {
    return terminalTypeMap; 
  }
  
  public Map<VariableDecl,Type> getVariableTypeMap() {
    return variableTypeMap; 
  }
  
  public Map<RuleDecl,Type> getRuleTypeMap() {
    HashMap<RuleDecl,Type> ruleTypeMap=new HashMap<RuleDecl,Type>();
    for(Map.Entry<RuleDecl,RuleInfo> entry:ruleInfoMap.entrySet()) {
      TerminalDecl terminal = entry.getValue().getTerminal();
      if (terminal!=null)
        ruleTypeMap.put(entry.getKey(),terminalTypeMap.get(terminal));
    }
    return ruleTypeMap;
  }
  
  public Set<? extends RuleDecl> getUnconditionalRuleSet() {
    return unconditionalRuleSet;
  }
  
  public Map<TerminalDecl,? extends Set<? extends RuleDecl>> getTerminalRulesMap() {
    HashMap<TerminalDecl,HashSet<RuleDecl>> map = new HashMap<TerminalDecl,HashSet<RuleDecl>>();
    for(Map.Entry<RuleDecl,RuleInfo> entry : ruleInfoMap.entrySet()) {
      TerminalDecl terminal = entry.getValue().getTerminal();
      RuleDecl rule = entry.getKey();
      addToTerminalSet(map, rule, terminal);
      if (entry.getValue().isAlwaysActive()) {
        addToTerminalSet(map, rule, null);
      }
    }
    return map;
  }

  private static void addToTerminalSet(HashMap<TerminalDecl, HashSet<RuleDecl>> map, RuleDecl rule, TerminalDecl terminal) {
    HashSet<RuleDecl> set=map.get(terminal);
    if (set==null) {
      set=new HashSet<RuleDecl>();
      map.put(terminal,set);
    }
    set.add(rule);
  }
  
  public void declareTerminalType(TerminalDecl terminal,Type type) {
    terminalTypeMap.put(terminal,type);
    variableTypeMap.put(terminal,type);
  }
  
  public void declareNonTerminalType(NonTerminalDecl nonTerminal,Type type) {
    variableTypeMap.put(nonTerminal,type);
  }
  
  public RuleInfo createRuleInfo(RuleDecl rule,TerminalDecl terminal,TerminalDecl terminalPart,boolean discard,boolean alwaysActive) {
    if (terminal != null && terminalPart != null) 
      throw new IllegalArgumentException("terminal and terminal-part attributes are incompatible");
    if (ruleInfoMap.containsKey(rule))
      throw new IllegalStateException("rule "+rule.getId()+" is already defined");
    
    boolean spawn;
    TerminalDecl ruleTerminal;
    if (terminal!=null){
      spawn=true;
      ruleTerminal=terminal;
    } else {
      spawn=false;
      ruleTerminal=terminalPart;
    }
    
    if (ruleTerminal==null)
      unconditionalRuleSet.add(rule);
    if (alwaysActive)
      unconditionalRuleSet.add(rule);
    
    //System.err.println("add rule info "+rule+" "+ruleTerminal);
    
    RuleInfo ruleInfo=new RuleInfo(ruleTerminal,discard,alwaysActive,spawn);
    ruleInfoMap.put(rule,ruleInfo);
    return ruleInfo;
  }
  
  public void checkUndefinedRules(RuleFactory ruleFactory,Map<RuleDecl,RuleInfo> ruleInfoMap) {
    HashSet<RuleDecl> rules=new HashSet<RuleDecl>(ruleFactory.getAllRules());
    rules.removeAll(ruleInfoMap.keySet());
    
    if (!rules.isEmpty()) {
      Info.warning("Undefined rule infos for rules %s",rules).report();
    }
  }
  
  public void checkUnspawnTerminals(GrammarFactory grammarFactory,Map<RuleDecl,RuleInfo> ruleInfoMap) {
    // check unspawn terminals
    TerminalDecl error = grammarFactory.getError();
    HashSet<TerminalDecl> spawnedTerminals=new HashSet<TerminalDecl>(grammarFactory.getAllTerminals());
    spawnedTerminals.remove(grammarFactory.getEof());
    spawnedTerminals.remove(error);
    for(RuleInfo info:ruleInfoMap.values()) {
      TerminalDecl terminal = info.getTerminal();
      if (terminal!=null) 
        spawnedTerminals.remove(terminal);
    }
    if (!spawnedTerminals.isEmpty()) {
      Info.warning("Unspawned terminals %s",spawnedTerminals).report();
    }
  }
  
  
  private final HashSet<RuleDecl> unconditionalRuleSet=
    new HashSet<RuleDecl>();
  
  private final HashMap<RuleDecl,RuleInfo> ruleInfoMap=
    new HashMap<RuleDecl,RuleInfo>();
  
  private final HashMap<TerminalDecl,Type> terminalTypeMap=
    new HashMap<TerminalDecl,Type>();
  private final HashMap<VariableDecl,Type> variableTypeMap=
    new HashMap<VariableDecl,Type>();
  
  
}
