package fr.umlv.tatoo.cc.parser.table;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.RuntimeInstance;

import fr.umlv.tatoo.cc.common.log.Info;
import fr.umlv.tatoo.cc.common.util.MultiMap;
import fr.umlv.tatoo.cc.common.velocity.VelocityLogger;
import fr.umlv.tatoo.cc.parser.grammar.Grammar;
import fr.umlv.tatoo.cc.parser.grammar.GrammarSets;
import fr.umlv.tatoo.cc.parser.grammar.NonTerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.ProductionDecl;
import fr.umlv.tatoo.cc.parser.grammar.TerminalDecl;
import fr.umlv.tatoo.cc.parser.grammar.VariableDecl;
import fr.umlv.tatoo.cc.parser.parser.AcceptActionDecl;
import fr.umlv.tatoo.cc.parser.parser.BranchActionDecl;
import fr.umlv.tatoo.cc.parser.parser.EnterActionDecl;
import fr.umlv.tatoo.cc.parser.parser.ErrorActionDecl;
import fr.umlv.tatoo.cc.parser.parser.ExitActionDecl;
import fr.umlv.tatoo.cc.parser.parser.ReduceActionDecl;
import fr.umlv.tatoo.cc.parser.parser.ShiftActionDecl;
import fr.umlv.tatoo.cc.parser.parser.SimpleActionDeclVisitor;
import fr.umlv.tatoo.cc.parser.parser.VersionedActionDecl;

/**
 * @author Gilles
 * @version $Revision: 1328 $ $Date: 2007-11-06 17:28:51 +0100 (mar., 06 nov. 2007) $
 */
public class TableWriter {

  
  public static <I extends NodeItem<I>> void dumpTable(File log,
      Grammar grammar,
      GrammarSets grammarSets,
      NodeFactory<I> nodeFactory,
      Map<NodeDecl<I>, ? extends MultiMap<TerminalDecl,?>> table,
      MultiMap<NodeDecl<I>,?> branch,
      Map<NodeDecl<I>, ? extends Map<NonTerminalDecl,NodeDecl<I>>> buildedGotos) {
    VelocityContext root = new VelocityContext();
    root.put("mapGetter", MapGetter.getInstance());
    root.put("grammar", grammar);
    root.put("grammarSets",grammarSets);
    root.put("nodeFactory", nodeFactory);
    root.put("table",table);
    root.put("branch",new HashMap<NodeDecl<I>,Object>(branch));
    root.put("buildedGotos", buildedGotos);
    root.put("displayVisitor", new SimpleActionDeclVisitor<String>() {
      @Override
      public String visit(ErrorActionDecl error) {
        return "error\u00a0"+error.getMessage();
      }
      @Override
      public String visit(BranchActionDecl error) {
        return "branch\u00a0"+error.getMessage();
      }
      @Override
      public String visit(ReduceActionDecl reduce) {
        ProductionDecl prod = reduce.getProduction();
        StringBuilder b = new StringBuilder("reduce\u00a0by\u00a0");
        b.append(prod.getLeft()).append("\u00a0::=\u00a0");
        if (prod.getRight().isEmpty())
          b.append("\u025b");
        else {
          for(VariableDecl v:prod.getRight())
            b.append(v).append("\u00a0");
          b.setLength(b.length()-1);
        }
        return b.toString();
      }
      @Override
      public String visit(ShiftActionDecl shift) {
        return String.format("shift\u00a0to\u00a0<a href=\"#%1$s\">%1$s</a>",shift.getState());
      }
      @Override
      public String visit(AcceptActionDecl accept) {
        return "accept";
      }
      @Override
      public String visit(VersionedActionDecl versioned) {
        throw new UnsupportedOperationException("invalid action type");
      }
      @Override
      public String visit(EnterActionDecl enter) {
        return "enter\u00a0"+enter.getBranchingTerminal().getId();
      }
      @Override
      public String visit(ExitActionDecl exit) {
        return "exit\u00a0"+exit.getId();
      }
    });
    RuntimeInstance ri = new RuntimeInstance();
    ri.setProperty("runtime.log.logsystem", new VelocityLogger());
    ri.setProperty("file.resource.loader.class", "fr.umlv.tatoo.cc.common.velocity.ClassResourceLoader");
    ri.setProperty("file.resource.loader.resourceClass", TableWriter.class);
//   ri.setProperty("velocimacro.library", "macros.vm");
    try {
      ri.init();
      Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(log),"UTF-8"));
      Template template = ri.getTemplate("debugToXHTML.vm", "UTF-8");
      template.merge(root, out);
      out.close();
      
      Info.info("Grammar table wrote to log file: %s",log).report();
    } catch(Exception e) {
      Info.error("error while writing table to log file: %s",log).
        cause(e).report();
    }

        
