package fr.umlv.tatoo.samples.tutorial;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

import fr.umlv.tatoo.samples.tutorial.ast.ASTEvaluator;
import fr.umlv.tatoo.samples.tutorial.ast.Analyzer;
import fr.umlv.tatoo.samples.tutorial.ast.Start_List;
import fr.umlv.tatoo.samples.tutorial.parser.NonTerminalEnum;
import fr.umlv.tatoo.samples.tutorial.parser.VersionEnum;

public class IntCounterTreeMain {
  public static void main(String[] args) 
     throws FileNotFoundException {
    Reader reader;
    if (args.length > 0) {
      reader = new FileReader(args[0]);
    } else {
      reader = new InputStreamReader(System.in);
    }
    IntExprAttributeEvaluator terminalEvaluator = 
      new IntExprAttributeEvaluator();
    ASTEvaluator grammarEvaluator = 
      new ASTEvaluator();
    VersionEnum version = VersionEnum.DEFAULT;
    NonTerminalEnum start = NonTerminalEnum.start;
    Analyzer.run(reader, terminalEvaluator, grammarEvaluator, start, version);
    Start_List tree=grammarEvaluator.getStart();
    System.out.println(tree.accept(new IntCounterVisitor(),null));
  }
}
