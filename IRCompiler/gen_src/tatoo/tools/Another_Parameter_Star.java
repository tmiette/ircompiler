package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.CompositeNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import tatoo.tools.Another_Parameter;

public class Another_Parameter_Star extends CompositeNode<Another_Parameter,Node> implements Serializable {
  public Another_Parameter_Star() {
    // default constructor needed by XML serialization
  }

  public Another_Parameter_Star(List<? extends Another_Parameter> list) {
    nodeList().addAll(list);
  }
  
  public Another_Parameter_Star(Another_Parameter... array) {
    Collections.addAll(nodeList(),array);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "another_parameter_star";
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<Another_Parameter> getElementType() {
    return Another_Parameter.class;
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

  private static final long serialVersionUID=-9127073418578351768L;
}

