package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.List;

abstract class AbstractNodeAST extends AbstractTreeAST implements NodeAST {
  private final List<TreeAST> treeList;
  private List<NodeAST> nodeList; // may be always null if nodeList() is overridden
  
  protected AbstractNodeAST(AST ast,List<TreeAST> treeList) {
    super(ast);
    
    // set parent
    for(TreeAST tree:treeList)
      ((AbstractTreeAST)tree).setParent(this);
    
    this.treeList=treeList;
  }
  
  public boolean isToken() {
    return false;
  }
  
  public List<TreeAST> treeList() {
    return treeList;
  }
  
  @Override
  public String toString() {
    if (treeList.isEmpty())
      return "["+getKind()+']';
    StringBuilder builder=new StringBuilder();
    builder.append('[').append(getKind()).append(' ');
    for(TreeAST tree:treeList)
      builder.append(tree).append(',');
    builder.setLength(builder.length()-1);
    return builder.append(']').toString();
  }

  public abstract Kind getKind();
  
  /** Returns null by default.
   */
  public Binding getBinding() {
    return null;
  }
  
  public List<NodeAST> nodeList() {
    if (nodeList!=null)
      return nodeList;
    TreeListBuilder<NodeAST> nodeList=new TreeListBuilder<NodeAST>();
    for(TreeAST node:treeList()) {
      if (node instanceof NodeAST) {
        nodeList.add((NodeAST)node);
      }
    }
    return this.nodeList=nodeList.createList();
  }
}
