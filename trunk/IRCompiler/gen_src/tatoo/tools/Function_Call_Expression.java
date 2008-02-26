package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;
import tatoo.tools.IArg_List;

public class Function_Call_Expression extends FlatNode<IArg_List,Node,String> implements Serializable, IExpression {
  Function_Call_Expression() {
    // used by XML serialization
  }
  public Function_Call_Expression(String identifier_, IArg_List arg_list) {
    setIdentifier_(identifier_);
    setArg_list(arg_list);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "function-call-expression";
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
  
  private String identifier_;
  private IArg_List arg_list;

  private static final long serialVersionUID=-2723750556100120650L;
}

