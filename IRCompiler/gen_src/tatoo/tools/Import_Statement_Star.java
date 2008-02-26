package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.CompositeNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import tatoo.tools.Import_Statement;

public class Import_Statement_Star extends CompositeNode<Import_Statement,Node> implements Serializable {
  public Import_Statement_Star() {
    // default constructor needed by XML serialization
  }

  public Import_Statement_Star(List<? extends Import_Statement> list) {
    nodeList().addAll(list);
  }
  
  public Import_Statement_Star(Import_Statement... array) {
    Collections.addAll(nodeList(),array);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "import_statement_star";
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<Import_Statement> getElementType() {
    return Import_Statement.class;
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

  private static final long serialVersionUID=-7774514808655064057L;
}

