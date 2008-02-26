package fr.umlv.IRCompiler.util;

import tatoo.tools.TerminalEvaluator;

/**
 * Evaluator of the terminal symbol of the custom language grammar.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public class IRCompilerTerminalEvaluator implements
    TerminalEvaluator<CharSequence> {

  @Override
  public Boolean boolean_(CharSequence data) {
    return Boolean.parseBoolean(data.toString());
  }

  @Override
  public Double double_(CharSequence data) {
    return Double.parseDouble(data.toString());
  }

  @Override
  public Float float_(CharSequence data) {
    return Float.parseFloat(data.toString());
  }

  @Override
  public String identifier_(CharSequence data) {
    return data.toString();
  }

  @Override
  public Integer number_(CharSequence data) {
    return Integer.parseInt(data.toString());
  }

  @Override
  public String string_(CharSequence data) {
    return new String(data.toString());
  }

}
