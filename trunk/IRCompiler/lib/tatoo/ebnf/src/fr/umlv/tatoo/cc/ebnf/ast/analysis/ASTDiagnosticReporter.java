package fr.umlv.tatoo.cc.ebnf.ast.analysis;

import fr.umlv.tatoo.cc.common.log.DiagnosticReporter;
import fr.umlv.tatoo.cc.common.log.DiagnosticReporter.Level;
import fr.umlv.tatoo.cc.ebnf.ast.TreeAST;

public interface ASTDiagnosticReporter {
  public enum ErrorKey implements DiagnosticReporter.Key {
    import_not_qualified,
    priority_var_unknown,
    version_var_unknown,
    priority_duplicate,
    rule_duplicate,
    terminal_eof_duplicate,
    terminal_duplicate,
    nonterminal_duplicate,
    nonTerminal_var_unknown,
    nonTerminal_var_terminal_instead,
    terminal_var_unknown,
    terminal_var_nonterminal_instead,
    terminal_var_branch_nonterminal_instead,
    duplicate_type_declared,
    unknown_directive;
    
    public Level defaultLevel() {
      return Level.ERROR;
    }
  }
  
  public enum WarningKey implements DiagnosticReporter.Key {
    two_terminals_with_same_alias,
    type_not_exist,
    import_duplicate,
    bad_associativity,
    startnonterminals_empty_recover,
    startnonterminals_duplicates,
    generated_production_id,
    duplicate_type_declared_recover;
    
    public Level defaultLevel() {
      return Level.WARNING;
    }
  }
  
  public boolean isOnError();
  
  public void error(ErrorKey key, TreeAST node, Object... data);
  public void warning(WarningKey key, TreeAST node, Object... data);
}
