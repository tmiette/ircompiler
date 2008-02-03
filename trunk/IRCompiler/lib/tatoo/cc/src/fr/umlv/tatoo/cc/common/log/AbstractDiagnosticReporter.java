package fr.umlv.tatoo.cc.common.log;

import java.util.ResourceBundle;

public abstract class AbstractDiagnosticReporter<W extends Enum<W> & DiagnosticReporter.Key> implements DiagnosticReporter {
  private boolean onError;
  private final UserDefinedLevelMap<W> userDefinedLevelMap;
  
  protected AbstractDiagnosticReporter(UserDefinedLevelMap<W> userDefinedLevelMap) {
    this.userDefinedLevelMap=userDefinedLevelMap;
  }
  
  public boolean isOnError() {
    return onError;
  }
  
  protected final void setOnError() {
    onError=true;
  }
  
  protected abstract ResourceBundle getBundle();
  
  protected final String formatMessage(Key key,Object... data) {
    String localizedMessage=getBundle().getString(key.name());
    if (localizedMessage==null)
      return "no localized message for key "+key;
    try {
      return String.format(localizedMessage,data);
    } catch(RuntimeException e) {
      e.printStackTrace();
      return "error while trying to localize "+key+" ("+e.getMessage()+')';
    }
  }
  
  protected final Level getLevel(Key key) {
    if (userDefinedLevelMap==null)
      return key.defaultLevel();
    return userDefinedLevelMap.getLevel(key);
  }
}
