package fr.umlv.tatoo.cc.tools.generator;

import fr.umlv.tatoo.cc.common.generator.ObjectId;

public class UpperCaser {
  
  private UpperCaser()  {
    // prevent instantiation
  }
  
  private static final UpperCaser INSTANCE = new UpperCaser();
  
  public static UpperCaser getInstance() {
    return INSTANCE;
  }
  
  public String toUpperCase(ObjectId object) {
    return toUpperCase(object.getId());
  }
  
  public String toUpperCase(String id) {
    StringBuilder builder=new StringBuilder();
    for(String s:id.split("_")) {
      builder.append(capitalize(s));
    }
    return builder.toString();
  }
  
  private static String capitalize(String name) {
    return Character.toUpperCase(name.charAt(0))+name.substring(1);
  }
}
