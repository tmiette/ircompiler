package fr.umlv.IRCompiler.util;

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

}
