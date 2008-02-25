package fr.umlv.IRCompiler.main;

import fr.umlv.IRCompiler.tatoo.tools.TerminalEvaluator;

public class IRCompilerTerminalEvaluator implements
    TerminalEvaluator<CharSequence> {

  @Override
  public String identifier_(CharSequence data) {
    return data.toString();
  }

  @Override
  public Boolean boolean_(CharSequence data) {
    return Boolean.parseBoolean(data.toString());
  }

  @Override
  public Integer number_(CharSequence data) {
    return Integer.parseInt(data.toString());
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
  public String string_(CharSequence data) {
    return new String(data.toString());
  }

}
