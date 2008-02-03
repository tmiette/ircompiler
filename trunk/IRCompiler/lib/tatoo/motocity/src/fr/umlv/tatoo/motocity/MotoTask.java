package fr.umlv.tatoo.motocity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class MotoTask {
  private File template,destFile;
  private final ArrayList<Param> params=new ArrayList<Param>();
  
  public void execute() throws FileNotFoundException {
    HashMap<String,String> map = new HashMap<String, String>();
    if (template==null)
      throw new IllegalArgumentException("Missing template");
    if (destFile==null)
      throw new IllegalArgumentException("Missing destFile");
    System.out.println("Applying "+template.getName()+" in "+destFile);
    for(Param param:params)
      map.put(param.getName(),param.getValue());
    MotoBatch.applyTemplate(new FileReader(template), new PrintWriter(destFile), map);
  }
  
  public void setTemplate(File template) {
    this.template = template;
  }
  public void setDestFile(File destFile) {
    this.destFile = destFile;
  }
  public Param createProperty() {
    Param param = new Param();
    params.add(param);
    return param;
  }
  static public class Param {
    private String name,value;
    public void setName(String name) {
      this.name = name;
    }
    public void setValue(String value) {
      this.value = value;
    }
    public String getName() {
      return name;
    }
    public String getValue() {
      return value;
    }
  }
}
