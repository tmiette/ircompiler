/*
 * Created on 6 juil. 2005
 */
package fr.umlv.tatoo.cc.lexer.lexer;

import fr.umlv.tatoo.cc.common.generator.AbstractObjectId;
import fr.umlv.tatoo.cc.lexer.regex.RegexIntervalTable;

public class RuleDecl extends AbstractObjectId  {
  public RuleDecl(String id,RegexIntervalTable main, RegexIntervalTable follow, boolean beginningOfLineRequired) {
    super(id);
    this.main = main;
    this.follow = follow;
    this.beginningOfLineRequired = beginningOfLineRequired;
  }
  
  public RegexIntervalTable getMainRegex() {
    return main;
  }

  public boolean isBeginningOfLineRequired() {
    return beginningOfLineRequired;
  }

  public RegexIntervalTable getFollowRegex() {
    return follow;
  }
  
  private final RegexIntervalTable main;
  private final RegexIntervalTable follow;
  private final boolean beginningOfLineRequired;
  
  @Override
  public String toString() {
    /*
    StringBuilder builder = new StringBuilder();
    builder.append(getId()).append(":\n");
    if (beginningOfLineRequired)
      builder.append("^\n");
    builder.append(main);
    if (follow!=null)
      builder.append("/\n").append(follow);
    return builder.toString();
    */
    return getId();
  }
}
