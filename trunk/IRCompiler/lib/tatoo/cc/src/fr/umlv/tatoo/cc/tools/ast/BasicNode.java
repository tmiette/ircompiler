/*
 * Created on 8 mars 2006
 *
 */
package fr.umlv.tatoo.cc.tools.ast;

import fr.umlv.tatoo.cc.common.generator.ObjectId;
import fr.umlv.tatoo.cc.common.generator.Type;

public abstract class BasicNode implements ASTNode {
  BasicNode(String packageName,String prefix,ObjectId objectId) {
    this.objectId=objectId;
    this.type=Type.createQualifiedType(packageName+'.'+prefix+processNodeName(objectId.getId()));
  }
  public Type getType() {
    return type;
  }
  
  //return the parameter name
  // Warning: called by ASTGenerator.vm
  public ObjectId getObjectId() {
    return objectId;
  }
  
  //return the parameter name
  // Warning: called by ASTEvaluator.vm, CompositeNode.vm, Visitor.vm
  public String getId() {
    return objectId.getId();
  }
  
  @Override
  public String toString() {
    return super.toString()+' '+getId();
  }
  
  private final Type type;
  private final ObjectId objectId;
  
  private static String processNodeName(String id) {
    String[] parts = id.split("-|_");
    StringBuilder builder = new StringBuilder();
    for (String part : parts) {
      if (part.length()==0)
        builder.append('_');
      else
        builder.append(ASTModel.capitalizeFirstLetter(part)).append('_');
    }
    builder.setLength(builder.length()-1);
    return builder.toString();
  }
}
