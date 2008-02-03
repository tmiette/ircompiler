package fr.umlv.tatoo.runtime.tools.builder;

import fr.umlv.tatoo.runtime.buffer.LexerBuffer;
import fr.umlv.tatoo.runtime.lexer.ErrorForwarder;
import fr.umlv.tatoo.runtime.lexer.Lexer;
import fr.umlv.tatoo.runtime.lexer.LexerErrorRecoveryPolicy;
import fr.umlv.tatoo.runtime.lexer.LexerListener;
import fr.umlv.tatoo.runtime.lexer.LexerTable;
import fr.umlv.tatoo.runtime.lexer.LifecycleHandler;
import fr.umlv.tatoo.runtime.lexer.RuleActivator;
import fr.umlv.tatoo.runtime.parser.BranchingParserListener;
import fr.umlv.tatoo.runtime.parser.ErrorRecoveryListener;
import fr.umlv.tatoo.runtime.parser.ParserErrorRecoveryPolicy;
import fr.umlv.tatoo.runtime.parser.LookaheadMap;
import fr.umlv.tatoo.runtime.parser.ParserListener;
import fr.umlv.tatoo.runtime.parser.ParserTable;
import fr.umlv.tatoo.runtime.tools.GenericStack;

public abstract class ToolsBuilder<R,B extends LexerBuffer,T,N,P,V,S extends ToolsBuilder<R,B,T,N,P,V,S>> {
  protected ToolsBuilder(LexerBuilder<R,B> lexerBuilder, ParserBuilder<T,N,P,V> parserBuilder) {
    
    if (lexerBuilder==null)
      throw new IllegalArgumentException("lexerBuilder is null");
    if (parserBuilder==null)
      throw new IllegalArgumentException("parserBuilder is null");
    
    this.lexerBuilder=lexerBuilder;
    this.parserBuilder=parserBuilder;
  }
  
  protected abstract S self();
  
  public RuleActivator<R> getActivator() {
    return lexerBuilder.getActivator();
  }
  public B getBuffer() {
    return lexerBuilder.getBuffer();
  }
  public ErrorForwarder<B> getForwarder() {
    return lexerBuilder.getForwarder();
  }
  public LexerListener<? super R, ? super B> getLexerListener() {
    return lexerBuilder.getLexerListener();
  }
  public LexerTable<R> getLexerTable() {
    return lexerBuilder.getLexerTable();
  }
  public LifecycleHandler<B> getLifecycleHandler() {
    return lexerBuilder.getLifecycleHandler();
  }
  public LexerErrorRecoveryPolicy<R, B> getLexerErrorPolicy() {
    return lexerBuilder.getLexerErrorPolicy();
  }
  
  public S setLexerErrorPolicy(LexerErrorRecoveryPolicy<R, B> policy) {
    lexerBuilder.setLexerErrorPolicy(policy);
    return self();
  }
  
  public S setActivator(RuleActivator<R> activator) {
    lexerBuilder.setActivator(activator);
    return self();
  }
  public S setBuffer(B buffer) {
    lexerBuilder.setBuffer(buffer);
    return self();
  }
  public S setForwarder(ErrorForwarder<B> forwarder) {
    lexerBuilder.setForwarder(forwarder);
    return self();
  }
  public S setLexerListener(
      LexerListener<? super R, ? super B> listener) {
    lexerBuilder.setLexerListener(listener);
    return self();
  }
  public S setLifecycleHandler(
      LifecycleHandler<B> lifecycleHandler) {
    lexerBuilder.setLifecycleHandler(lifecycleHandler);
    return self();
  }
  
  public static <T extends Enum<T>,N,P,V extends Enum<V>,S extends ToolsBuilder<?,?,T,N,P,V,S>> S setEnumLookaheadMap(
      S builder) {
    ParserBuilder.setEnumLookaheadMap(builder.parserBuilder);
    return builder;
  }
  
