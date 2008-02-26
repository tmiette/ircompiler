package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.Node.NoValue;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;
import tatoo.tools.Multiple_Statement;

public class Else_Statement extends FlatNode<Multiple_Statement,Node,NoValue> implements Serializable, IElse_Statement {
  Else_Statement() {
    // used by XML serialization
  }
  public Else_Statement(Multiple_Statement multiple_statement) {
    setMultiple_statement(multiple_statement);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "else-statement";
  }
  
  public Multiple_Statement getMultiple_statement() {
    return multiple_statement;
  }
  public void setMultiple_statement(Multiple_Statement multiple_statement) {
    this.multiple_statement= reparent(this.multiple_statement,multiple_statement);
  }
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<Multiple_Statement> getElementType() {
    return Multiple_Statement.class;
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
  protected Multiple_Statement nodeAt(int index) {
    switch(index) {
          case 0:
        return this.multiple_statement;
        }
    return super.nodeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Multiple_Statement nodeAt(int index, Multiple_Statement node) {
    Multiple_Statement old;
    switch(index) {
            case 0:
        old=this.multiple_statement;
        setMultiple_statement( node);
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
  
  private Multiple_Statement multiple_statement;

  private static final long serialVersionUID=-655400734319756671L;
}

