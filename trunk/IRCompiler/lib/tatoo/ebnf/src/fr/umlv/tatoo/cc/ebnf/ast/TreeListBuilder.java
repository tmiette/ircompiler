package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TreeListBuilder<T extends TreeAST> {
  private final ArrayList<T> children=new ArrayList<T>();
  public boolean isEmpty() {
    return children.isEmpty();
  }
  public TreeListBuilder<T> add(T tree) {
    // remove empty node
    if (tree!=null)
      children.add(tree);
    return this;
  }
  
  public TreeListBuilder<T> addAll(T... trees) {
    for(T tree:trees)
      // remove empty node
      if (tree!=null)
        children.add(tree);
    return this;
  }
  
  public TreeListBuilder<T> addAll(List<? extends T> trees) {
    for(T tree:trees)
      // remove empty node
      if (tree!=null)
        children.add(tree);
    return this;
  }
  
  public List<T> createList() {
    return Collections.unmodifiableList(children);   
  }
}
