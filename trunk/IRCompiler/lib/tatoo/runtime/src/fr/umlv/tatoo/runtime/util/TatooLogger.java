/*
 * Created on Jun 12, 2003
 *
 */
package fr.umlv.tatoo.runtime.util;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/** A logger specific for tatoo runtime that use
 *  java.util.logging for its implementation.
 * 
 * @author Julien
 */
public class TatooLogger {
  private TatooLogger (){/* nothing */}
  
  /** Set the logger level of all parent logger handlers.
   * @param newLevel the new level of the logger.
   * 
   * @see Logger#setLevel(Level)
   */
  public static void setLevel(Level newLevel) {
    for(Logger logger=LOGGER;logger!=null;logger=logger.getParent())
      for(Handler h : logger.getHandlers())
        h.setLevel(newLevel);
  }
	
  /**
   * Set whether or not this logger should send its output
   * to it's parent Logger.
   * 
   * @param useParentHandlers  true if output is to be sent to the
   *  logger's parent.
   * 
   * @see Logger#setUseParentHandlers(boolean)
   */
  public static void setUseParentHandlers(boolean useParentHandlers){
    LOGGER.setUseParentHandlers(useParentHandlers);
  }

  /** Add a new handler to this logger.
   * @param handler the new handler
   * 
   * @see Logger#addHandler(Handler)
   */
  public static void addHandler(Handler handler) {
    LOGGER.addHandler(handler);
  }
  
  /** Emits a warning with a message by the current logger.
   * @param message a message
   */
  public static void warning(String message) {
    LOGGER.warning(message);
  }
  
  /** Emits an information message by the current logger.
   * @param message a message
   */
  public static void info(String message) {
    LOGGER.info(message);
  }  
  
  /** Emits a message with g level SEVERE by the current logger.
   * @param message a message
   */
  public static void severe(String message) {
    LOGGER.severe(message);
  }
  
  /** Emits a message with g level FINE by the current logger.
   * @param message a message
   */
  public static void fine(String message) {
    LOGGER.fine(message);
  }
  
  /** Emits a message with g level FINEST by the current logger.
   * @param message a message
   */
  public static void finest(String message) {
    LOGGER.finest(message);
  }
  
  private final static Logger LOGGER = Logger.getLogger("fr.umlv.tatoo.runtime");
}
