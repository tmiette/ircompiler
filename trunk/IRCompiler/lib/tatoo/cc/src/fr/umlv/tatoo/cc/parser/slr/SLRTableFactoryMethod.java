/*
 * Created on 23 févr. 2006
 */
package fr.umlv.tatoo.cc.parser.slr;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import fr.umlv.tatoo.cc.parser.grammar.Grammar;
import fr.umlv.tatoo.cc.parser.grammar.GrammarSets;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.ProductionDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.table.GrammarAugmenter;
import fr.umlv.tatoo.cc.parser.table.NodeDecl;
import fr.umlv.tatoo.cc.parser.table.NodeFactory;
import fr.umlv.tatoo.cc.parser.table.TableFactoryMethod;

public class SLRTableFactoryMethod implements TableFactoryMethod<LR0Item> {

  public LR0ClosureComputer getClosureComputer(Grammar grammar, GrammarSets grammarSets, TerminalDecl eof) {
    return new LR0ClosureComputer(grammar);
  }

  public Grammar buildGrammar(Collection<? extends ProductionDecl> productions,
      Set<? extends NonTerminalDecl> starts, TerminalDecl eof) {
    return GrammarAugmenter.buildGrammar(productions, starts, eof, new SLRGrammarAugmenter());
  }

  public Set<TerminalDecl> getLookaheads(Grammar g, GrammarSets sets, LR0Item item,NodeDecl<LR0Item> node) {
    // no reduce by the start
    if (g.getStarts().contains(item.getLeft()))
      return Collections.emptySet();
    return sets.follow(item.getLeft());
  }

  public LR0Item createStartItem(ProductionDecl production, TerminalDecl eof) {
    return new LR0Item(production);
  }

  public void initializeComputation(NodeFactory<LR0Item> factory,
      Grammar grammar, GrammarSets grammarSets,TerminalDecl eof) {
    // nothing for SLR
  }

}
