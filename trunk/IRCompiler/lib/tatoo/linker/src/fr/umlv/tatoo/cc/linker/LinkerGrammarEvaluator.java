package fr.umlv.tatoo.cc.linker;

import fr.umlv.tatoo.cc.linker.linker.GrammarModel;
import fr.umlv.tatoo.cc.linker.linker.GrammarModule;
import fr.umlv.tatoo.cc.linker.linker.GrammarModuleFactory;
import fr.umlv.tatoo.cc.linker.tools.GrammarEvaluator;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.VersionDecl;

public class LinkerGrammarEvaluator implements GrammarEvaluator {
  public LinkerGrammarEvaluator(GrammarModuleFactory moduleFactory) {
    this.moduleFactory=moduleFactory;
  }
  
  public void acceptStart() {
    // do nothing
  }
  
  public void start_def(String grammarName) {
    GrammarModule grammar=moduleFactory.getModule(grammarName);
    moduleFactory.setRoot(grammar);
  }

  public void grammar_def(String grammarName, String reference) {
    moduleFactory.createModule(grammarName,reference);
  }

  public void link_def(String grammarName1, String terminalName, String grammarName2, String startNonTerminal2, String version2) {
    GrammarModule grammar1=moduleFactory.getModule(grammarName1);
    GrammarModule grammar2=moduleFactory.getModule(grammarName2);
    
    TerminalDecl terminal = grammar1.getModel().getBranchingPoint(terminalName);
    GrammarModel model2 = grammar2.getModel();
    NonTerminalDecl start;
    if (startNonTerminal2!=null)
      start=model2.getStartNonTerminal(startNonTerminal2);
    else
      start=model2.getDefaultStartNonTerminal();
    VersionDecl version;
    if (version2!=null)
      version=model2.getVersion(version2);
    else
      version=model2.getDefaultVersion();
    
    grammar1.addLink(terminal,grammar2,start,version);
  }
  
  public String linkStart_def(String startNonTerminal) {
    return startNonTerminal;
  }
  
  public String linkVersion_def(String version) {
    return version;
  }
  
  private final GrammarModuleFactory moduleFactory;
}
