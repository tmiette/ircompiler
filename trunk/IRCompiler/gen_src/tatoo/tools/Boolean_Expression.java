package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;

public class Boolean_Expression extends FlatNode<Node,Node,Boolean> implements Serializable, IExpression {
  Boolean_Expression() {
    // used by XML serialization
  }
  public Boolean_Expression(Boolean boolean_) {
    setBoolean_(boolean_);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "boolean-expression";
  }
  
  public Boolean getBoolean_() {
    return boolean_;
  }
  public void setBoolean_(Boolean boolean_) {
    this.boolean_=boolean_;
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
  protected int attributeCount() {
    return 1;
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Boolean attributeAt(int index) {
    switch(index) {
          case 0:
        return this.boolean_;
        }
    return super.attributeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  protected Boolean attributeAt(int index,Boolean value) {
    Boolean old;
    switch(index) {
            case 0:
        old=this.boolean_;
        setBoolean_( value);
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
        return "boolean_";
        }
    return super.attributeNameAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<? extends Boolean> attributeTypeAt(int index) {
    switch(index) {
          case 0:
        return Boolean.class;
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
  
  private Boolean boolean_;

  private static final long serialVersionUID=6296349541122591179L;
}

