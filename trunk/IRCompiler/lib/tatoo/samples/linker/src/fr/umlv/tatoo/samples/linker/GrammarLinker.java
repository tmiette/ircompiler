package fr.umlv.tatoo.samples.linker;

import java.util.HashMap;

import fr.umlv.tatoo.runtime.parser.BranchingParserListener;
import fr.umlv.tatoo.runtime.parser.ParserTable;
import fr.umlv.tatoo.runtime.tools.GenericStack;
import fr.umlv.tatoo.runtime.tools.builder.LexerAndParser;
import fr.umlv.tatoo.runtime.tools.builder.ToolsBuilder;

public class GrammarLinker<T,N,V> {
  private GrammarLinker(ToolsBuilder<?,?,T,N,?,V,?> builder) {
    this.builder=builder;
  }
  
  static class LinkerEntry<T,N,V> {
    private final GrammarLinker<T,N,V> linker;
    private final N startNonTerminal;
    private final V version;

    public LinkerEntry(GrammarLinker<T,N,V> linker, N startNonTerminal, V version) {
      this.linker=linker;
      this.startNonTerminal=startNonTerminal;
      this.version=version;
    }

    void setup(GenericStack semanticStack) {
      linker.setup(semanticStack);
    }

    public void enter() {
      linker.lexerAndParser.getParser().push(startNonTerminal,version);
      try {
        linker.lexerAndParser.getLexer().run();
      } catch(ExitLexing e) {
        // reduce are done by exit
      }
    }
  }

  //fake exception, used to stop lexing
  static class ExitLexing extends RuntimeException {
    private static final long serialVersionUID = 1L;  
  }
  static final ExitLexing EXIT_LEXING=new ExitLexing();
  
  void run() {
    setup(new GenericStack(20));
    lexerAndParser.getLexer().run();
  }
  
  void setup(GenericStack semanticStack) {
    // avoid cycle
    if (lexerAndParser!=null) {
      return;
    }

    // init builder
    builder.setSemanticStack(semanticStack);
    builder.setBranchingParserListener(new BranchingParserListener<T>() {
      public boolean enter(T terminal) {
        LinkerEntry<?,?,?> entry=entryMap.get(terminal);
        if (entry==null)
          return false;

        entry.enter();
        return true;
      }

      public void exit() {
        throw EXIT_LEXING;
      }
    });

    // creation
    lexerAndParser=builder.createLexerAndParser();

    for(LinkerEntry<?,?,?> entry:entryMap.values()) {
      entry.setup(semanticStack);
    }
  }

  public <T2,N2,V2> void register(T terminal,GrammarLinker<T2,N2,V2> linker) {
    register(terminal,linker,null,null);
  }
  
  public <T2,N2,V2> void register(T terminal,GrammarLinker<T2,N2,V2> linker,N2 startNonTerminal) {
    register(terminal,linker,startNonTerminal,null);
  }

  public <T2,N2,V2> void register(T terminal,GrammarLinker<T2,N2,V2> linker,N2 startNonTerminal,V2 version) {
    if (lexerAndParser!=null)
      throw new IllegalStateException("unable to register while running");
    ParserTable<T2,N2,?,V2> parserTable=linker.builder.getParserTable();
    if (parserTable==null)
      throw new IllegalStateException("linker builder has no parser table defined");
    if (startNonTerminal==null)
      startNonTerminal=parserTable.getDefaultStart();
    if (version==null)
      version=parserTable.getDefaultVersion();
    entryMap.put(terminal,new LinkerEntry<T2,N2,V2>(linker,startNonTerminal,version));
  }  

  private final ToolsBuilder<?,?,T,N,?,V,?> builder;
  LexerAndParser<?,T,N,?,V> lexerAndParser;
  final HashMap<T,LinkerEntry<?,?,?>> entryMap=
    new HashMap<T,LinkerEntry<?,?,?>>();
  
  public static <T,N,V,S extends ToolsBuilder<?,?,T,N,?,V,S>> GrammarLinker<T,N,V> create(S toolsBuilder) {
    return new GrammarLinker<T,N,V>(toolsBuilder);
  }
}
