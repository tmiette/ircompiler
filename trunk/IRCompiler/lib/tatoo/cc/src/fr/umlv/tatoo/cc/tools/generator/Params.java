/*
 *
 * Created: 2 mars 2006
 */
package fr.umlv.tatoo.cc.tools.generator;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fr.umlv.tatoo.cc.common.generator.Type;
import fr.umlv.tatoo.cc.parser.grammar.VariableDecl;

public class Params {
 public Params(Map<VariableDecl, Type> variableTypeMap) {
    this.variableTypeMap = variableTypeMap;
  }
 
 public List<ParamDecl> get(VariableDecl var) {
   Naming naming=new Naming();
   LinkedList<ParamDecl> params = new LinkedList<ParamDecl>();
   Type t = variableTypeMap.get(var);
   if (t == null || t.isVoid()){
     return params;
   }
   params.addLast(new ParamDecl(t,naming.name(var.getId())));
   return params;
 }
 
 public List<ParamDecl> get(List<VariableDecl> rhs) {
   Naming naming=new Naming();
   LinkedList<ParamDecl> params = new LinkedList<ParamDecl>();
   for (VariableDecl v:rhs) {
     Type t = variableTypeMap.get(v);
     if (t == null || t.isVoid()){
       continue;
     }
     params.addLast(new ParamDecl(t,naming.name(v.getId())));
   }
   return params;
 }
 
 public boolean notAllNull(List<VariableDecl> rhs) {
   for (VariableDecl v:rhs) {
     Type t = variableTypeMap.get(v);
     if (t!=null)
       return true;
   }
   return false;
 }
 
 public List<ParamDecl> getReverse(List<VariableDecl> rhs) {
   Naming naming=new Naming();
   LinkedList<ParamDecl> params = new LinkedList<ParamDecl>();
   for (VariableDecl v:rhs) {
     Type t = variableTypeMap.get(v);
     if (t == null || t.isVoid()){
       continue;
     }
     params.addFirst(new ParamDecl(t,naming.name(v.getId())));
   }
   return params;
 }
  
  private final Map<VariableDecl, Type> variableTypeMap;
  
  public static final class ParamDecl {
    ParamDecl(Type type, String name) {
      this.type = type;
      this.name = name;
    }
    public String getName() {
      return this.name;
    }
    public Type getType() {
      return this.type;
    }    
    private final Type type;
    private final String name;
  }
}