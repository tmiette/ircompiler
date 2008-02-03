/*
 * Created on 4 mars 2006
 *
 */
package fr.umlv.tatoo.cc.tools.ast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.umlv.tatoo.cc.common.generator.Type;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;

public class NonTerminalNode extends ElementNode implements ParentNode {
  NonTerminalNode(String packageName,NonTerminalDecl nonTerminal,boolean isStart) {
    super(packageName,"I",nonTerminal);
    this.isStart=isStart;
  }
  
  public boolean isStart() {
    return isStart;
  }
  
  public String getEnumName() {
    return getId().replace('-','_');
  }
  
  public Type getElementSuperType() {
    return processElementSuperType(impls);
  }
  
  @Override
  void serialUIDAsStream(DataOutputStream out) throws IOException {
    out.writeUTF(getId());
  }
  
  void addNodeImpl(FlatNode impl) {
    impls.add(impl);
  }
  
  private final boolean isStart;
  private final ArrayList<FlatNode> impls=
    new ArrayList<FlatNode>();
  
  private static Type processElementSuperType(List<FlatNode> nodes) {
    if (nodes.isEmpty())
      // oups, malformed grammar
      return null;
    
    Type type=null;
    for(int i=0;i<nodes.size();i++) {
      FlatNode node=nodes.get(i);
      if (node.getElements().isEmpty())
        continue;
      
      Type elementType=node.getElementSuperType();
      if (type==null) {
        if (elementType==null)
          return null;
        type=elementType;
      }
      else
        if (type!=elementType)
          return null;
    }
    return type;
  }
}