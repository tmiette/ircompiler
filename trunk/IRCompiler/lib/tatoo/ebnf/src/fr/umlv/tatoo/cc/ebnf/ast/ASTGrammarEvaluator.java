package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.tatoo.cc.ebnf.ast.EnhancedDefAST.Enhancement;
import fr.umlv.tatoo.cc.ebnf.ast.TerminalDefAST.TerminalKind;
import fr.umlv.tatoo.cc.lexer.ebnf.tools.GrammarEvaluator;

public class ASTGrammarEvaluator implements GrammarEvaluator {
  private final TreeFactory factory;
  public ASTGrammarEvaluator(TreeFactory factory) {
    this.factory=factory;
  }
  
  /*
  private <V> V getValueEscapeNull(SimpleNodeAST<V> node) {
    return (node==null)?null:node.getValue();
  }
  */

  public void acceptStart(RootDefAST start) {
    factory.setRoot(start);
  }
  
  public RootDefAST start_def(SimpleNodeAST<?> directives_lhs_optional,
      SimpleNodeAST<?> imports_lhs_optional,
      SimpleNodeAST<?> priorities_lhs_optional,SimpleNodeAST<?> token_lhs,
      SimpleNodeAST<?> blank_lhs_optional,
      SimpleNodeAST<?> branch_lhs_optional,
      SimpleNodeAST<?> error_lhs_optional,
      SimpleNodeAST<?> versions_optional,
      SimpleNodeAST<?> types_lhs_optional,
      StartNonTerminalSetDefAST start_non_terminals_optional,
      SimpleNodeAST<?> production_lhs) {
    
    return factory.createRootDef(start_non_terminals_optional).
      addAll(directives_lhs_optional,imports_lhs_optional,
             priorities_lhs_optional,token_lhs,
             blank_lhs_optional,branch_lhs_optional,error_lhs_optional,
             types_lhs_optional,versions_optional,start_non_terminals_optional,
             production_lhs).create();
  }
  
  public SimpleNodeAST<?> directives_def(TokenAST<?> directivesdecl,
      TokenAST<?> colon,List<DirectiveDefAST> directive_list) {
    return factory.createSimpleNode(Kind.NO_KIND,null).
      addAll(directivesdecl,colon).addAll(directive_list).create();
  }
  
  public DirectiveDefAST directive_def(TokenAST<String> id) {
    return factory.createDirectiveDef(id).add(id).create();
  }

  public TerminalDefAST branch_eof_terminal(TokenAST<?> eof,
      PriorityVarAST priority_optional) {
    return factory.createTerminalDef(TerminalKind.EOF,null,null,null,null,priority_optional).
      addAll(eof,priority_optional).create();
  }
  
  public TerminalDefAST branch_lexem_terminal(TokenAST<String> id,
      TypeVarAST type_optional,PriorityVarAST priority_optional) {
    return factory.createTerminalDef(TerminalKind.BRANCH,id,null,type_optional,null,priority_optional).
      addAll(id,type_optional,priority_optional).create();
  }
  
  public TreeAST prod_production(List<NodeAST> varlist,
      PriorityVarAST priority_optional,
      ProductionIdAndVersionDefAST production_id_optional) {
    return factory.createProductionDef(varlist,priority_optional,production_id_optional).
      addAll(varlist).addAll(priority_optional,production_id_optional).create();
  }
  
  public UnquotedIdVarAST startid_def(TokenAST<String> id) {
    return factory.createUnquotedIdVar(id).add(id).create();
  }
  
  public SimpleNodeAST<?> versions_def(TokenAST<?> versionsdecl,
      List<VersionDefAST> version_list) {
    return factory.createSimpleNode(Kind.NO_KIND,null).
      add(versionsdecl).addAll(version_list).create();
  }
  
  public VersionDefAST version_def(TokenAST<String> id,
      VersionVarAST parent_version_optional) {
    return factory.createVersionDef(id,parent_version_optional).
      addAll(id,parent_version_optional).create();
  }
  
  public VersionVarAST parent_version_def(TokenAST<?> colon,TokenAST<String> id) {
    return factory.createVersionVar(id).
      addAll(colon,id).create();
  }
  
