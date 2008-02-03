package fr.umlv.tatoo.motocity.compiler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;

public class MemoryFileManager implements JavaFileManager {
  
  final HashMap<String, MemoryJavaFileObject> data = new HashMap<String, MemoryJavaFileObject>();
  
  private final JavaFileManager standard;

  public void close() throws IOException {
    // nothing
  }

  public void flush() throws IOException {
    // nothing
  }

  public ClassLoader getClassLoader(Location location) {
    return getClassLoader(standard.getClassLoader(location));
  }
    
  public ClassLoader getClassLoader(ClassLoader parent) {
    return new ClassLoader(parent) {
    
      @Override
      protected Class<?> findClass(String name) throws ClassNotFoundException {
        MemoryJavaFileObject file = data.get(name.replace('.','/')+".class");
        if (file==null)
          throw new ClassNotFoundException();
        byte[] code = file.getCode();
        /*try {
          File debfile = new File("Test.class");
          FileOutputStream out = new FileOutputStream(debfile);
          out.write(code);
          out.close();
        }
        catch (IOException e){
          e.printStackTrace();
        }*/
        return defineClass(name, code, 0, code.length);
      }
    
    };
  }

  public FileObject getFileForInput(Location location, String packageName,
      String relativeName) throws IOException {
    MemoryJavaFileObject file = data.get(packageName.replace('.','/')+'/'+relativeName);
    if (file!=null)
      return file;
    return standard.getFileForInput(location, packageName, relativeName);
  }

  public FileObject getFileForOutput(Location location, String packageName,
      String relativeName, FileObject sibling) throws IOException {
    throw new UnsupportedOperationException("should not be called");
  }

  public JavaFileObject getJavaFileForOutput(Location location,
      String className, Kind kind, FileObject sibling) throws IOException {
    String name = className.replace('.','/')+".class";
    MemoryJavaFileObject file = data.get(name);
    if (file != null && file.getKind()==kind) {
      return file;
    }
    file = new MemoryJavaFileObject(className,kind,location);
    data.put(name,file);
    return file;
  }

  public JavaFileObject getJavaFileForInput(Location location,
      String className, Kind kind) throws IOException {
    String name = className.replace('.','/')+".class";
    MemoryJavaFileObject file = data.get(name);
    if (file==null)
      return standard.getJavaFileForInput(location, className, kind);
    return file;
  }

  public boolean handleOption(String current, Iterator<String> remaining) {
    return standard.handleOption(current, remaining);
  }

  public boolean hasLocation(Location location) {
    for(MemoryJavaFileObject f : data.values()) {
      if (f.getLocation().equals(location))
        return true;
    }
    return standard.hasLocation(location);
  }

  public String inferBinaryName(Location location, JavaFileObject file) {
    if (!(file instanceof MemoryJavaFileObject))
      return standard.inferBinaryName(location, file);
    return ((MemoryJavaFileObject)file).getClassName();
  }

  public boolean isSameFile(FileObject a, FileObject b) {
    if ((a instanceof MemoryJavaFileObject) && (b instanceof MemoryJavaFileObject)) {
      return a==b;
    }
    else {
      return standard.isSameFile(a, b);
    }
  }

  public Iterable<JavaFileObject> list(Location location, String packageName,
      Set<Kind> kinds, boolean recurse) throws IOException {
    ArrayList<JavaFileObject> list = new ArrayList<JavaFileObject>();
    for(JavaFileObject f : standard.list(location, packageName, kinds, recurse))
      list.add(f);
    for(MemoryJavaFileObject f : data.values())
      if (location.equals(f.getLocation()) && f.getName().replace('/', '.').startsWith(packageName))
        list.add(f);
    return list;
          
  }

  public int isSupportedOption(String option) {
    return standard.isSupportedOption(option);
  }

  public MemoryFileManager(JavaFileManager standard) {
    this.standard = standard;
  }

}
