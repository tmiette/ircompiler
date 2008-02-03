/*
 * Created on 4 mars 2006
 *
 */
package fr.umlv.tatoo.cc.tools.ast.generator;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

import org.apache.velocity.VelocityContext;

import fr.umlv.tatoo.cc.common.generator.Generator;
import fr.umlv.tatoo.cc.common.generator.GeneratorException;
import fr.umlv.tatoo.cc.common.generator.Type;
import fr.umlv.tatoo.cc.common.main.GeneratorBean;
import fr.umlv.tatoo.cc.tools.ast.ASTModel;
import fr.umlv.tatoo.cc.tools.ast.ASTNode;
import fr.umlv.tatoo.cc.tools.ast.AttributeNode;
import fr.umlv.tatoo.cc.tools.ast.CompositeNode;
import fr.umlv.tatoo.cc.tools.ast.FlatNode;
import fr.umlv.tatoo.cc.tools.ast.NonTerminalNode;
import fr.umlv.tatoo.cc.tools.ast.Parameter;
import fr.umlv.tatoo.cc.tools.ast.ProductionNode;
import fr.umlv.tatoo.cc.tools.generator.UpperCaser;
import fr.umlv.tatoo.cc.tools.main.ToolsAliasPrototype;

public class ASTGenerator extends Generator{
  public ASTGenerator(File sourceDir) throws GeneratorException {
    super(sourceDir,ASTGenerator.class);
  }
  
  public void generate(GeneratorBean bean,ASTModel model) throws GeneratorException {
    String packageRoot=model.getPackageName();
    // use a visitor instead ??
    for(ASTNode node:model.getAllNodes()) {
      if (node instanceof CompositeNode)
        generateCompositeNode(packageRoot,(CompositeNode)node);
      else
        if (node instanceof NonTerminalNode)
          generateNonTerminalNode(packageRoot,(NonTerminalNode)node);  
        else 
          if (node instanceof FlatNode)
            generateFlatNode(packageRoot,(FlatNode)node);  
    }
    
    generateVisitor(packageRoot,model);
    generateASTEvaluator(bean,packageRoot,model);
    generateXMLFactory(packageRoot,model);
  }
  
  private void generateNonTerminalNode(String packageRoot,NonTerminalNode node) throws GeneratorException {
    TreeSet<Type> imports=new TreeSet<Type>();
    imports.add(Type.createQualifiedType("fr.umlv.tatoo.runtime.ast.Node"));
    Type elementSuperType=node.getElementSuperType();
    if (elementSuperType!=null) {
      elementSuperType.addImportsTo(imports);
      imports.add(Type.createQualifiedType("java.util.List"));
    }
    Type parentSuperType=node.getParentSuperType();
    if (parentSuperType!=null)
      parentSuperType.addImportsTo(imports);
    
    HashMap<String, Object> root = new HashMap<String, Object>();
    root.put("package",packageRoot);
    root.put("node",node);
    root.put("imports",imports);
    generate(root,"NonTerminalNode",node.getType());
  }
  
  private void generateCompositeNode(String packageRoot,CompositeNode node) throws GeneratorException {
    Type elementType=node.getEnclosingParameter().getNode().getType();
    
    Type parentSuperType=node.getParentSuperType();
    if (parentSuperType==null) {
      parentSuperType=Type.createQualifiedType("fr.umlv.tatoo.runtime.ast.Node");
    }
    
    TreeSet<Type> imports=new TreeSet<Type>();
    Collections.addAll(imports,
      Type.createQualifiedType("java.io.Serializable"),
      Type.createQualifiedType("java.util.List"),
      Type.createQualifiedType("java.util.Collections"),
      Type.createQualifiedType("fr.umlv.tatoo.runtime.ast.CompositeNode"),
      Type.createQualifiedType("fr.umlv.tatoo.runtime.ast.NodeVisitor"));
    elementType.addImportsTo(imports);
    parentSuperType.addImportsTo(imports);
    
    HashMap<String, Object> root = new HashMap<String, Object>();
    root.put("package",packageRoot);
    root.put("node",node);
    root.put("elementType",elementType);
    root.put("parentSuperType",parentSuperType);
    root.put("imports",imports);
    generate(root,"CompositeNode",node.getType());
  }

