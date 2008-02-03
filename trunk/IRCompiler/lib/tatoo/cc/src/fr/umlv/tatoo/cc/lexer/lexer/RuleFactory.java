/*
 * Created on 21 juil. 2005
 *
 */
package fr.umlv.tatoo.cc.lexer.lexer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.umlv.tatoo.cc.common.generator.IdMap;
import fr.umlv.tatoo.cc.common.log.Info;
import fr.umlv.tatoo.cc.lexer.regex.RegexIntervalTable;

/** 
 * 
 * @author Remi
 */
public class RuleFactory {
  public RuleFactory() {
    this.registry = new IdMap<RuleDecl>();
  }

  public IdMap<RuleDecl> getRuleMap() {
    return registry;
  }
  
  public RuleDecl createRule(String id, RegexIntervalTable main, RegexIntervalTable follow,
      boolean beginningOfLineRequired) {
    
    if (follow != null && follow.acceptsEpsilon()) {
      Info.warning("follow regex of rule "+id+" accepts empty word and is ignored").report();
      
      follow=null;
    }
    
    RuleDecl rule=new RuleDecl(id,main,follow,beginningOfLineRequired);
    rules.add(rule);
    registry.put(rule);
    return rule;
  }
  
  public List<? extends RuleDecl> getAllRules() {
    return Collections.unmodifiableList(rules);
  }
  
  private final IdMap<RuleDecl> registry;
  private final ArrayList<RuleDecl> rules=
    new ArrayList<RuleDecl>();
}