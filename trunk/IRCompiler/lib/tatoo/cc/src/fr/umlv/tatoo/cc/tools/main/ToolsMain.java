package fr.umlv.tatoo.cc.tools.main;

import java.io.File;
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

public class ToolsMain {

  private static void usage(CommandLineParser<ToolsBean> clp) {
    System.err.println(clp.usage(
      "Usage: java fr.umlv.tatoo.cc.tools.main.ToolMain (options) lexer.xml grammar.xml tool.xml\n"+
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
    AliasPrototype[] aliases=ToolsAliasPrototype.tools();
    
    CommandLineParser<ToolsBean> clp=
      new CommandLineParser<ToolsBean>("--",
        GeneratorOption.destination,
        GeneratorOption.packaze("lexer","parser","tools"),
        GeneratorOption.validating,
        GeneratorOption.logLevel,
        GeneratorOption.extension,
        GeneratorOption.check,
        GeneratorOption.version,
        ToolsOption.generateAST,
        GeneratorOption.name(aliases),
        GeneratorOption.generate(aliases)
        );
    
    if (args.length==0) {
      usage(clp);
      return;
    }
    
    ToolsBean bean=new ToolsBean();
    bean.registerDefaults(aliases);
    
    List<? extends String> list;
    try {
      list=clp.parse(bean,args);
    } catch (IllegalCommandLineArgumentException e) {
      e.printStackTrace();
      usage(clp);
      return;
    }
    
    bean.addInputFile(Unit.lexer,new File(list.get(0)));
    bean.addInputFile(Unit.parser,new File(list.get(1)));
    bean.addInputFile(Unit.tools,new File(list.get(2)));
    bean.finish();
    
    ToolsBatch toolBatch = new ToolsBatch();
    toolBatch.execute(bean);
  }
}
