/*
 * Created on 22 feb. 2006
 */
package fr.umlv.tatoo.cc.parser.table;

import java.util.ResourceBundle;
import java.util.Set;

import fr.umlv.tatoo.cc.common.log.AbstractDiagnosticReporter;
import fr.umlv.tatoo.cc.common.log.DiagnosticReporter;
import fr.umlv.tatoo.cc.common.log.UserDefinedLevelMap;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.parser.ActionEntry;

public abstract class AbstractConflictDiagnosticReporter extends AbstractDiagnosticReporter<AbstractConflictDiagnosticReporter.WarningKey> { 
  public enum ErrorKey implements DiagnosticReporter.Key {
    shift_shift,
    reduce_reduce,
    no_priority
    ;

    public Level defaultLevel() {
      return Level.ERROR;
    }
  }
  
  public enum WarningKey implements DiagnosticReporter.Key {
    shift_by_default,
    ;
    
    public Level defaultLevel() {
      return Level.WARNING;
    }
  }
  
  public enum InfoKey implements DiagnosticReporter.Key {
    associativity_shift,
    associativity_reduce,
    associativity_error
    ;
    
    public Level defaultLevel() {
      return Level.INFO;
    }
  }
  
  protected AbstractConflictDiagnosticReporter(UserDefinedLevelMap<WarningKey> userDefinedLevelMap) {
    super(userDefinedLevelMap);
  }
  
  public void conflict(Key key, NodeDecl<?> node,
      TerminalDecl terminal, Set<? extends ActionEntry<?>> actions,
      TerminalDecl eof) {
    if (terminal==null)
      branchConflict(key, node, actions, eof);
    else
      terminalConflict(key,node,terminal,actions,eof);
  }
  

  protected abstract void terminalConflict(Key key, NodeDecl<?> node,
      TerminalDecl terminal,
      Set<? extends ActionEntry<?>> actions,
      TerminalDecl eof);

  protected abstract void branchConflict(Key key, NodeDecl<?> node,
      Set<? extends ActionEntry<?>> actions,
      TerminalDecl eof);
  
  @Override
  protected ResourceBundle getBundle() {
    return BUNDLE;
  }
  
  private static final ResourceBundle BUNDLE=
    ResourceBundle.getBundle("fr.umlv.tatoo.cc.parser.table.diagnostic");
}
