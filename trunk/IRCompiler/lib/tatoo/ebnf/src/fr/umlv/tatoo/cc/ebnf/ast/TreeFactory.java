package fr.umlv.tatoo.cc.ebnf.ast;

import java.util.List;

import fr.umlv.tatoo.cc.ebnf.ast.TerminalDefAST.TerminalKind;
import fr.umlv.tatoo.cc.lexer.ebnf.parser.TerminalEnum;

public class TreeFactory {
  final AST ast;
  private final AnnotationComputer annotationComputer;
  public TreeFactory(AST ast,AnnotationComputer annotationComputer) {
    this.ast=ast;
    this.annotationComputer=annotationComputer;
  }
  
  public void setRoot(AbstractNodeAST root) {
    ast.setRoot(root);
  }
  
  public <V> TokenAST<V> createToken(TerminalEnum kind, V value) {
    TokenAST<V> token=new TokenAST<V>(ast,kind,value);
    annotationComputer.computeTokenAnnotation(token);
    return token;
  }
  
  public <V> TreeBuilder<SimpleNodeAST<V>> createSimpleNode(final Kind kind,final V value) {
    return new TreeBuilder<SimpleNodeAST<V>>(annotationComputer) {
      @Override
      protected SimpleNodeAST<V> createNode(List<TreeAST> nodes) {
        return new SimpleNodeAST<V>(ast,kind,value,nodes);
      }
    };
  }
  
  public TreeBuilder<ProductionIdAndVersionDefAST> createProductionIdAndVersionDef(final TokenAST<String> name, final VersionVarAST version) {
    return new TreeBuilder<ProductionIdAndVersionDefAST>(annotationComputer) {
      @Override
      protected ProductionIdAndVersionDefAST createNode(List<TreeAST> nodes) {
        return new ProductionIdAndVersionDefAST(ast,name,version,nodes);
      }
    };
  }
  
  public TreeBuilder<EnhancedDefAST> createEnhancedVariable(final EnhancedDefAST.Enhancement enhancement,final VariableVarAST element,final VariableVarAST separator,final List<NodeAST> vargroup) {
    return new TreeBuilder<EnhancedDefAST>(annotationComputer) {
      @Override
      protected EnhancedDefAST createNode(List<TreeAST> nodes) {
        return new EnhancedDefAST(ast,enhancement,element,separator,vargroup,nodes);
      }
    };
  }
  
  public TreeBuilder<ImportDefAST> createImportDef(final TokenAST<String> qualifiedId) {
    return new TreeBuilder<ImportDefAST>(annotationComputer) {
      @Override
      protected ImportDefAST createNode(List<TreeAST> nodes) {
        return new ImportDefAST(ast,qualifiedId,nodes);
      }
    };
  }
  
  public TreeBuilder<MacroDefAST> createMacroDef(final TokenAST<String> name,final String regex) {
    return new TreeBuilder<MacroDefAST>(annotationComputer) {
      @Override
      protected MacroDefAST createNode(List<TreeAST> nodes) {
        return new MacroDefAST(ast,name,regex,nodes);
      }
    };
  }
  
  public TreeBuilder<NonTerminalDefAST> createNonTerminalDef(final TokenAST<String> name,final TypeVarAST type,final List<ProductionDefAST> productions) {
    return new TreeBuilder<NonTerminalDefAST>(annotationComputer) {
      @Override
      protected NonTerminalDefAST createNode(List<TreeAST> nodes) {
        return new NonTerminalDefAST(ast,name,type,productions,nodes);
      }
    };
  }
  
  public TreeBuilder<UnquotedIdVarAST> createUnquotedIdVar(final TokenAST<String> name) {
    return new TreeBuilder<UnquotedIdVarAST>(annotationComputer) {
      @Override
      protected UnquotedIdVarAST createNode(List<TreeAST> nodes) {
        return new UnquotedIdVarAST(ast,name,nodes);
      }
    };
  }
  
  public TreeBuilder<PriorityDefAST> createPriorityDef(final TokenAST<String> name,final double number,final String assoc) {
    return new TreeBuilder<PriorityDefAST>(annotationComputer) {
      @Override
      protected PriorityDefAST createNode(List<TreeAST> nodes) {
        return new PriorityDefAST(ast,name,number,assoc,nodes);
      }
    };
  }
  
  public TreeBuilder<PriorityVarAST> createPriorityVar(final TokenAST<String> name) {
    return new TreeBuilder<PriorityVarAST>(annotationComputer) {
      @Override
      protected PriorityVarAST createNode(List<TreeAST> nodes) {
        return new PriorityVarAST(ast,name,nodes);
      }
    };
  }
  
