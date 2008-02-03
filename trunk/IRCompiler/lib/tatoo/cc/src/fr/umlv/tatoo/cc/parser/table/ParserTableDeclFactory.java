/*
 * Created on 8 juil. 2003
 */
package fr.umlv.tatoo.cc.parser.table;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import fr.umlv.tatoo.cc.common.util.MultiMap;
import fr.umlv.tatoo.cc.parser.grammar.Grammar;
import fr.umlv.tatoo.cc.parser.grammar.GrammarSets;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.ProductionDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.VersionDecl;
import fr.umlv.tatoo.cc.parser.parser.AcceptActionDecl;
import fr.umlv.tatoo.cc.parser.parser.ActionDecl;
import fr.umlv.tatoo.cc.parser.parser.ActionDeclFactory;
import fr.umlv.tatoo.cc.parser.parser.ActionDeclVisitor;
import fr.umlv.tatoo.cc.parser.parser.ActionEntry;
import fr.umlv.tatoo.cc.parser.parser.BranchActionDecl;
import fr.umlv.tatoo.cc.parser.parser.BranchTableActionDecl;
import fr.umlv.tatoo.cc.parser.parser.EnterActionDecl;
import fr.umlv.tatoo.cc.parser.parser.ErrorActionDecl;
import fr.umlv.tatoo.cc.parser.parser.ExitActionDecl;
import fr.umlv.tatoo.cc.parser.parser.ReduceActionDecl;
import fr.umlv.tatoo.cc.parser.parser.RegularTableActionDecl;
import fr.umlv.tatoo.cc.parser.parser.ShiftActionDecl;
import fr.umlv.tatoo.cc.parser.parser.VersionedActionDecl;

/**
 * @author Julien
 * @version $Revision$ $Date$
 */
public class ParserTableDeclFactory<I extends NodeItem<I>> {  
  private final AbstractConflictDiagnosticReporter reporter;
  private final Grammar grammar;
  private final GrammarSets grammarSets;
  private final TerminalDecl eof;
  private final TerminalDecl error;
  private final Map<VersionDecl,? extends Set<? extends VersionDecl>> versionMap;
  private final ActionDeclFactory actionFactory;
  private final ConflictResolverPolicy conflictResolver;
  private final TableFactoryMethod<I> method;
  private final File log;


  ParserTableDeclFactory(AbstractConflictDiagnosticReporter reporter,
      Collection<? extends ProductionDecl> productions,
      Set<? extends NonTerminalDecl> starts, TerminalDecl eof,
      TerminalDecl error,
      Map<VersionDecl,? extends Set<? extends VersionDecl>> versionMap,
          ActionDeclFactory actionFactory, 
          TableFactoryMethod<I> method,
          ConflictResolverPolicy conflictResolver, File log)
       {
    this.reporter=reporter;
    this.grammar = method.buildGrammar(productions, starts, eof);
    this.grammarSets = new GrammarSets(grammar);
    this.eof=eof;
    this.versionMap=versionMap;
    this.error=error;
    this.actionFactory=actionFactory;
    this.conflictResolver=conflictResolver;
    this.log=log;
    this.method=method;
  }

  public static ParserTableDecl buildTable(AbstractConflictDiagnosticReporter reporter,
      Collection<? extends ProductionDecl> productions,
      Set<? extends NonTerminalDecl> starts, TerminalDecl eof,
      TerminalDecl error,
      Map<VersionDecl,? extends Set<? extends VersionDecl>> versionMap,
          ActionDeclFactory actionFactory, 
          TableFactoryMethod<?> method,
          ConflictResolverPolicy conflictResolver, File log) {
    
    //FIXME bug in javac 5.0 even with latest revision, it works with 6.0
    // use a raw type to compile with javac 5
    return captureBuildTable(reporter,
      productions, starts, eof, error, versionMap, actionFactory,
      (TableFactoryMethod)method, conflictResolver, log);
  }
  
