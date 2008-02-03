package fr.umlv.IRCompiler.main;

import java.io.FileNotFoundException;
import java.io.FileReader;

import fr.umlv.IRCompiler.tatoo.lexer.LexerDataTable;
import fr.umlv.IRCompiler.tatoo.lexer.RuleEnum;
import fr.umlv.tatoo.runtime.buffer.TokenBuffer;
import fr.umlv.tatoo.runtime.buffer.impl.LocationTracker;
import fr.umlv.tatoo.runtime.buffer.impl.ReaderWrapper;
import fr.umlv.tatoo.runtime.lexer.LexerListener;
import fr.umlv.tatoo.runtime.lexer.LexerTable;
import fr.umlv.tatoo.runtime.tools.builder.LexerBuilder;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {

    final ReaderWrapper buffer;
    if (args.length <= 0) {
      buffer = null;
      System.err.println("Erreur");
      System.exit(1);
    } else {
      buffer = new ReaderWrapper(new FileReader(args[0]), new LocationTracker());
    }

    LexerTable<RuleEnum> table = LexerDataTable.createTable();
    LexerListener<RuleEnum, TokenBuffer<?>> listener = new LexerListener<RuleEnum, TokenBuffer<?>>() {
      public void ruleVerified(RuleEnum rule, int lastTokenLength,
          TokenBuffer<?> buffer) {
        switch (rule) {
        case letter:
          System.err.println("ERREUR" + buffer.view());
          buffer.discard();
          System.exit(1);
          break;
        case space:
          buffer.discard();
          break;
        default:
          System.out.print(buffer.view());
          buffer.discard();
        }
      }
    };
    LexerBuilder.createBuilder(table, buffer).setLexerListener(listener)
        .createLexer().run();

  }

}
