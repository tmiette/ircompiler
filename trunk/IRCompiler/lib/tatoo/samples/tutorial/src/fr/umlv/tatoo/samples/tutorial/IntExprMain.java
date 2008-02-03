package fr.umlv.tatoo.samples.tutorial;

import java.io.*;
import fr.umlv.tatoo.samples.tutorial.tools.*;

public class IntExprMain {
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
    GrammarEvaluator grammarEvaluator = new IntExprGrammarEvaluator();
    Analyzer.run(reader, attributeEvaluator, grammarEvaluator, null, null);
  }
}
