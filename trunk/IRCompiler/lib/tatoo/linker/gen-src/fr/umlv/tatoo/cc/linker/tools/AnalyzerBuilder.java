package fr.umlv.tatoo.cc.linker.tools;

import fr.umlv.tatoo.cc.linker.lexer.LexerDataTable;
import fr.umlv.tatoo.cc.linker.lexer.RuleEnum;
import fr.umlv.tatoo.cc.linker.parser.ParserDataTable;
import fr.umlv.tatoo.cc.linker.parser.NonTerminalEnum;
import fr.umlv.tatoo.cc.linker.parser.TerminalEnum;
import fr.umlv.tatoo.cc.linker.parser.ProductionEnum;
import fr.umlv.tatoo.cc.linker.parser.VersionEnum;
import fr.umlv.tatoo.cc.linker.tools.TerminalEvaluator;
import fr.umlv.tatoo.cc.linker.tools.GrammarEvaluator;
import fr.umlv.tatoo.cc.linker.tools.TerminalRulesMap;
import fr.umlv.tatoo.cc.linker.tools.ToolsProcessor;

import fr.umlv.tatoo.runtime.buffer.LexerBuffer;
import fr.umlv.tatoo.runtime.buffer.TokenBuffer;
import fr.umlv.tatoo.runtime.lexer.Lexer;
import fr.umlv.tatoo.runtime.lexer.LexerListener;
import fr.umlv.tatoo.runtime.parser.Parser;
import fr.umlv.tatoo.runtime.parser.ParserTable;
import fr.umlv.tatoo.runtime.tools.AbstractToolsProcessor;
import fr.umlv.tatoo.runtime.tools.DataViewer;
import fr.umlv.tatoo.runtime.tools.GenericStack;
import fr.umlv.tatoo.runtime.tools.builder.LexerAndParser;
import fr.umlv.tatoo.runtime.tools.builder.LexerBuilder;
import fr.umlv.tatoo.runtime.tools.builder.ParserBuilder;
import fr.umlv.tatoo.runtime.tools.builder.ToolsBuilder;


/** Builder for analyzer
 *
 *  This class is generated - please do not edit it 
 */
public abstract class AnalyzerBuilder<B extends LexerBuffer>
  extends ToolsBuilder<RuleEnum,B,TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum,AnalyzerBuilder<B>>{

  AnalyzerBuilder(LexerBuilder<RuleEnum,B> lexerBuilder,
      ParserBuilder<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> parserBuilder) {
    super(lexerBuilder,parserBuilder);
  }
  
  abstract AbstractToolsProcessor<B,RuleEnum,TerminalEnum,NonTerminalEnum,ProductionEnum> createProcessor(GenericStack stack);
  
  public static <B extends LexerBuffer,D> AnalyzerBuilder<B> createAnalyzer(
      B buffer,
      final TerminalEvaluator<? super D> terminalEvaluator,
      final GrammarEvaluator grammarEvaluator,
      final DataViewer<? super B,? extends D> dataViewer) {
    
    return AnalyzerBuilder.createAnalyzer(
      LexerBuilder.createBuilder(LexerDataTable.createTable(),buffer),
      ParserBuilder.createParserBuilder(ParserDataTable.createTable()),
      terminalEvaluator, grammarEvaluator, dataViewer);
  }
  
  public static <B extends LexerBuffer> AnalyzerBuilder<B> createAnalyzer(B buffer,
      TerminalEvaluator<? super B> terminalEvaluator,
      GrammarEvaluator grammarEvaluator) {
    return AnalyzerBuilder.createAnalyzer(buffer,terminalEvaluator,grammarEvaluator,DataViewer.<B>getIdentityDataViewer());
  }

  public static <B extends LexerBuffer&TokenBuffer<D>,D> AnalyzerBuilder<B> createTokenBufferAnalyzer(
      B buffer,
      TerminalEvaluator<? super D> terminalEvaluator,
      GrammarEvaluator grammarEvaluator) {
    return AnalyzerBuilder.createAnalyzer(
        buffer,terminalEvaluator,grammarEvaluator,
        DataViewer.<D>getTokenBufferViewer());
  }
  
  public static <B extends LexerBuffer,D> AnalyzerBuilder<B> createAnalyzer(
      LexerBuilder<RuleEnum,B> lexerBuilder,
      ParserBuilder<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> parserBuilder,
      final TerminalEvaluator<? super D> terminalEvaluator,
      final GrammarEvaluator grammarEvaluator,
      final DataViewer<? super B,? extends D> dataViewer) {
    
    return new AnalyzerBuilder<B>(lexerBuilder,parserBuilder) {
      @Override
      AbstractToolsProcessor<B,RuleEnum,TerminalEnum,NonTerminalEnum,ProductionEnum> createProcessor(GenericStack stack) {
          return ToolsProcessor.createToolsProcessor(terminalEvaluator,
            grammarEvaluator,
            dataViewer,
            stack);
      }
    };
  }

  @Override
  protected AnalyzerBuilder<B> self() {
    return this;
  }

  /** Create a new lexer and a new parser.
   *  The lexer is plugged to the parser so when a rule is recognized by the lexer
   *  it will call the {@link TerminalEvaluator} and send the corresponding terminal
   *  (if there is one) to the parser.
   *  
   * @return an object containing the new lexer and the new parser.
   */
  @Override
  public LexerAndParser<B,TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> createLexerAndParser() {
    
    GenericStack stack=getSemanticStack();
    if (stack==null) {
      if (getBranchingParserListener()!=null)
        throw new IllegalStateException("branching parser must share the same stack: use setSemanticStack()");
      stack=new GenericStack(20);
    }
    
    AbstractToolsProcessor<B,RuleEnum,TerminalEnum,NonTerminalEnum,ProductionEnum> processor=createProcessor(stack);
    
    // create parser
    ParserTable<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> table = getParserTable();
    setParserListener(processor);
    //FIXME JDK1.7 beta bug
    ToolsBuilder.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum,AnalyzerBuilder<B>>setEnumLookaheadMap(this);
    
    ParserBuilder<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> parserBuilder = getParserBuilder();
    if (table.getErrorTerminal()!=null)
      parserBuilder.setParserErrorRecoveryListener(processor);

    final Parser<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> parser=parserBuilder.createParser();  
    
    // create lexer
    LexerListener<RuleEnum,B> lexerListener=processor.createLexerListener(parser);  
    getLexerBuilder().setParser(parser);
    setActivator(TerminalRulesMap.getActivator(parser));
    setLexerListener(lexerListener);

    final Lexer<B> lexer = getLexerBuilder().createLexer();
    return new LexerAndParser<B,TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>() {
      public Lexer<B> getLexer() {
        return lexer;
      }
      public Parser<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> getParser() {
        return parser;
      }
    };
  }
}