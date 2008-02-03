package fr.umlv.tatoo.cc.linker.linker;

import java.util.HashSet;

import fr.umlv.tatoo.cc.common.log.Info;
import fr.umlv.tatoo.cc.linker.linker.GrammarModule.LinkEntry;


public class GrammarModuleChecker {
  private GrammarModuleChecker() {
    // do not instantiate
  }
  public static void check(GrammarModuleFactory factory) {
    HashSet<GrammarModule> moduleSet=new HashSet<GrammarModule>(
      factory.getAllModules());
    traverse(factory.getRoot(), moduleSet);
    
    for(GrammarModule module:moduleSet)
      Info.error("module %s is not accessible from root module",module.getName()).report();
  }
  private static void traverse(GrammarModule root,
      HashSet<GrammarModule> moduleSet) {
    
    if (!moduleSet.remove(root))
      return;
    for(LinkEntry entry:root.getLinkMap().values()) {
      traverse(entry.getGrammarModule(),moduleSet);
    }
  }
}
