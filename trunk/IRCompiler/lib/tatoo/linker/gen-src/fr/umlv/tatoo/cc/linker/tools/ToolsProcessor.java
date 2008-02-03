package fr.umlv.tatoo.cc.linker.tools;

import fr.umlv.tatoo.cc.linker.lexer.RuleEnum;
import fr.umlv.tatoo.cc.linker.parser.TerminalEnum;
import fr.umlv.tatoo.cc.linker.parser.NonTerminalEnum;
import fr.umlv.tatoo.cc.linker.parser.ProductionEnum;
import fr.umlv.tatoo.cc.linker.tools.TerminalEvaluator;
import fr.umlv.tatoo.cc.linker.tools.GrammarEvaluator;

import fr.umlv.tatoo.runtime.buffer.LexerBuffer;
import fr.umlv.tatoo.runtime.lexer.LexerListener;
import fr.umlv.tatoo.runtime.parser.SimpleParser;
import fr.umlv.tatoo.runtime.parser.SmartStepReturn;
import fr.umlv.tatoo.runtime.tools.DataViewer;
import fr.umlv.tatoo.runtime.tools.GenericStack;
import fr.umlv.tatoo.runtime.tools.AbstractToolsProcessor;

/**  This class is called by the parser when
 *   <ol>
 *    <li>a terminal is shifted
 *    <li>a non terminal is reduced
 *    <li>a non terminal is accepted
 *   </ol>
 *   In that case, depending on the information of the .xtls, terminal and non-terminal
 *   values are pushed or pop from a semantic stack.
 *   
 *   Furthermore, in case of error recovery, values of the stack can be pop out
 *   depending if the last recognized element is a terminal or a non-terminal.
 * 
 *  This class is generated - please do not edit it 
 */
