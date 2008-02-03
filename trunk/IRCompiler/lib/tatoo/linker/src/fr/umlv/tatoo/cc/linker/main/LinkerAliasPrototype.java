package fr.umlv.tatoo.cc.linker.main;

import fr.umlv.tatoo.cc.common.main.AliasPrototype;
import fr.umlv.tatoo.cc.common.main.Unit;

public enum LinkerAliasPrototype implements AliasPrototype {
  linker("GrammarLinker");
  
  LinkerAliasPrototype(String defaultTypeName) {
    this.defaultTypeName=defaultTypeName;
  }
  
  public String getDefaultTypeName() {
    return defaultTypeName;
  }
  public Unit getUnit() {
    return Unit.linker;
  }
  
  private final String defaultTypeName;
  
  public static AliasPrototype[] linkers() {
    return LinkerAliasPrototype.values();
  }
}
