package fr.umlv.IRCompiler.util;


public class InvalidExpressionException extends Exception {

  private static final long serialVersionUID = 5716647090569727173L;

  public InvalidExpressionException(String message) {
    super(message);
  }

  public static InvalidExpressionException throwNewException(Operator op,
      Class<?>... types) {
    StringBuilder sb = new StringBuilder();

    sb.append("Cannot use the operator ");
    sb.append(op.getName());
    sb.append(" with type(s) : ");
    for (Class<?> t : types) {
      sb.append(t);
      sb.append(" ");
    }
    sb.append(".");

    return new InvalidExpressionException(sb.toString());
  }

}
