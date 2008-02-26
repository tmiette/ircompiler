package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.Node.NoValue;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;
import tatoo.tools.Function_Declaration;

public class Instruction extends FlatNode<Function_Declaration,Node,NoValue> implements Serializable, IInstruction {
  Instruction() {
    // used by XML serialization
  }
  public Instruction(Function_Declaration function_declaration) {
    setFunction_declaration(function_declaration);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "instruction";
  }
  
  public Function_Declaration getFunction_declaration() {
    return function_declaration;
  }
  public void setFunction_declaration(Function_Declaration function_declaration) {
    this.function_declaration= reparent(this.function_declaration,function_declaration);
  }
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<Function_Declaration> getElementType() {
    return Function_Declaration.class;
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
  protected Function_Declaration nodeAt(int index) {
    switch(index) {
          case 0:
        return this.function_declaration;
        }
    return super.nodeAt(index);
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Function_Declaration nodeAt(int index, Function_Declaration node) {
    Function_Declaration old;
    switch(index) {
            case 0:
        old=this.function_declaration;
        setFunction_declaration( node);
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
  
  private Function_Declaration function_declaration;

  private static final long serialVersionUID=-2729047134906102446L;
}

