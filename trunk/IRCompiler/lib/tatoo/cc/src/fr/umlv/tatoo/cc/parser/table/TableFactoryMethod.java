/*
 * Created on 23 févr. 2006
 */
package fr.umlv.tatoo.cc.parser.table;

import java.util.Collection;
import java.util.Set;

import fr.umlv.tatoo.cc.parser.grammar.Grammar;
import fr.umlv.tatoo.cc.parser.grammar.GrammarSets;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.ProductionDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;

public interface TableFactoryMethod<I extends NodeItem<I>> {
  /**
   * Returns the closure computer for this method
   * @param grammar the grammar
   * @param grammarSets the grammar sets
   * @param eof the end of file terminal
   * @return the closure computer for this method
   */
  NodeClosureComputer<I> getClosureComputer(Grammar grammar, GrammarSets grammarSets, TerminalDecl eof);
  
  /**
   * builds the augmented grammar
   * @param productions list of productions
   * @param starts list of starts
   * @param eof terminal eof
   * @return the grammar
   */
  Grammar buildGrammar(
      Collection<? extends ProductionDecl> productions,
      Set<? extends NonTerminalDecl> starts,
      TerminalDecl eof);
  
  /**
   * returns the set of lookahead corresponding to a reduce action
   * by item
   * @param g the grammar
   * @param grammarSets nullable, first and follow sets corresponding to g 
   * @param item the item to reduce by
   * @param node the node
   * @return the set of lookaheads
   */
  Set<TerminalDecl> getLookaheads(Grammar g,
      GrammarSets grammarSets, I item,NodeDecl<I> node);
    
  /**
   * Create the kernel item for the start states
   * @param production the starting production
   * @param eof the end of file terminal
   * @return the new item
   */
  I createStartItem(ProductionDecl production, TerminalDecl eof);
  
  /**
   * Initialize some computation (for LALR)
   * @param factory the factory used to build nodes
   * @param grammar the grammar
   * @param grammarSets grammarSets first and follow sets corresponding the grammar
   * @param eof the end of file terminal
   */
  void initializeComputation(NodeFactory<I> factory,
      Grammar grammar, GrammarSets grammarSets,TerminalDecl eof);
}