  public TreeBuilder<ProductionDefAST> createProductionDef(
      final List<NodeAST> varlist,final PriorityVarAST priority,final ProductionIdAndVersionDefAST idAndVersion) {
    return new TreeBuilder<ProductionDefAST>(annotationComputer) {
      @Override
      protected ProductionDefAST createNode(List<TreeAST> trees) {
        return new ProductionDefAST(ast,varlist,priority,idAndVersion,trees);
      }
    };
  }
  
  public TreeBuilder<DirectiveDefAST> createDirectiveDef(final TokenAST<String> name) {
    return new TreeBuilder<DirectiveDefAST>(annotationComputer) {
      @Override
      protected DirectiveDefAST createNode(List<TreeAST> trees) {
        return new DirectiveDefAST(ast,name,trees);
      }
    };
  }
  
  public TreeBuilder<RuleDefAST> createRuleDef(final TokenAST<String> name,final String regex) {
    return new TreeBuilder<RuleDefAST>(annotationComputer) {
      @Override
      protected RuleDefAST createNode(List<TreeAST> trees) {
        return new RuleDefAST(ast,name,regex,trees);
      }
    };
  }
  
  public TreeBuilder<RootDefAST> createRootDef(final StartNonTerminalSetDefAST startNonTerminalSetDef) {
    return new TreeBuilder<RootDefAST>(annotationComputer) {
      @Override
      protected RootDefAST createNode(List<TreeAST> trees) {
        return new RootDefAST(ast,startNonTerminalSetDef,trees);
      }
    };
  }
  
  public TreeBuilder<StartNonTerminalSetDefAST> createStartNonTerminalSetDef(final List<UnquotedIdVarAST> startNonTerminalList) {
    return new TreeBuilder<StartNonTerminalSetDefAST>(annotationComputer) {
      @Override
      protected StartNonTerminalSetDefAST createNode(List<TreeAST> trees) {
        return new StartNonTerminalSetDefAST(ast,startNonTerminalList,trees);
      }
    };
  }
  
  public TreeBuilder<TerminalDefAST> createTerminalDef(final TerminalKind kind,final TokenAST<String> name,final AliasDefAST alias,
      final TypeVarAST type, final RuleDefAST rule, final PriorityVarAST priority) {
    return new TreeBuilder<TerminalDefAST>(annotationComputer) {
      @Override
      protected TerminalDefAST createNode(List<TreeAST> trees) {
        return new TerminalDefAST(ast,kind,alias,name,type,rule,priority,trees);
      }
    };
  }
  
  public TreeBuilder<QuotedIdVarAST> createQuotedIdVar(final TokenAST<String> name) {
    return new TreeBuilder<QuotedIdVarAST>(annotationComputer) {
      @Override
      protected QuotedIdVarAST createNode(List<TreeAST> trees) {
        return new QuotedIdVarAST(ast,name,trees);
      }
    };
  }
  
  public TreeBuilder<TypeVarAST> createTypeVar(final TokenAST<String> qualifiedid) {
    return new TreeBuilder<TypeVarAST>(annotationComputer) {
      @Override
      protected TypeVarAST createNode(List<TreeAST> nodes) {
        return new TypeVarAST(ast,qualifiedid,nodes);
      }
    };
  }
  
  public TreeBuilder<VersionDefAST> createVersionDef(final TokenAST<String> name,final VersionVarAST parent) {
    return new TreeBuilder<VersionDefAST>(annotationComputer) {
      @Override
      protected VersionDefAST createNode(List<TreeAST> nodes) {
        return new VersionDefAST(ast,name,parent,nodes);
      }
    };
  }

  public TreeBuilder<AliasDefAST> createAliasDef(final TokenAST<String> name) {
    return new TreeBuilder<AliasDefAST>(annotationComputer) {
      @Override
      protected AliasDefAST createNode(List<TreeAST> nodes) {
        return new AliasDefAST(ast,name,nodes);
      }
    };
  }

  
  public TreeBuilder<VariableTypeDefAST> createVariableTypeDef(final VariableVarAST variable,final TypeVarAST type) {
    return new TreeBuilder<VariableTypeDefAST>(annotationComputer) {
      @Override
      protected VariableTypeDefAST createNode(List<TreeAST> nodes) {
        return new VariableTypeDefAST(ast,variable,type,nodes);
      }
    };
  }
  
  public TreeBuilder<VersionVarAST> createVersionVar(final TokenAST<String> name) {
    return new TreeBuilder<VersionVarAST>(annotationComputer) {
      @Override
      protected VersionVarAST createNode(List<TreeAST> nodes) {
        return new VersionVarAST(ast,name,nodes);
      }
    };
  }
}