package fr.umlv.tatoo.runtime.parser;

/** Instruct the parser about what it has to do.
 *  @author julien
 */
public enum ActionReturn {
  /** wait for the next terminal
   */
  NEXT {
    @Override
    SmartStepReturn smartStepReturn() {
      return SmartStepReturn.NEXT;
    }
  },
  
  /** continue with the same terminal
   */
  KEEP,
  
  /** current terminal must be discarded because
   *  there is an error.
   */
  NEXT_ERROR {
    @Override
    SmartStepReturn smartStepReturn() {
      return SmartStepReturn.NEXT;
    }
  },
  
  /**
   * Asks the lexer to reconsider the active rule to parse the input since
   * the expected lookahead set has decreased
   */
  RELEX {
    @Override
    SmartStepReturn smartStepReturn() {
      return SmartStepReturn.RELEX;
    }
  };
  
  SmartStepReturn smartStepReturn() {
    throw new AssertionError("doStep should not return this ActionReturn");
  }
}
