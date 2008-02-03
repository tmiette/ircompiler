/*
 * Created on Jun 13, 2003
 *
 */
package fr.umlv.tatoo.cc.lexer.regex;

import java.util.*;


import fr.umlv.tatoo.cc.common.util.*;
/**
 * @author jcervell
 * The same instance of this class can be shared within one Regex
 */
public class EpsilonLeaf extends Regex {
  
  @Override
  public boolean nullable() {
    return true;
  }
  
  /* no need to clone epsilon */
  @Override
  public Regex cloneRegex() {
    return this;
  }
  
  @Override
  public Set<Leaf> firstPos() {
    return Collections.emptySet();
  }
  
  @Override
  public Set<Leaf> lastPos() {
    return Collections.emptySet();
  }
  
  @Override
  public String toString() {
    return "\u03B5";
  }
  
  @Override
  protected void computeFollowPos(MultiMap<Leaf,Leaf> followPos)
  {/*nothing to be done*/}
  
}
