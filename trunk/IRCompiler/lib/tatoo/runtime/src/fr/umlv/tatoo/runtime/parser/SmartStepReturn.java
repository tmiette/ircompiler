package fr.umlv.tatoo.runtime.parser;

/** Instruct the parser about what it has to do.
 *  @author julien
 */
public enum SmartStepReturn {
  /** wait for the next terminal
   */
  NEXT,
  
  /**
   * Asks the lexer to reconsider the active rule to parse the input since
   * the expected lookahead set has decreased
   */
  RELEX
}
