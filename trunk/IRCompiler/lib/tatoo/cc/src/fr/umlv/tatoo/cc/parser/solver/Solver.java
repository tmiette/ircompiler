/*
 * Created on Jun 2, 2003
 *
 */
package fr.umlv.tatoo.cc.parser.solver;

import java.util.*;

/**
 * @author jcervell
 * @param <K> 
 * @param <V> 
 *
 */
public class Solver<K,V> {
  
  private final NodeFactory<K,V> factory;
  private final HashMap<K,NodeInfo<K,V>> infoNodes = 
    new HashMap<K,NodeInfo<K,V>>();
  
  public Solver(NodeFactory<K,V> factory) {
    this.factory=factory;
  }

  private static class NodeInfo<L,W> {
    private final NodeContent<L,W> node;
    // nodes which depend on the node (reverse of NodeContent.dependencies()) 
    private final HashSet<L> dependancyKeys;

    NodeInfo(NodeContent<L,W> node) {
      this.node = node;
      dependancyKeys = new HashSet<L>();
    }

    void addDependancy(L key) {
      dependancyKeys.add(key);
    }

    void removeDependancy(L key) {
      dependancyKeys.remove(key);
    }

    HashSet<L> getDependancies() {
      return dependancyKeys;
    }

    NodeContent<L,W> getNode() {
      return node;
    }

  }

  /* modified contains all the nodes that are newly created or modifed */
  private NodeInfo<K,V> createNodeInfo(K key,HashMap<K,NodeInfo<K,V>> modified) {
		NodeContent<K, V> node = factory.getNode(key);   
        NodeInfo<K,V> info=new NodeInfo<K,V>(node);
        infoNodes.put(key,info);
        
		Set<K> set = node.dependencies();
    
		for(K targetKey: set) {
          NodeInfo<K,V> targetInfo = infoNodes.get(targetKey);
          if(targetInfo == null) {
            targetInfo = createNodeInfo(targetKey,modified);
          }
          modified.put(targetKey,targetInfo);
          targetInfo.addDependancy(key);
		}
		return info;
  }
  
  public V solve(K key) {
    NodeInfo<K,V> info=infoNodes.get(key);

    if (info!=null) {
      return info.getNode().getResult();
    }
    HashMap<K, NodeInfo<K, V>> newNodes = new HashMap<K,NodeInfo<K,V>>();
    info=createNodeInfo(key,newNodes);

    
     HashMap<K, NodeInfo<K, V>> hasChanged = newNodes;	
     HashMap<K, NodeInfo<K, V>> buildingHasChanged = new HashMap<K, NodeInfo<K, V>>();
		
		while(! hasChanged.isEmpty()) {
			for(Map.Entry<K, NodeInfo<K, V>> entry : hasChanged.entrySet()) {
				K sourceKey=entry.getKey();
				NodeInfo<K,V> sourceInfo=entry.getValue();
				for (K targetKey : sourceInfo.getDependancies()) {
					NodeInfo<K,V> targetInfo=infoNodes.get(targetKey);
					if (targetInfo.getNode().hasChanged(sourceKey,sourceInfo.getNode())) {
						buildingHasChanged.put(targetKey,targetInfo);
					}
				}
			}
			
			HashMap<K,NodeInfo<K,V>> tmpMap=hasChanged;
			hasChanged=buildingHasChanged;
			buildingHasChanged=tmpMap;
			buildingHasChanged.clear();


      
 		}
    
    return info.getNode().getResult();

  }
  

}
