package fr.umlv.tatoo.motocity.compiler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileManager.Location;

public class MemoryJavaFileObject implements JavaFileObject {
  private byte[] code;
  private final Kind kind;
  private final String className;
  private final long time;
  private final Location location;
  private ByteArrayOutputStream outputStream;
  
  public MemoryJavaFileObject(String className, Kind kind,Location location) {
    this(className,kind,location,EMPTY);
  }
  
  public MemoryJavaFileObject(String className, Kind kind,Location location, byte[] code) {
    this.className = className;
    this.location=location;
    this.kind = kind;
    this.time = System.currentTimeMillis();
    this.code=code;
  }
  
  public Location getLocation() {
    return location;
  }
  public String getClassName() {
    return className;
  }
  public byte[] getCode() {
    if (code == EMPTY) {
      if (outputStream!=null) {
        code=outputStream.toByteArray();
        outputStream=null;
      }
    }
    return code;
  }
  public Modifier getAccessLevel() {
    return null;
  }
  public Kind getKind() {
    return kind;
  }
  public NestingKind getNestingKind() {
    if (kind==Kind.SOURCE)
      return NestingKind.TOP_LEVEL;
    else
      return null;
  }
  public boolean isNameCompatible(String simpleName, Kind kind) {
    return true;
  }
  public boolean delete() {
    code=EMPTY;
    return true;
  }
  public CharSequence getCharContent(boolean ignoreEncodingErrors)
      throws IOException {
    return new String(code);
  }
  public long getLastModified() {
    return time;
  }
  public String getName() {
    return className.replace('.', '/')+(kind==Kind.CLASS?".class":".java");
  }
  public InputStream openInputStream() throws IOException {
    return new ByteArrayInputStream(code);
  }
  public OutputStream openOutputStream() throws IOException {
    code=EMPTY;
    outputStream = new ByteArrayOutputStream();
    return outputStream;
  }
  public Reader openReader(boolean ignoreEncodingErrors) throws IOException {
    return new InputStreamReader(openInputStream());
  }
  public Writer openWriter() throws IOException {
    return new OutputStreamWriter(openOutputStream());
  }
  @Override
  public String toString() {
    return toUri().toString();
  }
  
  public URI toUri() {
    try {
      return new URI(scheme,null,'/'+getName(),null);
    } catch (URISyntaxException e) {
      throw new AssertionError(e);
    }
  }
  static final String scheme = "memory";
  private static final byte[] EMPTY = new byte[0];
}
