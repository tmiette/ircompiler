/*
 * Created on 4 mars 2006
 *
 */
package fr.umlv.tatoo.cc.tools.ast;

import fr.umlv.tatoo.cc.common.generator.Type;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;

public class AttributeSingleNode extends AttributeNode {
  AttributeSingleNode(String packageName,TerminalDecl terminal,Type type) {
    super(packageName,"",terminal);
    this.type=type;
  }
  @Override
  public Type getType() {
    return type;
  }
  private final Type type;
}