  private static <I extends NodeItem<I>> ParserTableDecl captureBuildTable(AbstractConflictDiagnosticReporter reporter,
      Collection<? extends ProductionDecl> productions,
      Set<? extends NonTerminalDecl> starts, TerminalDecl eof,
      TerminalDecl error,
      Map<VersionDecl,? extends Set<? extends VersionDecl>> versionMap,
          ActionDeclFactory actionFactory, 
          TableFactoryMethod<I> method,
          ConflictResolverPolicy conflictResolver, File log) {
    return new ParserTableDeclFactory<I>(reporter,productions,starts,eof,error,versionMap,actionFactory,method,conflictResolver,log).buildTable();
  }

  private ParserTableDecl buildTable() {

    // means actions associated to a state and a terminal
    HashMap<NodeDecl<I>, MultiMap<TerminalDecl,ActionEntry<RegularTableActionDecl>>> tmpTable = 
      new HashMap<NodeDecl<I>, MultiMap<TerminalDecl,ActionEntry<RegularTableActionDecl>>>();
    // means a set of actions associated to a state caused by a branch terminal: eof or a branching one
    MultiMap<NodeDecl<I>,ActionEntry<BranchTableActionDecl>> tmpBranch =
      new MultiMap<NodeDecl<I>,ActionEntry<BranchTableActionDecl>>();

    NodeFactory<I> factory = new NodeFactory<I>(grammar,grammarSets,eof,versionMap,method);
    Collection<? extends NodeDecl<I>> nodes = factory.getNodes();

    method.initializeComputation(factory,grammar,grammarSets,eof);

    /*
     * compute gotoes : gather all goto map from nodes
     */
    HashMap<NodeDecl<I>, HashMap<NonTerminalDecl,NodeDecl<I>>> buildedGotos =
      new HashMap<NodeDecl<I>, HashMap<NonTerminalDecl,NodeDecl<I>>>();
    for (NodeDecl<I> node : nodes)
      buildedGotos.put(node,node.getGotos());

    /*
     * add action for each nodes
     */
    for (NodeDecl<I> node : nodes) {
      MultiMap<TerminalDecl, ActionEntry<RegularTableActionDecl>> actions =
        new MultiMap<TerminalDecl,ActionEntry<RegularTableActionDecl>>();
      tmpTable.put(node,actions);

      HashMap<TerminalDecl, NodeDecl<I>> shifts = node.getShifts();
      /*
       * add shifts
       */
      for (Map.Entry<TerminalDecl, NodeDecl<I>> entry : shifts.entrySet()) {
        TerminalDecl t = entry.getKey();
        NodeDecl<I> to = entry.getValue();

        if (t == eof) {
          actions.add(t,new ActionEntry<RegularTableActionDecl>(actionFactory.getAccept(),t,t));
          tmpBranch.add(node, new ActionEntry<BranchTableActionDecl>(actionFactory.getExit(),t,t));
        } 
        else {
          if (t.isBranching()) {
            tmpBranch.add(node,new ActionEntry<BranchTableActionDecl>(actionFactory.getEnter(t,node),t,t));
          }
          else
            actions.add(t,new ActionEntry<RegularTableActionDecl>(actionFactory.getShift(to),t,t));
        }
      }

      /*
       * add actions for reduce
       */
      HashSet<I> reduces = node.getReduces();
      for (I item : reduces) {

        ProductionDecl p = item.getProduction();
        // accept case
        if (grammar.getStarts().contains(p.getLeft())) {
          actions.add(eof,new ActionEntry<RegularTableActionDecl>(actionFactory.getAccept(),eof,eof));
          tmpBranch.add(node, new ActionEntry<BranchTableActionDecl>(actionFactory.getExit(),eof,eof));
          continue;
        }
        Set<TerminalDecl> reduceBy = method.getLookaheads(grammar,grammarSets,item,node);
        for(TerminalDecl next: reduceBy) {
          ReduceActionDecl action = actionFactory.getReduce(p);
          if (next.isBranching())
            tmpBranch.add(node,new ActionEntry<BranchTableActionDecl>(action,p,next));
          else {
            if (next==eof)
              tmpBranch.add(node,new ActionEntry<BranchTableActionDecl>(action,p,next));
            actions.add(next,new ActionEntry<RegularTableActionDecl>(action, p,next));
          }

        }
      }
    }

    if (log != null) {
//    TableWriter.dumpTable(log,grammar,grammarSets,factory,tmpTable,tmpBranch,buildedGotos);
      TableWriter.dumpTable(log,grammar,grammarSets,factory,tmpTable,tmpBranch,buildedGotos);      
    }

    HashMap<NodeDecl<I>, HashMap<TerminalDecl, HashMap<VersionDecl,RegularTableActionDecl>>> map =
      resolveConflictsTable(tmpTable);

    HashMap<NodeDecl<I>, HashMap<VersionDecl,BranchTableActionDecl>> branchMap = resolveConflictsBranch(tmpBranch);

    if (reporter.isOnError())
      throw new FatalConflictException("some conflicts occur");


    //convert versioned action map to versionedActions

    HashMap<NodeDecl<I>, HashMap<TerminalDecl, RegularTableActionDecl>> newMap = 
      new HashMap<NodeDecl<I>, HashMap<TerminalDecl,RegularTableActionDecl>>();

    for(Map.Entry<NodeDecl<I>, HashMap<TerminalDecl, HashMap<VersionDecl,RegularTableActionDecl>>> entry:map.entrySet()) {
      HashMap<TerminalDecl,RegularTableActionDecl> map2 = new HashMap<TerminalDecl, RegularTableActionDecl>();
      newMap.put(entry.getKey(),map2);
      for (Map.Entry<TerminalDecl, HashMap<VersionDecl,RegularTableActionDecl>> entry2: entry.getValue().entrySet()) {
        map2.put(entry2.getKey(),computeVersionedAction(RegularTableActionDecl.class,entry.getKey(), entry2.getValue()));
      }
    }

    HashMap<NodeDecl<I>,BranchTableActionDecl> newBranchMap = 
      new HashMap<NodeDecl<I>, BranchTableActionDecl>();
    for(Map.Entry<NodeDecl<I>,HashMap<VersionDecl,BranchTableActionDecl>> entry:branchMap.entrySet()) {
      newBranchMap.put(entry.getKey(), computeVersionedAction(BranchTableActionDecl.class,entry.getKey(),entry.getValue()));
    }


    ParserTableDecl table = createParserTableDecl(newMap,newBranchMap,buildedGotos,nodes,
        factory.getStartStateMap());

    return table;
  }

