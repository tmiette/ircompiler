package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.Node.NoValue;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;
import tatoo.tools.IStatement;

public class Statement extends FlatNode<IStatement,Node,NoValue> implements Serializable, IInstruction {
  Statement() {
    // used by XML serialization
  }
  public Statement(IStatement statement) {
    setStatement(statement);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "statement";
  }
  
  public IStatement getStatement() {
    return statement;
  }
  public void setStatement(IStatement statement) {
    this.statement= reparent(this.statement,statement);
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
  @Override
  protected int nodeCount() {
    return 1;
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected IStatement nodeAt(int index) {
    switch(index) {
          case 0:
        return this.statement;
        }
    return super.nodeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected IStatement nodeAt(int index, IStatement node) {
    IStatement old;
    switch(index) {
            case 0:
        old=this.statement;
        setStatement( node);
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
  
  private IStatement statement;

  private static final long serialVersionUID=-986430439412684478L;
}

