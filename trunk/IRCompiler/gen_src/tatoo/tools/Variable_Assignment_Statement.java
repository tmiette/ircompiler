package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.Node.NoValue;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;
import tatoo.tools.Variable_Assignment;

public class Variable_Assignment_Statement extends FlatNode<Variable_Assignment,Node,NoValue> implements Serializable, IStatement {
  Variable_Assignment_Statement() {
    // used by XML serialization
  }
  public Variable_Assignment_Statement(Variable_Assignment variable_assignment) {
    setVariable_assignment(variable_assignment);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "variable-assignment-statement";
  }
  
  public Variable_Assignment getVariable_assignment() {
    return variable_assignment;
  }
  public void setVariable_assignment(Variable_Assignment variable_assignment) {
    this.variable_assignment= reparent(this.variable_assignment,variable_assignment);
  }
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<Variable_Assignment> getElementType() {
    return Variable_Assignment.class;
  }
  /** 
   * {@inheritDoc}
   */
  @Override
  protected int nodeCount() {
    return 1;
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Variable_Assignment nodeAt(int index) {
    switch(index) {
          case 0:
        return this.variable_assignment;
        }
    return super.nodeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Variable_Assignment nodeAt(int index, Variable_Assignment node) {
    Variable_Assignment old;
    switch(index) {
            case 0:
        old=this.variable_assignment;
        setVariable_assignment( node);
        return old;
          }
    return super.nodeAt(index,node);
  }
  
  /** 
   * {@inheritDoc}
   */
  public <_R,_P,_D,_E extends Throwable> _R accept(NodeVisitor<_R,_P,_D,_E> visitor,_P param) throws _E {
    return accept((Visitor<_R,_P,_D,_E>)visitor,param);
  }
  
  public <_R,_P,_D,_E extends Throwable> _R accept(Visitor<_R,_P,_D,_E> visitor,_P param) throws _E {
    return visitor.visit(this,param);
  }
  
  private Variable_Assignment variable_assignment;

  private static final long serialVersionUID=2105122903201337354L;
}