  public SimpleNodeAST<?> token_def(TokenAST<?> tokensdecl,TokenAST<?> colon,
      List<NodeAST> tokens_list) {
    return factory.createSimpleNode(Kind.NO_KIND,null).
       addAll(tokensdecl,colon).addAll(tokens_list).create();
  }
  
  public NodeAST lexem_macro(TokenAST<?> dollar,TokenAST<String> id,
      TokenAST<?> assign,SimpleNodeAST<String> regex) {
    return factory.createMacroDef(id,regex.getValue()).
      addAll(dollar,id,assign,regex).create();
  }
  
  public SimpleNodeAST<?> branch_def(TokenAST<?> branchesdecl,TokenAST<?> colon,
      List<TerminalDefAST> branches_list) {
    return factory.createSimpleNode(Kind.NO_KIND,null).
      addAll(branchesdecl,colon).addAll(branches_list).create();
  }
  
  public SimpleNodeAST<?> blank_def(TokenAST<?> blanksdecl,TokenAST<?> colon,
      List<NodeAST> blanks_list) {
    return factory.createSimpleNode(Kind.NO_KIND,null).
      addAll(blanksdecl,colon).addAll(blanks_list).create();
  }
  public NodeAST blank_lexem_macro(TokenAST<?> dollar,
      TokenAST<String> id,TokenAST<?> assign,SimpleNodeAST<String> regex) {
    return factory.createMacroDef(id,regex.getValue()).
      addAll(dollar,id,assign,regex).create();
  }
  public NodeAST blank_lexem_terminal(TokenAST<String> id,
      TokenAST<?> assign,SimpleNodeAST<String> regex) {
    RuleDefAST rule=factory.createRuleDef(id,regex.getValue()).
      add(regex).create();
    return factory.createTerminalDef(TerminalKind.BLANK,id,null,null,rule,null).
      addAll(id,assign,rule).create();
  }
  
  public SimpleNodeAST<?> error_def(TokenAST<?> errordecl,TokenAST<?> colon,
      TokenAST<String> id) {
    NodeAST node=factory.createTerminalDef(TerminalKind.ERROR,id,null,null,null,null).
       add(id).create();
    return factory.createSimpleNode(Kind.NO_KIND,null).
      addAll(errordecl,colon,node).create();
  }
  
  public SimpleNodeAST<?> types_def(TokenAST<?> typesdecl,TokenAST<?> colon,
      List<VariableTypeDefAST> vartypedef_list) {
    return factory.createSimpleNode(Kind.NO_KIND,null).
      addAll(typesdecl,colon).addAll(vartypedef_list).create();
  }
  
  public VariableTypeDefAST vartype_def(VariableVarAST variable,
      TypeVarAST type) {
    return factory.createVariableTypeDef(variable,type).
      addAll(variable,type).create();
  }
  
  public VariableVarAST variable_nonterminal(TokenAST<String> id) {
    return factory.createUnquotedIdVar(id).add(id).create();
  }
  
  public VariableVarAST variable_terminal(TokenAST<?> quote,
      TokenAST<String> id,TokenAST<?> quote2) {
    return factory.createQuotedIdVar(id).addAll(quote,id,quote2).create();
  }
  
  public PriorityVarAST terminal_or_prod_priority(TokenAST<?> lsqbracket,
      TokenAST<String> id,TokenAST<?> rsqbracket) {
    return factory.createPriorityVar(id).addAll(lsqbracket,id,rsqbracket).create();
  }
  
  public SimpleNodeAST<?> production_def(TokenAST<?> productionsdecl,
      TokenAST<?> colon,List<NonTerminalDefAST> decls) {
    return factory.createSimpleNode(Kind.NO_KIND,null).
      addAll(productionsdecl,colon).addAll(decls).create();
  }
  
