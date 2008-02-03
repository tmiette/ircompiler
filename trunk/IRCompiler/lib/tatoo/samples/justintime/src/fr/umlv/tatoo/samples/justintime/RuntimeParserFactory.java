package fr.umlv.tatoo.samples.justintime;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import fr.umlv.tatoo.cc.parser.grammar.GrammarFactory;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.Priority;
import fr.umlv.tatoo.cc.parser.grammar.ProductionDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.VariableDecl;
import fr.umlv.tatoo.cc.parser.grammar.VersionDecl;
import fr.umlv.tatoo.cc.parser.main.ConflictResolverType;
import fr.umlv.tatoo.cc.parser.main.ParserType;
import fr.umlv.tatoo.cc.parser.parser.AcceptActionDecl;
import fr.umlv.tatoo.cc.parser.parser.ActionDecl;
import fr.umlv.tatoo.cc.parser.parser.ActionDeclFactory;
import fr.umlv.tatoo.cc.parser.parser.BranchActionDecl;
import fr.umlv.tatoo.cc.parser.parser.EnterActionDecl;
import fr.umlv.tatoo.cc.parser.parser.ErrorActionDecl;
import fr.umlv.tatoo.cc.parser.parser.ExitActionDecl;
import fr.umlv.tatoo.cc.parser.parser.ReduceActionDecl;
import fr.umlv.tatoo.cc.parser.parser.ShiftActionDecl;
import fr.umlv.tatoo.cc.parser.parser.SimpleActionDeclVisitor;
import fr.umlv.tatoo.cc.parser.parser.VersionedActionDecl;
import fr.umlv.tatoo.cc.parser.table.LogInfoConflictDiagnosticReporter;
import fr.umlv.tatoo.cc.parser.table.ParserTableDecl;
import fr.umlv.tatoo.cc.parser.table.ParserTableDeclFactory;
import fr.umlv.tatoo.cc.parser.table.StateMetadataDecl;
import fr.umlv.tatoo.runtime.parser.AcceptAction;
import fr.umlv.tatoo.runtime.parser.Action;
import fr.umlv.tatoo.runtime.parser.BranchAction;
import fr.umlv.tatoo.runtime.parser.DefaultParserWarningReporter;
import fr.umlv.tatoo.runtime.parser.EnterAction;
import fr.umlv.tatoo.runtime.parser.ErrorAction;
import fr.umlv.tatoo.runtime.parser.ParserErrorRecoveryPolicy;
import fr.umlv.tatoo.runtime.parser.ExitAction;
import fr.umlv.tatoo.runtime.parser.Parser;
import fr.umlv.tatoo.runtime.parser.ParserListener;
import fr.umlv.tatoo.runtime.parser.ParserTable;
import fr.umlv.tatoo.runtime.parser.ReduceAction;
import fr.umlv.tatoo.runtime.parser.ShiftAction;
import fr.umlv.tatoo.runtime.parser.StateMetadata;
import fr.umlv.tatoo.runtime.parser.VersionedAction;

public class RuntimeParserFactory {
  @SuppressWarnings("unchecked")
  public static Parser<TerminalDecl,NonTerminalDecl,ProductionDecl,VersionDecl> createRuntimeParser(
    GrammarFactory factory,NonTerminalDecl start,VersionDecl version,
    ParserListener<? super TerminalDecl,? super NonTerminalDecl,? super ProductionDecl> listener) {
    
    // create eof
    TerminalDecl eof = factory.createTerminal("eof",Priority.getNoPriority(),false);
    
    // generate table
    ActionDeclFactory actionFactory=new ActionDeclFactory();
    LogInfoConflictDiagnosticReporter reporter=new LogInfoConflictDiagnosticReporter(null);
    ParserTableDecl grammar = ParserTableDeclFactory.
      buildTable(reporter,factory.getAllProductions(), 
        Collections.singleton(start), eof, null, 
        factory.getDependingVersionMap(),actionFactory, 
        ParserType.lalr.getMethod(), ConflictResolverType.DEFAULT.getConflictResolver(),null);
    
    Map<NonTerminalDecl, int[]> gotoes = grammar.getGotoes();
    
    // create action
    HashMap<TerminalDecl,Action<TerminalDecl,ProductionDecl,VersionDecl>[]> runtimeTable=
      new HashMap<TerminalDecl,Action<TerminalDecl,ProductionDecl,VersionDecl>[]>();
    for(Map.Entry<TerminalDecl,? extends ActionDecl[]> entry:grammar.getActionMap().entrySet()) {
      ActionDecl[] actionDecls=entry.getValue();
      Action<TerminalDecl,ProductionDecl,VersionDecl>[] actions=
        runtimize(gotoes,actionDecls);
      runtimeTable.put(entry.getKey(),actions);
    }
    
    // create branch array
    Action<TerminalDecl,ProductionDecl,VersionDecl>[] branchArray=
      runtimize(gotoes,grammar.getBranchArray());
    
    // create version array
    Collection<? extends VersionDecl> versionCollection = factory.getAllVersions();
    VersionDecl[] versions=versionCollection.toArray(new VersionDecl[versionCollection.size()]);
    
    // create metadata
    StateMetadata<TerminalDecl,NonTerminalDecl,VersionDecl>[] metadatas=
      (StateMetadata<TerminalDecl,NonTerminalDecl,VersionDecl>[])new StateMetadata<?,?,?>[grammar.getStateCount()];

    StateMetadataDecl[] stateMetadata = grammar.getMetadata();
    for(int i=0;i<stateMetadata.length;i++)
      metadatas[i]=createMetadata(stateMetadata[i],versions.length);
    
    
    
    // create parser table
    ParserTable<TerminalDecl,NonTerminalDecl,ProductionDecl,VersionDecl> parserTable=
      new ParserTable<TerminalDecl,NonTerminalDecl,ProductionDecl,VersionDecl>(
          runtimeTable,branchArray,metadatas,grammar.getStartStateMap(),versions,
          grammar.getStateCount(),eof,null
          );
    
    return Parser.createParser(
      parserTable,listener,
      ParserErrorRecoveryPolicy.getNoErrorRecoveryPolicy(
        new DefaultParserWarningReporter<TerminalDecl,NonTerminalDecl,ProductionDecl,VersionDecl>()),
      start,version,null);
  }

