package fr.umlv.tatoo.samples.tutorial;

import java.util.EnumSet;

import fr.umlv.tatoo.samples.tutorial.lexer.comment.RuleEnum;

public enum State  {
  in_comment(EnumSet.of(RuleEnum.comment_end, RuleEnum.any)) , 
  out_of_comment(EnumSet.complementOf(in_comment.getActiveRules())),
  all(EnumSet.allOf(RuleEnum.class));

  private State(EnumSet<RuleEnum> activeRules) {
    this.activeRules=activeRules;
  }  
  public EnumSet<RuleEnum> getActiveRules() {
    return this.activeRules;
  }
  private final EnumSet<RuleEnum> activeRules;
}
