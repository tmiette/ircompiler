package fr.umlv.tatoo.cc.ebnf.ast.analysis;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import fr.umlv.tatoo.cc.common.generator.Type;
import fr.umlv.tatoo.cc.ebnf.ast.AliasDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.BindingMap;
import fr.umlv.tatoo.cc.ebnf.ast.DirectiveDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.ImportDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.MacroDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.NonTerminalDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.PriorityDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.PriorityVarAST;
import fr.umlv.tatoo.cc.ebnf.ast.RuleDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.TerminalDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.TypeVarAST;
import fr.umlv.tatoo.cc.ebnf.ast.VariableTypeDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.VersionDefAST;
import fr.umlv.tatoo.cc.ebnf.ast.VersionVarAST;
import fr.umlv.tatoo.cc.ebnf.ast.Binding.DirectiveBinding;
import fr.umlv.tatoo.cc.ebnf.ast.Binding.NonTerminalBinding;
import fr.umlv.tatoo.cc.ebnf.ast.Binding.PriorityBinding;
import fr.umlv.tatoo.cc.ebnf.ast.Binding.RuleBinding;
import fr.umlv.tatoo.cc.ebnf.ast.Binding.TerminalBinding;
import fr.umlv.tatoo.cc.ebnf.ast.Binding.TypeBinding;
import fr.umlv.tatoo.cc.ebnf.ast.Binding.VersionBinding;
import fr.umlv.tatoo.cc.ebnf.ast.TerminalDefAST.TerminalKind;
import fr.umlv.tatoo.cc.ebnf.ast.analysis.ASTDiagnosticReporter.ErrorKey;
import fr.umlv.tatoo.cc.ebnf.ast.analysis.ASTDiagnosticReporter.WarningKey;
import fr.umlv.tatoo.cc.lexer.charset.encoding.Encoding;
import fr.umlv.tatoo.cc.lexer.lexer.RuleDecl;
import fr.umlv.tatoo.cc.lexer.lexer.RuleFactory;
import fr.umlv.tatoo.cc.lexer.regex.Regex;
import fr.umlv.tatoo.cc.lexer.regex.pattern.PatternRuleCompilerImpl;
import fr.umlv.tatoo.cc.parser.grammar.EBNFSupport;
import fr.umlv.tatoo.cc.parser.grammar.GrammarFactory;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.Priority;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.VariableDecl;
import fr.umlv.tatoo.cc.parser.grammar.VersionDecl;
import fr.umlv.tatoo.cc.parser.grammar.Priority.Associativity;
import fr.umlv.tatoo.cc.tools.tools.ToolsFactory;

public class EnterPassOne extends AbstractEnterPass {
  private final RuleFactory ruleFactory;
  private final GrammarFactory grammarFactory;
  private final ToolsFactory toolsFactory;
  
  private final PatternRuleCompilerImpl compiler;
  
  private final Map<String,Type> importMap;
  private final HashMap<String,Regex> macroes;
  
  private final TypeVerifier typeVerifier;
  private final ASTDiagnosticReporter diagnostic;
  private final HashMap<String,TerminalDecl> aliases;
  private final EnumSet<Directive> directiveSet;

  public EnterPassOne(BindingMap bindingMap,Map<String,Type> importMap,Encoding encoding,RuleFactory ruleFactory,GrammarFactory grammarFactory,ToolsFactory toolsFactory,TypeVerifier typeVerifier,ASTDiagnosticReporter diagnostic) {
    super(bindingMap);
    this.importMap=importMap;
    this.ruleFactory=ruleFactory;
    this.grammarFactory=grammarFactory;
    this.toolsFactory=toolsFactory;
    
    this.directiveSet=EnumSet.noneOf(Directive.class);
    this.aliases=new HashMap<String, TerminalDecl>();
    this.macroes=new HashMap<String,Regex>();
    this.compiler=new PatternRuleCompilerImpl(macroes,encoding);
    
    this.typeVerifier=typeVerifier;
    this.diagnostic=diagnostic;
  }
  
