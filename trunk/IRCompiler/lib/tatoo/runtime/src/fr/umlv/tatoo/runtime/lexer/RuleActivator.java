/*
 * Created on 17 juil. 2003
 */
package fr.umlv.tatoo.runtime.lexer;

/**
 * The lexer uses an object that implements this interface
 * to change the set of active rule at the beginning of a
 * token recognition. This can be use to enable/disable
 * some rule depending on the context.
 * 
 * @param <R> type of rules.
 * 
 * @author <a href="mailto:jcervell@univ-mlv.fr">Julien Cervelle</a>
 * @version $Revision$
 */
public interface RuleActivator<R> {
  /**
   * Determines the set of active rules. The method <code>activateRules</code>
   * is called before the beginning of new token recognition.
   * 
   * @return new set of active rules
   */
  public Iterable<? extends R> activeRules();
  
  public Iterable<? extends R> allRules();
}
