package fr.umlv.tatoo.cc.linker.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import fr.umlv.tatoo.cc.common.log.ReporterFactory;
import fr.umlv.tatoo.cc.common.main.Unit;
import fr.umlv.tatoo.cc.linker.LinkerParser;
import fr.umlv.tatoo.cc.linker.linker.EBNFGrammarResolver;
import fr.umlv.tatoo.cc.linker.linker.GrammarModuleFactory;
import fr.umlv.tatoo.cc.linker.linker.GrammarResolver;
import fr.umlv.tatoo.linker.generator.LinkerExtension;

public class LinkerBatch {
  public void parse(List<? extends File> linkerFiles,GrammarModuleFactory factory) throws IOException {
    LinkerParser parser=new LinkerParser(factory);
    for (File file : linkerFiles)
      parser.parse(file);
  }
  
  public void execute(LinkerBean bean) throws IOException {
    if (bean.getLogLevel()!=null)
      ReporterFactory.setLogLevel(bean.getLogLevel());
    
    if (bean.isRegisterDefaultExtensions())
      bean.getExtensionBus().register(new LinkerExtension());
    
    List<? extends File> linkerFiles=bean.getInputFiles(Unit.linker);
    GrammarResolver resolver=new EBNFGrammarResolver();
    GrammarModuleFactory factory=new GrammarModuleFactory(resolver);
    parse(linkerFiles,factory);
    bean.getExtensionBus().publish(LinkerDataKeys.moduleFactory,factory);
  }
}
