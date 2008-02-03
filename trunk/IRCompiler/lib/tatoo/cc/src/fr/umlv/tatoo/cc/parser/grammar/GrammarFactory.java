package fr.umlv.tatoo.cc.parser.grammar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.umlv.tatoo.cc.common.generator.IdMap;
import fr.umlv.tatoo.cc.common.log.Info;
import fr.umlv.tatoo.cc.common.util.MultiMap;
import fr.umlv.tatoo.cc.parser.grammar.Priority.Associativity;

public class GrammarFactory implements GrammarRepository {
  
  //TODO Remi praise
  public Set<TerminalDecl> checkUnusedTerminal() {
    HashSet<TerminalDecl> set = new HashSet<TerminalDecl>(terminals);
      for(ProductionDecl prod:productions)
        for(VariableDecl decl:prod.getRight())
          if (decl.isTerminal())
            set.remove(decl);
    if (eof!=null)
      set.remove(eof);
    if (set.contains(getError()))
      error=null;
    return set;
  }
  
  public GrammarFactory() {
    this(new IdMap<VariableDecl>());
  }
  
  public GrammarFactory(IdMap<VariableDecl> variableMap) {
    this.variableMap=variableMap;
    
    // for null, all versions match
    dependingVersionMap.put(null,dependingVersionMap.keySet());
  }
  
  public IdMap<VariableDecl> getVariableMap() {
    return variableMap;
  }
  
  public ProductionDecl createProduction(String id, NonTerminalDecl left,
      List<? extends VariableDecl> right, Priority priority, VersionDecl version) {
    if (priority==null)
      priority=Priority.getNoPriority();
    
    ProductionDecl production=new ProductionDecl(id,left,right,priority,version);
    productions.add(production);
    
    ArrayList<ProductionDecl> prods=productionsByNonTerminal.get(left);
    if (prods==null) {
      prods=new ArrayList<ProductionDecl>();
      productionsByNonTerminal.put(left,prods);
    }
    prods.add(production);
    
    return production;
  }
  
  /** Creates a new terminal.
   * @param id terminal id.
   * @param priority may be null, in that case default priority is used.
   * @param branching true if the terminal is a branching terminal
   * @return a terminal declaration
   * 
   * @see Priority#getNoPriority()
   * @see #getAllTerminals()
   * @see #createEof(Priority)
   */
  public TerminalDecl createTerminal(String id, Priority priority,boolean branching) {
    if (priority==null)
      priority=Priority.getNoPriority();
      
    TerminalDecl terminal=new TerminalDecl(id,priority,branching);
    terminals.add(terminal);
    variableMap.put(terminal);
    return terminal;
  }
  public NonTerminalDecl createNonTerminal(String id) {
    NonTerminalDecl nonTerminal=new NonTerminalDecl(id);
    nonTerminals.add(nonTerminal);
    variableMap.put(nonTerminal);
    return nonTerminal;
  }
  
  public void addStartNonTerminal(NonTerminalDecl nonTerminal) {
    startNonTerminalSet.add(nonTerminal);
  }
  
  public VersionDecl createVersion(String id,VersionDecl parent) {
    VersionDecl version=new VersionDecl(id,parent);
    versionMap.put(id,version);
    
    HashSet<VersionDecl> dependingSet=new HashSet<VersionDecl>();
    dependingSet.add(version);
    if (parent!=null) {
      dependingSet.addAll(dependingVersionMap.get(parent));
    }
    dependingVersionMap.put(version,dependingSet);
    return version;
  }
  
  public VersionDecl getVersion(String id) {
    return versionMap.get(id);  
  }
  
  public Priority createPriority(String id,double priorityValue,Associativity associativity) {
    if (priorityMap.containsKey(id))
      throw new IllegalStateException("priority with id "+id+" already exists");
    Priority priority=new Priority(id,priorityValue,associativity);
    priorityMap.put(id,priority);
    return priority;
  }
  
  public Priority getPriority(String id) {
    return priorityMap.get(id);
  }
  
