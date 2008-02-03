package fr.umlv.tatoo.cc.parser.grammar;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.umlv.tatoo.cc.common.util.MultiMap;
import fr.umlv.tatoo.cc.parser.grammar.Grammar.MarkedProduction;
import fr.umlv.tatoo.cc.parser.solver.NodeContent;
import fr.umlv.tatoo.cc.parser.solver.NodeFactory;
import fr.umlv.tatoo.cc.parser.solver.Solver;

public class GrammarSets {

  final Grammar grammar;
  
  private final Solver<NonTerminalDecl,HashSet<TerminalDecl>> first;
  private final Solver<NonTerminalDecl,HashSet<TerminalDecl>> follow;

  private final Solver<NonTerminalDecl,Boolean> epsilon;


  public GrammarSets(Grammar grammar) {
    this.grammar=grammar;
    epsilon = new Solver<NonTerminalDecl,Boolean>(new EpsilonNodeFactory());
    first = new Solver<NonTerminalDecl,HashSet<TerminalDecl>>(new FirstNodeFactory());
    follow = new Solver<NonTerminalDecl,HashSet<TerminalDecl>>(new FollowNodeFactory());
  }

  private final class EpsilonNodeFactory implements
    NodeFactory<NonTerminalDecl,Boolean> {
    
    EpsilonNodeFactory() { /* nothing */ }
    
    public NodeContent<NonTerminalDecl,Boolean> getNode(NonTerminalDecl key) {
      return new EpsilonNode(key);
    }
  }

  private final class EpsilonNode implements NodeContent<NonTerminalDecl,Boolean> {

    private Boolean epsilon; // null means not yet computed
    
    /* maps a non terminal to the set of non terminals which it belongs
     * such that if all are epsilon, the node's answer is true. It is
     * inited with all right members of production whose right
     * part is only made of nonterminals, and left part is the non
     * terminal this node is computing epsilon for*/
    private final MultiMap<NonTerminalDecl,HashSet<NonTerminalDecl>> currentDepends
       = new MultiMap<NonTerminalDecl,HashSet<NonTerminalDecl>>();
    private final Set<NonTerminalDecl> depends;
    

    public EpsilonNode(NonTerminalDecl nonTerminal) {
      List<ProductionDecl> list = grammar.getProductions().get(nonTerminal);

      production : for (ProductionDecl production : list) {
         List<? extends VariableDecl> var = production.getRight();

         /* if right member is epsilon, the result is true */
         if (var.size() == 0) {
           epsilon = true;
           depends = Collections.emptySet();
           return;
         }
        /* if there is a terminal in this production,
         * it can't reduce to epsilon; if it is recursive, it can't help
         */
        for (VariableDecl v: var) {
          if (v.isTerminal() || v.equals(nonTerminal)) {
            continue production;
          }
        }

        /* if the production is only made of NonTerminal, a
         * dependency is added to the first one.
         */
        HashSet<NonTerminalDecl> rights = new HashSet<NonTerminalDecl>();
        
        for(VariableDecl v:var) {
          NonTerminalDecl n = (NonTerminalDecl) v;
          rights.add(n);
          currentDepends.add(n, rights);
        }
      }

      if (currentDepends.keySet().isEmpty()) {
        epsilon = Boolean.FALSE;
        depends = Collections.emptySet();
      }
      else
        depends = new HashSet<NonTerminalDecl>(currentDepends.keySet());
    }

    public Boolean getCurrentResult() {
      return epsilon;
    }
    
    public Boolean getResult() {
      if (epsilon==null) {
        //System.out.println("null");
        epsilon=false;
      }
      return epsilon;
    }

    public boolean hasChanged(NonTerminalDecl key, NodeContent<NonTerminalDecl,Boolean> node) {
      if (epsilon!=null)
        return false;

      Boolean objNewVal = node.getCurrentResult();
      //System.out.println(key+" "+objNewVal);
      if (objNewVal == null)
        return false;
        
      if (!currentDepends.containsKey(key))
        return false;
      
      boolean newVal = objNewVal;

      if (newVal) {
        for (HashSet<NonTerminalDecl> set :  currentDepends.remove(key)) {
          set.remove(key);
          if (set.isEmpty()) {
            epsilon=true;
            currentDepends.clear();
            //System.out.println("true "+depends);
            return true;
          }
        }
      } else {
        //System.err.println(key+" "+currentDepends.get(key));
        for (HashSet<NonTerminalDecl> set :  currentDepends.remove(key)) {
          for(NonTerminalDecl n : set) {
            if (n==key)
              continue;
            Set<HashSet<NonTerminalDecl>> set2 = currentDepends.get(n);
            set2.remove(set);
            if (set2.isEmpty())
              currentDepends.remove(n);
          }
        }
      }
      if (currentDepends.keySet().isEmpty()) {
        epsilon = Boolean.FALSE;
        //System.out.println("false "+depends);
        return true;
      }
      return false;
    }

    public Set<NonTerminalDecl> dependencies() {
      return depends;
    }
  }

