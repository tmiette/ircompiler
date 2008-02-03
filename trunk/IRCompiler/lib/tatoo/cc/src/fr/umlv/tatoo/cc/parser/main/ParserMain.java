package fr.umlv.tatoo.cc.parser.main;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fr.umlv.tatoo.cc.Tatoo;
import fr.umlv.tatoo.cc.common.main.AliasPrototype;
import fr.umlv.tatoo.cc.common.main.CommandLineParser;
import fr.umlv.tatoo.cc.common.main.GeneratorOption;
import fr.umlv.tatoo.cc.common.main.IllegalCommandLineArgumentException;
import fr.umlv.tatoo.cc.common.main.Unit;

public class ParserMain {

  private static void usage(CommandLineParser<ParserBean> clp) {
    System.err.println(clp.usage(
      "Usage: java fr.umlv.tatoo.cc.parser.main.ParserMain (options) grammar.xml\n"+
      "  "+Tatoo.version()+'\n'));
  }

  
  /**
   * @param args
   * @throws SAXException 
   * @throws ParserConfigurationException 
   * @throws IOException 
   */
  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
    AliasPrototype[] aliases=ParserAliasPrototype.parsers();
    
    CommandLineParser<ParserBean> clp=
      new CommandLineParser<ParserBean>("--",
        GeneratorOption.destination,
        GeneratorOption.packaze("parser"),
        GeneratorOption.validating,
        GeneratorOption.logLevel,
        GeneratorOption.extension,
        GeneratorOption.check,
        GeneratorOption.version,
        ParserOption.logFile,
        ParserOption.parserType,
        //ParserOption.conflictResolver,
        GeneratorOption.name(aliases),
        GeneratorOption.generate(aliases)
        );
    
    if (args.length==0) {
      usage(clp);
      return;
    }
    
    ParserBean bean=new ParserBean();
    bean.registerDefaults(aliases);
    
    List<? extends String> filenames;
    try {
      filenames=clp.parse(bean,args);
    } catch (IllegalCommandLineArgumentException e) {
      e.printStackTrace();
      usage(clp);
      return;
    }
    bean.addInputFilenames(Unit.parser,filenames);
    bean.finish();
    
    ParserBatch batch = new ParserBatch();
    batch.execute(bean);
  }

}
