package fr.umlv.tatoo.runtime.util;

/** Permits to convert a string to an object of a
 *  specified type. 
 * 
 * @author Remi
 * 
 * @see fr.umlv.tatoo.runtime.ast.XML#unserialize(java.io.Reader, fr.umlv.tatoo.runtime.ast.XMLNodeFactory, StringConverter)
 */
public interface StringConverter {
  /** Convert a string to a value of type T.
   * @param <T> the type of the value.
   * @param text text representation of the value.
   * @param type type of the value.
   * @return the converted value.
   */
  public <T> T convert(String text,Class<T> type);
}
