/*
 * ASCIICharset.java
 *
 * Created: 23 mars 2006
 */
package fr.umlv.tatoo.cc.lexer.charset.encoding;


public class UTF16Encoding extends Encoding {

  @Override
  public int getMinValue() {
    return Character.MIN_VALUE;
  }

  @Override
  public int getMaxValue() {
    return Character.MAX_VALUE;
  }

  @Override
  public int encode(char c) {
    return c;
  }

  @Override
  public char decode(int c) {
    return (char)c;
  }
}
