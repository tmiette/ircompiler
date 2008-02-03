package fr.umlv.tatoo.motocity.compiler;

import java.util.Collections;

import javax.tools.JavaCompiler;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject.Kind;

public class Compiler {
  
  public static Class<?> compile(String className,byte[] array) {
    return compile(className,array,Compiler.class.getClassLoader());
  }
  
  public static Class<?> compile(String className,byte[] array,ClassLoader parentLoader) {
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    if (compiler==null)
      throw new IllegalStateException("no compiler available (maybe java is from jre and not sdk, by default under windows)");
    MemoryFileManager memoryFileManager = new MemoryFileManager(compiler.getStandardFileManager(null, null, null));
    CompilationTask task = compiler.getTask(null, memoryFileManager, null, null,
        null, Collections.singleton(new MemoryJavaFileObject(className,Kind.SOURCE,StandardLocation.SOURCE_PATH,array)));
    if (!task.call())
      return null;
    try {
      return memoryFileManager.getClassLoader(parentLoader).loadClass(className);
    } catch (ClassNotFoundException e) {
      return null;
    }
  }
}
