package fr.umlv.tatoo.cc.linker.linker;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import fr.umlv.tatoo.cc.common.generator.IdMap;
import fr.umlv.tatoo.cc.lexer.lexer.RuleFactory;
import fr.umlv.tatoo.cc.lexer.main.LexerType;
import fr.umlv.tatoo.cc.main.main.EBNFParser;
import fr.umlv.tatoo.cc.parser.grammar.GrammarFactory;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.ParserTableBuilder;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.VariableDecl;
import fr.umlv.tatoo.cc.parser.grammar.VersionDecl;
import fr.umlv.tatoo.cc.tools.tools.ToolsFactory;


public class EBNFGrammarResolver implements GrammarResolver {
  public GrammarModel getModel(String reference) {
    final GrammarFactory grammarFactory=new GrammarFactory();
    
    EBNFParser parser=EBNFParser.createEBNFParser(
      new RuleFactory(),
      LexerType.unicode.getEncoding(),
      grammarFactory,
      new ToolsFactory());
    
    ParserTableBuilder builder;
    try {
      builder = parser.parse(new File(reference));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    
    //FIXME need to get builder.isFatalError()
    
    final IdMap<VariableDecl> varMap = grammarFactory.getVariableMap();
    final Set<? extends NonTerminalDecl> startSet = grammarFactory.getStartNonTerminalSet();
    
    return new GrammarModel() {
      public TerminalDecl getBranchingPoint(String name){
        TerminalDecl terminal=varMap.get(TerminalDecl.class, name);
        if (terminal!=null && terminal.isBranching())
          return terminal;
        return null;
      }
      public NonTerminalDecl getStartNonTerminal(String name){
        NonTerminalDecl nonTerminal = varMap.get(NonTerminalDecl.class, name);
        if (startSet.contains(nonTerminal))
          return nonTerminal;
        return null;
      }
      public VersionDecl getVersion(String name){
        return grammarFactory.getVersion(name);
      }
      public NonTerminalDecl getDefaultStartNonTerminal() {
        //XXX fixme
        return startSet.iterator().next();
      }
      public VersionDecl getDefaultVersion() {
        //XXX fixme
        return grammarFactory.getAllVersions().iterator().next();
      }
    };
  }
}
