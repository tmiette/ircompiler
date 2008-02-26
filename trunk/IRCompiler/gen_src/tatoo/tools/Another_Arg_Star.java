package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.CompositeNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import tatoo.tools.Another_Arg;

public class Another_Arg_Star extends CompositeNode<Another_Arg,Node> implements Serializable {
  public Another_Arg_Star() {
    // default constructor needed by XML serialization
  }

  public Another_Arg_Star(List<? extends Another_Arg> list) {
    nodeList().addAll(list);
  }
  
  public Another_Arg_Star(Another_Arg... array) {
    Collections.addAll(nodeList(),array);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "another_arg_star";
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<Another_Arg> getElementType() {
    return Another_Arg.class;
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

  private static final long serialVersionUID=7017150100028176469L;
}

