package fr.umlv.tatoo.samples.tutorial;

import fr.umlv.tatoo.samples.tutorial.tools.*;

public class IntExprAttributeEvaluator 
    implements TerminalEvaluator<CharSequence> {
  public int value(CharSequence seq) {
    return Integer.parseInt(seq.toString());
  }
}