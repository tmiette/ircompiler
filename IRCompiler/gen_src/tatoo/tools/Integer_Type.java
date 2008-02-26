package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.Node.NoValue;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;

public class Integer_Type extends FlatNode<Node,Node,NoValue> implements Serializable, IType {
  public Integer_Type() {
    // default constructor
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "integer-type";
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
  public <_R,_P,_D,_E extends Throwable> _R accept(NodeVisitor<_R,_P,_D,_E> visitor,_P param) throws _E {
    return accept((Visitor<_R,_P,_D,_E>)visitor,param);
  }
  
  public <_R,_P,_D,_E extends Throwable> _R accept(Visitor<_R,_P,_D,_E> visitor,_P param) throws _E {
    return visitor.visit(this,param);
  }
  

  private static final long serialVersionUID=62235005215362933L;
}

