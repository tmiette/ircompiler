package fr.umlv.tatoo.cc.tools.ast;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import fr.umlv.tatoo.cc.common.generator.ObjectId;
import fr.umlv.tatoo.cc.common.generator.Type;

public abstract class ElementNode extends BasicNode {
  ElementNode(String packageName,String prefix,ObjectId objectId) {
    super(packageName,prefix,objectId);
    parentSuperType=processParentSuperType(parents);
  }
  
  public Type getParentSuperType() {
    return parentSuperType;
  }
  
  void addNodeParent(ParentNode parent) {
    parents.add(parent);
  }
  
  public long getSerialVersionUID() {
    if (serialHashUID==0) {
      serialHashUID=processSerialUID();
    }
    return serialHashUID;
  }
  
  private long processSerialUID() {
    ByteArrayOutputStream arrayOut = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(arrayOut);
    
    try {
      serialUIDAsStream(out);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("SHA");
    } catch (NoSuchAlgorithmException e) {
      throw new AssertionError(e);
    }
    
    long hash=0;
    byte[] bytes = md.digest(arrayOut.toByteArray());
    for (byte b:bytes)
      hash = (hash << 8) | (b & 0xFF);
    
    return hash;
  }
  
  abstract void serialUIDAsStream(DataOutputStream out) throws IOException;
  
  private long serialHashUID;
  private final Type parentSuperType;
  private final ArrayList<ParentNode> parents=
    new ArrayList<ParentNode>();
  
  private static Type processParentSuperType(List<ParentNode> nodes) {
    if (nodes.isEmpty())
      // oups, malformed grammar
      return null;
    
    ParentNode node=nodes.get(0); 
    for(int i=1;i<nodes.size();i++) {
      if (nodes.get(i)!=node)
        return null;
    }
    return node.getType();
  }
}