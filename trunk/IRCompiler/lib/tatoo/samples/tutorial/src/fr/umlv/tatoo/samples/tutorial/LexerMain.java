package fr.umlv.tatoo.samples.tutorial;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import fr.umlv.tatoo.runtime.buffer.impl.ReaderWrapper;
import fr.umlv.tatoo.runtime.lexer.LexerTable;
import fr.umlv.tatoo.runtime.tools.builder.LexerBuilder;
import fr.umlv.tatoo.samples.tutorial.lexer.comment.LexerDataTable;
import fr.umlv.tatoo.samples.tutorial.lexer.comment.RuleEnum;

public class LexerMain {

  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.err.println("Usage: LexerMain <file>");
      System.exit(1);
    }
    
    LexerTable<RuleEnum> table = LexerDataTable.createTable();
    Reader reader = new FileReader(args[0]);
    ListenerAndActivator listenerAndActivator = new ListenerAndActivator();
    
    LexerBuilder<RuleEnum, ReaderWrapper> lexerBuilder = LexerBuilder.createReaderBuilder(table, reader);
    lexerBuilder
      .setLexerListener(listenerAndActivator)
      .setActivator(listenerAndActivator)
      .createLexer()
      .run();
  }
}
