package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;

public class Float_Expression extends FlatNode<Node,Node,Float> implements Serializable, IExpression {
  Float_Expression() {
    // used by XML serialization
  }
  public Float_Expression(Float float_) {
    setFloat_(float_);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "float-expression";
  }
  
  public Float getFloat_() {
    return float_;
  }
  public void setFloat_(Float float_) {
    this.float_=float_;
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
  protected Float attributeAt(int index) {
    switch(index) {
          case 0:
        return this.float_;
        }
    return super.attributeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  protected Float attributeAt(int index,Float value) {
    Float old;
    switch(index) {
            case 0:
        old=this.float_;
        setFloat_( value);
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
        return "float_";
        }
    return super.attributeNameAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<? extends Float> attributeTypeAt(int index) {
    switch(index) {
          case 0:
        return Float.class;
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
  
  private Float float_;

  private static final long serialVersionUID=-626963124356467126L;
}

