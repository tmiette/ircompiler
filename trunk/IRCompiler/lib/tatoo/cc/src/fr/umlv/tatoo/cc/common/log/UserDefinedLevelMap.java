package fr.umlv.tatoo.cc.common.log;

import java.util.EnumMap;

public class UserDefinedLevelMap<K extends Enum<K> & DiagnosticReporter.Key> {
  private final EnumMap<K,DiagnosticReporter.Level> levelMap;
  
  public UserDefinedLevelMap(Class<K> keyType) {
    levelMap=new EnumMap<K,DiagnosticReporter.Level>(keyType);
  }
  
  public DiagnosticReporter.Level getLevel(DiagnosticReporter.Key key) {
    DiagnosticReporter.Level level=levelMap.get(key);
    if (level==null)
      return key.defaultLevel();
    return level;
  }
  
  public void changeKeyLevel(K key,DiagnosticReporter.Level level) {
    if (key.defaultLevel()!=level)
      levelMap.put(key,level);
    else
      levelMap.remove(key);
  }
}
