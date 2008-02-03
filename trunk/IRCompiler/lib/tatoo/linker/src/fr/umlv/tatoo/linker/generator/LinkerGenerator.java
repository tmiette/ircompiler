package fr.umlv.tatoo.linker.generator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import fr.umlv.tatoo.cc.common.generator.Generator;
import fr.umlv.tatoo.cc.common.generator.GeneratorException;
import fr.umlv.tatoo.cc.common.main.Alias;
import fr.umlv.tatoo.cc.common.main.AliasPrototype;
import fr.umlv.tatoo.cc.common.main.GeneratorBean;
import fr.umlv.tatoo.cc.linker.linker.GrammarModuleFactory;
import fr.umlv.tatoo.cc.linker.main.LinkerAliasPrototype;

public class LinkerGenerator extends Generator {
  public LinkerGenerator(File sourceDir) throws GeneratorException {
    super(sourceDir,fr.umlv.tatoo.cc.parser.generator.ParserGenerator.class);
  }
  public void generate(GeneratorBean bean,GrammarModuleFactory factory)
  throws GeneratorException {
    Map<AliasPrototype,? extends Alias> aliasMap=bean.getAliasMap();
    HashMap<String,Object> root = new HashMap<String,Object>();
    /*root.put("terminalEnum", aliasMap.get(ParserAliasPrototype.terminal).getType());
    root.put("nonTerminalEnum", aliasMap.get(ParserAliasPrototype.nonTerminal).getType());
    root.put("productionEnum", aliasMap.get(ParserAliasPrototype.production).getType());
    root.put("parserDataTable", aliasMap.get(ParserAliasPrototype.parserDataTable).getType());
    root.put("versionEnum", aliasMap.get(ParserAliasPrototype.version).getType());
    root.put("terminals", factory.getAllTerminals());
    root.put("nonTerminals", factory.getAllNonTerminals());
    root.put("productions", factory.getAllProductions());
    root.put("versions", factory.getAllVersions());
    */
    
    generate(root, aliasMap,LinkerAliasPrototype.linker);
  }
}
