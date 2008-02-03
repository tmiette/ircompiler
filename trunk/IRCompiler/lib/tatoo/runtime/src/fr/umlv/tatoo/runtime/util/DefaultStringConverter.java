package fr.umlv.tatoo.runtime.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/** Default converter implementation.
 *  This converter convert a string to a value by using of one
 *  of its 'parse' method.
 *  
 *  <ol>
 *   <li>The 'parse' method are recognized by the converter
 *       if a method obey to the following rules :
 *       <ul>
 *        <li>the method is tagged by the annotation {@link ParseMethod ParseMethod}
 *        <li>the method have one or two parameter, the first one is a string,
 *            the second one (optional) is the class.
 *        <li>the method must have a return type (other than void).
 *       </ul>
 *    <li>The converter choose the 'parse' method that have the most specific
 *        type comparing with the type argument of the method
 *        {@link #convert(String, Class) convert(String,T.class)}.
 *  </ol>
 *  
 * @author Remi
 */
public class DefaultStringConverter implements StringConverter {
  /** Marker annotation on method, indicates a 'parse' method.
   * @author Remi
   */
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface ParseMethod {
    // marker annotation
  }
  
  /** Create a converter.
   */
  public DefaultStringConverter() {
    HashMap<Class<?>,Method> map=new HashMap<Class<?>,Method>();
    for(Class<?> clazz = getClass();clazz!=Object.class;clazz=clazz.getSuperclass()) {
      for(Method method:clazz.getDeclaredMethods()) {
        if (!method.isAnnotationPresent(ParseMethod.class))
          continue;
        
        // for performance
        try {
          method.setAccessible(true);
        } catch(SecurityException e) {
          // do nothing
        }
        map.put(method.getReturnType(),method);
      }
    }
    this.map=map;
  }
  
  /** Converts a text to a single char.
   * @param text the text to parse.
   * @return a single char.
   * @throws NumberFormatException
   */
  @ParseMethod protected char parseChar(String text) {
    if (text.length()==1)
      return text.charAt(0);
    throw new IllegalStateException(text+" is not a character");
  }
  
  /** Converts a text to a string.
   * @param text the text to parse.
   * @return the texte take as argument.
   */
  @ParseMethod protected String parseString(String text) {
    return text;
  }
  
  /** Converts a text to an integer.
   * @param text text to parse.
   * @return an integer corresponding to the text.
   * @throws NumberFormatException if the text is not an integer.
   */
  @ParseMethod protected int parseInt(String text) {
    return Integer.parseInt(text);
  }
  
  /** Converts a text to a double.
   * @param text text to parse.
   * @return a double corresponding to the text.
   * @throws NumberFormatException if the text is not a double.
   */
  @ParseMethod protected double parseDouble(String text) {
    return Double.parseDouble(text);
  }
  
  /** Converts a text to a boolean
   * @param text text to parse.
   * @return a boolean corresponding to the text.
   * @throws NumberFormatException if the text is not a boolean.
   */
  @ParseMethod protected boolean parseBoolean(String text) {
    return Boolean.parseBoolean(text);
  }
  
  /** Convert a text containing the name of an enumeration item
   *  to its value.
   *  
   * @param <E> type of the enumeration.
   * @param text texte to parse.
   * @param clazz the class of the enumeration.
   * @return the corresponding enumeration object.
   * @throws IllegalArgumentException if the text isn't a name
   *  of one of the item of the enumeration.
   */
  @ParseMethod protected <E extends Enum<E>> E parseEnum(String text,Class<E> clazz) {
    return Enum.valueOf(clazz,text);
  }
  
  private Method findMethod(Class<?> clazz) {
    for(Class<?> c=clazz;c!=null;c=c.getSuperclass()) {
      Method method=map.get(c);
      if (method!=null) {
        if (clazz!=c)
          map.put(clazz,method);
        
        return method;
      }
    }
    throw new IllegalArgumentException("no converter for "+clazz);
  }
  
  @SuppressWarnings("unchecked")
  public <T> T convert(String value,Class<T> clazz) {
    Method method=findMethod(clazz);
    try {
      if (method.getParameterTypes().length==1)
        return (T)method.invoke(this,value);
      else
        return (T)method.invoke(this,value,clazz);
    } catch (IllegalAccessException e) {
      throw new AssertionError(e);
    } catch (InvocationTargetException e) {
      throw new AssertionError(e.getCause());
    }
  }
  
  private final HashMap<Class<?>,Method> map;
}
