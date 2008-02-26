package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.Node.NoValue;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;
import tatoo.tools.IExpression;

public class Not_Expression extends FlatNode<IExpression,Node,NoValue> implements Serializable, IExpression {
  Not_Expression() {
    // used by XML serialization
  }
  public Not_Expression(IExpression expression) {
    setExpression(expression);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "not-expression";
  }
  
  public IExpression getExpression() {
    return expression;
  }
  public void setExpression(IExpression expression) {
    this.expression= reparent(this.expression,expression);
  }
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<IExpression> getElementType() {
    return IExpression.class;
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
  protected IExpression nodeAt(int index) {
    switch(index) {
          case 0:
        return this.expression;
        }
    return super.nodeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected IExpression nodeAt(int index, IExpression node) {
    IExpression old;
    switch(index) {
            case 0:
        old=this.expression;
        setExpression( node);
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

  private static final long serialVersionUID=1890868172549859207L;
}