  private void generateFlatNode(String packageRoot,FlatNode node) throws GeneratorException {
    Type elementSuperType;
    if (node.getElements().isEmpty()) {
      elementSuperType=node.getParent().getElementSuperType();
    } else {
      elementSuperType=node.getElementSuperType();
    }
    if (elementSuperType==null) {
      elementSuperType=Type.createQualifiedType("fr.umlv.tatoo.runtime.ast.Node");
    }
    
    Type attributeSuperType=node.getAttributeSuperType();
    if (attributeSuperType==null) {
      if (node.getAttributes().isEmpty())
        attributeSuperType=Type.createQualifiedType("fr.umlv.tatoo.runtime.ast.Node.NoValue");
      else
        attributeSuperType=Type.createQualifiedType("java.lang.Object");
    }
    
    Type parentSuperType=node.getParent().getParentSuperType();
    if (parentSuperType==null) {
      parentSuperType=Type.createQualifiedType("fr.umlv.tatoo.runtime.ast.Node");
    }
    
    TreeSet<Type> imports=new TreeSet<Type>();
    Collections.addAll(imports,
      Type.createQualifiedType("java.io.Serializable"),
      Type.createQualifiedType("fr.umlv.tatoo.runtime.ast.FlatNode"),
      Type.createQualifiedType("fr.umlv.tatoo.runtime.ast.NodeVisitor"));
    elementSuperType.addImportsTo(imports);
    attributeSuperType.addImportsTo(imports);
    for(Parameter<AttributeNode> params:node.getAttributes()) {
      Type type=params.getNode().getType();
      if (type.isQualifiedType())
        imports.add(type);
    }
    parentSuperType.addImportsTo(imports);
    
    Type nodeType=node.getType();
    HashMap<String, Object> root = new HashMap<String, Object>();
    root.put("package",packageRoot);
    root.put("node",node);
    root.put("nodeName",nodeType.getSimpleName());
    root.put("elementSuperType",elementSuperType);
    root.put("parentSuperType",parentSuperType);
    root.put("attributeSuperType",attributeSuperType);
    root.put("imports",imports);
    generate(root,"FlatNode",nodeType);
  }
  
  private void generateVisitor(String packageRoot,ASTModel model) throws GeneratorException {
    HashMap<String, Object> root = new HashMap<String, Object>();
    root.put("package",packageRoot);
    root.put("nonTerminals",model.getAllNodes(NonTerminalNode.class));
    root.put("productions",model.getAllNodes(ProductionNode.class));
    Type type=Type.createQualifiedType(packageRoot+".Visitor");
    generate(root,"Visitor",type);
  }
  
  private void generateASTEvaluator(GeneratorBean bean,String packageRoot,ASTModel model) throws GeneratorException {
    HashMap<String, Object> root = new HashMap<String, Object>();
    root.put("upperCaser", UpperCaser.getInstance());
    root.put("package",packageRoot);
    root.put("flats",model.getAllNodes(FlatNode.class));
    root.put("startNonTerminalParameters", model.getStartParameters());
    root.put("grammarEvaluator",bean.getAliasMap().get(ToolsAliasPrototype.grammarEvaluator).getType());
    
    TreeSet<Type> imports=new TreeSet<Type>();
    for(FlatNode node:model.getAllNodes(FlatNode.class)) {
      for(Parameter<AttributeNode> params:node.getAttributes()) {
        Type type=params.getNode().getType();
        if (type.isQualifiedType())
          imports.add(type);
      }
    }
    root.put("imports",imports);
    
    Type type=Type.createQualifiedType(packageRoot+".ASTEvaluator");
    generate(root,"ASTEvaluator",type);
  }
  
  private void generateXMLFactory(String packageRoot,ASTModel model) throws GeneratorException {
    HashMap<String, Object> root = new HashMap<String, Object>();
    root.put("package",packageRoot);
    root.put("nodes",model.getAllNodes(ProductionNode.class));
    Type type=Type.createQualifiedType(packageRoot+".XMLFactory");
    generate(root,"XMLFactory",type);
  }
}
