/*
 * Created on 6 juil. 2005
 *
 */
package fr.umlv.tatoo.cc.common.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.RuntimeInstance;

import fr.umlv.tatoo.cc.common.main.Alias;
import fr.umlv.tatoo.cc.common.main.AliasPrototype;
import fr.umlv.tatoo.cc.common.velocity.VelocityLogger;

/** Generate enums that represents at runtimn
 *  productions, terminals, non-terminals, rules and parser tables
 *  using respectively an array of
 *  {@link fr.umlv.tatoo.cc.parser.grammar.ProductionDecl ProductionDecl},
 *  {@link fr.umlv.tatoo.cc.parser.grammar.TerminalDecl TerminalDecl},
 *  {@link fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl NonTerminalDecl},
 *  {@link fr.umlv.tatoo.cc.lexer.lexer.RuleDecl RuleDecl},
 *  {@link fr.umlv.tatoo.cc.parser.table.ParserTableDecl ParserTableDecl}.
 *  
 *  
 * @author remi
 *
 */
public class Generator {

  public Generator(File sourceDir, Class<?> clazz) throws GeneratorException {
    this.sourceDir=sourceDir;
    try {
      ri = new RuntimeInstance();
      ri.setProperty("runtime.log.logsystem", new VelocityLogger());
      ri.setProperty("file.resource.loader.class", "fr.umlv.tatoo.cc.common.velocity.ClassResourceLoader");
      ri.setProperty("file.resource.loader.resourceClass", clazz);
      // ri.setProperty("velocimacro.library", "macros.vm");
      ri.init();
    } catch(Exception e) {
      throw new GeneratorException(e);
    }
  }
  
  
  public void generate(Map<String,?> root,Map<AliasPrototype,? extends Alias> aliasMap,AliasPrototype prototype) throws GeneratorException {
    Alias alias=aliasMap.get(prototype);
    
    //System.out.println("generate "+prototype.getDefaultTypeName()+" "+alias.generate());
    
    if (alias.generate())
      generate(root,prototype.getDefaultTypeName(),alias.getType());
  }
  
  /** Generates the source code of a type using a template.
   *  
   * @param map a map containg properties.
   * @param templateName the name of the velocity template.
   * @param type the generated type.
   * @throws GeneratorException 
   */
  protected void generate(Map<String,?> map,String templateName,Type type) throws GeneratorException {
    VelocityContext root = new VelocityContext(map);
    String packageName=type.getPackageName();
    String simpleName=type.getSimpleName();
    
    String pathName=packageName.replace('.',File.separatorChar);
    File path=new File(sourceDir,pathName);
    File file=new File(path,simpleName+".java");
    
    path.mkdirs();
    
    try {
      Writer out = new BufferedWriter(new FileWriter(file));
      try {
        Template template = ri.getTemplate(templateName+".vm", "UTF-8");
        template.merge(root, out);
      } finally {
        out.close();
      }
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new GeneratorException(e);
    }
  }

  private final File sourceDir;
  private final RuntimeInstance ri;
}
