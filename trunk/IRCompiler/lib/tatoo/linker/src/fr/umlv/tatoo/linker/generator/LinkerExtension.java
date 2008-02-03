package fr.umlv.tatoo.linker.generator;

import fr.umlv.tatoo.cc.common.extension.Extension;
import fr.umlv.tatoo.cc.common.extension.ExtensionBus;
import fr.umlv.tatoo.cc.common.extension.ExtensionBus.Context;
import fr.umlv.tatoo.cc.common.extension.ExtensionBus.Registry;
import fr.umlv.tatoo.cc.common.generator.GeneratorException;
import fr.umlv.tatoo.cc.common.main.CommonDataKeys;
import fr.umlv.tatoo.cc.common.main.GeneratorBean;
import fr.umlv.tatoo.cc.linker.linker.GrammarModuleChecker;
import fr.umlv.tatoo.cc.linker.linker.GrammarModuleFactory;
import fr.umlv.tatoo.cc.linker.main.LinkerDataKeys;

public class LinkerExtension implements Extension {
  public void register(Registry registry) {
    registry.register(CommonDataKeys.bean,LinkerDataKeys.moduleFactory);
  }

  public void execute(ExtensionBus bus,Context context) {
    GeneratorBean bean = context.getData(CommonDataKeys.bean);
    GrammarModuleFactory factory=context.getData(LinkerDataKeys.moduleFactory);
    
    GrammarModuleChecker.check(factory);
    
    try {
      new LinkerGenerator(bean.getDestination()).generate(bean,factory);
    } catch (GeneratorException e) {
      throw new IllegalStateException(e);
    }
  }
}
