package fr.umlv.tatoo.cc.common.log;


public interface DiagnosticReporter {
  public enum Level {
    ERROR, WARNING, INFO, DEBUG, NONE
  }
  public interface Key {
    public String name();
    public Level defaultLevel();
  }
  
  public boolean isOnError();
}
