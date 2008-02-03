package fr.umlv.tatoo.motocity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import fr.umlv.tatoo.motocity.compiler.Compiler;
import fr.umlv.tatoo.motocity.tools.Analyzer;
import fr.umlv.tatoo.motocity.tools.Evaluator;

public class MotoBatch {
  
  public static void generateFile(Reader template, String className, File outputDir) throws FileNotFoundException {
    File dest = new File(outputDir,className+".java");
    PrintWriter out = new PrintWriter(dest);
    generate(template,out,className);
    if (out.checkError()) {
      System.err.println("I/O error during process (hidden because of PrintWriter)");
      dest.delete();
    }
  }
  
  public static Template compileTask(Reader template) {
    final Class<?> clazz = compile(template);
    return new Template() {
    
      public void call(PrintWriter out, Map<String, ?> params) {
        invoke(clazz,out,params);
      }
    
    };
  }

  static void invoke(Class<?> clazz, PrintWriter out, Map<String,?> params) {
    try {
      Method method = clazz.getDeclaredMethod("__input", PrintWriter.class,Map.class);
      try {
        method.invoke(null, out,params);
        out.flush();
      } catch (IllegalAccessException e) {
        throw new AssertionError(e);
      } catch (InvocationTargetException e) {
        Throwable t = e.getCause();
        if (t instanceof Error)
          throw (Error)t;
        if (t instanceof RuntimeException)
          throw (RuntimeException)t;
        throw (AssertionError)new AssertionError("generated method does not throws").initCause(t);
      } 
    } catch (NoSuchMethodException e) {
      throw new AssertionError(e);
    }
  }
  
  private static Class<?> compile(Reader template) {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    generate(template,new PrintWriter(stream),GENERATED_CLASS_NAME);
    return Compiler.compile(GENERATED_CLASS_NAME, stream.toByteArray());
  }
  
  public static void applyTemplate(Reader template, PrintWriter out, Map<String,?> params) {
    Class<?> clazz = compile(template);
    if (clazz==null)
      return;
    invoke(clazz, out, params);
  }

  private static void generate(Reader template,PrintWriter out,String className) {
    Evaluator evaluator = new Evaluator(out,className);
    /* Analyzer.run(new FileReader(template), (TerminalEvaluator<CharSequence>)Debug.createTraceProxy(TerminalEvaluator.class,evaluator),
       Debug.createTraceProxy(GrammarEvaluator.class,evaluator), null,null); */
    Analyzer.run(template, evaluator,evaluator,null,null);
    out.close();
  }
  
  static final String GENERATED_CLASS_NAME = "__generated";
}
