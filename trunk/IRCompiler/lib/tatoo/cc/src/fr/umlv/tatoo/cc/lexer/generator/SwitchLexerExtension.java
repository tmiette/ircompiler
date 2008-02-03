package fr.umlv.tatoo.cc.lexer.generator;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.velocity.VelocityContext;

import fr.umlv.tatoo.cc.common.extension.Extension;
import fr.umlv.tatoo.cc.common.extension.ExtensionBus;
import fr.umlv.tatoo.cc.common.extension.ExtensionBus.Context;
import fr.umlv.tatoo.cc.common.extension.ExtensionBus.Registry;
import fr.umlv.tatoo.cc.common.generator.Generator;
import fr.umlv.tatoo.cc.common.generator.GeneratorException;
import fr.umlv.tatoo.cc.common.main.Alias;
import fr.umlv.tatoo.cc.common.main.AliasPrototype;
import fr.umlv.tatoo.cc.common.main.CommonDataKeys;
import fr.umlv.tatoo.cc.common.main.GeneratorBean;
import fr.umlv.tatoo.cc.lexer.lexer.RuleDecl;
import fr.umlv.tatoo.cc.lexer.lexer.RuleFactory;
import fr.umlv.tatoo.cc.lexer.lexer.impl.SwitchRuleImpl;
import fr.umlv.tatoo.cc.lexer.main.LexerAliasPrototype;
import fr.umlv.tatoo.cc.lexer.main.LexerDataKeys;

public class SwitchLexerExtension implements Extension {

  public void register(Registry registry) {
    registry.register(CommonDataKeys.bean,LexerDataKeys.ruleFactory);
  }

  public void execute(ExtensionBus bus,Context context) {
    GeneratorBean bean = context.getData(CommonDataKeys.bean);
    RuleFactory ruleFactory = context.getData(LexerDataKeys.ruleFactory);
    File sourceDir = bean.getDestination();
    
    Map<AliasPrototype,? extends Alias> aliasMap = bean.getAliasMap();
    HashMap<String,Object> root = new HashMap<String,Object>();
    root.put("ruleEnum", aliasMap.get(LexerAliasPrototype.rule).getType());
    root.put("lexerSwitch", aliasMap.get(LexerAliasPrototype.lexerSwitch).getType());
    root.put("lexerDataTable", aliasMap.get(LexerAliasPrototype.lexerDataTable).getType());
    List<? extends RuleDecl> rules = ruleFactory.getAllRules();
    List<SwitchRuleImpl> switchRules = new LinkedList<SwitchRuleImpl>();
    for(RuleDecl rule : rules) {
       switchRules.add(new SwitchRuleImpl(rule));
    }
    root.put("rules", switchRules);
    
    try {
      Generator gen = new Generator(sourceDir,SwitchLexerExtension.class);
      gen.generate(root,aliasMap,LexerAliasPrototype.lexerSwitch);
    } catch (GeneratorException e) {
      throw new AssertionError(e);
    }
    
  }
}
