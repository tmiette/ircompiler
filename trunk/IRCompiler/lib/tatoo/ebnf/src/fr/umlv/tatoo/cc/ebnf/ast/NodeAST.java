package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.List;

public interface NodeAST extends TreeAST {
  public Kind getKind();
  public Binding getBinding();
  
  public List<TreeAST> treeList();
  public List<NodeAST> nodeList();
}