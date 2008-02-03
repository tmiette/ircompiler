package fr.umlv.tatoo.motocity;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.HashMap;

public class MotoMain {
  public static void main(String[] args) throws IOException {
    if ("-debug".equals(args[0])) {
      FileReader template = new FileReader(args[1]);
      String className=args[2];
      File outputDir = new File(args[3]);
      MotoBatch.generateFile(template, className, outputDir);
      template.close();
    } 
    else {
      Reader template = new FileReader(args[0]);
      HashMap<String, Object> map = new HashMap<String, Object>();
      for(int i=1;i<args.length;i++) {
        String arg = args[i];
        int index=arg.indexOf('=');
        if (index==-1)
          throw new IllegalArgumentException("parameters must be formed key=value");
        map.put(arg.substring(0,index), arg.substring(index+1));
      }
      MotoBatch.applyTemplate(template, new PrintWriter(System.out), map);
      template.close();
    }
  }
}
