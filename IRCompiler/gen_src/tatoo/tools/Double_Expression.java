package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;

public class Double_Expression extends FlatNode<Node,Node,Double> implements Serializable, IExpression {
  Double_Expression() {
    // used by XML serialization
  }
  public Double_Expression(Double double_) {
    setDouble_(double_);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "double-expression";
  }
  
  public Double getDouble_() {
    return double_;
  }
  public void setDouble_(Double double_) {
    this.double_=double_;
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
  protected Double attributeAt(int index) {
    switch(index) {
          case 0:
        return this.double_;
        }
    return super.attributeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  protected Double attributeAt(int index,Double value) {
    Double old;
    switch(index) {
            case 0:
        old=this.double_;
        setDouble_( value);
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
        return "double_";
        }
    return super.attributeNameAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<? extends Double> attributeTypeAt(int index) {
    switch(index) {
          case 0:
        return Double.class;
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
  
  private Double double_;

  private static final long serialVersionUID=7929688468254267035L;
}

