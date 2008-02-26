package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.Node.NoValue;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;

public class Parameter_List extends FlatNode<Node,Node,NoValue> implements Serializable, IParameter_List {
  Parameter_List() {
    // used by XML serialization
  }
  public Parameter_List(Parameter parameter, Another_Parameter_Star another_parameter_star) {
    setParameter(parameter);
    setAnother_parameter_star(another_parameter_star);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "parameter-list";
  }
  
  public Parameter getParameter() {
    return parameter;
  }
  public void setParameter(Parameter parameter) {
    this.parameter= reparent(this.parameter,parameter);
  }
  public Another_Parameter_Star getAnother_parameter_star() {
    return another_parameter_star;
  }
  public void setAnother_parameter_star(Another_Parameter_Star another_parameter_star) {
    this.another_parameter_star= reparent(this.another_parameter_star,another_parameter_star);
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
        return this.parameter;
          case 1:
        return this.another_parameter_star;
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
        old=this.parameter;
        setParameter( (Parameter) node);
        return old;
            case 1:
        old=this.another_parameter_star;
        setAnother_parameter_star( (Another_Parameter_Star) node);
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
  
  private Parameter parameter;
  private Another_Parameter_Star another_parameter_star;

  private static final long serialVersionUID=7679786910240072468L;
}