  private HashMap<NodeDecl<I>, HashMap<VersionDecl,BranchTableActionDecl>> 
    resolveConflictsBranch(MultiMap<NodeDecl<I>, ActionEntry<BranchTableActionDecl>> map) {
    
    HashSet<TerminalDecl> branchingTerminals = new HashSet<TerminalDecl>();
    for(TerminalDecl terminal:grammar.getAlphabet())
      if(terminal.isBranching())
        branchingTerminals.add(terminal);
    
    
    HashMap<NodeDecl<I>, HashMap<VersionDecl,BranchTableActionDecl>> branchMap =
      new HashMap<NodeDecl<I>, HashMap<VersionDecl,BranchTableActionDecl>>();
    
    for(Map.Entry<NodeDecl<I>,Set<ActionEntry<BranchTableActionDecl>>> entry:map.entrySet()) {
      HashMap<VersionDecl,BranchTableActionDecl> actionMap = new HashMap<VersionDecl, BranchTableActionDecl>();
      fillActionMap(BranchTableActionDecl.class, actionMap, entry.getValue(), entry.getKey(), null,actionFactory.getDefaultErrorAction());
      branchMap.put(entry.getKey(), actionMap);
    }
    
    return branchMap;
  }

  private <A extends ActionDecl> A computeVersionedAction(Class<A> type,
      NodeDecl<I> state, HashMap<VersionDecl,? extends A> actionMap) {    
    Iterator<VersionDecl> it = state.getCompatibleVersion().iterator();
    if (!it.hasNext())
      throw new AssertionError("versions must at least contains a default version");
    A action=actionMap.get(it.next());
    for(;it.hasNext();) {
      A otherAction = actionMap.get(it.next());
      if (action!=otherAction) {
        // more than one action, create a versioned action
        return type.cast(actionFactory.getVersionedAction(actionMap));
      }
    }
    return action;
  }

