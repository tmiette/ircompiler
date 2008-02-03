/*
 * Created on 16 juil. 2005
 *
 */
package fr.umlv.tatoo.cc.tools.generator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.velocity.VelocityContext;

import fr.umlv.tatoo.cc.common.generator.Generator;
import fr.umlv.tatoo.cc.common.generator.GeneratorException;
import fr.umlv.tatoo.cc.common.generator.Type;
import fr.umlv.tatoo.cc.common.main.Alias;
import fr.umlv.tatoo.cc.common.main.AliasPrototype;
import fr.umlv.tatoo.cc.common.main.GeneratorBean;
import fr.umlv.tatoo.cc.lexer.lexer.RuleDecl;
import fr.umlv.tatoo.cc.lexer.lexer.RuleFactory;
import fr.umlv.tatoo.cc.lexer.main.LexerAliasPrototype;
import fr.umlv.tatoo.cc.parser.grammar.EBNFSupport;
import fr.umlv.tatoo.cc.parser.grammar.GrammarRepository;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.ProductionDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.VariableDecl;
import fr.umlv.tatoo.cc.parser.grammar.VersionDecl;
import fr.umlv.tatoo.cc.parser.main.ParserAliasPrototype;
import fr.umlv.tatoo.cc.tools.main.ToolsAliasPrototype;
import fr.umlv.tatoo.cc.tools.tools.ToolsFactory;

public class ToolsGenerator extends Generator {
  public ToolsGenerator(File sourceDir) throws GeneratorException {
    super(sourceDir,fr.umlv.tatoo.cc.tools.generator.ToolsGenerator.class);
  }

  public void generate(GeneratorBean bean,RuleFactory ruleFactory,
    GrammarRepository grammarItemsRepository,EBNFSupport ebnfSupport,
    ToolsFactory toolsFactory,Set<? extends ProductionDecl> astSet) throws GeneratorException {
    
    
    Set<? extends NonTerminalDecl> starts = grammarItemsRepository.getStartNonTerminalSet();
    Map<TerminalDecl,Type> terminalTypeMap=toolsFactory.getTerminalTypeMap();
    TreeSet<Type> terminalImports = new TreeSet<Type>();
    for(Type type:terminalTypeMap.values()) {
      type.addImportsTo(terminalImports);
    }
    
    Map<VariableDecl, Type> variableTypeMap=toolsFactory.getVariableTypeMap();
    TreeSet<Type> variableImports = new TreeSet<Type>(terminalImports);    
    for(Type type:variableTypeMap.values()) {
      type.addImportsTo(variableImports);
    }
    HashMap<String, Object> root = new HashMap<String, Object>();
    Map<AliasPrototype,? extends Alias> aliasMap=bean.getAliasMap();
    root.put("ruleEnum", aliasMap.get(LexerAliasPrototype.rule).getType());
    root.put("lexerDataTable", aliasMap.get(LexerAliasPrototype.lexerDataTable).getType());
    root.put("terminalEnum", aliasMap.get(ParserAliasPrototype.terminal).getType());
    root.put("nonTerminalEnum", aliasMap.get(ParserAliasPrototype.nonTerminal).getType());
    root.put("productionEnum", aliasMap.get(ParserAliasPrototype.production).getType());
    root.put("versionEnum", aliasMap.get(ParserAliasPrototype.version).getType());
    
    root.put("terminals",grammarItemsRepository.getAllTerminals());
    root.put("nonTerminals",grammarItemsRepository.getAllNonTerminals());
    root.put("productions",grammarItemsRepository.getAllProductions());
    root.put("parserDataTable", aliasMap.get(ParserAliasPrototype.parserDataTable).getType());
    root.put("analyzer", aliasMap.get(ToolsAliasPrototype.analyzer).getType());
    root.put("analyzerBuilder", aliasMap.get(ToolsAliasPrototype.analyzerBuilder).getType());
    root.put("terminalRulesMap", aliasMap.get(ToolsAliasPrototype.terminalRulesMap).getType());
    root.put("terminalEvaluator", aliasMap.get(ToolsAliasPrototype.terminalEvaluator).getType());
    root.put("grammarEvaluator", aliasMap.get(ToolsAliasPrototype.grammarEvaluator).getType());
    root.put("toolsProcessor", aliasMap.get(ToolsAliasPrototype.toolsProcessor).getType());
    root.put("rules",ruleFactory.getAllRules());
    
    if (starts.isEmpty())
      throw new IllegalStateException("No starting non terminal");
    NonTerminalDecl defaultStart=starts.iterator().next();
    
    VersionDecl defaultVersion=grammarItemsRepository.getAllVersions().iterator().next();
    
    root.put("starts",starts);
    root.put("defaultStart",defaultStart);
    root.put("defaultVersion",defaultVersion);
    
    //System.out.println(ebnfSupport.getEBNFTypeMap());
    
    root.put("ebnfSupport",ebnfSupport.getEBNFTypeMap());
    root.put("astSet",astSet);
    
    root.put("upperCaser", UpperCaser.getInstance());
    
    root.put("params", new Params(variableTypeMap));
    Map<TerminalDecl, ? extends Set<? extends RuleDecl>> terminalRulesMap=
      toolsFactory.getTerminalRulesMap();
    root.put("terminalRules", terminalRulesMap);
    root.put("variableTypeMap", variableTypeMap);
    root.put("terminalTypeMap", terminalTypeMap);
    root.put("ruleTypeMap",toolsFactory.getRuleTypeMap());
    root.put("ruleInfoMap",toolsFactory.getRuleInfoMap());
    
    //System.err.println("rule type map "+toolsFactory.getRuleTypeMap());
    
    root.put("unconditionalRules",toolsFactory.getUnconditionalRuleSet());
    root.put("terminalImports",terminalImports);
    root.put("variableImports",variableImports);
    
    generate(root,aliasMap,ToolsAliasPrototype.analyzer);
    generate(root,aliasMap,ToolsAliasPrototype.analyzerBuilder);
    generate(root,aliasMap,ToolsAliasPrototype.terminalRulesMap);
    generate(root,aliasMap,ToolsAliasPrototype.terminalEvaluator);
    generate(root,aliasMap,ToolsAliasPrototype.grammarEvaluator);
    generate(root,aliasMap,ToolsAliasPrototype.toolsProcessor);
  }
}
