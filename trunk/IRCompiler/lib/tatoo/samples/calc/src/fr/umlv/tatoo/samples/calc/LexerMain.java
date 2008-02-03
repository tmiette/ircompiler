package fr.umlv.tatoo.samples.calc;

import java.io.FileReader;
import java.io.IOException;

import fr.umlv.tatoo.runtime.buffer.TokenBuffer;
import fr.umlv.tatoo.runtime.buffer.impl.LocationTracker;
import fr.umlv.tatoo.runtime.buffer.impl.ReaderWrapper;
import fr.umlv.tatoo.runtime.lexer.LexerListener;
import fr.umlv.tatoo.runtime.lexer.LexerTable;
import fr.umlv.tatoo.runtime.tools.builder.LexerBuilder;
import fr.umlv.tatoo.samples.calc.lexer.LexerDataTable;
import fr.umlv.tatoo.samples.calc.lexer.RuleEnum;

public class LexerMain {

  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.err.println("Usage: LexerMain <file>");
      System.exit(1);
    }
    
    final LexerTable<RuleEnum> table = LexerDataTable.createTable();
    
    final ReaderWrapper buffer = new ReaderWrapper(new FileReader(args[0]), new LocationTracker());

    final LexerListener<RuleEnum, TokenBuffer<?>> listener = new LexerListener<RuleEnum, TokenBuffer<?>>() {
      public void ruleVerified(RuleEnum t, int lastTokenLength, TokenBuffer<?> buffer) {
        switch (t) {
        case value:
          System.out.println(buffer.view());
          buffer.discard();
          break;
        default:
          buffer.discard();
        }
      }
    };
    
    LexerBuilder.createBuilder(table, buffer)
      .setLexerListener(listener)
      .createLexer().run();
  }
}
