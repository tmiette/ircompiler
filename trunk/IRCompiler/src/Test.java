
public class Test {

  public static int W = 12;
  
  public static void main(String[] args) {
    int x = 2;
    int y = 3;
    System.out.println(diff(x, x));
  }

  public static int diff(int x, int y) {
    int z = x + y;
    return W * y * z;
  }

}
