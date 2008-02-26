package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.Node.NoValue;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;
import tatoo.tools.Parameter;

public class Another_Parameter extends FlatNode<Parameter,Node,NoValue> implements Serializable {
  Another_Parameter() {
    // used by XML serialization
  }
  public Another_Parameter(Parameter parameter) {
    setParameter(parameter);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "another-parameter";
  }
  
  public Parameter getParameter() {
    return parameter;
  }
  public void setParameter(Parameter parameter) {
    this.parameter= reparent(this.parameter,parameter);
  }
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<Parameter> getElementType() {
    return Parameter.class;
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
  protected Parameter nodeAt(int index) {
    switch(index) {
          case 0:
        return this.parameter;
        }
    return super.nodeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Parameter nodeAt(int index, Parameter node) {
    Parameter old;
    switch(index) {
            case 0:
        old=this.parameter;
        setParameter( node);
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

  private static final long serialVersionUID=-5148399508378930740L;
}

