package fr.umlv.tatoo.cc.parser.parser;

public interface ActionDecl {
  public boolean isReduce();
  public <R,P> R accept(ActionDeclVisitor<? extends R,? super P> visitor, P parameter);
  public <R> R accept(SimpleActionDeclVisitor<? extends R> visitor);
}
