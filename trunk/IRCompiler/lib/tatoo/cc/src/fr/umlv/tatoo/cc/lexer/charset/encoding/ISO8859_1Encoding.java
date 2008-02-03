/*
 * ASCIICharset.java
 *
 * Created: 23 mars 2006
 */
package fr.umlv.tatoo.cc.lexer.charset.encoding;


public class ISO8859_1Encoding extends Encoding {

  @Override
  public int getMinValue() {
    return Byte.MIN_VALUE;
  }

  @Override
  public int getMaxValue() {
    return Byte.MAX_VALUE;
  }

  @Override
  public int encode(char c) {
    if (c<Byte.MIN_VALUE || c>Byte.MAX_VALUE) {
      throw new IllegalArgumentException("Character "+c+" is not part of latin-1");
    }
    return (byte)c;
  }

  @Override
  public char decode(int c) {
    return (char)(c&0xFF);
  }
}
