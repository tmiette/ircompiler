package fr.umlv.tatoo.cc.linker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import fr.umlv.tatoo.cc.linker.linker.GrammarModuleFactory;
import fr.umlv.tatoo.cc.linker.tools.Analyzer;

public class LinkerParser {
  public LinkerParser(GrammarModuleFactory factory) {
    this.factory=factory;
  }
  public void parse(File source) throws FileNotFoundException {
    LinkerGrammarEvaluator grammarEvaluator=new LinkerGrammarEvaluator(factory);
    LinkerTerminalEvaluator terminalEvaluator=new LinkerTerminalEvaluator(); 
    Analyzer.run(new FileReader(source),terminalEvaluator,grammarEvaluator,null,null);
  }
  
  private final GrammarModuleFactory factory;
}
