/*
 * Created on 23 févr. 2006
 */
package fr.umlv.tatoo.cc.parser.lr;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import fr.umlv.tatoo.cc.parser.grammar.Grammar;
import fr.umlv.tatoo.cc.parser.grammar.GrammarSets;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.ProductionDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.table.GrammarAugmenter;
import fr.umlv.tatoo.cc.parser.table.NodeClosureComputer;
import fr.umlv.tatoo.cc.parser.table.NodeDecl;
import fr.umlv.tatoo.cc.parser.table.NodeFactory;
import fr.umlv.tatoo.cc.parser.table.TableFactoryMethod;

public class LRTableFactoryMethod implements TableFactoryMethod<LR1Item> {

  public NodeClosureComputer<LR1Item> getClosureComputer(Grammar grammar, GrammarSets grammarSets, TerminalDecl eof) {
    return new LR1ClosureComputer(grammar,grammarSets,eof);
  }

  public Grammar buildGrammar(Collection<? extends ProductionDecl> productions, Set<? extends NonTerminalDecl> starts, TerminalDecl eof) {
    return GrammarAugmenter.buildGrammar(productions, starts, eof, new LRGrammarAugmenter());
  }

  public Set<TerminalDecl> getLookaheads(Grammar g, GrammarSets sets, LR1Item item,NodeDecl<LR1Item> node) {
    return Collections.singleton(item.getLookahead());
  }

  public LR1Item createStartItem(ProductionDecl production, TerminalDecl eof) {
    return new LR1Item(production,eof);
  }

  public void initializeComputation(NodeFactory<LR1Item> factory,
      Grammar grammar, GrammarSets grammarSets,TerminalDecl eof) {
    // nothing for LR
  }

}
