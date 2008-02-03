package fr.umlv.tatoo.samples.tutorial;

import java.io.*;
import fr.umlv.tatoo.samples.tutorial.ast.*;
import fr.umlv.tatoo.samples.tutorial.parser.*;

public class IntExprTreeMain {
  public static void main(String[] args) 
     throws FileNotFoundException {
    Reader reader;
    if (args.length > 0) {
      reader = new FileReader(args[0]);
    } else {
      reader = new InputStreamReader(System.in);
    }
    IntExprAttributeEvaluator attributeEvaluator = 
      new IntExprAttributeEvaluator();
    ASTEvaluator grammarEvaluator = 
      new ASTEvaluator();
    VersionEnum version = VersionEnum.DEFAULT;
    NonTerminalEnum start = NonTerminalEnum.start;
    Analyzer.run(reader, attributeEvaluator, grammarEvaluator, start, version);
    Start_List tree = grammarEvaluator.getStart();
    System.out.println(tree);
    tree.accept(new IntExprVisitor(),null);
    System.out.println(tree);
  }
}
