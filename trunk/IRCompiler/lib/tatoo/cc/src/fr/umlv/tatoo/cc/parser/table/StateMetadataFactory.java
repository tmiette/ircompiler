package fr.umlv.tatoo.cc.parser.table;

import java.util.HashMap;
import java.util.Set;

import fr.umlv.tatoo.cc.parser.grammar.VariableDecl;
import fr.umlv.tatoo.cc.parser.grammar.VersionDecl;

public class StateMetadataFactory {
  /**
   * Create a state meta-data.
   * @param compatibleVersions set of version compatible with the state, of null, if all are compatible
   * @param variable associated terminal or non terminal
   */
  public StateMetadataDecl create(Set<? extends VersionDecl> compatibleVersions,VariableDecl variable) {
    StateMetadataMap map=versionsMap.get(compatibleVersions);
    if (map==null) {
      map=new StateMetadataMap(versionsMap.size(),compatibleVersions);
      versionsMap.put(compatibleVersions,map);
    }
    return map.createStateMetadata(variable);
  }
  
  static class StateMetadataMap {
    public StateMetadataMap(int index,Set<? extends VersionDecl> compatibleVersions) {
      this.index=index;
      this.compatibleVersions=compatibleVersions;
    }
    public StateMetadataDecl createStateMetadata(VariableDecl variable) {
      StateMetadataDecl stateMetadata=map.get(variable);
      if (stateMetadata==null) {
        String name=getId(variable);
        stateMetadata=new StateMetadataDecl(name,compatibleVersions,variable);
        map.put(variable,stateMetadata);
      }
      return stateMetadata;
    }
    
    private String getId(VariableDecl variable) {
      String id=(variable!=null)?variable.getId():"null";
      return "metadata"+index+id;
    }
    
    private final int index;
    private final Set<? extends VersionDecl> compatibleVersions;
    private final HashMap<VariableDecl,StateMetadataDecl> map=
      new HashMap<VariableDecl,StateMetadataDecl>();
  }
  
  private final HashMap<Set<? extends VersionDecl>,StateMetadataMap> versionsMap=
    new HashMap<Set<? extends VersionDecl>,StateMetadataMap>();
}