package fr.umlv.tatoo.cc.linker.linker;

import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.VersionDecl;

public interface GrammarModel {
  public NonTerminalDecl getStartNonTerminal(String name);
  public TerminalDecl getBranchingPoint(String name);
  public VersionDecl getVersion(String name);
  
  public VersionDecl getDefaultVersion();
  public NonTerminalDecl getDefaultStartNonTerminal();
}
