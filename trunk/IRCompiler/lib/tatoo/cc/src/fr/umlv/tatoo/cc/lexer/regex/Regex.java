/*
 * Created on 8 juin 2003
 */
package fr.umlv.tatoo.cc.lexer.regex;

import java.util.*;


import fr.umlv.tatoo.cc.common.util.*;

/**
 * @author julien
 *
 */
public abstract class Regex {

  protected abstract void computeFollowPos(MultiMap<Leaf,Leaf> followPos);

  public abstract boolean nullable();
  public abstract Set<Leaf> firstPos();
    
  public abstract Set<Leaf> lastPos();
  public abstract Regex cloneRegex();

}
