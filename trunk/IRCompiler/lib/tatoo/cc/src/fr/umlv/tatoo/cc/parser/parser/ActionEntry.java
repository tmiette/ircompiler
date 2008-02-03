/*
 * Created on 20 juil. 2005
 */
package fr.umlv.tatoo.cc.parser.parser;

import fr.umlv.tatoo.cc.parser.grammar.Priority;
import fr.umlv.tatoo.cc.parser.grammar.PriorityOwner;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;

public class ActionEntry<A extends ActionDecl> {
    private final A action;
    private final PriorityOwner priorityOwner;
    private final TerminalDecl terminal;
    
    public ActionEntry(A action, PriorityOwner priorityOwner,TerminalDecl terminal) {
      assert priorityOwner!=null:"null priority source for "+action;
      this.priorityOwner = priorityOwner;
      this.action = action;
      this.terminal=terminal;
    }
    
    public TerminalDecl getTerminal() {
      return terminal;
    }
    
    public A getAction() {
      return action;
    }
    
    public Priority getPriority() {
      return priorityOwner.getPriority();
    }
    
    @Override
    public String toString() {
      return action+" ("+priorityOwner+")";
    }
}
