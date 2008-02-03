/*
 * Created on 7 avr. 2006
 *
 */
package fr.umlv.tatoo.samples.ext.cc;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import fr.umlv.tatoo.cc.common.generator.Generator;
import fr.umlv.tatoo.cc.common.generator.GeneratorException;
import fr.umlv.tatoo.cc.common.generator.Type;
import fr.umlv.tatoo.cc.common.main.GeneratorBean;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.main.ParserAliasPrototype;

public class SampleGenerator extends Generator {

  protected SampleGenerator(File sourceDir) throws GeneratorException {
    super(sourceDir,SampleGenerator.class);
  }

  public void generate(GeneratorBean bean,Map<TerminalDecl,Type> terminalType) throws GeneratorException {
    HashMap<String,Object> root=new HashMap<String,Object>();
    Type type=Type.createQualifiedType(
      "fr.umlv.tatoo.samples.ext.tools.TypeInformationsImpl");
    root.put("currentType",type);
    
    root.put("terminalEnum",bean.getAliasMap().get(ParserAliasPrototype.terminal).getType());
    root.put("terminalType",terminalType);
    
    generate(root,type.getSimpleRawName(),type);
  }
}
