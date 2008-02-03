package fr.umlv.tatoo.cc.tools.ast;

import fr.umlv.tatoo.cc.common.generator.ObjectId;

public abstract class AttributeNode extends BasicNode {
  AttributeNode(String packageName,String prefix,ObjectId objectId) {
    super(packageName,prefix,objectId);
  }
}