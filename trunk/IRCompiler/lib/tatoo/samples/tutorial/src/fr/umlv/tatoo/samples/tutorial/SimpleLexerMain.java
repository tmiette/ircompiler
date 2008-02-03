package fr.umlv.tatoo.samples.tutorial;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import fr.umlv.tatoo.runtime.lexer.LexerTable;
import fr.umlv.tatoo.runtime.tools.builder.LexerBuilder;
import fr.umlv.tatoo.samples.tutorial.lexer.LexerDataTable;
import fr.umlv.tatoo.samples.tutorial.lexer.RuleEnum;

public class SimpleLexerMain {

  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.err.println("Usage: LexerMain <file>");
      System.exit(1);
    }
    
    LexerTable<RuleEnum> table = LexerDataTable.createTable();
    Reader reader = new FileReader(args[0]);
    SimpleLexerListener listener = new SimpleLexerListener();
    
    LexerBuilder.createReaderBuilder(table, reader)
      .setLexerListener(listener)
      .createLexer()
      .run();
  }
}
