package fr.umlv.tatoo.cc.lexer.main;

import fr.umlv.tatoo.cc.lexer.charset.encoding.ASCIIEncoding;
import fr.umlv.tatoo.cc.lexer.charset.encoding.Encoding;
import fr.umlv.tatoo.cc.lexer.charset.encoding.ISO8859_1Encoding;
import fr.umlv.tatoo.cc.lexer.charset.encoding.UTF16Encoding;
import fr.umlv.tatoo.cc.lexer.charset.encoding.UTF8Encoding;

public enum LexerType  {
  unicode(new UTF16Encoding()),
  ascii(new ASCIIEncoding()),
  latin1(new ISO8859_1Encoding()),
  utf8(new UTF8Encoding());
 
  
  private LexerType(Encoding charset) {
    this.charset = charset;
  }
  public Encoding getEncoding() {
    return charset;
  }
  
  private final Encoding charset;
}