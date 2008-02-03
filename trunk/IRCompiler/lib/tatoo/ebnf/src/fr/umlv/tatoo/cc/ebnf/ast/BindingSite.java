package fr.umlv.tatoo.cc.ebnf.ast;

public interface BindingSite extends NodeAST {
  /** 
   * @return may be null.
   */
  public TokenAST<String> getTokenId();
}
