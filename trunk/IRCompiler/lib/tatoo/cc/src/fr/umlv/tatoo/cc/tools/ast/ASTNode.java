/*
 * Created on 8 mars 2006
 *
 */
package fr.umlv.tatoo.cc.tools.ast;

import fr.umlv.tatoo.cc.common.generator.ObjectId;
import fr.umlv.tatoo.cc.common.generator.Type;

public interface ASTNode {
  public Type getType();
  public ObjectId getObjectId();
  public String getId();
}
