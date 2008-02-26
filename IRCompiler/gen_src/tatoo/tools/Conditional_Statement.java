package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.Node.NoValue;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;

public class Conditional_Statement extends FlatNode<Node,Node,NoValue> implements Serializable, IStatement {
  Conditional_Statement() {
    // used by XML serialization
  }
  public Conditional_Statement(IExpression expression, Multiple_Statement multiple_statement, IElse_Statement else_statement) {
    setExpression(expression);
    setMultiple_statement(multiple_statement);
    setElse_statement(else_statement);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "conditional-statement";
  }
  
  public IExpression getExpression() {
    return expression;
  }
  public void setExpression(IExpression expression) {
    this.expression= reparent(this.expression,expression);
  }
  public Multiple_Statement getMultiple_statement() {
    return multiple_statement;
  }
  public void setMultiple_statement(Multiple_Statement multiple_statement) {
    this.multiple_statement= reparent(this.multiple_statement,multiple_statement);
  }
  public IElse_Statement getElse_statement() {
    return else_statement;
  }
  public void setElse_statement(IElse_Statement else_statement) {
    this.else_statement= reparent(this.else_statement,else_statement);
  }
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<Node> getElementType() {
    return Node.class;
  }
  /** 
   * {@inheritDoc}
   */
  @Override
  protected int nodeCount() {
    return 3;
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Node nodeAt(int index) {
    switch(index) {
          case 0:
        return this.expression;
          case 1:
        return this.multiple_statement;
          case 2:
        return this.else_statement;
        }
    return super.nodeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Node nodeAt(int index, Node node) {
    Node old;
    switch(index) {
            case 0:
        old=this.expression;
        setExpression( (IExpression) node);
        return old;
            case 1:
        old=this.multiple_statement;
        setMultiple_statement( (Multiple_Statement) node);
        return old;
            case 2:
        old=this.else_statement;
        setElse_statement( (IElse_Statement) node);
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
  
  private IExpression expression;
  private Multiple_Statement multiple_statement;
  private IElse_Statement else_statement;

  private static final long serialVersionUID=4229420218841700665L;
}

