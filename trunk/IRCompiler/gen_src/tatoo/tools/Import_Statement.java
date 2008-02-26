package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.Node.NoValue;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;
import tatoo.tools.IPackage_Name;

public class Import_Statement extends FlatNode<IPackage_Name,Node,NoValue> implements Serializable {
  Import_Statement() {
    // used by XML serialization
  }
  public Import_Statement(IPackage_Name package_name) {
    setPackage_name(package_name);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "import-statement";
  }
  
  public IPackage_Name getPackage_name() {
    return package_name;
  }
  public void setPackage_name(IPackage_Name package_name) {
    this.package_name= reparent(this.package_name,package_name);
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
  public <_R,_P,_D,_E extends Throwable> _R accept(NodeVisitor<_R,_P,_D,_E> visitor,_P param) throws _E {
    return accept((Visitor<_R,_P,_D,_E>)visitor,param);
  }
  
  public <_R,_P,_D,_E extends Throwable> _R accept(Visitor<_R,_P,_D,_E> visitor,_P param) throws _E {
    return visitor.visit(this,param);
  }
  
  private IPackage_Name package_name;

  private static final long serialVersionUID=3044165057302175009L;
}

