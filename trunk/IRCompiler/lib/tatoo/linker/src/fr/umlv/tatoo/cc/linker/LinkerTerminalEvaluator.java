package fr.umlv.tatoo.cc.linker;

import fr.umlv.tatoo.cc.linker.tools.TerminalEvaluator;

public class LinkerTerminalEvaluator implements TerminalEvaluator<CharSequence> {
  public String identifier(CharSequence identifier) {
    return identifier.toString();
  }

  // remove leading and trailing quote
  public String quotedstring(CharSequence quotedstring) {
    return quotedstring.subSequence(1,quotedstring.length()-2).toString();
  }

  public void space(CharSequence data) {
    // do nothing
  }
  
  public void assign(CharSequence data) {
    // do nothing
  }

  public void dot(CharSequence data) {
    // do nothing
  }
  
  public void colon(CharSequence data) {
    // do nothing
  }

  public void grammar(CharSequence data) {
    // do nothing
  }
  public void link(CharSequence data) {
    // do nothing
  }
  public void root(CharSequence data) {
    // do nothing
  }
  
  public void lbracket(CharSequence data) {
    // do nothing
  }
  public void rbracket(CharSequence data) {
    // do nothing
  }
}
