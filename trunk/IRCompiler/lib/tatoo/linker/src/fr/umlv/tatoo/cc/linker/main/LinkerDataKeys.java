package fr.umlv.tatoo.cc.linker.main;

import fr.umlv.tatoo.cc.common.extension.ExtensionBus.DataKey;
import fr.umlv.tatoo.cc.linker.linker.GrammarModuleFactory;


public class LinkerDataKeys {
  public static final DataKey<GrammarModuleFactory> moduleFactory=
    new DataKey<GrammarModuleFactory>();
}
