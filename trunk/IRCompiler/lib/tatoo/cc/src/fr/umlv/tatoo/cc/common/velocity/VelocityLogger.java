/*
 * Logger.java
 *
 * Created: 8 mars 2006
 */
package fr.umlv.tatoo.cc.common.velocity;

import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogSystem;

import fr.umlv.tatoo.cc.common.log.Info;

/** This class implements a velocity logger that
 *  redirect all errors to the thread local {@link fr.umlv.tatoo.cc.common.log.Reporter}. 
 * 
 * @author Gilles
 */
public class VelocityLogger  implements LogSystem {
  public void init(RuntimeServices service) throws Exception {
    this.service = service;
    // do nothing
  }

  public void logVelocityMessage(int level, String message) {
     Info.fine(message,service).report();
  }
  
  private RuntimeServices service;
}
