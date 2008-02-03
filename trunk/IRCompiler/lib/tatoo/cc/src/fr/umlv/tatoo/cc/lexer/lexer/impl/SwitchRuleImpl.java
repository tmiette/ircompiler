/*
 * SwitchRuleDecl.java
 *
 * Created: 27 avr. 2006
 */
package fr.umlv.tatoo.cc.lexer.lexer.impl;

import fr.umlv.tatoo.cc.lexer.lexer.RuleDecl;
import fr.umlv.tatoo.cc.lexer.regex.RegexSwitch;

public class SwitchRuleImpl  {
  public SwitchRuleImpl(RuleDecl rule) {
    mainRegex = new RegexSwitch(rule.getMainRegex());
    followRegex = (rule.getFollowRegex() == null) ? null:new RegexSwitch(rule.getFollowRegex());
    this.isBeginningOfLineRequired = rule.isBeginningOfLineRequired();
    this.id = rule.getId();
  }
  
  public RegexSwitch getMainRegex() {
    return mainRegex;
  }
  
  public RegexSwitch getFollowRegex() {
    return followRegex;
  }
  
  public boolean isBeginningOfLineRequired() {
    return isBeginningOfLineRequired;
  }

  public String getId() {
    return id;
  }
  
  private final RegexSwitch mainRegex;
  private final RegexSwitch followRegex;
  private final boolean isBeginningOfLineRequired;
  private final String id;

}
