/*
 * Created on 13 déc. 2005
 */
package fr.umlv.tatoo.cc.parser.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import fr.umlv.tatoo.cc.parser.grammar.Grammar;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.ProductionDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;

public abstract class GrammarAugmenter {
  public abstract ProductionDecl 
  getAugmentingProduction(NonTerminalDecl start, TerminalDecl eof);
  
  public static Grammar buildGrammar(Collection<? extends ProductionDecl> productions,
      Set<? extends NonTerminalDecl> starts,
      TerminalDecl eof,
      GrammarAugmenter factory) {
    
    int size=productions.size();
    ArrayList<ProductionDecl> productionList =
      new ArrayList<ProductionDecl>(size+starts.size());
    productionList.addAll(productions);
    
    HashSet<NonTerminalDecl> roots = new HashSet<NonTerminalDecl>(starts.size());
    for(NonTerminalDecl start : starts) {
      ProductionDecl production = factory.getAugmentingProduction(start, eof);
      productionList.add(production);
      roots.add(production.getLeft());
    }
    
    return new Grammar(productionList, roots);
  }
  
  private int count;
  
  public NonTerminalDecl getNewRoot() {
    return new NonTerminalDecl("+root+"+(count++));
  }
}
