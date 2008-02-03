package fr.umlv.tatoo.cc.lexer.main;

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

public class LexerMain {

  private static void usage(CommandLineParser<LexerBean> clp) {
    System.err.println(clp.usage(
      "Usage: java fr.umlv.tatoo.cc.lexer.main.ParserMain (options) grammar.xml\n"+
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
    AliasPrototype[] aliases=LexerAliasPrototype.lexers();
    
    CommandLineParser<LexerBean> clp=
      new CommandLineParser<LexerBean>("--",
        GeneratorOption.destination,
        GeneratorOption.packaze("lexer"),
        GeneratorOption.validating,
        GeneratorOption.logLevel,
        GeneratorOption.extension,
        GeneratorOption.check,
        GeneratorOption.version,
        LexerOption.charset,
        //LexerOption.name,
        GeneratorOption.name(aliases),
        GeneratorOption.generate(aliases)
        );
    
    if (args.length==0) {
      usage(clp);
      return;
    }
    
    LexerBean bean=new LexerBean();
    bean.registerDefaults(aliases);
    
    List<? extends String> filenames;
    try {
      filenames=clp.parse(bean,args);
    } catch (IllegalCommandLineArgumentException e) {
      e.printStackTrace();
      usage(clp);
      return;
    }
    bean.addInputFilenames(Unit.lexer,filenames);
    bean.finish();
    LexerBatch batch = new LexerBatch();
    batch.execute(bean);
  }
}
