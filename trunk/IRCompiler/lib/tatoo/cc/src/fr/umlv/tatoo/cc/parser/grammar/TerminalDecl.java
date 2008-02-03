package fr.umlv.tatoo.cc.parser.grammar;

import fr.umlv.tatoo.cc.common.generator.AbstractObjectId;
import fr.umlv.tatoo.cc.parser.grammar.Priority;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;

public class TerminalDecl extends AbstractObjectId implements VariableDecl,PriorityOwner {
  public TerminalDecl(String id,Priority priority,boolean branching) {
    super(id);
    this.priority = priority;
    this.branching=branching;
  }

  public Priority getPriority() {
    return priority;
  }
  public boolean isBranching() {
    return branching;
  }
  public boolean isTerminal() {
    return true;
  }
  public void setAlias(String alias) {
    assert alias==null;
    this.alias = alias;
  }
  public String getAlias() {
    return alias;
  }
  
  @Override
  public String toString() {
    return '\''+getId()+'\'';
  }
  
  private final boolean branching;
  private final Priority priority;
  private String alias;
}
