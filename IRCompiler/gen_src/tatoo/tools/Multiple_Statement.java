package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.Node.NoValue;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;
import tatoo.tools.Statement_Star;

public class Multiple_Statement extends FlatNode<Statement_Star,Node,NoValue> implements Serializable {
  Multiple_Statement() {
    // used by XML serialization
  }
  public Multiple_Statement(Statement_Star statement_star) {
    setStatement_star(statement_star);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "multiple-statement";
  }
  
  public Statement_Star getStatement_star() {
    return statement_star;
  }
  public void setStatement_star(Statement_Star statement_star) {
    this.statement_star= reparent(this.statement_star,statement_star);
  }
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<Statement_Star> getElementType() {
    return Statement_Star.class;
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
  protected Statement_Star nodeAt(int index) {
    switch(index) {
          case 0:
        return this.statement_star;
        }
    return super.nodeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Statement_Star nodeAt(int index, Statement_Star node) {
    Statement_Star old;
    switch(index) {
            case 0:
        old=this.statement_star;
        setStatement_star( node);
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
  
  private Statement_Star statement_star;

  private static final long serialVersionUID=-918285078359973394L;
}

