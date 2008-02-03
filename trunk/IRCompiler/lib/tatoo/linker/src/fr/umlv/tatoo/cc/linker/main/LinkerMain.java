package fr.umlv.tatoo.cc.linker.main;

import java.io.IOException;
import java.util.List;

import fr.umlv.tatoo.cc.Tatoo;
import fr.umlv.tatoo.cc.common.main.AliasPrototype;
import fr.umlv.tatoo.cc.common.main.CommandLineParser;
import fr.umlv.tatoo.cc.common.main.GeneratorOption;
import fr.umlv.tatoo.cc.common.main.IllegalCommandLineArgumentException;
import fr.umlv.tatoo.cc.common.main.Unit;

public class LinkerMain {
  private static void usage(CommandLineParser<LinkerBean> clp) {
    System.err.println(clp.usage(
      "Usage: java fr.umlv.tatoo.cc.linker.main.LinkerMain [options] files\n"+
      "  form: LinkerMain [options] linker.ebnf\n"+
      "  "+Tatoo.version()+'\n'));
  }

  public static AliasPrototype[] linkerAliases() {
    return LinkerAliasPrototype.linkers();
  }
  
  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws IOException {
    AliasPrototype[] aliases=linkerAliases();
    
    CommandLineParser<LinkerBean> clp=
      new CommandLineParser<LinkerBean>("--",
        GeneratorOption.destination,
        GeneratorOption.packaze("linker"),
        GeneratorOption.validating,
        GeneratorOption.logLevel,
        GeneratorOption.extension,
        GeneratorOption.check,
        GeneratorOption.version,
        GeneratorOption.name(aliases),
        GeneratorOption.generate(aliases)
        );
    
    if (args.length==0) {
      usage(clp);
      return;
    }
    
    LinkerBean bean=new LinkerBean();
    bean.registerDefaults(aliases);
    
    List<? extends String> filenames;
    try {
      filenames=clp.parse(bean,args);
    } catch (IllegalCommandLineArgumentException e) {
      e.printStackTrace();
      usage(clp);
      return;
    }
    bean.addInputFilenames(Unit.linker,filenames);
    bean.finish();
    
    LinkerBatch linkerBatch = new LinkerBatch();
    linkerBatch.execute(bean);
  }
}
