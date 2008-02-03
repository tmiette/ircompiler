/*
 * Created on 13 déc. 2005
 */
package fr.umlv.tatoo.cc.parser.lr;

import java.util.Collections;

import fr.umlv.tatoo.cc.parser.grammar.FakeProduction;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.ProductionDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.table.GrammarAugmenter;

public class LRGrammarAugmenter extends GrammarAugmenter {

  
  @Override
  public ProductionDecl getAugmentingProduction(NonTerminalDecl start,
      TerminalDecl eof) {
    final NonTerminalDecl root = getNewRoot();
    
    return new FakeProduction(root.getId(),root,Collections.singletonList(start));
  }

}
