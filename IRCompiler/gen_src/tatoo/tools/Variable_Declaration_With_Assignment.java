package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;

public class Variable_Declaration_With_Assignment extends FlatNode<Node,Node,String> implements Serializable, IVariable_Declaration {
  Variable_Declaration_With_Assignment() {
    // used by XML serialization
  }
  public Variable_Declaration_With_Assignment(IType type, String identifier_, IExpression expression) {
    setType(type);
    setIdentifier_(identifier_);
    setExpression(expression);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "variable-declaration-with-assignment";
  }
  
  public IType getType() {
    return type;
  }
  public void setType(IType type) {
    this.type= reparent(this.type,type);
  }
  public IExpression getExpression() {
    return expression;
  }
  public void setExpression(IExpression expression) {
    this.expression= reparent(this.expression,expression);
  }
  public String getIdentifier_() {
    return identifier_;
  }
  public void setIdentifier_(String identifier_) {
    this.identifier_=identifier_;
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
  @Override
  protected int nodeCount() {
    return 2;
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Node nodeAt(int index) {
    switch(index) {
          case 0:
        return this.type;
          case 1:
        return this.expression;
        }
    return super.nodeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Node nodeAt(int index, Node node) {
    Node old;
    switch(index) {
            case 0:
        old=this.type;
        setType( (IType) node);
        return old;
            case 1:
        old=this.expression;
        setExpression( (IExpression) node);
        return old;
          }
    return super.nodeAt(index,node);
  }
  /** 
   * {@inheritDoc}
   */
  @Override
  protected int attributeCount() {
    return 1;
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected String attributeAt(int index) {
    switch(index) {
          case 0:
        return this.identifier_;
        }
    return super.attributeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  protected String attributeAt(int index,String value) {
    String old;
    switch(index) {
            case 0:
        old=this.identifier_;
        setIdentifier_( value);
        return old;
          }    
    return super.attributeAt(index,value);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected String attributeNameAt(int index) {
    switch(index) {
          case 0:
        return "identifier_";
        }
    return super.attributeNameAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<? extends String> attributeTypeAt(int index) {
    switch(index) {
          case 0:
        return String.class;
        }
    return super.attributeTypeAt(index);
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
  
  private IType type;
  private String identifier_;
  private IExpression expression;

  private static final long serialVersionUID=-1098627832074769260L;
}

