/*
 * Created on 13 juil. 2005
 */
package fr.umlv.tatoo.cc.lexer.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fr.umlv.tatoo.cc.common.log.ReporterFactory;
import fr.umlv.tatoo.cc.common.main.Unit;
import fr.umlv.tatoo.cc.lexer.charset.encoding.Encoding;
import fr.umlv.tatoo.cc.lexer.generator.LexerExtension;
import fr.umlv.tatoo.cc.lexer.lexer.RuleFactory;
import fr.umlv.tatoo.cc.lexer.xml.LexerXMLDigester;

public class LexerBatch {
  
  public LexerXMLDigester digest(List<? extends File> lexerFiles,
      boolean validating,RuleFactory factory,Encoding charset)
    throws IOException, ParserConfigurationException, SAXException {
    
    LexerXMLDigester digester=new LexerXMLDigester(factory,charset);
    for (File file : lexerFiles)
      digester.parse(file, validating);
    return digester;
  }
  
  public void execute(LexerBean bean)
    throws IOException, ParserConfigurationException, SAXException {
    
    if (bean.getLogLevel()!=null)
      ReporterFactory.setLogLevel(bean.getLogLevel());
    
    if (bean.isRegisterDefaultExtensions())
      bean.getExtensionBus().register(new LexerExtension());
    
    RuleFactory ruleFactory=new RuleFactory();
    
    Encoding charset = bean.getLexerType().getEncoding();
    digest(bean.getInputFiles(Unit.lexer),bean.isValidating(),ruleFactory,charset);

    bean.getExtensionBus().publish(LexerDataKeys.ruleFactory,ruleFactory);
  }
}
