/*
 * Created on 18 nov. 2005
 *
 */
package fr.umlv.tatoo.runtime.buffer.impl;

import fr.umlv.tatoo.runtime.buffer.TokenBuffer;

/**
 * A {@code CharSequenceWrapper} wraps a {@code CharSequence} in order to allow
 * a lexer to process its content in order to have its tokens extracted.
 * 
 * @see fr.umlv.tatoo.runtime.lexer.Lexer
 * @author Julien
 */
public class CharSequenceWrapper extends AbstractBufferWrapper implements TokenBuffer<CharSequence>,CharSequence {
  /**
   * Constructs a {@link CharSequenceWrapper} which wraps specified {@code CharSequence}.
   * 
   * @param seq the {@code CharSequence} to wrap
   * @param tracker the location tracker
   */ 
  public CharSequenceWrapper(CharSequence seq, LocationTracker tracker) {
    super(tracker);
    this.seq=seq;
    newLine=true;
  }
  
  public int getPosition() {
    return position;
  }
  public CharSequence getSequence() {
    return seq;
  }
  public int getTokenStart() {
    return tokenStart;
  }
  public int getTokenEnd() {
    return tokenEnd;
  }
  
  @Override
  protected void unwindImpl(int l) {
    tokenEnd += l;
    char lastChar=seq.charAt(tokenEnd-1);
    newLine = lastChar=='\n'|| lastChar=='\r';
    position=tokenEnd;
  }
  
  @Override
  protected void resetImpl() {
    position=tokenEnd;
  }

  public boolean previousWasNewLine() {
    return newLine;
  }

  public boolean hasRemaining() {
    return position<seq.length();
  }
  
  public boolean read() {
    return hasRemaining();
  }

  @Override
  protected int nextImpl() {
    return seq.charAt(position++);
  }
  
  @Override
  protected void discardImpl() {
    tokenStart=tokenEnd;
  }

  public CharSequence view() {
    return seq.subSequence(tokenStart,tokenEnd);
  }
  
  public char charAt(int position) {
    checkIndex(position);
    return seq.charAt(tokenStart+position);
  }
  
  public int length() {
    return tokenEnd-tokenStart;
  }
  
  public CharSequence subSequence(int start,int end) {
    checkIndex(start);
    checkIndex(end);
    if(start>end)
      throw new IndexOutOfBoundsException();
    return seq.subSequence(tokenStart+start, tokenStart+end);
  }
  
  private void checkIndex(int index) {
    if (index<0 || index>=tokenEnd-tokenStart)
      throw new IndexOutOfBoundsException();
  }
  
  public int lastChar() {
    if (position==0)
      return -1;
    return seq.charAt(position-1);
  }
  
  @Override
  protected void restartImpl() {
    position = tokenStart;
    tokenEnd = tokenStart;
    if (position==0)
      newLine=true;
    else {
      newLine=isEoln(seq.charAt(position-1));
    }
  }
  
  private int position;
  private int tokenStart;
  private int tokenEnd;
  private boolean newLine;
  
  private final CharSequence seq;
}
