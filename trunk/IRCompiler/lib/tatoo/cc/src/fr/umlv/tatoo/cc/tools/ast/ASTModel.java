/*
 * Created on 4 mars 2006
 *
 */
package fr.umlv.tatoo.cc.tools.ast;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import fr.umlv.tatoo.cc.common.generator.ObjectId;
import fr.umlv.tatoo.cc.common.generator.Type;
import fr.umlv.tatoo.cc.parser.grammar.EBNFSupport;
import fr.umlv.tatoo.cc.parser.grammar.GrammarRepository;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.ProductionDecl;
import fr.umlv.tatoo.cc.parser.grammar.VariableDecl;
import fr.umlv.tatoo.cc.parser.grammar.EBNFSupport.StarDesc;
import fr.umlv.tatoo.cc.tools.tools.ToolsFactory;

public class ASTModel {
  public ASTModel(ASTNodeBuilder builder, 
      Set<? extends NonTerminalDecl> startNonTerminals) {
    this.startNonTerminals = startNonTerminals;
    this.builder=builder;
    this.allNodeMap=builder.getNodeMap();
  }
  
  public String getPackageName() {
    return builder.getPackageName();
  }
  
  public Collection<ASTNode> getAllNodes() {
    return builder.getAllNodes();
  }
  
  public <A extends ASTNode> Collection<A> getAllNodes(Class<A> clazz) {
    HashSet<A> list=new HashSet<A>();
    for(ASTNode node:getAllNodes())
      if (clazz.isInstance(node))
        list.add(clazz.cast(node));
    return list;
  }
  
  public void addAllProductions(GrammarRepository grammarItemsRepository,
      EBNFSupport ebnfSupport, ToolsFactory toolsfactory) {
    
    HashSet<ProductionDecl> productions=
      new HashSet<ProductionDecl>(grammarItemsRepository.getAllProductions());
    Map<VariableDecl, Type> varMap = toolsfactory.getVariableTypeMap();
    
    // search for non terminal with a single production
    Set<? extends NonTerminalDecl> fakeNonTerminalSet = ebnfSupport.getStarNonTerminals();
    for(Map.Entry<NonTerminalDecl,? extends Collection<? extends ProductionDecl>> entry:grammarItemsRepository.getProductionsByNonTerminal().entrySet()) {
      Collection<? extends ProductionDecl> value=entry.getValue();
      NonTerminalDecl nonTerminal=entry.getKey();
      if (value.size()==1) {
        ProductionDecl production=value.iterator().next();
        builder.newFlatNode(production,varMap,true);
        builder.setAsNonExpressive(nonTerminal,production);
        productions.remove(production);
      } else {
        if (!fakeNonTerminalSet.contains(nonTerminal))
          builder.newNonTerminalNode(nonTerminal,startNonTerminals.contains(nonTerminal));
      }
    }
    
    // search node that can be a composite node or an attribute list
    Map<NonTerminalDecl, ? extends StarDesc> starMap = ebnfSupport.getStarDescMap();
    for (Map.Entry<NonTerminalDecl, ? extends StarDesc> entry : starMap.entrySet()) {
      StarDesc starDesc = entry.getValue();

      VariableDecl separator = starDesc.getSeparator();
      if (separator != null) {
        Type type = varMap.get(separator);
        if (type != null && type != Type.VOID) {
          continue;
        }
      }

      // System.out.println("sep "+separator);
      VariableDecl enclosingElement = starDesc.getElement();
      NonTerminalDecl parentNT = entry.getKey();
      if (enclosingElement.isTerminal()) {
        // FIXME, varMap.get can return null
        builder.newAttributeListNode(parentNT,varMap.get(enclosingElement));
      } else {
        builder.newCompositeNode(parentNT,(NonTerminalDecl)enclosingElement);
      }
      // remove all ENBF productions
      productions.removeAll(starDesc.getFakeProductions());
    }
    // System.out.println(removalSet);
    
    // creates other production nodes
    for (ProductionDecl production:productions) {
      builder.newFlatNode(production,varMap,false);
    }
  }

  public Iterable<? extends Parameter<? extends ASTNode>> getStartParameters() {
    return startParameters;
  }

  private final ASTNodeBuilder builder;
  final Map<ObjectId,ASTNode> allNodeMap;
  final Set<? extends NonTerminalDecl> startNonTerminals;

  private final AbstractCollection<Parameter<ASTNode>> startParameters = new AbstractCollection<Parameter<ASTNode>>() {
    @Override
    public int size() {
      return startNonTerminals.size();
    }

    @Override
    public Iterator<Parameter<ASTNode>> iterator() {
      final Iterator<? extends NonTerminalDecl> it = startNonTerminals.iterator();
      return new Iterator<Parameter<ASTNode>>() {
        public boolean hasNext() {
          return it.hasNext();
        }

        public Parameter<ASTNode> next() {
          ASTNode node = allNodeMap.get(it.next());
          return new Parameter<ASTNode>(node, uncapitalize(node
              .getType().getSimpleRawName()));
        }

        public void remove() {
          throw new UnsupportedOperationException();
        }
      };
    }
  };
  
  static String capitalizeFirstLetter(String name) {
    return Character.toUpperCase(name.charAt(0)) + name.substring(1);
  }

  static String getter(String name, Type type) {
    String prefix;
    if (type == Type.BOOLEAN)
      prefix = "is";
    else
      prefix = "get";
    return prefix + capitalize(name);
  }

  static String setter(String name) {
    return "set" + capitalize(name);
  }

  private static String capitalize(String name) {
    return Character.toUpperCase(name.charAt(0)) + name.substring(1);
  }

  static String uncapitalize(String name) {
    return Character.toLowerCase(name.charAt(0)) + name.substring(1);
  }
}
