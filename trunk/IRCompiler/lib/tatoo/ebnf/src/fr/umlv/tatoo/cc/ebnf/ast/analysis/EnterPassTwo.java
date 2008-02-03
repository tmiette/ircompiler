package fr.umlv.tatoo.cc.ebnf.ast.analysis;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import fr.umlv.tatoo.cc.common.generator.Type;
import fr.umlv.tatoo.cc.common.util.MultiMap;
import fr.umlv.tatoo.cc.ebnf.ast.DirectiveDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.EnhancedDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.ImportDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.MacroDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.NodeAST;
import fr.umlv.tatoo.cc.ebnf.ast.NonTerminalDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.UnquotedIdVarAST;
import fr.umlv.tatoo.cc.ebnf.ast.PriorityDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.PriorityVarAST;
import fr.umlv.tatoo.cc.ebnf.ast.ProductionDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.ProductionIdAndVersionDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.RootDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.RuleDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.StartNonTerminalSetDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.TerminalDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.QuotedIdVarAST;
import fr.umlv.tatoo.cc.ebnf.ast.TokenAST;
import fr.umlv.tatoo.cc.ebnf.ast.TypeVarAST;
import fr.umlv.tatoo.cc.ebnf.ast.VariableTypeDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.VariableVarAST;
import fr.umlv.tatoo.cc.ebnf.ast.VersionDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.VersionVarAST;
import fr.umlv.tatoo.cc.ebnf.ast.Binding.NonTerminalBinding;
import fr.umlv.tatoo.cc.ebnf.ast.Binding.ProductionBinding;
import fr.umlv.tatoo.cc.ebnf.ast.Binding.TerminalBinding;
import fr.umlv.tatoo.cc.ebnf.ast.analysis.ASTDiagnosticReporter.ErrorKey;
import fr.umlv.tatoo.cc.ebnf.ast.analysis.ASTDiagnosticReporter.WarningKey;
import fr.umlv.tatoo.cc.parser.grammar.EBNFSupport;
import fr.umlv.tatoo.cc.parser.grammar.GrammarFactory;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.Priority;
import fr.umlv.tatoo.cc.parser.grammar.ProductionDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.VariableDecl;
import fr.umlv.tatoo.cc.parser.grammar.VersionDecl;
import fr.umlv.tatoo.cc.parser.grammar.Priority.Associativity;
import fr.umlv.tatoo.cc.tools.tools.ToolsFactory;

public class EnterPassTwo extends AbstractEnterPass {
  private final GrammarFactory grammarFactory;
  private final EBNFSupport ebnfSupport;
  private final ToolsFactory toolsFactory;
  
  private final Map<String,Type> importMap;
  private final TypeVerifier typeVerifier;
  private final EnumSet<Directive> directiveSet;
  
  private final ASTDiagnosticReporter diagnostic;
  
  EnterPassTwo(EnterPassOne enter,EnumSet<Directive> directiveSet, Map<String,Type> importMap,GrammarFactory grammarFactory,EBNFSupport ebnfSupport,ToolsFactory toolsFactory,TypeVerifier typeVerifier,ASTDiagnosticReporter diagnostic) {
    super(enter);
    
    this.directiveSet=directiveSet;
    this.importMap=importMap;
    this.grammarFactory=grammarFactory;
    this.ebnfSupport=ebnfSupport;
    this.toolsFactory=toolsFactory;
    this.typeVerifier=typeVerifier;
    
    this.diagnostic=diagnostic;
  }
  
