package fr.umlv.tatoo.cc.ebnf;

import java.io.File;

import fr.umlv.tatoo.cc.parser.grammar.EBNFSupport;
import fr.umlv.tatoo.cc.parser.grammar.GrammarFactory;
import fr.umlv.tatoo.cc.parser.grammar.GrammarRepository;
import fr.umlv.tatoo.cc.parser.grammar.ParserTableBuilder;
import fr.umlv.tatoo.cc.parser.parser.ActionDeclFactory;
import fr.umlv.tatoo.cc.parser.table.AbstractConflictDiagnosticReporter;
import fr.umlv.tatoo.cc.parser.table.ConflictResolverPolicy;
import fr.umlv.tatoo.cc.parser.table.ParserTableDecl;
import fr.umlv.tatoo.cc.parser.table.ParserTableDeclFactory;
import fr.umlv.tatoo.cc.parser.table.TableFactoryMethod;

public class EBNFParserTableBuilder implements ParserTableBuilder {
  private final GrammarFactory grammarFactory;
  private final EBNFSupport ebnfSupport;
  private final boolean isFatalError;
  
  public EBNFParserTableBuilder(GrammarFactory grammarFactory,EBNFSupport ebnfSupport, boolean isFatalError) {
    this.grammarFactory=grammarFactory;
    this.ebnfSupport=ebnfSupport;
    this.isFatalError=isFatalError;
  }
  
  public ParserTableDecl createParserTableDecl(ActionDeclFactory actionFactory,
      TableFactoryMethod<?> method, ConflictResolverPolicy conflictResolver,
      AbstractConflictDiagnosticReporter reporter,File log) {
    
    // FIXME java cast to work around bug http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=5034571 
    return ParserTableDeclFactory.buildTable(reporter,grammarFactory.getAllProductions(), 
        grammarFactory.getStartNonTerminalSet(), grammarFactory.getEof(), grammarFactory.getError(),
        grammarFactory.getDependingVersionMap(), actionFactory, 
       (TableFactoryMethod)method, conflictResolver, log);
  }
  
  public GrammarRepository getGrammarItemsRepository() {
    return grammarFactory;
  }
  
  public EBNFSupport getEBNFSupport() {
    return ebnfSupport;
  }
  
  public boolean isFatalError() {
    return isFatalError;
  }
}
