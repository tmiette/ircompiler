/*
 * Created on 20 févr. 2006
 */
package fr.umlv.tatoo.cc.parser.table;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import fr.umlv.tatoo.cc.parser.grammar.Grammar;
import fr.umlv.tatoo.cc.parser.grammar.GrammarSets;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.ProductionDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.VariableDecl;
import fr.umlv.tatoo.cc.parser.grammar.VersionDecl;

public class NodeFactory<I extends NodeItem<I>> {
  
  private final NodeClosureComputer<I> closureComputer;
  private final Map<VersionDecl,? extends Set<? extends VersionDecl>> versionMap;
  
  private final LinkedHashMap<Set<I>,NodeDecl<I>> nodes =
    new LinkedHashMap<Set<I>,NodeDecl<I>>();
  
  private final HashSet<NodeDecl<I>> startNodes =
    new HashSet<NodeDecl<I>>();
  
  public void register(Set<I> kernelItems, NodeDecl<I> node) {
    nodes.put(kernelItems, node);
  }
  
  public Collection<? extends NodeDecl<I>> getNodes() {
    return nodes.values();
  }
  
  public NodeDecl<I> buildNode(
      Set<I> kI,
      VariableDecl associated) {
    NodeDecl<I> node = nodes.get(kI);
    if (node == null) {
      node = new NodeDecl<I>(kI, compatibleVersion(kI), nodes.size(),
          this, closureComputer,associated);
    }
    return node;
  }
  
  public NodeFactory(Grammar grammar,GrammarSets grammarSets, TerminalDecl eof,
      Map<VersionDecl,? extends Set<? extends VersionDecl>> versionMap,
     TableFactoryMethod<I> method) {
    this.versionMap = versionMap;
    this.closureComputer = method.getClosureComputer(grammar,grammarSets,eof);
    for(NonTerminalDecl start : grammar.getStarts()) {
      ProductionDecl production = grammar.getProductions().get(start).get(0);
      I item = method.createStartItem(production,eof);
      NodeDecl<I> newNode = new NodeDecl<I>(item,
          compatibleVersion(grammar,(NonTerminalDecl)production.getRight().get(0)),
          nodes.size(),this,closureComputer);
      startStateMap.put((NonTerminalDecl)production.getRight().get(0),newNode);
      startNodes.add(newNode);
    }  
  }
  
  private HashSet<VersionDecl> compatibleVersion(Set<I> kI) {
    HashSet<VersionDecl> compatibleVersion = new HashSet<VersionDecl>();
    for(I i:kI)
      compatibleVersion.addAll(versionMap.get(i.getProduction().getVersion()));
    return compatibleVersion;
  }
  
  private HashSet<VersionDecl> compatibleVersion(Grammar grammar,NonTerminalDecl nt) {
    HashSet<VersionDecl> compatibleVersion = new HashSet<VersionDecl>();
    for(ProductionDecl production : grammar.getProductions().get(nt)) {
      compatibleVersion.addAll(versionMap.get(production.getVersion()));
    }
    return compatibleVersion;
  }
  
  public Map<NonTerminalDecl, NodeDecl<I>> getStartStateMap() {
    return startStateMap;
  }
  
  public HashSet<NodeDecl<I>> getStartNodes() {
    return startNodes;
  }
  
  private final LinkedHashMap<NonTerminalDecl,NodeDecl<I>> startStateMap =
    new LinkedHashMap<NonTerminalDecl, NodeDecl<I>>();
  
  
}