  @Override
  public Object visit(RootDefAST node,Object parameter) {
    // process start non terminal set if it exists
    StartNonTerminalSetDefAST startNonTerminalSetDef=node.getStartNonTerminalSetDef();
    processOneSubNode(startNonTerminalSetDef,null);
    
    // check empty start nonterminal set
    if (grammarFactory.getStartNonTerminalSet().isEmpty()) {
      NodeAST reportingNode=(startNonTerminalSetDef==null)?node:startNonTerminalSetDef;
      
      Iterator<? extends NonTerminalDecl> it=grammarFactory.getAllNonTerminals().iterator();
      if (it.hasNext()) {
        // try to recover, choose the first non terminal
        NonTerminalDecl nonTerminal=it.next();
        grammarFactory.addStartNonTerminal(nonTerminal);
        diagnostic.warning(WarningKey.startnonterminals_empty_recover,reportingNode,nonTerminal);
      }
    }
    
    // process all nodes except start non terminal set
    for(NodeAST n:node.nodeList())
      if (n!=startNonTerminalSetDef)
        processOneSubNode(n,null);
    
    return null;
  }
  
  @Override
  public Object visit(DirectiveDefAST node,Object parameter) {
    // don't process children
    return null;
  }
  
  @Override
  public Object visit(ImportDefAST node,Object parameter) {
    // don't process children
    return null;
  }
  
  @Override
  public Object visit(PriorityDefAST node,Object parameter) {
    // don't process children
    return null;
  }
  
  @Override
  public Object visit(MacroDefAST node,Object parameter) {
    // don't process children
    return null;
  }
  
  @Override
  public Object visit(RuleDefAST node,Object parameter) {
    throw new AssertionError();
  }
  
  @Override
  public Object visit(TerminalDefAST node,Object parameter) {
    // don't process children
    return null;
  }
  
  @Override
  public Object visit(VersionDefAST node,Object parameter) {
    // don't process children
    return null;
  }
  
  @Override
  public Object visit(TypeVarAST node,Object parameter) {
    return Commons.visit(node,importMap,typeVerifier,getBindingMap(),diagnostic);
  }
  
  @Override
  public Object visit(VariableTypeDefAST node,Object parameter) {
    Type type=(Type)processOneSubNode(node.getType(),null);
    VariableDecl variable=(VariableDecl)processOneSubNode(node.getVariable(),null);
    
    if (type==null || variable==null)  //error recovery
      return null;
    
    // checks duplicate
    Map<VariableDecl,Type> variableTypeMap=toolsFactory.getVariableTypeMap();
    Type declaredType=variableTypeMap.get(variable);
    if (declaredType!=null) {
      if (declaredType.equals(type)) {
        diagnostic.warning(WarningKey.duplicate_type_declared_recover,node,variable.getId(),type);
      } else {
        diagnostic.error(ErrorKey.duplicate_type_declared,node,variable.getId(),type,declaredType);
      }
      return null;
    }
    
    if (variable.isTerminal())
      toolsFactory.declareTerminalType((TerminalDecl)variable,type);
    else
      toolsFactory.declareNonTerminalType((NonTerminalDecl)variable,type);
    
    return null;
  }
  
  @Override
  public Object visit(NonTerminalDefAST node,Object parameter) {
    NonTerminalBinding binding=node.getBinding();
    if (binding==null) { // error recovery
      return null;
    }
    
    // send current non terminal to all productions
    // i.e. type node must not be visited again
    NonTerminalDecl nonTerminal=binding.getDomainObject();
    for(ProductionDefAST production:node.getProductions()) {
      processOneSubNode(production,nonTerminal);
    }
    return null;
  }
  
  static class ProductionIdAndVersion {
    final TokenAST<String> name;
    final VersionDecl version;
    ProductionIdAndVersion(TokenAST<String> name,VersionDecl version) {
      this.name=name;
      this.version=version;
    }
  }
  
  // compute production id and version
  @Override
  public Object visit(ProductionIdAndVersionDefAST node,Object parameter) {
    VersionDecl version=(VersionDecl)processOneSubNode(node.getVersion(),null);
    return new ProductionIdAndVersion(node.getTokenId(),version);
  }
  
  //compute production version
  @Override
  public Object visit(VersionVarAST node,Object parameter) {
    return Commons.visit(node,grammarFactory,getBindingMap(),diagnostic);
  }
  
