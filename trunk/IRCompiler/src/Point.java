







public class Point {
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Point(Object x, Object y){
    this.x = 0;
    this.y = 0;
  }
  
  public Point(int x) {
    this(x, x);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getS(int a, int b, int c){
    return 0;
  }
  
  public int getS(int a, Point p){
    return 0;
  }
  
  public String toString() {
    return "(" + x + "," + y + ")";
  }
  
  public static Point plus(Point p1, Point p2){
    System.out.println("plus de Point");
    return new Point(33,33);
  }
  
  public static Point minus(Point p1, Point p2){
    System.out.println("minus de Point");
    return new Point(44,44);
  }

  private final int x;
  private final int y;
}
