package fr.umlv.tatoo.cc.ebnf.ast.analysis;

import java.util.ResourceBundle;

import fr.umlv.tatoo.cc.common.log.AbstractDiagnosticReporter;
import fr.umlv.tatoo.cc.common.log.UserDefinedLevelMap;
import fr.umlv.tatoo.cc.ebnf.ast.TreeAST;

public abstract class AbstractASTDiagnosticReporter extends AbstractDiagnosticReporter<ASTDiagnosticReporter.WarningKey> implements ASTDiagnosticReporter {
  
  protected AbstractASTDiagnosticReporter(UserDefinedLevelMap<WarningKey> userDefinedLevelMap) {
    super(userDefinedLevelMap);
  }

  public void error(ErrorKey key,TreeAST node,Object... data) {
    report(key,node,data);
    setOnError();
  }

  public void warning(WarningKey key,TreeAST node,Object... data) {
    report(key,node,data);
  }
  
  protected abstract void report(Key key,TreeAST node,Object... data);
  
  @Override
  protected ResourceBundle getBundle() {
    return BUNDLE;
  }
  
  private static final ResourceBundle BUNDLE=
    ResourceBundle.getBundle("fr.umlv.tatoo.cc.ebnf.ast.analysis.diagnostic");
}