  public Map<VersionDecl, ? extends Set<? extends VersionDecl>> getDependingVersionMap() {
    MultiMap<VersionDecl,VersionDecl> reverse = new MultiMap<VersionDecl, VersionDecl>();
    for(Map.Entry<VersionDecl,Set<VersionDecl>> entry : dependingVersionMap.entrySet())
      for(VersionDecl child : entry.getValue())
        if (child != null && entry.getKey()!=null)
          reverse.add(child,entry.getKey());
    for(VersionDecl v : versionMap.values())
      reverse.add(null, v);
    return Collections.unmodifiableMap(reverse);
  }
  
  public Priority findProductionPriority(String productionId,List<? extends VariableDecl> rights) {
    Priority priority = Priority.getNoPriority();
    TerminalDecl terminal = null;
    for(int i=rights.size()-1;i>=0;i--) {
      VariableDecl v = rights.get(i);
      if (v.isTerminal()) {
        TerminalDecl tmpTerminal = (TerminalDecl)v;
        Priority tmpPriority = tmpTerminal.getPriority();
        if (tmpPriority != Priority.getNoPriority()) {
          if (terminal == null) {
            terminal = tmpTerminal;
            priority = terminal.getPriority();
          } else {
            if (tmpPriority != priority) {
              Info.warning(
                "Priority %s of terminal %s added to production %s conflicts "+
                "with %s of terminal %s of the same production",
                priority,terminal.getId(),productionId,tmpPriority,tmpTerminal.getId()).report();
            }
          }
        }
      }  
    }
    if (terminal != null) {
      Info.warning("Priority %s of terminal %s added for production %s",
        priority,terminal.getId(),productionId).report();
    }
    return priority;
  }
  
  public TerminalDecl getEof() {
    if (eof==null)
      eof=createEof(new Priority("min",Double.MIN_VALUE,Associativity.NONE));
    return eof;
  } 
  
  public boolean isEofDefined() {
    return eof!=null;
  }
  
  public TerminalDecl createEof(Priority priority) {
    if (eof!=null)
      throw new IllegalStateException("eof already exists");
    return eof=createTerminal("__eof__",priority,false);
  }
  
  public TerminalDecl getError() {
    return error;
  }
  
  public TerminalDecl createError(String id) {
    return error=createTerminal(id,Priority.getNoPriority(),false);
  }
  
  public Collection<? extends ProductionDecl> getAllProductions() {
    return Collections.unmodifiableList(productions);
  }
  public Collection<? extends TerminalDecl> getAllTerminals() {
    return Collections.unmodifiableList(terminals);
  }
  public Collection<? extends NonTerminalDecl> getAllNonTerminals() {
    return Collections.unmodifiableList(nonTerminals);
  }
  
  public Set<? extends NonTerminalDecl> getStartNonTerminalSet() {
    return Collections.unmodifiableSet(startNonTerminalSet);
  }
  
  public Collection<? extends Priority> getAllPriorities() {
    return Collections.unmodifiableCollection(priorityMap.values());
  }
  
  public Collection<? extends VersionDecl> getAllVersions() {
    return Collections.unmodifiableCollection(versionMap.values());
  }
  
  public Map<NonTerminalDecl,? extends Collection<? extends ProductionDecl>> getProductionsByNonTerminal() {
    return Collections.unmodifiableMap(productionsByNonTerminal);
  }
  
  private final IdMap<VariableDecl> variableMap;
  
  private TerminalDecl eof;
  private TerminalDecl error;
  private final ArrayList<ProductionDecl> productions=
    new ArrayList<ProductionDecl>();
  private final ArrayList<TerminalDecl> terminals=
    new ArrayList<TerminalDecl>();
  private final ArrayList<NonTerminalDecl> nonTerminals=
    new ArrayList<NonTerminalDecl>();
  private final LinkedHashMap<NonTerminalDecl,ArrayList<ProductionDecl>> productionsByNonTerminal=
    new LinkedHashMap<NonTerminalDecl,ArrayList<ProductionDecl>>();
  
  private final LinkedHashSet<NonTerminalDecl> startNonTerminalSet=
    new LinkedHashSet<NonTerminalDecl>();
  private final LinkedHashMap<String,Priority> priorityMap=
    new LinkedHashMap<String,Priority>();
  private final LinkedHashMap<String,VersionDecl> versionMap=
    new LinkedHashMap<String,VersionDecl>();
  private final HashMap<VersionDecl,Set<VersionDecl>> dependingVersionMap=
    new HashMap<VersionDecl,Set<VersionDecl>>();
}