  public EnterPassTwo createEnterPassTwo(EBNFSupport ebnfSupport) {
    return new EnterPassTwo(this,directiveSet,importMap,grammarFactory,ebnfSupport,toolsFactory,typeVerifier,diagnostic);
  }
  
  @Override
  public Object visit(DirectiveDefAST node,Object parameter) {
    String name=node.getTokenId().getValue();
    
    Directive directive;
    try {
      directive=Directive.parse(name);
      directiveSet.add(directive);
    } catch(IllegalArgumentException e) {
      diagnostic.error(ErrorKey.unknown_directive,node,name);
      return null;
    }
    
    DirectiveBinding binding=new DirectiveBinding(node,directive);
    node.setBinding(binding);
    getBindingMap().registerBinding(directive,binding);
    return null;
  }
  
  @Override
  public Object visit(ImportDefAST node,Object parameter) {
    String qualifiedName=node.getQualifiedId();
    
    // check if name is qualified
    if (qualifiedName.indexOf('.')==-1) {
      diagnostic.error(ErrorKey.import_not_qualified,node,qualifiedName);
      return null; 
    }
    
    Type type=Type.createQualifiedType(qualifiedName);
    if (!typeVerifier.typeExist(type)) {
      diagnostic.warning(WarningKey.type_not_exist,node);
    }
    
    String unqualifiedRawName=type.getSimpleRawName();
    
    // check duplicate
    if (importMap.containsKey(unqualifiedRawName)) {
      diagnostic.warning(WarningKey.import_duplicate,node);
    }
    
    importMap.put(unqualifiedRawName,type);
    
    TypeBinding binding=new TypeBinding(node,type);
    node.setBinding(binding);
    getBindingMap().registerBinding(type,binding);
    return null;
  }
  
  @Override
  public Object visit(PriorityDefAST node,Object parameter) {
    // check duplicate
    String name=node.getName();
    if (grammarFactory.getPriority(name)!=null) {
      diagnostic.error(ErrorKey.priority_duplicate,node,name);
      return null;
    }
    
    String assoc=node.getAssociation();
    Associativity associativity;
    try {
      associativity=Priority.parseAssociativity(assoc);
    } catch(IllegalArgumentException e) {
      diagnostic.warning(WarningKey.bad_associativity,node,name);
      associativity=Associativity.NON_ASSOCIATIVE;
    }
    
    Priority priority=grammarFactory.createPriority(name,node.getNumber(),associativity);
   
    PriorityBinding binding=new PriorityBinding(node,priority);
    node.setBinding(binding);
    getBindingMap().registerBinding(priority,binding);
    return priority;
  }
  
  @Override
  public Object visit(MacroDefAST node,Object parameter) {
    Regex macro=compiler.createMacro(node.getRegex());
    macroes.put(node.getName(),macro);
    return null;
  }
  
  @Override
  public Object visit(RuleDefAST node,Object parameter) {
    // check duplicate
    String name=node.getName();
    if (ruleFactory.getRuleMap().is(VariableDecl.class,name)) {
      diagnostic.error(ErrorKey.rule_duplicate,node,name);
      return null;
    }
    
    RuleDecl rule=compiler.createRule(ruleFactory,name,node.getRegex());
    
    RuleBinding binding=new RuleBinding(node,rule);
    node.setBinding(binding);
    getBindingMap().registerBinding(rule,binding);
    return rule;
  }
  
  // This method computes only terminal priority and return it.
  // Other priority like production priority are processed
  // during EnterTwo pass
  @Override
  public Object visit(PriorityVarAST node, Object parameter) {
    return Commons.visit(node,grammarFactory,getBindingMap(),diagnostic);
  }
  
  @Override
  public Object visit(VariableTypeDefAST node,Object parameter) {
    // skip visit
    return null;
  }
  
