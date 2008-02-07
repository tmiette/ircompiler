package fr.umlv.IRCompiler.main;

import java.io.FileReader;

import fr.umlv.IRCompiler.IRCompilerTerminalEvaluator;
import fr.umlv.IRCompiler.PrintVisitor;
import fr.umlv.IRCompiler.tatoo.parser.NonTerminalEnum;
import fr.umlv.IRCompiler.tatoo.tools.ASTEvaluator;
import fr.umlv.IRCompiler.tatoo.tools.Analyzer;
import fr.umlv.IRCompiler.tatoo.tools.Start;
import fr.umlv.IRCompiler.tatoo.tools.TerminalEvaluator;
import fr.umlv.tatoo.runtime.buffer.impl.LocationTracker;
import fr.umlv.tatoo.runtime.buffer.impl.ReaderWrapper;

public class Main {

  public static void main(String[] args) throws Throwable {

    final ReaderWrapper reader;
    if (args.length <= 0) {
      reader = null;
      System.exit(1);
    } else {
      reader = new ReaderWrapper(new FileReader(args[0]), new LocationTracker());
    }

    final TerminalEvaluator<CharSequence> attributeEvaluator = new IRCompilerTerminalEvaluator();
    final ASTEvaluator ast = new ASTEvaluator();
    Analyzer.run(reader, attributeEvaluator, ast, NonTerminalEnum.start, null);

    final Start start = ast.getStart();

    // display code
    final PrintVisitor printVisitor = new PrintVisitor();
    start.accept(printVisitor, null);

  }

}