  public NonTerminalDefAST decl_productions(TokenAST<String> id,
      TypeVarAST type_optional,TokenAST<?> assign,List<TreeAST> prods,
      TokenAST<?> semicolon) {
    
    // prods contains productions AND token 'pipe', filter out pipe
    ArrayList<ProductionDefAST> productions=new ArrayList<ProductionDefAST>();
    for(TreeAST tree:prods) {
      if (tree instanceof ProductionDefAST)
        productions.add((ProductionDefAST)tree);
    }
    
    return factory.createNonTerminalDef(id,type_optional,productions).
      addAll(id,type_optional,assign).addAll(prods).add(semicolon).create();
  }
  
  public ProductionIdAndVersionDefAST production_id(TokenAST<?> lbracket,
      TokenAST<String> id,VersionVarAST production_version_optional,
      TokenAST<?> rbracket) {
    return factory.createProductionIdAndVersionDef(id,production_version_optional).
      addAll(lbracket,id,production_version_optional,rbracket).create();
  }
  
  public VersionVarAST production_version(TokenAST<?> colon,TokenAST<String> id) {
    return factory.createVersionVar(id).addAll(colon,id).create();
  }
  
  public SimpleNodeAST<?> priorities_def(TokenAST<?> prioritiesdecl,
      TokenAST<?> colon,List<PriorityDefAST> priority_list) {
    return factory.createSimpleNode(Kind.NO_KIND,null).
      addAll(prioritiesdecl,colon).addAll(priority_list).create();
  }
  
  public PriorityDefAST priority_def(TokenAST<String> id,TokenAST<?> assign,
      TokenAST<Double> number,TokenAST<String> assoc) {
    return factory.createPriorityDef(id,number.getValue(),assoc.getValue()).
      addAll(id,assign,number,assoc).create();
  }
  
  public SimpleNodeAST<String> regex_doublequote(TokenAST<?> doublequote,
      TokenAST<String> regexdoublequote,TokenAST<?> doublequote2) {
    return factory.createSimpleNode(Kind.NO_KIND,regexdoublequote.getValue()).
      addAll(doublequote,regexdoublequote,doublequote2).create();
  }
  
  public SimpleNodeAST<String> regex_quote(TokenAST<?> quote,
      TokenAST<String> regexquote,TokenAST<?> quote2) {
    return factory.createSimpleNode(Kind.NO_KIND,regexquote.getValue()).
      addAll(quote,regexquote,quote2).create();
  }
  
  public SimpleNodeAST<String> regex_terminal_decl(TokenAST<?> assign,
      SimpleNodeAST<String> regex) {
    return factory.createSimpleNode(Kind.NO_KIND,regex.getValue()).
      addAll(assign,regex).create();
  }
  
  public PriorityVarAST priority_decl_def(TokenAST<?> prioritydecl,
      TokenAST<?> assign,TokenAST<String> id) {
    return factory.createPriorityVar(id).
      addAll(prioritydecl,assign,id).create();
  }
  
  public VariableVarAST separator_non_terminal(TokenAST<?> slash,
      TokenAST<String> id) {
    return factory.createUnquotedIdVar(id).
      addAll(slash,id).create();
    
  }
  public VariableVarAST separator_terminal(TokenAST<?> slash,TokenAST<?> quote,
      TokenAST<String> id,TokenAST<?> quote2) {
    return factory.createQuotedIdVar(id).
      addAll(slash,quote,id,quote2).create();
  }
  public StartNonTerminalSetDefAST start_non_terminals_def(TokenAST<?> startsdecl,
      TokenAST<?> colon,List<UnquotedIdVarAST> starts_list) {
    return factory.createStartNonTerminalSetDef(starts_list).
      addAll(startsdecl,colon).addAll(starts_list).create();
  }
  
