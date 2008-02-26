package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.Node.NoValue;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;
import tatoo.tools.IVariable_Declaration;

public class Variable_Declaration_Statement extends FlatNode<IVariable_Declaration,Node,NoValue> implements Serializable, IStatement {
  Variable_Declaration_Statement() {
    // used by XML serialization
  }
  public Variable_Declaration_Statement(IVariable_Declaration variable_declaration) {
    setVariable_declaration(variable_declaration);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "variable-declaration-statement";
  }
  
  public IVariable_Declaration getVariable_declaration() {
    return variable_declaration;
  }
  public void setVariable_declaration(IVariable_Declaration variable_declaration) {
    this.variable_declaration= reparent(this.variable_declaration,variable_declaration);
  }
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<IVariable_Declaration> getElementType() {
    return IVariable_Declaration.class;
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
  protected IVariable_Declaration nodeAt(int index) {
    switch(index) {
          case 0:
        return this.variable_declaration;
        }
    return super.nodeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected IVariable_Declaration nodeAt(int index, IVariable_Declaration node) {
    IVariable_Declaration old;
    switch(index) {
            case 0:
        old=this.variable_declaration;
        setVariable_declaration( node);
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
  
  private IVariable_Declaration variable_declaration;

  private static final long serialVersionUID=1611651913124139392L;
}

