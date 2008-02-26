package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;
import tatoo.tools.IPackage_Name;

public class Multiple_Package_Name extends FlatNode<IPackage_Name,Node,String> implements Serializable, IPackage_Name {
  Multiple_Package_Name() {
    // used by XML serialization
  }
  public Multiple_Package_Name(IPackage_Name package_name, String identifier_) {
    setPackage_name(package_name);
    setIdentifier_(identifier_);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "multiple-package-name";
  }
  
  public IPackage_Name getPackage_name() {
    return package_name;
  }
  public void setPackage_name(IPackage_Name package_name) {
    this.package_name= reparent(this.package_name,package_name);
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
  protected Class<IPackage_Name> getElementType() {
    return IPackage_Name.class;
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
  protected IPackage_Name nodeAt(int index) {
    switch(index) {
          case 0:
        return this.package_name;
        }
    return super.nodeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected IPackage_Name nodeAt(int index, IPackage_Name node) {
    IPackage_Name old;
    switch(index) {
            case 0:
        old=this.package_name;
        setPackage_name( node);
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
  
  private IPackage_Name package_name;
  private String identifier_;

  private static final long serialVersionUID=-3035692409960119068L;
}

