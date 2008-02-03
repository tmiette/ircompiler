package fr.umlv.tatoo.cc.linker.linker;

import java.util.HashMap;
import java.util.Map;

import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.VersionDecl;

public class GrammarModule {
  public GrammarModule(String name,GrammarModel model) {
    this.model=model;
    this.name=name;
  }
  
  public static class LinkEntry {
    public LinkEntry(GrammarModule grammar, NonTerminalDecl start, VersionDecl version) {
      this.grammar=grammar;
      this.start=start;
      this.version=version;
    }
    
    public GrammarModule getGrammarModule() {
      return grammar;
    }
    public NonTerminalDecl getStart() {
      return start;
    }
    public VersionDecl getVersion() {
      return version;
    }
    
    private final GrammarModule grammar;
    private final NonTerminalDecl start;
    private final VersionDecl version;
  }
  
  public String getName() {
    return name;
  }
  
  public GrammarModel getModel() {
    return model;
  }
  
  public Map<TerminalDecl,LinkEntry> getLinkMap() {
    return linkMap;
  }
  
  public void addLink(TerminalDecl terminal, GrammarModule grammar,NonTerminalDecl start,VersionDecl version) {
    linkMap.put(terminal,new LinkEntry(grammar,start,version));
  }
  
  private final String name;
  private final GrammarModel model;
  private final HashMap<TerminalDecl,LinkEntry> linkMap=
    new HashMap<TerminalDecl,LinkEntry>();
}
