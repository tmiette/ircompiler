/*
 * Created on May 30, 2003
 */
package fr.umlv.tatoo.cc.parser.lr;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.umlv.tatoo.cc.parser.grammar.Grammar;
import fr.umlv.tatoo.cc.parser.grammar.GrammarSets;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.ProductionDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.VariableDecl;
import fr.umlv.tatoo.cc.parser.solver.NodeContent;
import fr.umlv.tatoo.cc.parser.solver.NodeFactory;
import fr.umlv.tatoo.cc.parser.solver.Solver;
import fr.umlv.tatoo.cc.parser.table.Closure;
import fr.umlv.tatoo.cc.parser.table.NodeClosureComputer;

/**
 * @author jcervell
 *
 */
public class LR1ClosureComputer implements NodeClosureComputer<LR1Item>{
  final Grammar grammar;
  final GrammarSets grammarSets;
  final TerminalDecl eof;
  
  private final Solver<ClosureInput,Closure<LR1Item>> closure = 
    new Solver<ClosureInput,Closure<LR1Item>>(new ClosureNodeFactory());
  
  private final class ClosureInput {
    /* this item must have a NonTerminal after the dot */
    ClosureInput(LR1Item p) {
      lookaheads = computeLookaheads(p);
      nonTerminal = (NonTerminalDecl) p.getDottedVariable();
    }
    
    private final Set<TerminalDecl> lookaheads;
    private final NonTerminalDecl nonTerminal;
    
    @Override
    public boolean equals(Object o) {
      if (!(o instanceof ClosureInput))
        return false;
      ClosureInput i = (ClosureInput)o;
      return lookaheads.equals(i.getLookaheads())
      && nonTerminal.equals(i.getNonTerminal());
    }
    
    @Override
    public int hashCode() {
      return nonTerminal.hashCode()^lookaheads.hashCode();
    }
    
    @Override
    public String toString() {
      return nonTerminal+" | "+lookaheads;
    }
    public Set<TerminalDecl> getLookaheads() {
      return lookaheads;
    }
    
    public NonTerminalDecl getNonTerminal() {
      return nonTerminal;
    }
    
  }
  
  Set<TerminalDecl> computeLookaheads(LR1Item p) {
    int dotPlace = p.getDotPlace();
    List<? extends VariableDecl> right = p.getRight();
    int size = right.size();
    VariableDecl[] tab = new VariableDecl[size - dotPlace];
    for (int i = dotPlace + 1; i < size; i++)
      tab[i - dotPlace - 1] = right.get(i);
    tab[size - dotPlace - 1] = p.getLookahead();
    
    return grammarSets.first(tab);
  }
  
  public LR1ClosureComputer(Grammar grammar,GrammarSets grammarSets, TerminalDecl eof) {
    this.grammar = grammar;
    this.grammarSets = grammarSets;
    this.eof = eof;
  }
  
  private class ClosureNodeFactory
  implements NodeFactory<ClosureInput,Closure<LR1Item>> {
    
    ClosureNodeFactory() { /* nothing */ }
    
    public NodeContent<ClosureInput,Closure<LR1Item>> getNode(ClosureInput key) {
      return new ClosureNode(key);
    }
  }
  
  private class ClosureNode implements NodeContent<ClosureInput,Closure<LR1Item>> {
    private final HashSet<ClosureInput> depends =
      new HashSet<ClosureInput>();
    private final Closure<LR1Item> closure;
    
    public ClosureNode(ClosureInput input) {
      Map<? extends NonTerminalDecl,? extends List<? extends ProductionDecl>> prodmap = 
        grammar.getProductions();
      
      List<? extends ProductionDecl> prods = prodmap.get(input.getNonTerminal());
      HashSet<LR1Item> items = new HashSet<LR1Item>();
      
      for (ProductionDecl prod : prods) {
        Set<TerminalDecl> lookaheads = input.getLookaheads();
        List<? extends VariableDecl> right = prod.getRight();
                
        for (TerminalDecl lookahead : lookaheads) {
          LR1Item item = new LR1Item(prod, lookahead);
          items.add(item);
                
          if (right.isEmpty()) {
            continue;
          }
        
          VariableDecl v = right.get(0);
        
          if (!v.isTerminal()) {
            ClosureInput tmpInput = new ClosureInput(item);
            if (!input.equals(tmpInput)) {
              depends.add(tmpInput);
            }
          }
        }
      }
      closure = new Closure<LR1Item>(items);
    }
    
    public boolean hasChanged(ClosureInput key,
        NodeContent<ClosureInput,Closure<LR1Item>> node) {
      Closure<LR1Item> tmpC = node.getCurrentResult();
      return closure.update(tmpC);
    }
    
    public Set<ClosureInput> dependencies() {
      return depends;
    }
    
    
    public Closure<LR1Item> getCurrentResult() {
      return closure;
    }
    
    public Closure<LR1Item> getResult() {
      return closure;
    }
  }
  
  private Closure<LR1Item> getClosure(ClosureInput e) {
    return closure.solve(e);
  }
  
  public Closure<LR1Item> getClosure(LR1Item p) {
    return getClosure(new ClosureInput(p));
  }
  
}
