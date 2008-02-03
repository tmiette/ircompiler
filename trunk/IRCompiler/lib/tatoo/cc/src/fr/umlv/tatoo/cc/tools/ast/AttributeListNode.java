/*
 * Created on 4 mars 2006
 *
 */
package fr.umlv.tatoo.cc.tools.ast;

import fr.umlv.tatoo.cc.common.generator.Type;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;

public class AttributeListNode extends AttributeNode {
  AttributeListNode(String packageName,NonTerminalDecl nonTerminal,Type elementType) {
    super(packageName,"",nonTerminal);
    this.elementType=elementType;
  }
  
  @Override
  public Type getType() {
    return Type.createParametrizedType(listType,elementType);
  }
  
  private final Type elementType;
  private static Type listType=Type.createQualifiedType("java.util.List");
}
