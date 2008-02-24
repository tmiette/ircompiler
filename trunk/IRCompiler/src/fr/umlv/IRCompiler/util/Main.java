package fr.umlv.IRCompiler.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

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
    if (args.length < 2) {
      reader = null;
      System.out.println("Usage : <input file> <output file>");
      System.exit(1);
    } else {
      reader = new ReaderWrapper(new FileReader(args[0]), new LocationTracker());
    }

    final TerminalEvaluator<CharSequence> attributeEvaluator = new IRCompilerTerminalEvaluator();
    final ASTEvaluator ast = new ASTEvaluator();
    Analyzer.run(reader, attributeEvaluator, ast, NonTerminalEnum.start, null);

    final Start start = ast.getStart();

    final PrintVisitor printVisitor = new PrintVisitor();
    // start.accept(printVisitor, null);

    final SemanticVisitor semanticVisitor2 = new SemanticVisitor();
    start.accept(semanticVisitor2, null);

    final CodeGenerator codeGenerator = new AsmCodeGenerator(args[1]);
    final CodeGeneratorVisitor codeVisitor = new CodeGeneratorVisitor(
        codeGenerator, semanticVisitor2.getImports(), semanticVisitor2
            .getFunctions());
    start.accept(codeVisitor, null);

    OutputStream o = new FileOutputStream(new File(args[1]));
    o.write(codeGenerator.getByteArray());
    o.close();

  }

}
