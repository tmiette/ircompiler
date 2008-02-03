import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collections;

import fr.umlv.tatoo.motocity.MotoBatch;
import fr.umlv.tatoo.motocity.Template;


public class Main {
  public static void main1(String[] args) {
    PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out),true);
    Test.__input(out,Collections.singletonMap("name", "Yeah"));
    out.println();
  }
  
  public static void main(String[] args) throws FileNotFoundException {
    String[] names = {"Alice","Bob"};
    Template task = MotoBatch.compileTask(new FileReader("test.mcty"));
    for(String name:names) {
      task.call(new PrintWriter(System.out), Collections.singletonMap("name", name));
    }
  }
}