public class ToolsProcessor<B extends LexerBuffer,D>
  extends AbstractToolsProcessor<B,RuleEnum,TerminalEnum,NonTerminalEnum,ProductionEnum> {
 
  B buffer;
    
           
  private final GrammarEvaluator grammarEvaluator;
  private final TerminalEvaluator<? super D> terminalEvaluator;
  private final DataViewer<? super B,? extends D> dataViewer;
  private final GenericStack stack;

  /** Creates a tools processor.
      This constructor allows to share the same stack between more
      than one parser processor.
      @param terminalEvaluator the terminal evaluator.
      @param grammarEvaluator the grammar evaluator.
      @param stack the stack used by the processor
   */
  private ToolsProcessor(TerminalEvaluator<? super D> terminalEvaluator, GrammarEvaluator grammarEvaluator, DataViewer<? super B,? extends D> dataViewer, GenericStack stack) {
    this.terminalEvaluator=terminalEvaluator;
    this.grammarEvaluator=grammarEvaluator;
    this.dataViewer=dataViewer;
    this.stack=stack;
  }
  
  public static <B extends LexerBuffer,D> AbstractToolsProcessor<B,RuleEnum,TerminalEnum,NonTerminalEnum,ProductionEnum>
    createToolsProcessor(TerminalEvaluator<? super D> terminalEvaluator, GrammarEvaluator grammarEvaluator, DataViewer<? super B,? extends D> dataViewer, GenericStack stack) {
    
    return new ToolsProcessor<B,D>(terminalEvaluator,grammarEvaluator,dataViewer,stack);
  }
  
  /** Creates a lexer listener that forwards recognized rule to the parser.
   * @param parser a parser
   * @return a lexer listener.
   */
  @Override
  public LexerListener<RuleEnum,B> createLexerListener(SimpleParser<? super TerminalEnum> parser) {
    return new LexerAdapter(parser);
  }
  
  protected class LexerAdapter implements LexerListener<RuleEnum,B> {
    private final SimpleParser<? super TerminalEnum> parser;
    
    protected LexerAdapter(SimpleParser<? super TerminalEnum> parser) {
      this.parser=parser;
    }
    
    
    /**
     * {@inheritDoc}
     */
    public final void ruleVerified(RuleEnum rule, int lastTokenLength,B buffer)  {   
    ToolsProcessor.this.buffer=buffer; 
    try {
      switch(rule) {
        case grammar:
                 if (parser.smartStep(TerminalEnum.grammar)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case root:
                 if (parser.smartStep(TerminalEnum.root)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case link:
                 if (parser.smartStep(TerminalEnum.link)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case identifier:
                 if (parser.smartStep(TerminalEnum.identifier)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case quotedstring:
                 if (parser.smartStep(TerminalEnum.quotedstring)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case colon:
                 if (parser.smartStep(TerminalEnum.colon)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case assign:
                 if (parser.smartStep(TerminalEnum.assign)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case dot:
                 if (parser.smartStep(TerminalEnum.dot)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case lbracket:
                 if (parser.smartStep(TerminalEnum.lbracket)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case rbracket:
                 if (parser.smartStep(TerminalEnum.rbracket)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case space:
              buffer.discard();
             return;
        }
        throw new AssertionError("unknown rule "+rule);
      }
      finally {
        ToolsProcessor.this.buffer = null;
      }
    }
  }

  public void shift(TerminalEnum terminal) {
     D data;
     switch(terminal) {
      case grammar: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case root: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case link: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case identifier: {
          data=dataViewer.view(ToolsProcessor.this.buffer);
             String identifier= terminalEvaluator.identifier(data);
                 ToolsProcessor.this.buffer.discard();
               stack.push_Object(identifier);
         return;
      }
      case quotedstring: {
          data=dataViewer.view(ToolsProcessor.this.buffer);
             String quotedstring= terminalEvaluator.quotedstring(data);
                 ToolsProcessor.this.buffer.discard();
               stack.push_Object(quotedstring);
         return;
      }
      case colon: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case assign: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case dot: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case lbracket: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case rbracket: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case __eof__: {
            return;
      }
    }
    throw new AssertionError("unknown terminal "+terminal);
  }

  @SuppressWarnings("unchecked")
  public void reduce(ProductionEnum production) {
    switch(production) {
      case grammarDef_plus_element: {
// ast false
// starType STAR_SINGLETON
          // star singleton
          return;
      }
      case grammarDef_plus_rec: {
// ast false
// starType STAR_RECURSIVE_LEFT
          // star recursive left
          return;
      }
      case linkDef_plus_element: {
// ast false
// starType STAR_SINGLETON
          // star singleton
          return;
      }
      case linkDef_plus_rec: {
// ast false
// starType STAR_RECURSIVE_LEFT
          // star recursive left
          return;
      }
      case start_def: {
// ast false
// starType NO_STAR

          String identifier=(String) stack.pop_Object();
            grammarEvaluator.start_def(identifier);
            return;
      }
      case grammar_def: {
// ast false
// starType NO_STAR

          String quotedstring=(String) stack.pop_Object();

          String identifier=(String) stack.pop_Object();
            grammarEvaluator.grammar_def(identifier, quotedstring);
            return;
      }
      case linkStart_opt_optional0_empty: {
// ast false
// starType OPTIONAL_EMPTY
          // optional empty
          stack.push_Object(null);
          return;
      }
      case linkStart_opt_optional0_linkStart: {
// ast false
// starType OPTIONAL_SINGLETON
          // optional singleton    
          return;
      }
      case linkVersion_opt_optional1_empty: {
// ast false
// starType OPTIONAL_EMPTY
          // optional empty
          stack.push_Object(null);
          return;
      }
      case linkVersion_opt_optional1_linkVersion: {
// ast false
// starType OPTIONAL_SINGLETON
          // optional singleton    
          return;
      }
      case link_def: {
// ast false
// starType NO_STAR

          String linkVersion_opt_optional1=(String) stack.pop_Object();

          String linkStart_opt_optional0=(String) stack.pop_Object();

          String identifier3=(String) stack.pop_Object();

          String identifier2=(String) stack.pop_Object();

          String identifier=(String) stack.pop_Object();
            grammarEvaluator.link_def(identifier, identifier2, identifier3, linkStart_opt_optional0, linkVersion_opt_optional1);
            return;
      }
      case linkStart_def: {
// ast false
// starType NO_STAR

          String identifier=(String) stack.pop_Object();
          stack.push_Object(grammarEvaluator.linkStart_def(identifier));
          return;
      }
      case linkVersion_def: {
// ast false
// starType NO_STAR

          String identifier=(String) stack.pop_Object();
          stack.push_Object(grammarEvaluator.linkVersion_def(identifier));
          return;
      }
    }
    throw new AssertionError("unknown production "+production);
  }

  @SuppressWarnings("unchecked")
  public void accept(NonTerminalEnum nonterminal) {
    switch(nonterminal) {
      case start:
        return;
      default:
    }
    throw new AssertionError("unknown start nonterminal "+nonterminal);
  }

  public void popTerminalOnError(TerminalEnum terminal) {
    switch(terminal) {
      case grammar:
        return;
      case root:
        return;
      case link:
        return;
      case identifier:
        stack.pop_Object();
        return;
      case quotedstring:
        stack.pop_Object();
        return;
      case colon:
        return;
      case assign:
        return;
      case dot:
        return;
      case lbracket:
        return;
      case rbracket:
        return;
      case __eof__:
        return;
    }
    throw new AssertionError("unknown terminal "+terminal);
  }

  public void popNonTerminalOnError(NonTerminalEnum nonterminal) {
    switch(nonterminal) {
      case start:
        return;
      case grammarDef:
        return;
      case linkDef:
        return;
      case linkStart:
        stack.pop_Object();
        return;
      case linkVersion:
        stack.pop_Object();
        return;
      case grammarDef_plus:
        return;
      case linkDef_plus:
        return;
      case linkStart_opt_optional0:
        stack.pop_Object();
        return;
      case linkVersion_opt_optional1:
        stack.pop_Object();
        return;
    }
    throw new AssertionError("unknown nonterminal "+nonterminal);
  }
}