  private static StateMetadata<TerminalDecl,NonTerminalDecl,VersionDecl> createMetadata(StateMetadataDecl decl,int versionCount) {
    if (decl==null)
      return null;
    
    VariableDecl associated=decl.getAssociated();
    Set<? extends VersionDecl> compatibleVersions=decl.getCompatibleVersions();
    
    if (associated.isTerminal()) {
      TerminalDecl terminal=(TerminalDecl)associated;  
      if (compatibleVersions.size()==versionCount)
        return StateMetadata.createAllVersionWithTerminal(terminal);
      else
        return StateMetadata.createWithTerminal(compatibleVersions,terminal);
    }
    else {
      NonTerminalDecl nonTerminal=(NonTerminalDecl)associated;
      if (compatibleVersions.size()==versionCount)
        return StateMetadata.createAllVersionWithNonTerminal(nonTerminal);
      else
        return StateMetadata.createWithNonTerminal(compatibleVersions,nonTerminal);
    }
  }

  private static Action<TerminalDecl,ProductionDecl,VersionDecl>[] runtimize(
      Map<NonTerminalDecl, int[]> gotoes,
      ActionDecl[] actionDecls) {
    @SuppressWarnings("unchecked")
    Action<TerminalDecl,ProductionDecl,VersionDecl>[] actions=
      (Action<TerminalDecl,ProductionDecl,VersionDecl>[])new Action<?,?,?>[actionDecls.length];
    for(int i=0;i<actionDecls.length;i++)
      actions[i]=runtimize(gotoes,actionDecls[i]);
    return actions;
  }
  
  private static Action<TerminalDecl,ProductionDecl,VersionDecl> runtimize(final Map<NonTerminalDecl,int[]> gotoes,ActionDecl decl) {
    SimpleActionDeclVisitor<Action<TerminalDecl,ProductionDecl,VersionDecl>> visitor=
      new SimpleActionDeclVisitor<Action<TerminalDecl,ProductionDecl,VersionDecl>>() {
        @Override
        public Action<TerminalDecl,ProductionDecl, VersionDecl> visit(AcceptActionDecl accept) {
          return AcceptAction.getInstance();
        }
        @Override
        public Action<TerminalDecl,ProductionDecl, VersionDecl> visit(ShiftActionDecl shift) {
          return new ShiftAction<TerminalDecl,ProductionDecl,VersionDecl>(shift.getState().getStateNo());
        }
        @Override
        public Action<TerminalDecl,ProductionDecl, VersionDecl> visit(ReduceActionDecl reduce) {
          ProductionDecl production = reduce.getProduction();
          return new ReduceAction<TerminalDecl,ProductionDecl,VersionDecl>(production,production.getRight().size(),
            gotoes.get(reduce.getGotos()));
        }
        @Override
        public Action<TerminalDecl,ProductionDecl, VersionDecl> visit(ErrorActionDecl error) {
          return new ErrorAction<TerminalDecl,ProductionDecl,VersionDecl>(error.getMessage());
        }
        @Override
        public Action<TerminalDecl,ProductionDecl, VersionDecl> visit(BranchActionDecl error) {
          return new BranchAction<TerminalDecl,ProductionDecl,VersionDecl>(error.getMessage());
        }
        @Override
        public Action<TerminalDecl,ProductionDecl,VersionDecl> visit(VersionedActionDecl versioned) {
          HashMap<VersionDecl,Action<TerminalDecl,ProductionDecl,VersionDecl>> map=
            new HashMap<VersionDecl,Action<TerminalDecl,ProductionDecl,VersionDecl>>();
 
          for(Map.Entry<VersionDecl,? extends ActionDecl> entry:versioned.getActionMap().entrySet())
            map.put(entry.getKey(),entry.getValue().accept(this));
          
          return new VersionedAction<TerminalDecl,ProductionDecl,VersionDecl>(map);
        }
        @Override
        public Action<TerminalDecl,ProductionDecl,VersionDecl> visit(EnterActionDecl enter) {
          return new EnterAction<TerminalDecl,ProductionDecl,VersionDecl>(
            enter.getBranchingTerminal(),
            enter.getState().getStateNo());
        }
        @Override
        public Action<TerminalDecl, ProductionDecl, VersionDecl> visit(ExitActionDecl exit) {
          return ExitAction.<TerminalDecl,ProductionDecl,VersionDecl>getInstance();
        }
    };
    return decl.accept(visitor);
  }
}
