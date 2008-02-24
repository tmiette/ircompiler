

public class Complexe {

  public Complexe(float x, float y) {
    this.x = x;
    this.y = y;
  }

  // définition de opérateur +
  public static Complexe plus(Complexe x, Complexe y) {
    return new Complexe(x.getReel() + y.getReel(), x.getImaginaire()
        + y.getImaginaire());
  }

  // définition de l'opérateur -
  public static Complexe minus(Complexe x, Complexe y) {
    return new Complexe(x.getReel() - y.getReel(), x.getImaginaire()
        - y.getImaginaire());
  }

  public String toString() {
    return x + "+" + y + ".i";
  }

  public float getReel() {
    return x;
  }

  public float getImaginaire() {
    return y;
  }

  public boolean equals(Object o) {
    if (o instanceof Complexe) {
      Complexe c = (Complexe) o;
      return (c.getReel() == this.getReel())
          && (c.getImaginaire() == this.getImaginaire());
    }
    return false;
  }

  private float x;
  private float y;
}
