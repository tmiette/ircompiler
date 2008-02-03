package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.List;

abstract class TreeBuilder<N extends NodeAST> {
  private final AnnotationComputer annotationComputer;
  private final TreeListBuilder<TreeAST> children=new TreeListBuilder<TreeAST>();
  TreeBuilder(AnnotationComputer annotationComputer) {
    this.annotationComputer=annotationComputer;
  }
  
  public N create() {
    List<TreeAST> list=children.createList();
    N node=createNode(list);
    
    if (children.isEmpty()) {
      annotationComputer.computeEmptyNodeAnnotation(node);
    } else {
      annotationComputer.computeNodeAnnotation(node,
          list.get(0),
          list.get(list.size()-1));  
    }
    
    return node;
  }
  
  protected abstract N createNode(List<TreeAST> children);
  
  public TreeBuilder<N> add(TreeAST tree) {
    children.add(tree);
    return this;
  }
  
  public TreeBuilder<N> addAll(TreeAST... trees) {
    children.addAll(trees);
    return this;
  }
  
  public TreeBuilder<N> addAll(List<? extends TreeAST> trees) {
    children.addAll(trees);
    return this;
  }
}