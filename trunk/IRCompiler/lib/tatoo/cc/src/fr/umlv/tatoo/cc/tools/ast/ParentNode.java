/*
 * Created on 24 nov. 06
 *
 */
package fr.umlv.tatoo.cc.tools.ast;

import fr.umlv.tatoo.cc.common.generator.Type;

public interface ParentNode {
  public Type getType();
  public Type getElementSuperType();
  public Type getParentSuperType();
}