  private ParserTableDecl createParserTableDecl(
      HashMap<NodeDecl<I>, HashMap<TerminalDecl, RegularTableActionDecl>> table,
      HashMap<NodeDecl<I>, BranchTableActionDecl> branch,
      HashMap<NodeDecl<I>, HashMap<NonTerminalDecl,NodeDecl<I>>> buildedGotos,
      Collection<? extends NodeDecl<I>> nodes,
          Map<NonTerminalDecl,NodeDecl<I>> startStateTable) {

    int stateNb = nodes.size();

    // transform table
    HashMap<TerminalDecl,RegularTableActionDecl[]> map=new HashMap<TerminalDecl,RegularTableActionDecl[]>();
    for(Map.Entry<NodeDecl<I>,HashMap<TerminalDecl,RegularTableActionDecl>> entry:table.entrySet()) {
      NodeDecl<I> state = entry.getKey();
      for(Map.Entry<TerminalDecl,RegularTableActionDecl> actionEntry:entry.getValue().entrySet()) {

        TerminalDecl terminal = actionEntry.getKey();
        RegularTableActionDecl[] actions = map.get(terminal);
        if (actions == null) {
          actions=new RegularTableActionDecl[stateNb];
          for(int i=0;i<stateNb;i++)
            actions[i]=actionFactory.getDefaultBranchAction();
          map.put(terminal,actions);
        }    
        if (actionEntry.getValue()!=null)
          actions[state.getStateNo()]=actionEntry.getValue();
      }
    }

    // transform branchTable
    BranchTableActionDecl[] branchMap = new BranchTableActionDecl[stateNb];

    BranchTableActionDecl errorAction=actionFactory.getDefaultErrorAction();
    for(int i=0;i<stateNb;i++)
      branchMap[i]=errorAction;

    for(Map.Entry<NodeDecl<I>, BranchTableActionDecl> entry:branch.entrySet()) {
      NodeDecl<I> state = entry.getKey();
      branchMap[state.getStateNo()]=entry.getValue();
    }

    // transform gotos
    HashMap<NonTerminalDecl,int[]> gotoMap=new HashMap<NonTerminalDecl,int[]>();
    for(Map.Entry<NodeDecl<I>,HashMap<NonTerminalDecl,NodeDecl<I>>> entry:buildedGotos.entrySet()) {

      for(Map.Entry<NonTerminalDecl,NodeDecl<I>> gotoEntry:entry.getValue().entrySet()) {
        int[] gotoes = gotoMap.get(gotoEntry.getKey());
        if (gotoes == null) {
          gotoes =new int[stateNb];
          Arrays.fill(gotoes,-1);
          gotoMap.put(gotoEntry.getKey(),gotoes);
        }
        gotoes[entry.getKey().getStateNo()]=gotoEntry.getValue().getStateNo();
      }
    }

    // transform start states
    HashMap<NonTerminalDecl,Integer> startStateMap=new HashMap<NonTerminalDecl,Integer>();
    for(Map.Entry<NonTerminalDecl,NodeDecl<I>> entry:startStateTable.entrySet()) {
      startStateMap.put(entry.getKey(),entry.getValue().getStateNo());
    }

    // order nodes
    //TODO Remi (^^) a factory to share metadata
    StateMetadataFactory stateMetadataFactory=new StateMetadataFactory();
    StateMetadataDecl[] metadata= new StateMetadataDecl[stateNb];
    for(NodeDecl<I> node : nodes) {
      Set<VersionDecl> compatibleVersion = node.getCompatibleVersion();
      boolean allVersions = true;
      for(VersionDecl v : versionMap.keySet()) {
        if (v!=null && !compatibleVersion.contains(v)) {
          allVersions=false;
          break;
        }
      }
      if (allVersions)
        compatibleVersion=null;
      metadata[node.getStateNo()]=
        stateMetadataFactory.create(compatibleVersion,node.getAssociated());
    }

    return new ParserTableDecl(map,branchMap,gotoMap,stateNb,startStateMap,
        actionFactory,metadata,eof,error);
  }


