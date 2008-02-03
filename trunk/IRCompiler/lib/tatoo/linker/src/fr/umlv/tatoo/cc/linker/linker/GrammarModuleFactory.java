package fr.umlv.tatoo.cc.linker.linker;

import java.util.Collection;
import java.util.HashMap;


public class GrammarModuleFactory {
  public GrammarModuleFactory(GrammarResolver resolver) {
    this.resolver=resolver;
  }
  
  public GrammarModule createModule(String name,String reference) {
    GrammarModule module=new GrammarModule(name,resolver.getModel(reference));
    moduleMap.put(name,module);
    return module;
  }
  
  public GrammarModule getModule(String name) {
    GrammarModule module=moduleMap.get(name);
    if (module==null)
      throw new IllegalStateException("unknown grammar module "+name);
    return module;
  }
  
  public Collection<? extends GrammarModule> getAllModules() {
    return moduleMap.values();
  }
  
  public void setRoot(GrammarModule root) {
    this.root=root;  
  }
  
  public GrammarModule getRoot() {
    return root;
  }
  
  private GrammarModule root;
  private final GrammarResolver resolver;
  private final HashMap<String,GrammarModule> moduleMap=
    new HashMap<String,GrammarModule>();
}