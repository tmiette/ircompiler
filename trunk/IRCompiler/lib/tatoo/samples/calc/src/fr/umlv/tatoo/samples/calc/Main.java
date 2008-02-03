package fr.umlv.tatoo.samples.calc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

import fr.umlv.tatoo.samples.calc.parser.NonTerminalEnum;
import fr.umlv.tatoo.samples.calc.parser.VersionEnum;
import fr.umlv.tatoo.samples.calc.tools.Analyzer;
import fr.umlv.tatoo.samples.calc.tools.GrammarEvaluator;
import fr.umlv.tatoo.samples.calc.tools.TerminalEvaluator;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    final Reader reader;
    if (args.length>0) {
      reader = new FileReader(args[0]);
    } else {
      reader = new InputStreamReader(System.in);
    }
    final GrammarEvaluator grammarEvaluator=new IntGrammarEvaluator();
    final TerminalEvaluator<CharSequence> attributeEvaluator=new IntAttributeEvaluator();
    
    Analyzer.run(reader,attributeEvaluator,grammarEvaluator,NonTerminalEnum.start,VersionEnum.V2);
  }
}
