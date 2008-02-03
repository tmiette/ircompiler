package fr.umlv.tatoo.runtime.tools.builder;

import java.io.Reader;
import java.util.Set;

import fr.umlv.tatoo.runtime.buffer.LexerBuffer;
import fr.umlv.tatoo.runtime.buffer.LocationProvider;
import fr.umlv.tatoo.runtime.buffer.TokenBuffer;
import fr.umlv.tatoo.runtime.buffer.impl.LocationTracker;
import fr.umlv.tatoo.runtime.buffer.impl.ReaderWrapper;
import fr.umlv.tatoo.runtime.lexer.DefaultErrorForwarder;
import fr.umlv.tatoo.runtime.lexer.DefaultLexerErrorRecoveryPolicy;
import fr.umlv.tatoo.runtime.lexer.DefaultLexerWarningReporter;
import fr.umlv.tatoo.runtime.lexer.ErrorForwarder;
import fr.umlv.tatoo.runtime.lexer.Lexer;
import fr.umlv.tatoo.runtime.lexer.LexerErrorRecoveryPolicy;
import fr.umlv.tatoo.runtime.lexer.LexerListener;
import fr.umlv.tatoo.runtime.lexer.LexerTable;
import fr.umlv.tatoo.runtime.lexer.LifecycleHandler;
import fr.umlv.tatoo.runtime.lexer.RuleActivator;
import fr.umlv.tatoo.runtime.lexer.SimpleLexer;
import fr.umlv.tatoo.runtime.parser.SimpleParser;
import fr.umlv.tatoo.runtime.tools.ParserForwarder;

public class LexerBuilder<R,B extends LexerBuffer> {
  protected LexerBuilder(LexerTable<R> table) {
    if (table == null)
      throw new IllegalStateException("lexer is null");
    this.table=table;
  }
  
  public static <R,B extends LexerBuffer> LexerBuilder<R,B> createBuilder(LexerTable<R> table,B buffer) {
    return new LexerBuilder<R,B>(table).setBuffer(buffer);
  }
  
  public static <R> LexerBuilder<R,ReaderWrapper> createReaderBuilder(LexerTable<R> table,Reader reader) {
    return createBuilder(table,new ReaderWrapper(reader, new LocationTracker()));
  }
  
  public SimpleLexer createSimpleLexer() {
    return createLexer();
  }
  
  public Lexer<B> createLexer() {
    LexerErrorRecoveryPolicy<R,B> policy=this.policy;
    if (policy == null) {
      ErrorForwarder<B> forwarder = this.forwarder;
      if (forwarder == null) {
        forwarder = DefaultErrorForwarder.defaultForwarder();
      }
      policy = createDefaultPolicy(forwarder);
    }
    LexerListener<? super R,? super B> listener = this.listener;
    if (listener == null)
      listener = defaultListener;

    RuleActivator<R> activator = this.activator;
    if (activator==null)
       activator=createAllRuleActivator(table);
    
    return Lexer.createLexer(table, buffer, listener, activator, lifecycleHandler, policy);
  }

  /**
   * optional, but if null the buffer have to be given to the lexer using {@link Lexer#reset}
   * @param buffer
   */
  public LexerBuilder<R,B> setBuffer(B buffer) {
    this.buffer = buffer;
    return this;
  }

  /**
   * optional (logging listener by default)
   * @param listener
   */
  public LexerBuilder<R,B> setLexerListener(LexerListener<? super R,? super B> listener) {
    this.listener = listener;
    return this;
  }

  /**
   * optional (all rules by default)
   * @param activator may be null, in that case all rules are activated
   */
  public LexerBuilder<R,B> setActivator(RuleActivator<R> activator) {
    this.activator = activator;
    return this;
  }
    
  /** Creates a rule activator that activate all rules.
   *  This method is called by {@link #createLexer()} if not 
   *  {@link #setActivator(RuleActivator) activator} is set.
   *  
   * @param table the parser table, never null.
   * 
   * @see LexerTable#getRuleDataMap()
   */
  protected RuleActivator<R> createAllRuleActivator(LexerTable<R> table) {
    final Set<? extends R> rules = table.getRuleDataMap().keySet();
    return  new RuleActivator<R>() {
      public Iterable<? extends R> activeRules() {
        return rules;
      }
      public Iterable<? extends R> allRules() {
        return rules;
      }
    };
  }

  /**
   * optionnal (null i.e. nothing or set by setParser by default)
   * @param lifecycleHandler
   */
  public LexerBuilder<R,B> setLifecycleHandler(LifecycleHandler<B> lifecycleHandler) {
    this.lifecycleHandler = lifecycleHandler;
    return this;
  }


  /**
   * optional ({@link DefaultErrorForwarder} i.e. nothing or set by setParser by default)
   * @param forwarder
   */
  public LexerBuilder<R,B> setForwarder(ErrorForwarder<B> forwarder) {
    this.forwarder = forwarder;
    return this;
  }
  
  public void setLexerErrorPolicy(LexerErrorRecoveryPolicy<R, B> policy) {
    this.policy = policy;
  }
  
  protected LexerErrorRecoveryPolicy<R, B> createDefaultPolicy(ErrorForwarder<B> forwarder) {
    return new DefaultLexerErrorRecoveryPolicy<R,B>(forwarder, new DefaultLexerWarningReporter<B>());
  }
  
  /**
   * sets parser forwarder and lifecycle handler forwarding to the parser
   * @param parser
   * @return this
   * 
   * @see #createParserForwarder
   */
  public <T,N,P,V> LexerBuilder<R,B> setParser(SimpleParser<T> parser) {
    ParserForwarder<T,B> forwarder = createParserForwarder(parser);
    setForwarder(forwarder);
    setLifecycleHandler(forwarder);
    return this;
  }
  
  protected <T> ParserForwarder<T,B> createParserForwarder(SimpleParser<T> parser) {
    return new ParserForwarder<T,B>(parser);
  }
  
  


  private static final LexerListener<Object,LexerBuffer> defaultListener =
    new LexerListener<Object,LexerBuffer>() {

    public void ruleVerified(Object rule, int lastTokenLength, LexerBuffer buffer)
    throws RuntimeException {
      String s="";
      if (buffer instanceof TokenBuffer) {
        s = "("+((TokenBuffer<?>)buffer).view().toString()+") ";
      }
      LocationProvider location = buffer.getLocationProvider();
      if (location!=null) {
        System.err.printf("At %d:%d, token %s %srecognized of length %d%n",
            location.getLineNumber(),location.getColumnNumber(),
            rule,s,lastTokenLength);
      }
      else
        System.err.printf("Token %s %srecognized of length %d%n",rule,s,lastTokenLength);
      buffer.discard();
    }

  };

  public RuleActivator<R> getActivator() {
    return activator;
  }

  public B getBuffer() {
    return buffer;
  }

  public ErrorForwarder<B> getForwarder() {
    return forwarder;
  }

  public LifecycleHandler<B> getLifecycleHandler() {
    return lifecycleHandler;
  }

  public LexerListener<? super R,? super B> getLexerListener() {
    return listener;
  }

  public LexerTable<R> getLexerTable() {
    return table;
  }
  
  public LexerErrorRecoveryPolicy<R, B> getLexerErrorPolicy() {
    return policy;
  }

  private final LexerTable<R> table;
  private B buffer;
  private LexerListener<? super R,? super B> listener;
  private RuleActivator<R> activator;
  private LifecycleHandler<B> lifecycleHandler;
  private ErrorForwarder<B> forwarder;
  private LexerErrorRecoveryPolicy<R, B> policy;
}
