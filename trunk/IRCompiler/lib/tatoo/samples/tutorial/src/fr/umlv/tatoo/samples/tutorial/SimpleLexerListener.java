package fr.umlv.tatoo.samples.tutorial;

import fr.umlv.tatoo.runtime.buffer.TokenBuffer;
import fr.umlv.tatoo.runtime.lexer.LexerListener;
import fr.umlv.tatoo.samples.tutorial.lexer.RuleEnum;

public class SimpleLexerListener implements LexerListener<RuleEnum, TokenBuffer<CharSequence>> {
  public void ruleVerified(RuleEnum t, int lastTokenLength, TokenBuffer<CharSequence> buffer) {
    switch (t) {
    case value:
      System.out.println(buffer.view());
      buffer.discard();
      break;
    default:
      buffer.discard();
    }
  }
}
