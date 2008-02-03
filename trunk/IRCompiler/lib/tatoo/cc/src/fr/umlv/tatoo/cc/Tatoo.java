package fr.umlv.tatoo.cc;

public class Tatoo {
  private Tatoo() {
    // can't be instantiated
  }
  
  public enum Status {
    ALPHA, BETA, RC, RELEASE;
  }
  
  public static String version() {
    return "Tatoo version "+major()+'.'+minor()+
      ' '+status().name().toLowerCase()+' '+revision()
      +" ("+date()+')';
  }
  
  public static int major() {
    return 3;
  }
  
  public static int minor() {
    return 0;
  }
  
  public static Status status() {
    return Status.BETA;
  }
  
  public static int revision() {
    try {
      return Integer.parseInt(shrink(11,"$Revision: 1419 $"));
    } catch (NumberFormatException e) {
      return -1;
    }
  }
  
  public static String date() {
    return shrink(7,"$Date: 2007-11-23 10:23:14 +0100 (ven., 23 nov. 2007) $");
  }
  
  private static String shrink(int index,String text) {
    return text.substring(index,text.length()-2);
  }
}
