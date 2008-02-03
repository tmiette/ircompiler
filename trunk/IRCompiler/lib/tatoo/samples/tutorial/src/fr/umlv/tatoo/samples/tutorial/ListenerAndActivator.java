package fr.umlv.tatoo.samples.tutorial;

import fr.umlv.tatoo.runtime.buffer.TokenBuffer;
import fr.umlv.tatoo.runtime.lexer.LexerListener;
import fr.umlv.tatoo.runtime.lexer.RuleActivator;
import fr.umlv.tatoo.samples.tutorial.lexer.comment.RuleEnum;

public class ListenerAndActivator implements 
       LexerListener<RuleEnum, TokenBuffer<CharSequence>>,
       RuleActivator<RuleEnum> {
  
  public void ruleVerified(RuleEnum rule, int lastTokenLength, TokenBuffer<CharSequence> buffer) throws RuntimeException {
    switch (rule) {
    case comment_end :
      state = State.out_of_comment;
      buffer.discard();
      break;
    case comment_start :
      state = State.in_comment;
      buffer.discard();
      break;
    case any:
    case space:
      buffer.discard();
      break;
    case semicolon:
      System.out.println(";");
      buffer.discard();
      break;
    default :
      System.out.print(buffer.view());
      buffer.discard();
    }
  }

  public Iterable< ? extends RuleEnum> activeRules() {
        return state.getActiveRules();
  }
  public Iterable<? extends RuleEnum> allRules() {
        return State.all.getActiveRules();
  }
  
  private State state = State.out_of_comment;
}