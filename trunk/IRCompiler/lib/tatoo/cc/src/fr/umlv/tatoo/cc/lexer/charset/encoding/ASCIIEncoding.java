/*
 * ASCIICharset.java
 *
 * Created: 23 mars 2006
 */
package fr.umlv.tatoo.cc.lexer.charset.encoding;


public class ASCIIEncoding extends Encoding {

  @Override
  public int getMinValue() {
    return 0;
  }

  @Override
  public int getMaxValue() {
    return 127;
  }

  @Override
  public int encode(char c) {
    if (c<0 || c>127) {
      throw new IllegalArgumentException("Character "+c+" is not part of ASCII");
    }
    return c;
  }

  @Override
  public char decode(int c) {
    return (char)c;
  }
  

}
