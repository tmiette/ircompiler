package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.CompositeNode;
import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.ast.NodeVisitor;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import tatoo.tools.IInstruction;

public class Instruction_Star extends CompositeNode<IInstruction,Node> implements Serializable {
  public Instruction_Star() {
    // default constructor needed by XML serialization
  }

  public Instruction_Star(List<? extends IInstruction> list) {
    nodeList().addAll(list);
  }
  
  public Instruction_Star(IInstruction... array) {
    Collections.addAll(nodeList(),array);
  }
  
  /** 
   * {@inheritDoc}
   */
  public String getName() {
    return "instruction_star";
  }
  
  /** 
   * {@inheritDoc}
   */
  @Override
  protected Class<IInstruction> getElementType() {
    return IInstruction.class;
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

  private static final long serialVersionUID=-3114100464410665944L;
}

