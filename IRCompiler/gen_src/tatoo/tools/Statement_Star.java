package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.CompositeNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import tatoo.tools.IStatement;

public class Statement_Star extends CompositeNode<IStatement,Node> implements Serializable {
  public Statement_Star() {
    // default constructor needed by XML serialization
  }

  public Statement_Star(List<? extends IStatement> list) {
    nodeList().addAll(list);
  }
  
  public Statement_Star(IStatement... array) {
    Collections.addAll(nodeList(),array);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "statement_star";
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<IStatement> getElementType() {
    return IStatement.class;
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

  private static final long serialVersionUID=1215867234408765490L;
}

