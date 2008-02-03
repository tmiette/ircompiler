package fr.umlv.tatoo.samples.calc;

import fr.umlv.tatoo.samples.calc.tools.TerminalEvaluator;

public class IntAttributeEvaluator implements TerminalEvaluator<CharSequence> {
  public int value(CharSequence seq) {
    return Integer.parseInt(seq.toString());
  }
  public boolean comma(CharSequence seq) {
    return true;
  }
}