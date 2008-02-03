package fr.umlv.tatoo.cc.tools.ast.generator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import fr.umlv.tatoo.cc.common.extension.Extension;
import fr.umlv.tatoo.cc.common.extension.ExtensionBus;
import fr.umlv.tatoo.cc.common.extension.ExtensionBus.Context;
import fr.umlv.tatoo.cc.common.extension.ExtensionBus.Registry;
import fr.umlv.tatoo.cc.common.generator.GeneratorException;
import fr.umlv.tatoo.cc.common.generator.ObjectId;
import fr.umlv.tatoo.cc.common.generator.Type;
import fr.umlv.tatoo.cc.common.main.CommonDataKeys;
import fr.umlv.tatoo.cc.common.main.GeneratorBean;
import fr.umlv.tatoo.cc.common.main.Unit;
import fr.umlv.tatoo.cc.lexer.lexer.RuleFactory;
import fr.umlv.tatoo.cc.lexer.main.LexerDataKeys;
import fr.umlv.tatoo.cc.parser.grammar.EBNFSupport;
import fr.umlv.tatoo.cc.parser.grammar.GrammarRepository;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.ProductionDecl;
import fr.umlv.tatoo.cc.parser.grammar.VariableDecl;
import fr.umlv.tatoo.cc.parser.grammar.EBNFSupport.StarDesc;
import fr.umlv.tatoo.cc.parser.main.ParserDataKeys;
import fr.umlv.tatoo.cc.tools.ast.ASTModel;
import fr.umlv.tatoo.cc.tools.ast.ASTNode;
import fr.umlv.tatoo.cc.tools.ast.ASTNodeBuilder;
import fr.umlv.tatoo.cc.tools.ast.CompositeNode;
import fr.umlv.tatoo.cc.tools.generator.ToolsGenerator;
import fr.umlv.tatoo.cc.tools.main.ToolsDataKeys;
import fr.umlv.tatoo.cc.tools.tools.ToolsFactory;

public class ToolsASTExtension implements Extension {
  public void register(Registry registry) {
    registry.register(CommonDataKeys.bean,
      LexerDataKeys.ruleFactory,
      ParserDataKeys.grammarRepository,
      ParserDataKeys.ebnfSupport,
      ToolsDataKeys.toolsFactory);
  }

  public void execute(ExtensionBus bus,Context context) {
    GeneratorBean bean = context.getData(CommonDataKeys.bean);
    RuleFactory ruleFactory=context.getData(LexerDataKeys.ruleFactory);
    GrammarRepository grammarRepository=context.getData(ParserDataKeys.grammarRepository);
    EBNFSupport ebnfSupport = context.getData(ParserDataKeys.ebnfSupport);
    ToolsFactory toolsFactory=context.getData(ToolsDataKeys.toolsFactory);
    
    //System.out.println(this);
    ASTNodeBuilder builder=new ASTNodeBuilder(bean.getPackage(Unit.tools));
    ASTModel model=new ASTModel(builder,grammarRepository.getStartNonTerminalSet());
    model.addAllProductions(grammarRepository,ebnfSupport,toolsFactory);

    Map<NonTerminalDecl,? extends StarDesc> starMap = ebnfSupport.getStarDescMap();
    
    HashSet<ProductionDecl> astSet=new HashSet<ProductionDecl>();
    for(CompositeNode node:model.getAllNodes(CompositeNode.class)) {
      StarDesc desc=starMap.get(node.getObjectId());
      astSet.addAll(desc.getFakeProductions());
    }
    
    HashMap<VariableDecl, Type> variableTypeMap=new HashMap<VariableDecl,Type>();
    variableTypeMap.putAll(toolsFactory.getTerminalTypeMap());
    for(Map.Entry<ObjectId,ASTNode> entry:builder.getNodeMap().entrySet()) {
      ObjectId objectId=entry.getKey();
      if (objectId instanceof VariableDecl) {
        VariableDecl var = (VariableDecl)objectId;
        ASTNode node=entry.getValue();
        Type type=node.getType();
        variableTypeMap.put(var,type);
        
        // propagate type to sub non-terminal of a star non terminal
        StarDesc starDesc=starMap.get(var);
        if (starDesc!=null) {
          NonTerminalDecl subNonTerminal=starDesc.getSubNonTerminal();
          
          // System.out.println("subNonterminal "+subNonTerminal+" type "+type);
          
          if (subNonTerminal!=null)
            variableTypeMap.put(subNonTerminal,type);
        }
      }
    }
      
    toolsFactory.getVariableTypeMap().clear();
    toolsFactory.getVariableTypeMap().putAll(variableTypeMap);
    
    try {
      new ToolsGenerator(bean.getDestination()).generate(
        bean,ruleFactory,grammarRepository,ebnfSupport,toolsFactory,
        astSet);
    } catch (GeneratorException e) {
      throw new IllegalStateException(e);
    }
    
    try {
      new ASTGenerator(bean.getDestination()).generate(bean,model);
    } catch (GeneratorException e) {
      throw new IllegalStateException(e);
    }
  }
}