//    InputStream stylesheet=TableWriter.class.getResourceAsStream("debugToXHTML.xsl");
//    //System.out.println(stylesheet);
//    
//    SAXTransformerFactory factory=(SAXTransformerFactory)TransformerFactory.newInstance();
//    Templates template;
//    try {
//      template=factory.newTemplates(new StreamSource(stylesheet));
//    } catch (TransformerConfigurationException e) {
//      throw new AssertionError(e);
//    }
//    
//    try {
//      TransformerHandler handler;
//      try {
//        handler = factory.newTransformerHandler(template);
//      } catch (TransformerConfigurationException e) {
//        throw new AssertionError(e);
//      }
//
//      handler.setResult(new StreamResult(log));
//      handler.startDocument();
//
//      XMLEmitter emitter=new XMLEmitter(handler);
//      emitter.emitStartElement("debug");
//      dumpNonTerminalSet(emitter,grammar,grammarSets);
//      dumpStartStates(emitter,nodeFactory.getStartStateMap());
//      dumpStates(emitter,table,nodeFactory,buildedGotos);
//      emitter.emitEndElement("debug");
//
//      handler.endDocument();
//      TatooLogger.info("Grammar table wrote to log file:"+ log);
//    } catch(SAXException e) {
//      TatooLogger.warning(e.getMessage());
//    }

  }
//private static void dumpTerminalSet(XMLEmitter emitter,
//Set<? extends TerminalDecl> set) throws SAXException {
//
//for (TerminalDecl terminal : set) {
//emitter.emitElement("terminal","name",terminal.getId());
//}
//}
//
//private static void dumpNonTerminalSet(XMLEmitter emitter,Grammar grammar,GrammarSets grammarSets) throws SAXException {
//emitter.emitStartElement("non-terminal-set");
//
//Set<? extends NonTerminalDecl> starts = grammar.getStarts();
//for (NonTerminalDecl d : grammar.getNonTerminals()) {
//emitter.emitStartElement("non-terminal","name",d,"derivesToEpsilon",grammarSets.derivesToEpsilon(d)?"true":"false");
//emitter.emitStartElement("terminals-first");
//dumpTerminalSet(emitter, grammarSets.first(d));
//emitter.emitEndElement("terminals-first");
//if (!starts.contains(d)) {
//  emitter.emitStartElement("terminals-follow");
//  dumpTerminalSet(emitter, grammarSets.follow(d));
//  emitter.emitEndElement("terminals-follow");
//}
//emitter.emitEndElement("non-terminal");
//}
//emitter.emitEndElement("non-terminal-set");
//}
//
//private static <I extends NodeItem<I>> void dumpStartStates(XMLEmitter emitter,Map<NonTerminalDecl,NodeDecl<I>> startMap) throws SAXException {
//emitter.emitStartElement("start-states");
//
//for (Map.Entry<NonTerminalDecl,NodeDecl<I>> startEntry:startMap.entrySet()) {
//emitter.emitElement("start-state",
//  "non-terminal",startEntry.getKey().getId(),
//  "start",startEntry.getValue());
//}
//emitter.emitEndElement("start-states");
//}
//
//private static <I extends NodeItem<I>> void dumpStates(XMLEmitter emitter,
//HashMap<TerminalDecl,MultiMap<NodeDecl<I>,ActionEntry>> table,
//NodeFactory<I> nodeFactory,
//HashMap<NonTerminalDecl,HashMap<NodeDecl<I>,NodeDecl<I>>> buildedGotos) throws SAXException {
//
//emitter.emitStartElement("states");
//
//
//for (NodeDecl<I> node:nodeFactory.getNodes()) {
//emitter.emitStartElement("state","value",node);
//
//emitter.emitStartElement("kernel-items");
//for (I item : node.getKernelItems()) {
//  item.emit(emitter);
//}
//emitter.emitEndElement("kernel-items");
//
//emitter.emitStartElement("action-map");
//for (Map.Entry<TerminalDecl,MultiMap<NodeDecl<I>,ActionEntry>> entry : table.entrySet()) {
//  TerminalDecl terminal = entry.getKey();
//  
//  Set<ActionEntry> set = entry.getValue().get(node);
//  if (isNotError(set)) {
//    emitter.emitStartElement("actions","terminal",terminal.getId());
//  
//    for(ActionEntry action:set) {
//      action.getAction().emit(emitter);
//    }
//    emitter.emitEndElement("actions");
//  }
//}
//emitter.emitEndElement("action-map");
//
//emitter.emitStartElement("gotoes");
//for (Map.Entry<NonTerminalDecl,HashMap<NodeDecl<I>,NodeDecl<I>>> entry : buildedGotos.entrySet()) {
//  NodeDecl<I> to=entry.getValue().get(node);
//  if (to!=null)
//    emitter.emitElement("goto","non-terminal",entry.getKey(),"to",to);
//}
//emitter.emitEndElement("gotoes");
//
//emitter.emitEndElement("state");
//}
//
//emitter.emitEndElement("states");
//}
//
//private static boolean isNotError(Set<ActionEntry> set) {
//for(ActionEntry action:set) {
// if (!action.getAction().isError())
//   return true;
//}
//return false;
//}
}