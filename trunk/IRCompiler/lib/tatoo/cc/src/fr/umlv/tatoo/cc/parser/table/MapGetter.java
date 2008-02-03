package fr.umlv.tatoo.cc.parser.table;

import java.util.Map;

public class MapGetter {
  
  private MapGetter()  {
    // prevent instantiation
  }
  
  private static final MapGetter INSTANCE = new MapGetter();
  
  public static MapGetter getInstance() {
    return INSTANCE;
  }
  
  public <V> V getFromMap(Map<?,? extends V> map, Object key) {
    return map.get(key);
  }
}
