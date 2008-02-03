package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.HashMap;


public class BindingMap {
  private final HashMap<Object,Binding> bindingMap=
    new HashMap<Object,Binding>();
  
  public <B extends Binding> B getBinding(Object domainObject,Class<B> bindingType,boolean allowNull) {
    Binding binding=bindingMap.get(domainObject);
    if (binding==null && !allowNull)
      throw new AssertionError("null binding for "+domainObject);
    return bindingType.cast(binding);
  }
  
  public void registerBinding(Object domainObject,Binding binding) {
    if (bindingMap.put(domainObject,binding)!=null)
      throw new AssertionError("domain object "+domainObject+" has already a binding");
  }
}
