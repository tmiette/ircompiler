package fr.umlv.tatoo.cc.lexer.main;

import java.io.File;

import fr.umlv.tatoo.cc.common.main.GeneratorBean;
import fr.umlv.tatoo.cc.lexer.main.LexerOption.LexerParam;

public class LexerBean extends GeneratorBean implements LexerParam {
  public LexerBean() {
    setDestination(new File("."));
    setLexerType(LexerType.unicode);
  }
  
  public LexerType getLexerType() {
    return lexerType;
  }
  
  public void setLexerType(LexerType lexerType) {
    this.lexerType = lexerType;
  }
  
  private LexerType lexerType;
}
