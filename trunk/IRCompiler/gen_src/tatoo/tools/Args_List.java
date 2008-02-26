package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.Node.NoValue;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;

public class Args_List extends FlatNode<Node,Node,NoValue> implements Serializable, IArg_List {
  Args_List() {
    // used by XML serialization
  }
  public Args_List(IExpression expression, Another_Arg_Star another_arg_star) {
    setExpression(expression);
    setAnother_arg_star(another_arg_star);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "args-list";
  }
  
  public IExpression getExpression() {
    return expression;
  }
  public void setExpression(IExpression expression) {
    this.expression= reparent(this.expression,expression);
  }
  public Another_Arg_Star getAnother_arg_star() {
    return another_arg_star;
  }
  public void setAnother_arg_star(Another_Arg_Star another_arg_star) {
    this.another_arg_star= reparent(this.another_arg_star,another_arg_star);
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
        return this.another_arg_star;
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
        old=this.another_arg_star;
        setAnother_arg_star( (Another_Arg_Star) node);
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
  private Another_Arg_Star another_arg_star;

  private static final long serialVersionUID=5662242650838464336L;
}

