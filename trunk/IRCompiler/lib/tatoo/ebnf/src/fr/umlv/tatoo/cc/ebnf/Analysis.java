package fr.umlv.tatoo.cc.ebnf;

import java.util.HashMap;

import fr.umlv.tatoo.cc.common.generator.Type;
import fr.umlv.tatoo.cc.ebnf.ast.BindingMap;
import fr.umlv.tatoo.cc.ebnf.ast.Binding.NonTerminalBinding;
import fr.umlv.tatoo.cc.ebnf.ast.Binding.TerminalBinding;
import fr.umlv.tatoo.cc.ebnf.ast.Binding.VariableBinding;
import fr.umlv.tatoo.cc.ebnf.ast.analysis.EnterPassOne;
import fr.umlv.tatoo.cc.ebnf.ast.analysis.EnterPassTwo;
import fr.umlv.tatoo.cc.ebnf.ast.analysis.TypeVerifier;
import fr.umlv.tatoo.cc.lexer.charset.encoding.Encoding;
import fr.umlv.tatoo.cc.lexer.lexer.RuleFactory;
import fr.umlv.tatoo.cc.parser.grammar.EBNFSupport;
import fr.umlv.tatoo.cc.parser.grammar.GrammarFactory;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.VariableDecl;
import fr.umlv.tatoo.cc.tools.tools.ToolsFactory;

public class Analysis {
  public static boolean analyse(EBNFASTImpl ast,
      RuleFactory ruleFactory,Encoding encoding,GrammarFactory grammarFactory,
      EBNFSupport ebnfSupport,ToolsFactory toolsFactory) {
    
    // fake type verifier
    TypeVerifier typeVerifier=new TypeVerifier() {
      public boolean typeExist(Type type) {
        return true;
      }
    };
    HashMap<String,Type> importMap=new HashMap<String,Type>();
    LogInfoASTDiagnosticReporter diagnosticReporter=new LogInfoASTDiagnosticReporter(null);
    
    BindingMap bindingMap=createBindingMap(grammarFactory);
    
    EnterPassOne passOne=new EnterPassOne(bindingMap,importMap,encoding,ruleFactory,grammarFactory,toolsFactory,typeVerifier,diagnosticReporter);
    ast.getRoot().accept(passOne,null);
    EnterPassTwo passTwo=passOne.createEnterPassTwo(ebnfSupport);
    ast.getRoot().accept(passTwo,null);
    return diagnosticReporter.isOnError();
  }
  
  //FIXME Remi create cross compilation unit bindings
  private static BindingMap createBindingMap(GrammarFactory grammarFactory) {
    BindingMap bindingMap=new BindingMap();
    
    // update map if incremental (more than one ebnf file)
    for(VariableDecl variable:grammarFactory.getVariableMap().getAllValues()) {
      VariableBinding<?> binding;
      if (variable.isTerminal()) {
        binding=new TerminalBinding(null,(TerminalDecl)variable);
      } else {
        binding=new NonTerminalBinding(null,(NonTerminalDecl)variable);
      }
      
      bindingMap.registerBinding(variable,binding);
    }
    
    return bindingMap;
  }
}
