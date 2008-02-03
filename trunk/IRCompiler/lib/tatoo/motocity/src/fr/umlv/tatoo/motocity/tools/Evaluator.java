package fr.umlv.tatoo.motocity.tools;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Evaluator implements GrammarEvaluator, TerminalEvaluator<CharSequence>{
  
  public Evaluator(PrintWriter out, String className) {
    this.out = out;
    this.className = className;
  }
  
  private final HashMap<String,String> map = new HashMap<String, String>();

  public void classDef() {
    out.printf("%n%npublic class %s {%n",className);
  }
  /*public void firstJavaLetter(char c) {
    moreJavaLetter(c);
  }*/
  public void firstLetter(char c) {
    moreLetter(c);
  }
  public void head() {
    // nothing
  }
  public void moreJavaLetter(char c) {
    out.print(c);
  }
  public void moreLetter(char c) {
    builder.append(c);
  }
  public void wordOutput() {
    out.print("out.print(\"");
    printQuoted(builder);
    out.println("\");");
    builder.setLength(0);
  }
  private void printQuoted(CharSequence c) {
    for(int i=0;i<c.length();i++) {
      char ch = c.charAt(i);
      switch(ch) {
        case '\b': out.print("\\b");break;
        case '\t': out.print("\\t");break;
        case '\n': out.print("\\n");break;
        case '\r': out.print("\\r");break;
        case '"': out.print("\\\"");break;
        case '\'': out.print("\\'");break;
        case '\\': out.print("\\\\");break;
        default: {
          if (ch>=128) {
            out.print("\\u");
            for (int j = 0; j < 4; j++) {
              int b =  (ch >> 4 * (3 - j));
              out.print(Character.forDigit(b, 16));
            }
          }
          else
            out.print(ch);
        }
      }
    }
  }
  private void print(CharSequence c) {
    for(int i=0;i<c.length();i++)
      out.print(c.charAt(i));
  }
  public void imports(CharSequence data) {
    print(data);
  }
  public char javaLetter(CharSequence data) {
    return data.charAt(0);
  }
  public char letter(CharSequence data) {
    return data.charAt(0);
  }
  public void acceptStart() {
    out.println("}");
    out.println("}");
  }
  public void start() {
    // done in accept
  }
  
  private final StringBuilder builder = new StringBuilder();
  private final PrintWriter out;
  private final String className;
  public void endOut() {
    out.println(");");
  }

  public void headClassDef() {
    // nothing
  }

  public void javaExprOuput() {
    // nothing
  }
  
  public void methodStart() {
    out.println();
    out.println("/* end of headings */");
    out.println();
    if (map.isEmpty()) {
      out.println("public static void __input(java.io.PrintWriter out) {");
      out.println("  __input(out,null);");
      out.println("}");
      out.println();
    }
    out.println("public static void __input(java.io.PrintWriter out,java.util.Map<String,?> map) {");
    for(Map.Entry<String,String> entry:map.entrySet()) {
      out.format("  %2$s %1$s = (%2$s)map.get(\"%1$s\");%n",entry.getKey(),entry.getValue());
    }
  }

  public void startOut() {
    out.print("out.print(");
  }

  public void paramDef(String name, String type) {
    map.put(name,type);
  }

  public String name(CharSequence data) {
    return data.toString();
  }

  public String type(CharSequence data) {
    return data.toString();
  }
}
