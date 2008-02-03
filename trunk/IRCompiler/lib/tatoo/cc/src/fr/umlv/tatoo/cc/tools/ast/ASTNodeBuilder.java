package fr.umlv.tatoo.cc.tools.ast;

import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import fr.umlv.tatoo.cc.common.generator.ObjectId;
import fr.umlv.tatoo.cc.common.generator.Type;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.ProductionDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.VariableDecl;

public class ASTNodeBuilder {
  public ASTNodeBuilder(String packageName) {
    this.packageName=packageName;
  }
  
  public String getPackageName() {
    return packageName;
  }
  
  public interface Creator<A extends ASTNode> {
    public A getNode();
  }
  
  public abstract class LazyCreator<A extends ASTNode> implements Creator<A> {
    public A getNode() {
      if (node!=null)
        return node;
      return node=createNode();
    }
    protected abstract A createNode();
    private A node;
  }
  
  public Collection<ASTNode> getAllNodes() {
    return new AbstractCollection<ASTNode>() {
      @Override
      public int size() {
        return creatorMap.size();
      }
      @Override
      public Iterator<ASTNode> iterator() {
        final Iterator<Creator<?>> it=creatorMap.values().iterator();
        return new Iterator<ASTNode>() {
          public boolean hasNext() {
            return it.hasNext();
          }
          public ASTNode next() {
            return it.next().getNode();
          }
          public void remove() {
            throw new UnsupportedOperationException();
          }
        };
      }
    };
  }
  
  public Map<ObjectId,ASTNode> getNodeMap() {
    return new AbstractMap<ObjectId,ASTNode>() {
      @Override
      public int size() {
        return creatorMap.size();
      }
      @Override
      public ASTNode get(Object key) {
        Creator<?> creator=creatorMap.get(key);
        if (creator==null)
          return null;
        return creator.getNode();
      }
      @Override
      public Set<Entry<ObjectId,ASTNode>> entrySet() {
        final Set<Entry<ObjectId,Creator<?>>> set=creatorMap.entrySet();
        return new AbstractSet<Entry<ObjectId,ASTNode>>() {
          @Override
          public int size() {
            return set.size();
          }
          @Override
          public Iterator<Entry<ObjectId,ASTNode>> iterator() {
            final Iterator<Entry<ObjectId,Creator<?>>> it=set.iterator();
            return new Iterator<Entry<ObjectId,ASTNode>>() {
              public boolean hasNext() {
                return it.hasNext();
              }
              public Entry<ObjectId,ASTNode> next() {
                final Entry<ObjectId,Creator<?>> entry=it.next();
                // remplaced by AbstractMap.SimpleInmuttableentry
                return new Map.Entry<ObjectId,ASTNode>() {
                  public ObjectId getKey() {
                    return entry.getKey();
                  }
                  public ASTNode getValue() {
                    return entry.getValue().getNode();
                  }
                  public ASTNode setValue(ASTNode value) {
                    throw new UnsupportedOperationException();
                  }
                };
              }
              public void remove() {
                throw new UnsupportedOperationException();
              }
            };
          }
        };
      }
    };
  }
  
  <A extends ASTNode> A getDelayedNode(final ObjectId handle,final Class<A> clazz) {
    Creator<?> creator=creatorMap.get(handle);
    if (creator==null)
      throw new AssertionError("no creator for "+handle.getId()+
        " while asking a "+clazz.getName());
    ASTNode node=creator.getNode();
    if (!clazz.isInstance(node))
      throw new ClassCastException("can't cast a "+node.getClass().getName()+" named "+
        node.getId()+" to class "+clazz);
    return clazz.cast(node);
  }
  
  private <A extends ASTNode> Creator<A> register(ObjectId handle,Creator<A> creator) {
    creatorMap.put(handle,creator);
    return creator;
  }
  
  final String packageName;
  final LinkedHashMap<ObjectId,Creator<?>> creatorMap=
    new LinkedHashMap<ObjectId,Creator<?>>();
  
  public void setAsNonExpressive(NonTerminalDecl nonTerminal,ProductionDecl production) {
    Creator<?> creator=creatorMap.get(production);
    if (creator==null)
      throw new AssertionError("no creator for production "+production);
    creator=creatorMap.put(nonTerminal,creator);
    if (creator!=null)
      throw new AssertionError("already a creator for non-terminal "+nonTerminal);
  }
  
  public Creator<CompositeNode> newCompositeNode(final NonTerminalDecl nonTerminal,final NonTerminalDecl enclosingElement) {
    return register(nonTerminal,new LazyCreator<CompositeNode>() {
      @Override
      protected CompositeNode createNode() {
        return new CompositeNode(packageName,nonTerminal,getDelayedNode(enclosingElement,ElementNode.class));
      }
    });
  }
  
  public Creator<NonTerminalNode> newNonTerminalNode(final NonTerminalDecl nonTerminal,final boolean isStart) {
    return register(nonTerminal,new Creator<NonTerminalNode>() {
      public NonTerminalNode getNode() {
        return new NonTerminalNode(packageName,nonTerminal,isStart);
      }
    });
  }
  
  AttributeSingleNode newAttributeSingleNode(TerminalDecl terminal,Type type) {
    return new AttributeSingleNode(packageName,terminal,type);
  }
  
  public Creator<AttributeListNode> newAttributeListNode(final NonTerminalDecl nonTerminal,final Type elementType) {
    return register(nonTerminal,new Creator<AttributeListNode>() {
      public AttributeListNode getNode() {
        return new AttributeListNode(packageName,nonTerminal,elementType);
      }
    });
  }
  
  public Creator<ProductionNode> newFlatNode(final ProductionDecl production,final Map<VariableDecl,Type> varMap,final boolean isNonTerminalNotExpressive) {
    return register(production,new LazyCreator<ProductionNode>() {
      @Override
      protected ProductionNode createNode() {
        NonTerminalNode ntNode;
        if (isNonTerminalNotExpressive)
          ntNode=null;
        else
          ntNode=getDelayedNode(production.getLeft(),NonTerminalNode.class);
        
        ArrayList<ASTNode> nodes=new ArrayList<ASTNode>();
        for(VariableDecl var:production.getRight()) {
          if (var.isTerminal()) {
            TerminalDecl terminal = (TerminalDecl)var;
            Type type=varMap.get(terminal);
            if (type!=null && !type.isVoid())
              nodes.add(newAttributeSingleNode(terminal,type));
          }
          else {
            ASTNode node=getDelayedNode(var,ASTNode.class);
            nodes.add(node);
          }
        }
        
        return new FlatNode(packageName,production,ntNode,nodes);
      }
    });
  }
}