  private final class FirstNodeFactory
    implements NodeFactory<NonTerminalDecl,HashSet<TerminalDecl>> {
    
    FirstNodeFactory() { /* nothing */ }
    
    public NodeContent<NonTerminalDecl,HashSet<TerminalDecl>> getNode(NonTerminalDecl key) {
      return new FirstNode(key);
    }
  }

  private final class FirstNode implements NodeContent<NonTerminalDecl,HashSet<TerminalDecl>> {

    private final HashSet<NonTerminalDecl> depends;
    private final HashSet<TerminalDecl> first;

    public FirstNode(NonTerminalDecl nonTerminal) {
      depends = new HashSet<NonTerminalDecl>();
      first = new HashSet<TerminalDecl>();
      List<ProductionDecl> list = grammar.getProductions().get(nonTerminal);
      for (ProductionDecl production : list) {
         List<? extends VariableDecl> var = production.getRight();

        for (VariableDecl v : var) {
          if (v.isTerminal()) {
            first.add((TerminalDecl)v);
            break;
          }
          if (!v.equals(nonTerminal))
            depends.add((NonTerminalDecl)v);
          //System.out.println(v);
          if (!derivesToEpsilon((NonTerminalDecl) v))
            break;
        }
      }
    }

    public boolean hasChanged(NonTerminalDecl key,
        NodeContent<NonTerminalDecl,HashSet<TerminalDecl>> node) {
      /*System.out.println(
        "updating "
          + nonTerminal
          + " from "
          + key
          + " : "
          + ((FirstNode)node).getFirst());*/
      return first.addAll(node.getCurrentResult());
    }

    public HashSet<NonTerminalDecl> dependencies() {
      return depends;
    }

    public HashSet<TerminalDecl> getCurrentResult() {
      return first;
    }
    
    public HashSet<TerminalDecl> getResult() {
      return first;
    }
  }

  private final class FollowNodeFactory
     implements NodeFactory<NonTerminalDecl,HashSet<TerminalDecl>> {
     
     FollowNodeFactory() { /* nothing */ }
     
    public NodeContent<NonTerminalDecl,HashSet<TerminalDecl>> getNode(NonTerminalDecl key) {
      return new FollowNode(key);
    }
  }

  private final class FollowNode
    implements NodeContent<NonTerminalDecl,HashSet<TerminalDecl>> {

    private final HashSet<NonTerminalDecl> depends;
    private final HashSet<TerminalDecl> follow;

    public FollowNode(NonTerminalDecl nonTerminal) {
      depends = new HashSet<NonTerminalDecl>();
      follow = new HashSet<TerminalDecl>();
      List<MarkedProduction> list = grammar.getRightProductionMap().get(nonTerminal);
      //System.out.println(nonTerminal+" appears in "+list);
      production : for (MarkedProduction markedProduction : list) {
        ProductionDecl production = markedProduction.getProduction();
        List<? extends VariableDecl> right = production.getRight();
        int max = right.size();
        for (int i=markedProduction.getPosition()+1; i<max; i++) {
          VariableDecl var = right.get(i);
          if (var.isTerminal()) {
            follow.add((TerminalDecl)var);
            continue production;
          }
          NonTerminalDecl tmpNT = (NonTerminalDecl) var;
          follow.addAll(first(tmpNT));
          if (!derivesToEpsilon(tmpNT))
            continue production;
        }
        /* if control arrive to end of right part of production, need
         * to add follow of left part.
         */
        if (!production.getLeft().equals(nonTerminal)
          && !grammar.getStarts().contains(production.getLeft())) {
          depends.add(production.getLeft());
        }
      }
    }

    public boolean hasChanged(NonTerminalDecl key, 
        NodeContent<NonTerminalDecl,HashSet<TerminalDecl>> node) {
       return follow.addAll(node.getCurrentResult());
    }

    public Set<NonTerminalDecl> dependencies() {
      return depends;
    }

    public HashSet<TerminalDecl> getCurrentResult() {
      return follow;
    }
    
    public HashSet<TerminalDecl> getResult() {
      return follow;
    }
  }

  public boolean derivesToEpsilon(NonTerminalDecl t) {
    //System.out.println(t);
    return epsilon.solve(t);
  }

  public Set<TerminalDecl> first(NonTerminalDecl t) {
    return first.solve(t);
  }

  /* vars must not be empty */
  public Set<TerminalDecl> first(VariableDecl[] vars) {
    HashSet<TerminalDecl> rep = new HashSet<TerminalDecl>();
    for (int i = 0; i < vars.length; i++) {
      if (vars[i].isTerminal()) {
        rep.add((TerminalDecl)vars[i]);
        break;
      }
      NonTerminalDecl n = (NonTerminalDecl) vars[i];
      rep.addAll(first(n));

      if (!derivesToEpsilon(n))
        break;
    }
    return rep;
  }

  public Set<TerminalDecl> follow(NonTerminalDecl t) {
    if (grammar.getStarts().contains(t))
      return null;
    return follow.solve(t);
  }

}
