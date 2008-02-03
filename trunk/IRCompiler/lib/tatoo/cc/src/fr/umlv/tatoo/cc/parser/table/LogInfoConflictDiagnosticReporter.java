/*
 * Created on 22 feb. 2006
 */
package fr.umlv.tatoo.cc.parser.table;

import java.util.Set;

import fr.umlv.tatoo.cc.common.log.Info;
import fr.umlv.tatoo.cc.common.log.UserDefinedLevelMap;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.parser.ActionEntry;

public class LogInfoConflictDiagnosticReporter extends AbstractConflictDiagnosticReporter { 
  public LogInfoConflictDiagnosticReporter(UserDefinedLevelMap<WarningKey> userDefinedLevelMap) {
    super(userDefinedLevelMap);
  }
  
  @Override
  public void terminalConflict(Key key, NodeDecl<?> node,
      TerminalDecl terminal,
      Set<? extends ActionEntry<?>> actions,
      TerminalDecl eof) {
    if (getLevel(key)==Level.ERROR)
      setOnError();
    reportConflict(key,node,terminal.getId());
  }

  @Override
  public void branchConflict(Key key, NodeDecl<?> node,
      Set<? extends ActionEntry<?>> actions,
      TerminalDecl eof) {
    if (getLevel(key)==Level.ERROR)
      setOnError();
    reportConflict(key,node,actions);
  }
  
  private String processLocation(NodeDecl<?> node) {
    return node.getKernelItems().toString();
  }
  
  private void reportConflict(Key key, NodeDecl<?> node,Object context) {
    String location=processLocation(node);
    
    String message=formatMessage(key,location,context);
    switch(getLevel(key)) {
      case ERROR:
        Info.error(message).report();
        return;
      case WARNING:
        Info.warning(message).report();
        return;
      default:
    }
  }
}
