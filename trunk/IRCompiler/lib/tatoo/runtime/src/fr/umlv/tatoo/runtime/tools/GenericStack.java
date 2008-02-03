package fr.umlv.tatoo.runtime.tools;

import java.util.ArrayList;

import fr.umlv.tatoo.runtime.util.IntArrayList;



/*
 * Created on 16 mai 2004
 *
 */

/** A generic auto-resizeable stack that can store primitive type and objects
 *  used by the parser evaluator generated by Tatoo Tools.
 *  
 * @author Remi
 */
public class GenericStack {
  /** Creates a stack with a specific capacity.
   * @param capacity the initial capacity of the stack before a resize.
   */
  public GenericStack(int capacity) {
    objectStack=new ArrayList<Object>(capacity);
    primitiveStack=new IntArrayList(capacity);
  }
  
  /** Push an object on the stack.
   * @param value an object.
   */
  public void push_Object(Object value) {
    objectStack.add(value);
  }
  
  /** Pop an object from the stack.
   * @return the object on top of the stack.
   */
  public Object pop_Object() {
    return objectStack.remove(objectStack.size()-1);
  }
  
  /** Push an integer on the stack.
   * @param value an integer.
   */
  public void push_int(int value) {
    primitiveStack.add(value);
  }
  
  /** Pop an integer from the stack.
   * @return the integer on top of the stack.
   */
  public int pop_int() {
    return primitiveStack.removeLast();
  }
  
  /** Push a float on the stack.
   * @param value a float.
   */
  public void push_float(float value) {
    push_int(Float.floatToIntBits(value));
  }
  
  /** Pop a float from the stack.
   * @return the float on top of the stack.
   */
  public float pop_float() {
    return Float.intBitsToFloat(pop_int());
  }
  
  /** Push a character on the stack.
   * @param value a character.
   */
  public void push_char(char value) {
    push_int(value);
  }
  
  /** Pop a character from the stack.
   * @return the character on top of the stack.
   */
  public char pop_char() {
    return (char)pop_int();
  }
  
  /** Push a short on the stack.
   * @param value a short.
   */
  public void push_short(short value) {
    push_int(value);
  }
  
  /** Pop a short from the stack.
   * @return the sh on top of the stack.
   */
  public short pop_short() {
    return (short)pop_int();
  }
  
  /** Push a byte on the stack.
   * @param value a byte.
   */
  public void push_byte(byte value) {
    push_int(value);
  }
  
  /** Pop a byte from the stack.
   * @return the byte on top of the stack.
   */
  public byte pop_byte() {
    return (byte)pop_int();
  }
  
  /** Push a boolean on the stack.
   * @param value a boolean.
   */
  public void push_boolean(boolean value) {
    push_int(value?1:0);
  }
  
  /** Pop a boolean from the stack.
   * @return the boolean on top of the stack.
   */
  public boolean pop_boolean() {
    return pop_int()==0?false:true;
  }
  
  /** Push a long on the stack.
   * @param value a long.
   */
  public void push_long(long value) {
    push_int((int)(value&0xffffffff));
    push_int((int)((value>>32)&0xffffffff));
  }
  
  /** Pop a long from the stack.
   * @return the long on top of the stack.
   */
  public long pop_long() {
    return ((long)pop_int()<<32) | pop_int();
  }
  
  /** Push a double on the stack.
   * @param value a double.
   */
  public void push_double(double value) {
    push_long(Double.doubleToLongBits(value));
  }
  
  /** Pop a double from the stack.
   * @return the double on top of the stack.
   */
  public double pop_double() {
    return Double.longBitsToDouble(pop_long());
  }
  
  private final IntArrayList primitiveStack;
  private final ArrayList<Object> objectStack;
}
