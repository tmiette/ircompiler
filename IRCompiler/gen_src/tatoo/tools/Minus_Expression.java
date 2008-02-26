package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.Node.NoValue;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;

public class Minus_Expression extends FlatNode<Node,Node,NoValue> implements Serializable, IExpression {
  Minus_Expression() {
    // used by XML serialization
  }
  public Minus_Expression(IExpression expression, IExpression expression2) {
    setExpression(expression);
    setExpression2(expression2);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "minus-expression";
  }
  
  public IExpression getExpression() {
    return expression;
  }
  public void setExpression(IExpression expression) {
    this.expression= reparent(this.expression,expression);
  }
  public IExpression getExpression2() {
    return expression2;
  }
  public void setExpression2(IExpression expression2) {
    this.expression2= reparent(this.expression2,expression2);
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
    return 2;
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
        return this.expression2;
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
        old=this.expression2;
        setExpression2( (IExpression) node);
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
  private IExpression expression2;

  private static final long serialVersionUID=-5618812274221938455L;
}

