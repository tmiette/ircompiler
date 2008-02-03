/*
 * Created on 14 janv. 2006
 *
 */
package fr.umlv.tatoo.samples.editor;

import javax.swing.text.AbstractDocument.Content;

import fr.umlv.tatoo.samples.editor.parser.NonTerminalEnum;
import fr.umlv.tatoo.samples.editor.parser.ProductionEnum;
import fr.umlv.tatoo.samples.editor.parser.VersionEnum;
import fr.umlv.tatoo.samples.editor.tools.Analyzer;
import fr.umlv.tatoo.samples.editor.tools.GrammarEvaluator;
import fr.umlv.tatoo.samples.editor.tools.TerminalEvaluator;

public class Compiler {
  @SuppressWarnings("unchecked")
  public Compiler(Content content) {
    grammarEvaluator=Proxies.createParserVisitor(ProductionEnum.class,GrammarEvaluator.class);
    
    ContentTokenBuffer buffer=new ContentTokenBuffer(content);
    TerminalEvaluator<CharSequence> attributeEvaluator=
      Proxies.createTerminalAttributeEvaluator(buffer,TerminalEvaluator.class);
    
    Analyzer.run(buffer,attributeEvaluator,grammarEvaluator,NonTerminalEnum.start,VersionEnum.DEFAULT);
  }
  
  public GrammarElement getRoot() {
    return Proxies.getRoot(grammarEvaluator);
  }
  
  private GrammarEvaluator grammarEvaluator;
}