  private HashMap<NodeDecl<I>, HashMap<TerminalDecl, HashMap<VersionDecl,RegularTableActionDecl>>> resolveConflictsTable(
      HashMap<NodeDecl<I>, MultiMap<TerminalDecl,ActionEntry<RegularTableActionDecl>>> tmpTable) {
    HashMap<NodeDecl<I>, HashMap<TerminalDecl, HashMap<VersionDecl,RegularTableActionDecl>>> map =
      new HashMap<NodeDecl<I>, HashMap<TerminalDecl, HashMap<VersionDecl,RegularTableActionDecl>>>();
    for(Map.Entry<NodeDecl<I>, MultiMap<TerminalDecl, ActionEntry<RegularTableActionDecl>>> entry : tmpTable.entrySet()) {
      NodeDecl<I> state = entry.getKey();
      HashMap<TerminalDecl, HashMap<VersionDecl,RegularTableActionDecl>> map2 = new HashMap<TerminalDecl, HashMap<VersionDecl,RegularTableActionDecl>>();
      map.put(state, map2);
      Set<? extends TerminalDecl> alphabet = grammar.getAlphabet();
      for(TerminalDecl a:alphabet) {
        //map2.put(a, buildDefault(versionMap.keySet(),actionFactory.getDefaultAction()));
        map2.put(a, new HashMap<VersionDecl,RegularTableActionDecl>());
      }
      if (!alphabet.contains(eof)) {
        //map2.put(eof,buildDefault(versionMap.keySet(),actionFactory.getDefaultAction()));
        map2.put(eof, new HashMap<VersionDecl,RegularTableActionDecl>());
      }
      for(Map.Entry<TerminalDecl,Set<ActionEntry<RegularTableActionDecl>>> entry2 : entry.getValue().entrySet()) {
        TerminalDecl terminal = entry2.getKey();
        Set<ActionEntry<RegularTableActionDecl>> actions = entry2.getValue();
        HashMap<VersionDecl,RegularTableActionDecl> actionMap = map2.get(terminal);
        fillActionMap(RegularTableActionDecl.class,actionMap,actions,state,terminal,actionFactory.getDefaultBranchAction());
      }
    }

    if (reporter.isOnError())
      return null;

    return map;
  }
  
  private <A extends ActionDecl> void fillActionMap(Class<A> type, HashMap<VersionDecl,A> actionMap,Set<ActionEntry<A>> actions,NodeDecl<?> state,TerminalDecl terminal,A defaultAction) {
    for(Map.Entry<VersionDecl,? extends Set<? extends VersionDecl>> versionEntry : versionMap.entrySet()) {
      VersionDecl version=versionEntry.getKey();
      if (version==null)
        continue;
      A action=conflictResolver.priorityAction(type,
          reporter,actionFactory,
          getValidAction(actions,versionEntry),
          state,terminal,eof);
      if (action==null)
        action=defaultAction;
      actionMap.put(version,action);
    }
  }


  /**
   * @param actions the set of actions
   * @param versionEntry a pair of version/compatible version
   * @return the valid action
   */
  private static <A extends ActionDecl> HashSet<ActionEntry<A>> getValidAction(Set<ActionEntry<A>> actions, Map.Entry<VersionDecl,? extends Set<? extends VersionDecl>> versionEntry) {

    HashSet<ActionEntry<A>> compatible = new HashSet<ActionEntry<A>>();
    for(ActionEntry<A> action : actions) {
      if (action.getAction().accept(validActionVisitor,versionEntry))
        compatible.add(action);
    }
    return compatible;
  }

