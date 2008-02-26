package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;
import java.lang.Object;
import tatoo.tools.IArg_List;

public class Method_Call_Expression extends FlatNode<IArg_List,Node,Object> implements Serializable, IExpression {
  Method_Call_Expression() {
    // used by XML serialization
  }
  public Method_Call_Expression(String identifier_, String identifier_2, IArg_List arg_list) {
    setIdentifier_(identifier_);
    setIdentifier_2(identifier_2);
    setArg_list(arg_list);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "method-call-expression";
  }
  
  public IArg_List getArg_list() {
    return arg_list;
  }
  public void setArg_list(IArg_List arg_list) {
    this.arg_list= reparent(this.arg_list,arg_list);
  }
  public String getIdentifier_() {
    return identifier_;
  }
  public void setIdentifier_(String identifier_) {
    this.identifier_=identifier_;
  }
  public String getIdentifier_2() {
    return identifier_2;
  }
  public void setIdentifier_2(String identifier_2) {
    this.identifier_2=identifier_2;
  }
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<IArg_List> getElementType() {
    return IArg_List.class;
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
  protected IArg_List nodeAt(int index) {
    switch(index) {
          case 0:
        return this.arg_list;
        }
    return super.nodeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected IArg_List nodeAt(int index, IArg_List node) {
    IArg_List old;
    switch(index) {
            case 0:
        old=this.arg_list;
        setArg_list( node);
        return old;
          }
    return super.nodeAt(index,node);
  }
  /** 
   * {@inheritDoc}
   */
  @Override
  protected int attributeCount() {
    return 2;
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Object attributeAt(int index) {
    switch(index) {
          case 0:
        return this.identifier_;
          case 1:
        return this.identifier_2;
        }
    return super.attributeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  protected Object attributeAt(int index,Object value) {
    Object old;
    switch(index) {
            case 0:
        old=this.identifier_;
        setIdentifier_( (String) value);
        return old;
            case 1:
        old=this.identifier_2;
        setIdentifier_2( (String) value);
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
          case 1:
        return "identifier_2";
        }
    return super.attributeNameAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<? extends Object> attributeTypeAt(int index) {
    switch(index) {
          case 0:
        return String.class;
          case 1:
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
  
  private String identifier_;
  private String identifier_2;
  private IArg_List arg_list;

  private static final long serialVersionUID=1051511747910910981L;
}

