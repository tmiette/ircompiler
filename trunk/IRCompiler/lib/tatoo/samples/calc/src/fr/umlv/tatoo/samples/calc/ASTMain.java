package fr.umlv.tatoo.samples.calc;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import fr.umlv.tatoo.runtime.ast.Node;
import fr.umlv.tatoo.runtime.tools.Debug;
import fr.umlv.tatoo.runtime.util.DefaultStringConverter;
import fr.umlv.tatoo.samples.calc.parser.NonTerminalEnum;
import fr.umlv.tatoo.samples.calc.parser.VersionEnum;
import fr.umlv.tatoo.samples.calc.tools.ast.ASTEvaluator;
import fr.umlv.tatoo.samples.calc.tools.ast.Analyzer;
import fr.umlv.tatoo.samples.calc.tools.ast.Start_List;
import fr.umlv.tatoo.samples.calc.tools.ast.TerminalEvaluator;
import fr.umlv.tatoo.samples.calc.tools.ast.Visitor;

public class ASTMain {
  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws IOException {
    Reader reader;
    if (args.length>0) {
      reader = new FileReader(args[0]);
    } else {
      reader = new InputStreamReader(System.in);
    }
    //TODO implements the terminal attribute evaluator here
    //TerminalEvaluator attributeEvaluator = Debug.createTraceProxy(TerminalEvaluator.class);
    TerminalEvaluator<Object> terminalEvaluator = Debug.createConverterProxy(
      TerminalEvaluator.class,new DefaultStringConverter());
    
    //TODO implements the grammar evaluator here
    //GrammarEvaluator grammarEvaluator = Debug.createTraceProxy(GrammarEvaluator.class);
    ASTEvaluator astEvaluator = new ASTEvaluator();

    Analyzer.run(reader,terminalEvaluator,astEvaluator,NonTerminalEnum.start,VersionEnum.V1);
    
    Start_List root=astEvaluator.getStart();
    Visitor<Void,Void,Void,RuntimeException> visitor=
      new Visitor<Void,Void,Void,RuntimeException>() {
      @Override
      protected Void visit(Node node,Void param) {
        System.out.println("node "+node.getName()+" "+node.attributeMap());
        for(Node child:node.nodeList())
          child.accept(this,null);
        return null;
      }
    };
    root.accept(visitor,null);
  }
}
