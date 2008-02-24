package fr.umlv.IRCompiler.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class Import {

  private final ArrayList<Class<?>> imports;
  private final HashMap<String, Class<?>> identifiers;

  public Import() {
    this.imports = new ArrayList<Class<?>>();
    this.identifiers = new HashMap<String, Class<?>>();
  }

  public void validateImport(List<String> packageDeclaration)
      throws UnresolvedClassException {

    final StringBuilder sb = new StringBuilder();
    for (final Iterator<String> it = packageDeclaration.iterator(); it
        .hasNext();) {
      sb.append(it.next());
      if (it.hasNext()) {
        sb.append(".");
      }
    }

    try {
      String className = sb.toString();
      Class<?> clazz = Class.forName(className);
      this.imports.add(clazz);
      this.identifiers.put(className.substring(className.lastIndexOf(".") + 1,
          className.length()), clazz);
    } catch (ClassNotFoundException e) {
      throw new UnresolvedClassException("Class " + sb.toString()
          + " cannot be resolved.", e.getCause());
    }
  }

  public boolean isImported(String identifier) {
    return this.identifiers.get(identifier) != null;
  }

  public Class<?> getClass(String identifier) throws UnresolvedClassException {
    if (!isImported(identifier)) {
      throw new UnresolvedClassException("Class " + identifier
          + " cannot be resolved.");
    }
    return this.identifiers.get(identifier);
  }



}