  public TypeVarAST type_def(TokenAST<?> colon,TokenAST<String> qualifiedid) {
    return factory.createTypeVar(qualifiedid).
      addAll(colon,qualifiedid).create();
  }
  public NodeAST var_group(TokenAST<?> lpar,
      List<NodeAST> vargroup,TokenAST<?> rpar) {
    return factory.createEnhancedVariable(Enhancement.GROUP,null,null,vargroup).
      add(rpar).addAll(vargroup).add(lpar).create();
  }
  public NodeAST var_variable(VariableVarAST variable) {
    return variable;
  }
  public NodeAST var_nonterminal_plus(TokenAST<String> id,
      VariableVarAST separator_optional,TokenAST<?> plus) {
    UnquotedIdVarAST node=factory.createUnquotedIdVar(id).add(id).create();
    return factory.createEnhancedVariable(Enhancement.PLUS,node,separator_optional,null).
      addAll(node,separator_optional,plus).create();
  }
  public NodeAST var_nonterminal_star(TokenAST<String> id,
      VariableVarAST separator_optional,TokenAST<?> star) {
    VariableVarAST node=factory.createUnquotedIdVar(id).add(id).create();
    return factory.createEnhancedVariable(Enhancement.STAR,node,separator_optional,null).
      addAll(node,separator_optional,star).create();
  }
  public VariableVarAST var_nonterminal(TokenAST<String> id, TokenAST<?> optional) {
    UnquotedIdVarAST node=factory.createUnquotedIdVar(id).add(id).create();
    if (optional==null)
      return node;
    return factory.createEnhancedVariable(Enhancement.OPTIONAL,node,null,null).
      addAll(node,optional).create();
  }
  public VariableVarAST var_terminal(TokenAST<?> quote,TokenAST<String> id,
      TokenAST<?> quote2,TokenAST<?> optional) {
    QuotedIdVarAST node=factory.createQuotedIdVar(id).
      addAll(quote,id,quote2).create();
    if (optional==null)
      return node;
    return factory.createEnhancedVariable(Enhancement.OPTIONAL,node,null,null).
      addAll(node,optional).create();
  }
  public NodeAST var_terminal_plus(TokenAST<?> quote,
      TokenAST<String> id,TokenAST<?> quote2,
      VariableVarAST separator_optional,TokenAST<?> plus) {
    QuotedIdVarAST node=factory.createQuotedIdVar(id).addAll(quote,id,quote2).create();
    return factory.createEnhancedVariable(Enhancement.PLUS,node,separator_optional,null).
      addAll(node,separator_optional,plus).create();
  }
  public NodeAST var_terminal_star(TokenAST<?> quote,
      TokenAST<String> id,TokenAST<?> quote2,
      VariableVarAST separator_optional,TokenAST<?> star) {
    QuotedIdVarAST node=factory.createQuotedIdVar(id).addAll(quote,id,quote2).create();
    return factory.createEnhancedVariable(Enhancement.STAR,node,separator_optional,null).
      addAll(node,separator_optional,star).create();
  }
  
  public SimpleNodeAST<?> versions_def(TokenAST<?> versionsdecl,
      TokenAST<?> colon,List<VersionDefAST> version_list) {
    return factory.createSimpleNode(Kind.NO_KIND,null).
      addAll(versionsdecl,colon).addAll(version_list).create();
  }
  
  public SimpleNodeAST<?> imports_def(TokenAST<?> importsdecl,
      TokenAST<?> colon,List<ImportDefAST> import_list) {
    return factory.createSimpleNode(Kind.NO_KIND,null).
      addAll(importsdecl,colon).addAll(import_list).create();
  }
  public ImportDefAST import_def(TokenAST<String> qualifiedid) {
    return factory.createImportDef(qualifiedid).addAll(qualifiedid).create();
  }

  public AliasDefAST alias_def(TokenAST<?> lpar, TokenAST<String> quoted_name,
      TokenAST<?> rpar) {
    return factory.createAliasDef(quoted_name).addAll(lpar,quoted_name,rpar).create();
  }

  public NodeAST lexem_terminal(TokenAST<String> id,
    AliasDefAST alias_optional,TypeVarAST type_optional,
    SimpleNodeAST<String> regex_optional,
    PriorityVarAST priority_optional) {
    
    RuleDefAST rule;
    if (regex_optional!=null) {
      rule=factory.createRuleDef(id,regex_optional.getValue()).
        add(regex_optional).create();
    } else {
      rule=null;
    }
    return factory.createTerminalDef(TerminalKind.TOKEN,id,alias_optional,type_optional,rule,priority_optional).
      addAll(id,alias_optional,type_optional,rule,priority_optional).create();
  }
}