  @Override
  public Object visit(TerminalDefAST node,Object parameter) {
    String name=node.getName();
    TerminalKind terminalKind=node.getTerminalKind();
    
    // check duplicate
    switch(terminalKind) {
      case TOKEN:
      case BLANK:
      case BRANCH:
        if (grammarFactory.getVariableMap().is(VariableDecl.class,name)) {
          diagnostic.error(ErrorKey.terminal_duplicate,node,name);
          return null;
        }
        break;
      case EOF:
        if (grammarFactory.isEofDefined()) {
          diagnostic.error(ErrorKey.terminal_eof_duplicate,node,name);
          return null;
        }
        break;
      case ERROR:
        // do nothing, there is no way to create more than one error
        // in the EBNF grammar
    }
    
    Type type=(Type)processOneSubNode(node.getType(),null);
    RuleDecl rule=(RuleDecl)processOneSubNode(node.getRule(),null);
    Priority priority=(Priority)processOneSubNode(node.getPriority(),null);
    
    TerminalDecl terminal;
    switch(terminalKind) {
      case BLANK:
        terminal=null;
        break;
      case TOKEN:
        terminal=grammarFactory.createTerminal(name,priority,false);
        break;
      case BRANCH:
        terminal=grammarFactory.createTerminal(name,priority,true);
        break;
      case EOF:
        terminal=grammarFactory.createEof(priority);
        break;
      case ERROR:
        terminal=grammarFactory.createError(name);
        break;
      default:
        throw new AssertionError("unknown terminal kind "+terminalKind);
    }
    
    if (type!=null) {
      toolsFactory.declareTerminalType(terminal,type);
    }
    if (rule!=null) {
      toolsFactory.createRuleInfo(rule,terminal,null,true,terminalKind==TerminalKind.BLANK);
    }
    
    AliasDefAST alias = node.getAlias();
    // send terminal to AliasDefAST
    processOneSubNode(alias,terminal);
    
    TerminalBinding binding=new TerminalBinding(node,terminal);
    node.setBinding(binding);
    getBindingMap().registerBinding(terminal,binding);
    return null;
  }
  
  @Override
  public Object visit(AliasDefAST node, Object parameter) {
    TerminalDecl terminal=(TerminalDecl)parameter;
    
    String name=node.getTokenId().getValue();
    TerminalDecl old=aliases.put(name,terminal);
    if (old!=null)
      diagnostic.warning(WarningKey.two_terminals_with_same_alias,node,name,terminal.getId(),old.getId());
    terminal.setAlias(name);
    
    return null;
  }
  
  @Override
  public Object visit(TypeVarAST node,Object parameter) {
    return Commons.visit(node,importMap,typeVerifier,getBindingMap(),diagnostic);
  }
  
  @Override
  public Object visit(VersionDefAST node,Object parameter) {
    String name=node.getName();
    
    VersionDecl parentVersion=(VersionDecl)processOneSubNode(node.getParentVersion(),null);
    
    VersionDecl version=grammarFactory.createVersion(name,parentVersion);
    
    VersionBinding binding=new VersionBinding(node,version);
    node.setBinding(binding);
    getBindingMap().registerBinding(version,binding);
    
    return null;
  }
  
  @Override
  public Object visit(VersionVarAST node,Object parameter) {
    return Commons.visit(node,grammarFactory,getBindingMap(),diagnostic);
  }
  
  @Override
  public Object visit(NonTerminalDefAST node,Object parameter) {
    String name=node.getName();
    
    // check duplicate
    if (grammarFactory.getVariableMap().is(VariableDecl.class,name)) {
      diagnostic.error(ErrorKey.nonterminal_duplicate,node,name);
      return null;
    }
    
    Type type=(Type)processOneSubNode(node.getType(),null);
    NonTerminalDecl nonTerminal=grammarFactory.createNonTerminal(node.getName());
    
    if (type!=null) {
      toolsFactory.declareNonTerminalType(nonTerminal,type);
    }
    
    NonTerminalBinding binding=new NonTerminalBinding(node,nonTerminal);
    node.setBinding(binding);
    getBindingMap().registerBinding(nonTerminal,binding);
    return null;
  }
}
