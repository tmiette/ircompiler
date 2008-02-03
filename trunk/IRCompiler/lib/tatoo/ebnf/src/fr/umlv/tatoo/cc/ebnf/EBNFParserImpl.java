package fr.umlv.tatoo.cc.ebnf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import fr.umlv.tatoo.cc.ebnf.ast.ASTGrammarEvaluator;
import fr.umlv.tatoo.cc.ebnf.ast.ASTTerminalEvaluator;
import fr.umlv.tatoo.cc.ebnf.ast.TreeFactory;
import fr.umlv.tatoo.cc.lexer.charset.encoding.Encoding;
import fr.umlv.tatoo.cc.lexer.ebnf.tools.Analyzer;
import fr.umlv.tatoo.cc.lexer.lexer.RuleFactory;
import fr.umlv.tatoo.cc.main.main.EBNFParser;
import fr.umlv.tatoo.cc.parser.grammar.EBNFSupport;
import fr.umlv.tatoo.cc.parser.grammar.GrammarFactory;
import fr.umlv.tatoo.cc.parser.grammar.ParserTableBuilder;
import fr.umlv.tatoo.cc.tools.tools.ToolsFactory;
import fr.umlv.tatoo.runtime.buffer.impl.LocationTracker;
import fr.umlv.tatoo.runtime.buffer.impl.ReaderWrapper;

public class EBNFParserImpl extends EBNFParser {
  private final RuleFactory ruleFactory;
  private final Encoding encoding;
  private final GrammarFactory grammarFactory;
  private final ToolsFactory toolsFactory;
  
  public EBNFParserImpl(RuleFactory ruleFactory,Encoding encoding,GrammarFactory grammarFactory,ToolsFactory toolsFactory) {
    this.ruleFactory=ruleFactory;
    this.encoding=encoding;
    this.grammarFactory=grammarFactory;
    this.toolsFactory=toolsFactory;
  }
  
  @Override
  public ParserTableBuilder parse(File source) throws FileNotFoundException {
    return parse(new FileReader(source));
  }
  
  @Override
  public ParserTableBuilder parse(Reader reader)  {
    LocationTracker locationTracker=new LocationTracker();
    ReaderWrapper buffer=new ReaderWrapper(reader,locationTracker);
    
    EBNFASTImpl ast=new EBNFASTImpl();
    EBNFAnnotationComputer locationComputer=new EBNFAnnotationComputer(locationTracker);
    TreeFactory treeFactory=new TreeFactory(ast,locationComputer);
    
    ASTTerminalEvaluator terminalEvaluator=new ASTTerminalEvaluator(treeFactory);
    ASTGrammarEvaluator grammarEvaluator=new ASTGrammarEvaluator(treeFactory);
    
    Analyzer.run(buffer,terminalEvaluator,grammarEvaluator,null,null);
    
    /*
    LexerBuilder<RuleEnum,ReaderWrapper> builder = 
      LexerBuilder.createReaderBuilder(null,reader);
    
    LexerAndParser<ReaderWrapper,TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> lexerAndParser=
      AnalyzerBuilder.createTokenBufferAnalyzer(builder, grammarEvaluator, terminalEvaluator).createLexerAndParser();
    
    SimpleParser<?> parser=lexerAndParser.getParser();
    lexerAndParser.getLexer().run();
    */
    
    EBNFSupport ebnfSupport=new EBNFSupport(grammarFactory);
    boolean onError=Analysis.analyse(ast,ruleFactory,encoding,grammarFactory,ebnfSupport,toolsFactory);
    
    return new EBNFParserTableBuilder(grammarFactory,ebnfSupport,onError);
  }
}