  //computes production priority
  @Override
  public Object visit(PriorityVarAST node, Object parameter) {
    return Commons.visit(node,grammarFactory,getBindingMap(),diagnostic);
  }
  
  @Override
  public Object visit(StartNonTerminalSetDefAST node,Object parameter) {
    
    MultiMap<NonTerminalDecl,UnquotedIdVarAST> nonTerminalMultiMap=
      new MultiMap<NonTerminalDecl,UnquotedIdVarAST>();
    for(UnquotedIdVarAST nonTerminalNode:node.getStartNonTerminalList()) {
      NonTerminalDecl nonTerminal=(NonTerminalDecl)processOneSubNode(nonTerminalNode,null);
      nonTerminalMultiMap.add(nonTerminal,nonTerminalNode);
    }
    
    // emptiness check is done by visiting RootDefAST
    // check duplicates
    for(Map.Entry<NonTerminalDecl,Set<UnquotedIdVarAST>> entry:nonTerminalMultiMap.entrySet()) {
      if (entry.getValue().size()>1) {
        diagnostic.warning(WarningKey.startnonterminals_duplicates,node,entry.getKey(),entry.getValue());
      }
    }
    
    for(NonTerminalDecl nonTerminal:nonTerminalMultiMap.keySet()) {
      grammarFactory.addStartNonTerminal(nonTerminal);
    }
    
    return null;
  }
  
  
  @Override
  public Object visit(ProductionDefAST node,Object parameter) {
    // get non terminal from parent
    NonTerminalDecl nonTerminal=(NonTerminalDecl)parameter;
    
    ArrayList<VariableDecl> variables=new ArrayList<VariableDecl>();
    for(NodeAST n:node.getVariableList()) {
      VariableDecl variable=(VariableDecl)processOneSubNode(n,null);
      if (variable==null) // error recovery
        continue;
      
      variables.add(variable); 
    }
    
    Priority priority=(Priority)processOneSubNode(node.getPriority(),null);
    ProductionIdAndVersion idAndVersion=(ProductionIdAndVersion)processOneSubNode(node.getIdAndVersion(),null);
    
    String name;
    VersionDecl version;
    if (idAndVersion==null) {
      // compute production name
      StringBuilder builder=new StringBuilder(nonTerminal.getId()).append('_');
      for(VariableDecl var:variables)
        builder.append('_').append(var.getId());
      name=builder.toString();
      diagnostic.warning(WarningKey.generated_production_id,node,name);
      
      version=null;
    } else {
      name=idAndVersion.name.getValue();
      version=idAndVersion.version;
      
      // update production name
      node.setTokenId(idAndVersion.name);
    }
    
    ProductionDecl production=grammarFactory.createProduction(name,nonTerminal,variables,priority,version);
    ProductionBinding binding=new ProductionBinding(node,production);
    node.setBinding(binding);
    getBindingMap().registerBinding(production,binding);
    return null;
  }
  
  @Override
  public Object visit(UnquotedIdVarAST node,Object parameter) {
    String name=node.getName();
    
    VariableDecl variable=grammarFactory.getVariableMap().get(VariableDecl.class,name);
    
    // check if variable exists
    if (variable==null) {
      diagnostic.error(ErrorKey.nonTerminal_var_unknown,node,name);
      return null;
    }
    // check if variable is a terminal
    if (variable instanceof TerminalDecl) {
      TerminalDecl terminal=(TerminalDecl)variable;
      
      // check if it's a branch terminal
      if (!terminal.isBranching()) {
        diagnostic.error(ErrorKey.nonTerminal_var_terminal_instead,node,name);    
      }
      
      TerminalBinding binding=getBindingMap().getBinding(terminal,TerminalBinding.class,false);
      node.setBinding(binding);
      return terminal;
    }
    
    NonTerminalDecl nonTerminal=(NonTerminalDecl)variable;
    NonTerminalBinding binding=getBindingMap().getBinding(nonTerminal,NonTerminalBinding.class,false);
    node.setBinding(binding);
    return nonTerminal;
  }
  