  public ErrorRecoveryListener<? super T,? super N> getErrorRecoveryListener() {
    return parserBuilder.getErrorRecoveryListener();
  }
  public LookaheadMap<? extends T,? super V> getLookaheadMap() {
    return parserBuilder.getLookaheadMap();
  }
  public ParserErrorRecoveryPolicy<T,N,P,V> getParserErrorRecoveryPolicy() {
    return parserBuilder.getParserErrorRecoveryPolicy();
  }
  public ParserListener<? super T,? super N,? super P> getParserListener() {
    return parserBuilder.getParserListener();
  }
  public BranchingParserListener<? super T> getBranchingParserListener() {
    return parserBuilder.getBranchingParserListener();
  }
  public N getStart() {
    return parserBuilder.getStart();
  }
  public ParserTable<T,N,P,V> getParserTable() {
    return parserBuilder.getParserTable();
  }
  public V getVersion() {
    return parserBuilder.getVersion();
  }
  
  /** Returns the semantic stack that will be shared
   *  by all parser created by this builder.
   *  
   * @return the semantic stack.
   */
  public GenericStack getSemanticStack() {
    return semanticStack;
  }
  
  public S setErrorRecoveryListener(
      ErrorRecoveryListener<? super T,? super N> errorRecoveryListener) {
    parserBuilder.setParserErrorRecoveryListener(errorRecoveryListener);
    return self();
  }
  public S setHashLookaheadMap() {
    parserBuilder.setHashLookaheadMap();
    return self();
  }
  public S setLookaheadMap(
      LookaheadMap<? extends T,? super V> lookaheadMap) {
    parserBuilder.setLookaheadMap(lookaheadMap);
    return self();
  }
  public S setParserErrorRecoveryPolicy(
      ParserErrorRecoveryPolicy<T, N, P, V> policy) {
    parserBuilder.setParserErrorRecoveryPolicy(policy);
    return self();
  }
  public S setParserListener(
      ParserListener<? super T, ? super N, ? super P> listener) {
    parserBuilder.setParserListener(listener);
    return self();
  }
  public S setBranchingParserListener(
      BranchingParserListener<? super T> branchingListener) {
    parserBuilder.setBranchingParserListener(branchingListener);
    return self();
  }
  public S setStart(N start) {
    parserBuilder.setStart(start);
    return self();
  }
  public S setVersion(V version) {
    parserBuilder.setVersion(version);
    return self();
  }
  
  /** Set the stack that will be used by the parser processor.
   * @param semanticStack the semantic stack or null.
   * @return the current builder.
   */
  public S setSemanticStack(GenericStack semanticStack) {
    this.semanticStack=semanticStack;
    return self();
  }
  
  /** Export the underlying lexer builder to subclass.
   * @return a lexer builder.
   */
  protected final LexerBuilder<R, B> getLexerBuilder() {
    return lexerBuilder;
  }
  
  /** Export the underlying parser builder to subclass.
   * @return a parser builder.
   */
  protected final ParserBuilder<T, N, P, V> getParserBuilder() {
    return parserBuilder;
  }
  
  /** Creates a parser and a lexer linked to the parser.
   *  The created lexer and parser can be retrieved by using
   *  respectively {@link LexerAndParser#getLexer()} or
   *  {@link LexerAndParser#getParser()}.
   *  
   *  The lexer a the parser a created using the values of the
   *  current builder.
   *  
   * @return a new lexer and parser.
   */
  public abstract LexerAndParser<B,T,N,P,V> createLexerAndParser();
  
  /** Creates a parser and a lexer linked to the parser but
   *  doesn't provide a reference on the created parser.
   *  
   *  The lexer a the parser a created using the values of the
   *  current builder.
   *  
   * @return a new lexer linked to a parser.
   */
  public Lexer<B> createLexer() {
    return createLexerAndParser().getLexer();
  }
  
  private GenericStack semanticStack;
  private final LexerBuilder<R,B> lexerBuilder;
  private final ParserBuilder<T,N,P,V> parserBuilder;
}
