package tatoo.tools;

import fr.umlv.tatoo.runtime.ast.Node;

public interface IPackage_Name extends Node {
    
    
  public <_R,_P,_D,_E extends Throwable> _R accept(Visitor<_R,_P,_D,_E> visitor,_P param) throws _E;
}
