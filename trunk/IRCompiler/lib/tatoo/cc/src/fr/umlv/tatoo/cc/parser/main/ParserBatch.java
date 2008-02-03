package fr.umlv.tatoo.cc.parser.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fr.umlv.tatoo.cc.common.log.ReporterFactory;
import fr.umlv.tatoo.cc.common.main.Unit;
import fr.umlv.tatoo.cc.parser.generator.ParserExtension;
import fr.umlv.tatoo.cc.parser.grammar.GrammarFactory;
import fr.umlv.tatoo.cc.parser.parser.ActionDeclFactory;
import fr.umlv.tatoo.cc.parser.table.LogInfoConflictDiagnosticReporter;
import fr.umlv.tatoo.cc.parser.table.ParserTableDecl;
import fr.umlv.tatoo.cc.parser.xml.ParserXMLDigester;

public class ParserBatch {
  public ParserXMLDigester digest(List<? extends File> grammarFiles,boolean validating,GrammarFactory factory) throws IOException, ParserConfigurationException, SAXException {
    ParserXMLDigester digester=new ParserXMLDigester(factory);
    for (File file : grammarFiles)
      digester.parse(file,validating);
    return digester;
  }
  
  public void execute(ParserBean bean) throws IOException, ParserConfigurationException, SAXException {
    if (bean.getLogLevel()!=null)
      ReporterFactory.setLogLevel(bean.getLogLevel());
    
    if (bean.isRegisterDefaultExtensions())
      bean.getExtensionBus().register(new ParserExtension());
    
    List<? extends File> parserFiles=bean.getInputFiles(Unit.parser);
    GrammarFactory factory=new GrammarFactory();
    ParserXMLDigester digester = digest(parserFiles,bean.isValidating(),factory);
    bean.getExtensionBus().publish(ParserDataKeys.grammarRepository,factory);
    
    ReporterFactory.setAndSealDefaultInfo(ReporterFactory.getDefaultInfo().file(parserFiles));
    ParserTableDecl table=digester.createParserTableDecl(new ActionDeclFactory(),
      bean.getParserType().getMethod(),
      bean.getConflictResolverType().getConflictResolver(),
      new LogInfoConflictDiagnosticReporter(null),
      bean.getLogFile());
    bean.getExtensionBus().publish(ParserDataKeys.parserTable,table);
  }
}