  @Override
  public Object visit(QuotedIdVarAST node,Object parameter) {
    String name=node.getName();
    
    VariableDecl variable=grammarFactory.getVariableMap().get(VariableDecl.class,name);
    
    // check if directive auto-token
    if (directiveSet.contains(Directive.AUTOTOKEN) &&
        !(variable instanceof TerminalDecl)) {
      TerminalDecl terminal=grammarFactory.createTerminal(name,Priority.getNoPriority(),false);
      TerminalBinding binding=new TerminalBinding(null,terminal);
      getBindingMap().registerBinding(terminal,binding);
      node.setBinding(binding);
      return terminal;
    }
    
    // check if variable exists
    if (variable==null) {
      diagnostic.error(ErrorKey.terminal_var_unknown,node,name);
      return null;
    }
    // check if variable is a non terminal
    if (variable instanceof NonTerminalDecl) {
      diagnostic.error(ErrorKey.terminal_var_nonterminal_instead,node,name);
      return variable;
    }
    TerminalDecl terminal=(TerminalDecl)variable;
    // check if it's a branch terminal
    if (terminal.isBranching()) {
      diagnostic.error(ErrorKey.terminal_var_branch_nonterminal_instead,node,name);
      return variable;
    }
    
    TerminalBinding binding=getBindingMap().getBinding(terminal,TerminalBinding.class,false);
    node.setBinding(binding);
    return terminal;
  }
  
  @Override
  public Object visit(EnhancedDefAST node,Object parameter) {
    NonTerminalDecl nonTerminal;
    switch(node.getEnhancement()) {
      case OPTIONAL: {
        VariableVarAST element=node.getElement();
        VariableDecl elementVar=(VariableDecl)element.accept(this,null);
        if (elementVar==null) // error recovery
          return null;
        nonTerminal=ebnfSupport.createOptionnalNonTerminal(
          element.getName()+"_opt",elementVar,null);
      }
      break;
      case GROUP: {
        ArrayList<VariableDecl> variables=new ArrayList<VariableDecl>();
        for(NodeAST n:node.getVarGroup()) {
          Object result=n.accept(this,null);
          if (result instanceof VariableDecl) {
            variables.add((VariableDecl)variables);
          }
        }
        nonTerminal=ebnfSupport.createAnonymousNonTerminal(variables,null);
      }
      break;
      case PLUS: {
        VariableVarAST element=node.getElement();
        VariableDecl elementVar=(VariableDecl)element.accept(this,null);
        if (elementVar==null) // error recovery
          return null;
        VariableVarAST separator=node.getSeparator();
        VariableDecl separatorVar=(separator==null)?null:(VariableDecl)separator.accept(this,null);
        nonTerminal=ebnfSupport.createStarNonTerminal(element.getName()+"_plus",
          elementVar,Associativity.LEFT,null,false,separatorVar);
      }
      break;
      case STAR: {
        VariableVarAST element=node.getElement();
        VariableDecl elementVar=(VariableDecl)element.accept(this,null);
        if (elementVar==null) // error recovery
          return null;
        VariableVarAST separator=node.getSeparator();
        VariableDecl separatorVar=(separator==null)?null:(VariableDecl)separator.accept(this,null);
        nonTerminal=ebnfSupport.createStarNonTerminal(element.getName()+"_star",
          elementVar,Associativity.LEFT,null,true,separatorVar);
      }
      break;
      default:
        throw new AssertionError("unknown enhancement "+node.getEnhancement());
    }
    
    // process node name
    node.setName(nonTerminal.getId());
    
    NonTerminalBinding binding=new NonTerminalBinding(node,nonTerminal);
    node.setBinding(binding);
    getBindingMap().registerBinding(nonTerminal,binding);
    
    return nonTerminal;
  }
}
