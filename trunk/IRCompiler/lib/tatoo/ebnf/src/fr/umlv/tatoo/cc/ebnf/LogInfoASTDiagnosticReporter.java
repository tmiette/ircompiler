package fr.umlv.tatoo.cc.ebnf;

import fr.umlv.tatoo.cc.common.log.Info;
import fr.umlv.tatoo.cc.common.log.UserDefinedLevelMap;
import fr.umlv.tatoo.cc.ebnf.ast.TreeAST;
import fr.umlv.tatoo.cc.ebnf.ast.analysis.AbstractASTDiagnosticReporter;

public class LogInfoASTDiagnosticReporter extends AbstractASTDiagnosticReporter {
  public LogInfoASTDiagnosticReporter(UserDefinedLevelMap<WarningKey> userDefinedLevelMap) {
    super(userDefinedLevelMap);
  }

  @Override
  protected void report(Key key,TreeAST node,Object... data) {
    LineColumnLocation location=node.getAttribute(LineColumnLocation.class);
    
    String message;
    switch(getLevel(key)) {
      case ERROR:
        message=formatMessage(key,data);
        Info.error(message).line(location.getLineNumber()).column(location.getColumnNumber()).report();
        return;
      case WARNING:
        message=formatMessage(key,data);
        Info.warning(message).line(location.getLineNumber()).column(location.getColumnNumber()).report();
        return;
      default:
    }
  }
}
