/*
 * Created on 13 déc. 2005
 */
package fr.umlv.tatoo.cc.parser.slr;

import java.util.Arrays;
import fr.umlv.tatoo.cc.parser.grammar.FakeProduction;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.ProductionDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.VariableDecl;
import fr.umlv.tatoo.cc.parser.table.GrammarAugmenter;

public class SLRGrammarAugmenter extends GrammarAugmenter {
  
  @Override
  public ProductionDecl getAugmentingProduction(NonTerminalDecl start,TerminalDecl eof) {
    final NonTerminalDecl root = getNewRoot();
    VariableDecl[] right = new VariableDecl[] {start,eof};

    return new FakeProduction(root.getId(),root,Arrays.asList(right));
  }
}
