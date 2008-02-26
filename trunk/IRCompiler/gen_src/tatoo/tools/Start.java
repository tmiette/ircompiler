package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.FlatNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.Node.NoValue;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;

public class Start extends FlatNode<Node,Node,NoValue> implements Serializable {
  Start() {
    // used by XML serialization
  }
  public Start(Import_Statement_Star import_statement_star, Instruction_Star instruction_star) {
    setImport_statement_star(import_statement_star);
    setInstruction_star(instruction_star);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "start";
  }
  
  public Import_Statement_Star getImport_statement_star() {
    return import_statement_star;
  }
  public void setImport_statement_star(Import_Statement_Star import_statement_star) {
    this.import_statement_star= reparent(this.import_statement_star,import_statement_star);
  }
  public Instruction_Star getInstruction_star() {
    return instruction_star;
  }
  public void setInstruction_star(Instruction_Star instruction_star) {
    this.instruction_star= reparent(this.instruction_star,instruction_star);
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
        return this.import_statement_star;
          case 1:
        return this.instruction_star;
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
        old=this.import_statement_star;
        setImport_statement_star( (Import_Statement_Star) node);
        return old;
            case 1:
        old=this.instruction_star;
        setInstruction_star( (Instruction_Star) node);
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
  
  private Import_Statement_Star import_statement_star;
  private Instruction_Star instruction_star;

  private static final long serialVersionUID=8779389995875632438L;
}

