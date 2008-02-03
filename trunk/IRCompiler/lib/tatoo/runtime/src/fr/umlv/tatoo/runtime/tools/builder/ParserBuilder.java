package fr.umlv.tatoo.runtime.tools.builder;

import fr.umlv.tatoo.runtime.parser.BranchingParserListener;
import fr.umlv.tatoo.runtime.parser.DefaultParserErrorRecoveryPolicy;
import fr.umlv.tatoo.runtime.parser.DefaultParserWarningReporter;
import fr.umlv.tatoo.runtime.parser.ErrorRecoveryListener;
import fr.umlv.tatoo.runtime.parser.ParserErrorRecoveryPolicy;
import fr.umlv.tatoo.runtime.parser.LookaheadMap;
import fr.umlv.tatoo.runtime.parser.Parser;
import fr.umlv.tatoo.runtime.parser.ParserListener;
import fr.umlv.tatoo.runtime.parser.ParserTable;
import fr.umlv.tatoo.runtime.parser.ParserWarningReporter;
import fr.umlv.tatoo.runtime.parser.SimpleParser;
import fr.umlv.tatoo.runtime.tools.LookaheadMapFactory;

public class ParserBuilder<T,N,P,V> {
  
  public static <T,N,P,V> ParserBuilder<T,N,P,V> createParserBuilder(ParserTable<T,N,P,V> table) {
    return new ParserBuilder<T,N,P,V>(table);
  }

  protected ParserBuilder(ParserTable<T,N,P,V> table) {
    if (table==null)
      throw new IllegalArgumentException("table is null");
    this.table=table;
  }
  
  public Parser<T,N,P,V> createParser() {
    if (listener == null)
      listener=defaultParserListener;
    if (policy==null)
      policy=createDefaultParserErrorRecoveryPolicy();
    if (start==null)
      start=table.getDefaultStart();
    if (version==null)
      version=table.getDefaultVersion();
    Parser<T,N,P,V> parser=Parser.createParser(table, listener, policy, start, version, lookaheadMap);
    if (branchingListener != null) {
      parser.setBranchingParserListener(branchingListener);
    }
    return parser;
  }
  
  public SimpleParser<T> createSimpleParser() {
    return createParser();
  }
  
  private ParserErrorRecoveryPolicy<T, N, P, V> createDefaultParserErrorRecoveryPolicy() {
    T error = table.getErrorTerminal();
    if (error == null || parserErrorRecoveryListener == null)
      return createNoErrorRecoveryPolicy();
    else
      return new DefaultParserErrorRecoveryPolicy<T, N, P, V>(parserErrorRecoveryListener);
  }
  
  /** Overrides this method if you want to change the warning reporter used
   *  by the no error recovery policy.
   *  
   * @return the no error recovery policy with a default warning reporter.
   * 
   * @see ParserErrorRecoveryPolicy#getNoErrorRecoveryPolicy(ParserWarningReporter)
   */
  protected ParserErrorRecoveryPolicy<T,N,P,V> createNoErrorRecoveryPolicy() {
    return ParserErrorRecoveryPolicy.getNoErrorRecoveryPolicy(
      new DefaultParserWarningReporter<T,N,P,V>());
  }
  
  /**
   * optional (default is no error recovery)
   * @param errorRecoveryListener
   */
  public ParserBuilder<T,N,P,V> setParserErrorRecoveryListener(
      ErrorRecoveryListener<? super T, ? super N> errorRecoveryListener) {
    
    if (table.getErrorTerminal()==null)
      throw new IllegalStateException("the grammar file doesn't define an error terminal");
    
    this.parserErrorRecoveryListener = errorRecoveryListener;
    return this;
  }

  /**
   * optional (default prints actions on standard error)
   * @param listener
   */
  public ParserBuilder<T,N,P,V> setParserListener(ParserListener<? super T, ? super N, ? super P> listener) {
    this.listener = listener;
    return this;
  }

  /**
   * optional (default null)
   * @param branchingListener branching listener
   */
  public ParserBuilder<T,N,P,V> setBranchingParserListener(BranchingParserListener<? super T> branchingListener) {
    this.branchingListener=branchingListener;
    return this;
  }
  
