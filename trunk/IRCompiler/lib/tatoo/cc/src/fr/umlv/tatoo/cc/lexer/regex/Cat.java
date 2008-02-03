/*
 * Created on Jun 13, 2003
 *
 */
package fr.umlv.tatoo.cc.lexer.regex;

import java.util.*;


import fr.umlv.tatoo.cc.common.util.*;

/**
 * @author jcervell
 *
 */
public class Cat extends Node {
	
  private final Regex left,right;
  private final boolean nullable;
  private final Set<Leaf> firstPos=new HashSet<Leaf>();
  private final Set<Leaf> lastPos=new HashSet<Leaf>();
	
  public Cat(Regex left,Regex right) {
    this.left=left;
    this.right=right;
    nullable=left.nullable()&&right.nullable();
    firstPos.addAll(left.firstPos());
    if (left.nullable())
      firstPos.addAll(right.firstPos());
    lastPos.addAll(right.lastPos());
    if (right.nullable())
      lastPos.addAll(left.lastPos());
	  
  }
  
  @Override
  public Regex cloneRegex() {
    return new Cat(left.cloneRegex(),right.cloneRegex());
  }

  @Override
  protected void computeFollowPos(MultiMap<Leaf,Leaf> followPos) {
    for (Leaf leaf : left.lastPos()) {
      followPos.addSet(leaf,right.firstPos());
    }
    left.computeFollowPos(followPos);
    right.computeFollowPos(followPos);
  }

  @Override
  public boolean nullable() {
    return nullable;
  }

  @Override
  public Set<Leaf> firstPos() {
    return firstPos;
  }

  @Override
  public Set<Leaf> lastPos() {
    return lastPos;
  }
  
  @Override
  public String toString() {
    return "("+left+right+")";
  }

}
