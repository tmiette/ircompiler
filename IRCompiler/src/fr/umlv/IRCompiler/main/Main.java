package fr.umlv.IRCompiler.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

import tatoo.parser.NonTerminalEnum;
import tatoo.tools.ASTEvaluator;
import tatoo.tools.Analyzer;
import tatoo.tools.Start;
import tatoo.tools.TerminalEvaluator;
import fr.umlv.IRCompiler.codegenerator.AsmCodeGenerator;
import fr.umlv.IRCompiler.codegenerator.CodeGenerator;
import fr.umlv.IRCompiler.util.IRCompilerTerminalEvaluator;
import fr.umlv.IRCompiler.visitor.CodeGeneratorVisitor;
import fr.umlv.IRCompiler.visitor.PrintVisitor;
import fr.umlv.IRCompiler.visitor.SemanticVisitor;
import fr.umlv.tatoo.runtime.buffer.impl.LocationTracker;
import fr.umlv.tatoo.runtime.buffer.impl.ReaderWrapper;

/**
 * This class is the main class which lauches the compilation.
 * 
 * @author Tom MIETTE {tmiette@etudiant.univ-mlv.fr}
 * @author Sebastien MOURET {smouret@etudiant.univ-mlv.fr}
 * 
 */
public class Main {

  /**
   * Main method.
   * 
   * @param args
   *            command line arguments.
   * @throws Throwable
   *             any compilation exception.
   */
  public static void main(String[] args) throws Throwable {

    int printCode = 0;
    final ReaderWrapper reader;

    // files arguments
    if (args.length < 2) {
      reader = null;
      printUsage();
    } else {
      reader = new ReaderWrapper(new FileReader(args[0]), new LocationTracker());
    }

    // print argument
    if (args.length > 2) {
      try {
        printCode = Integer.parseInt(args[2]);
      } catch (NumberFormatException e) {
        System.err.println("Wrong argument for print code.");
        printUsage();
      }
    }

    final TerminalEvaluator<CharSequence> attributeEvaluator = new IRCompilerTerminalEvaluator();
    final ASTEvaluator ast = new ASTEvaluator();
    Analyzer.run(reader, attributeEvaluator, ast, NonTerminalEnum.start, null);

    final Start start = ast.getStart();

    if (printCode == 1) {
      final PrintVisitor printVisitor = new PrintVisitor();
      start.accept(printVisitor, null);
    }

    final SemanticVisitor semanticVisitor2 = new SemanticVisitor();
    start.accept(semanticVisitor2, null);

    final CodeGenerator codeGenerator = new AsmCodeGenerator(args[1]);
    final CodeGeneratorVisitor codeVisitor = new CodeGeneratorVisitor(
        codeGenerator, semanticVisitor2.getImports(), semanticVisitor2
            .getFunctions());
    start.accept(codeVisitor, null);

    final OutputStream o = new FileOutputStream(new File(args[1]));
    o.write(codeGenerator.getByteArray());
    o.close();

  }

  /**
   * Prints the program's usage.
   */
  private static void printUsage() {
    System.err
        .println("Usage : <input file> <output file> <print code (optional, 1 or 0)>");
    System.exit(1);
  }
}