  private static final ActionDeclVisitor<Boolean,Map.Entry<VersionDecl,? extends Set<? extends VersionDecl>>> validActionVisitor=
    new ActionDeclVisitor<Boolean,Map.Entry<VersionDecl,? extends Set<? extends VersionDecl>>>() {
    public Boolean visit(ErrorActionDecl accept,Map.Entry<VersionDecl,? extends Set<? extends VersionDecl>> notUsed) {
      return true;
    }
    public Boolean visit(BranchActionDecl accept,Map.Entry<VersionDecl,? extends Set<? extends VersionDecl>> notUsed) {
      return true;
    }
    public Boolean visit(ReduceActionDecl accept,Map.Entry<VersionDecl,? extends Set<? extends VersionDecl>> versionEntry) {
      Set<? extends VersionDecl> compatibleVersions=versionEntry.getValue();
      VersionDecl version = accept.getProduction().getVersion();
      return version==null || compatibleVersions.contains(version);
    }
    public Boolean visit(ShiftActionDecl accept,Map.Entry<VersionDecl,? extends Set<? extends VersionDecl>> versionEntry) {
      VersionDecl version=versionEntry.getKey();
      return accept.getState().getCompatibleVersion().contains(version);
    }
    public Boolean visit(AcceptActionDecl accept,Map.Entry<VersionDecl,? extends Set<? extends VersionDecl>> versionEntry) {
      return true;
    }
    public Boolean visit(VersionedActionDecl versioned,Map.Entry<VersionDecl,? extends Set<? extends VersionDecl>> versionEntry) {
      throw new AssertionError("illegal action type");
    }

    public Boolean visit(EnterActionDecl enter,Map.Entry<VersionDecl,? extends Set<? extends VersionDecl>> versionEntry) {
      VersionDecl version=versionEntry.getKey();
      return enter.getState().getCompatibleVersion().contains(version);
    }

    public Boolean visit(ExitActionDecl exit,Map.Entry<VersionDecl,? extends Set<? extends VersionDecl>> versionEntry) {
      return true;
    }
  };

  /*private static HashMap<VersionDecl, ActionDecl> buildDefault(Set<? extends VersionDecl> versions,ErrorActionDecl defaultAction) {
    HashMap<VersionDecl, ActionDecl> map = new HashMap<VersionDecl, ActionDecl>();
    for(VersionDecl v : versions) {
      if (v!=null)
        map.put(v, defaultAction);
    }
    return map;
  }*/

 /* private static ActionDeclVisitor<ActionEntry,TerminalDecl> createBranchActionTransformer(
      final ActionDeclFactory factory, final boolean branchActionsForEof) {
    return new ActionDeclVisitor<ActionEntry,TerminalDecl>() {
      public ActionEntry visit(BranchActionDecl accept, TerminalDecl parameter) {
        throw new IllegalArgumentException("no branch at this stage");
      }

      public ActionEntry visit(ExitActionDecl exit,TerminalDecl terminal) {
        throw new IllegalArgumentException("no exit at this stage");
      }

      public ActionEntry visit(EnterActionDecl enter,TerminalDecl terminal) {
        throw new IllegalArgumentException("no enter at this stage");
      }

      public ActionEntry visit(VersionedActionDecl versioned,TerminalDecl terminal) {
        HashMap<VersionDecl,ActionEntry> newMap = new HashMap<VersionDecl, ActionEntry>();
        for(Map.Entry<VersionDecl, ? extends ActionDecl> entry:versioned.getActionMap().entrySet()) {
          newMap.put(entry.getKey(), entry.getValue().accept(this,terminal));
        }
        return factory.getVersionedAction(newMap);
      }

      public ActionEntry visit(ErrorActionDecl error,TerminalDecl terminal) {
        return new ActionEntry(factory.getBranch(error.getMessage()),Priority.getNoPriority());
      }

      public ActionEntry visit(ReduceActionDecl reduce,TerminalDecl terminal) {
        return new ActionEntry(reduce,reduce.getProduction().getPriority());
      }

      // DONTFIXME Remi: remove tests since they are asserts
      public ActionEntry visit(ShiftActionDecl shift,TerminalDecl terminal) {
        if (branchActionsForEof)
          throw new IllegalArgumentException("eof shouldn't be shifted");
        return new ActionEntry(factory.getEnter(terminal, shift.getState()),Priority.getNoPriority());
      }

      public ActionEntry visit(AcceptActionDecl accept,TerminalDecl terminal) {
        if (branchActionsForEof)
          return new ActionEntry(factory.getExit(),eofPriority);
        throw new IllegalArgumentException("no accept except for eof");
      }
    };
  }*/

}
