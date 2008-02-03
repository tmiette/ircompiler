package fr.umlv.tatoo.samples.calc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import fr.umlv.tatoo.samples.calc.tools.TerminalEvaluator;
import fr.umlv.tatoo.samples.calc.tools.tree.GrammarEvaluator;
import fr.umlv.tatoo.samples.calc.tools.tree.TreeAnalyser;


public class TreeMain {
  public static void main(String[] args) throws FileNotFoundException {
    Reader reader;
    if (args.length>0)
      reader=new FileReader(args[0]);
    else
      reader=new InputStreamReader(System.in);

    TerminalEvaluator<CharSequence> attributeEvaluator=new IntAttributeEvaluator();
    ArrayList<Expr> exprs=new ArrayList<Expr>();
    GrammarEvaluator grammarEvaluator=new TreeGrammarEvaluator(exprs);
    
    TreeAnalyser.run(reader,attributeEvaluator,grammarEvaluator,null,null);
    for(Expr expr:exprs)
      System.out.println(expr+"="+expr.eval());
  }
}