  /**
   * optional (default is no lookahead map)
   * @param lookaheadMap
   */
  public ParserBuilder<T,N,P,V> setLookaheadMap(LookaheadMap<? extends T,? super V> lookaheadMap) {
    this.lookaheadMap = lookaheadMap;
    return this;
  }
 
  /**
   * parser table and version (or default) must be set before calling this method.
   * @return this
   */
  public ParserBuilder<T,N,P,V> setHashLookaheadMap() {
    lookaheadMap=LookaheadMapFactory.hashLookaheadMap(table);
    return this;
  }
  
  /**
   * parser table must be set before calling this method.
   * @return this
   */
  public static <T extends Enum<T>,N,P,V extends Enum<V>> ParserBuilder<T,N,P,V> setEnumLookaheadMap(
      ParserBuilder<T,N,P,V> builder) {
    
    builder.lookaheadMap=LookaheadMapFactory.enumLookaheadMap(builder.table);
    return builder;
  }

  /** Set the paraesr error recovery policy. 
   * optional (default is default policy if there is an error terminal and an
   * error recovery listener, or else no recovery)
   * 
   * @param policy an error recovery policy.
   * @return the current builder.
   */
  public ParserBuilder<T,N,P,V> setParserErrorRecoveryPolicy(ParserErrorRecoveryPolicy<T, N, P, V> policy) {
    this.policy = policy;
    return this;
  }

  /** Set the non terminal that will be used to start the parsing.
   *  This method is optional (default is default start)
   * @param start a start non terminal.
   * @return return the current builder.
   * 
   * @see #setParserTable(ParserTable)
   * @see #setVersion(Object)
   * @see #getStart()
   */
  public ParserBuilder<T,N,P,V> setStart(N start) {
    this.start = start;
    return this;
  }

  /** Set the version of the grammar that will be use to start the parsing.
   *  This method is optional (default is default version)
   *  
   * @param version the parsing version,
   *        if null the default version will be used.
   * @return return the current builder.
   * 
   * @see #setStart(Object)
   * @see #setParserTable(ParserTable)
   * @see #getVersion()
   */
  public ParserBuilder<T,N,P,V> setVersion(V version) {
    this.version = version;
    return this;
  }
  
  public ErrorRecoveryListener<? super T, ? super N> getErrorRecoveryListener() {
    return parserErrorRecoveryListener;
  }

  public ParserListener<? super T,? super N,? super P> getParserListener() {
    return listener;
  }
  
  public BranchingParserListener<? super T> getBranchingParserListener() {
    return branchingListener;
  }

  public LookaheadMap<? extends T,? super V> getLookaheadMap() {
    return lookaheadMap;
  }

  public ParserErrorRecoveryPolicy<T, N, P, V> getParserErrorRecoveryPolicy() {
    return policy;
  }

  
  /** Returns the starting non termianl of the grammar
   *  that will be used to start the parsing.
   *  
   * @return the starting non terminal.
   * 
   * @see #setStart(Object)
   */
  public N getStart() {
    return start;
  }

  /** Returns the parser table.
   * @return the parser table.
   */
  public ParserTable<T, N, P, V> getParserTable() {
    return table;
  }

  /** Returns the version of the grammar that will be
   *  used during parsing.
   * @return the version of the grammar.
   * 
   * @see #setVersion(Object)
   */
  public V getVersion() {
    return version;
  }  
  
  private final ParserTable<T,N,P,V> table;
  private ParserListener<? super T, ? super N, ? super P> listener;
  private BranchingParserListener<? super T> branchingListener;
  private ParserErrorRecoveryPolicy<T, N, P, V> policy;
  private N start;
  private V version;
  private LookaheadMap<? extends T,? super V> lookaheadMap;
  
  private ErrorRecoveryListener<? super T,? super N> parserErrorRecoveryListener;
  
  private final static ParserListener<Object, Object, Object> defaultParserListener = 
    new ParserListener<Object, Object, Object>() {
      public void shift(Object terminal) {
        System.err.println("shifting terminal "+terminal);
      }
      public void reduce(Object production) {
        System.err.println("reducing by production "+production);
      }
    
      public void accept(Object nonTerminal) {
        System.err.println("accept");
      }
    };
}
