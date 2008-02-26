package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;

public class Number_Expression extends FlatNode<Node,Node,Integer> implements Serializable, IExpression {
  Number_Expression() {
    // used by XML serialization
  }
  public Number_Expression(Integer number_) {
    setNumber_(number_);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "number-expression";
  }
  
  public Integer getNumber_() {
    return number_;
  }
  public void setNumber_(Integer number_) {
    this.number_=number_;
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
  protected Integer attributeAt(int index) {
    switch(index) {
          case 0:
        return this.number_;
        }
    return super.attributeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  protected Integer attributeAt(int index,Integer value) {
    Integer old;
    switch(index) {
            case 0:
        old=this.number_;
        setNumber_( value);
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
        return "number_";
        }
    return super.attributeNameAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<? extends Integer> attributeTypeAt(int index) {
    switch(index) {
          case 0:
        return Integer.class;
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
  
  private Integer number_;

  private static final long serialVersionUID=3201552866393660899L;
}

