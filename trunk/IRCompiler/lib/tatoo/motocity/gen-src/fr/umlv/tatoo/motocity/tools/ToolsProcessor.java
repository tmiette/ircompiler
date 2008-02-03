package fr.umlv.tatoo.motocity.tools;

import fr.umlv.tatoo.motocity.lexer.RuleEnum;
import fr.umlv.tatoo.motocity.parser.TerminalEnum;
import fr.umlv.tatoo.motocity.parser.NonTerminalEnum;
import fr.umlv.tatoo.motocity.parser.ProductionEnum;
import fr.umlv.tatoo.motocity.tools.TerminalEvaluator;
import fr.umlv.tatoo.motocity.tools.GrammarEvaluator;

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
        case letter:
                 if (parser.smartStep(TerminalEnum.letter)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case javaLetter:
                 if (parser.smartStep(TerminalEnum.javaLetter)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case jokerIn:
                 if (parser.smartStep(TerminalEnum.jokerIn)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case jokerOut:
                 if (parser.smartStep(TerminalEnum.jokerOut)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case javaJokerIn:
                 if (parser.smartStep(TerminalEnum.javaJokerIn)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case javaJokerOut:
                 if (parser.smartStep(TerminalEnum.javaJokerOut)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case headJokerIn:
                 if (parser.smartStep(TerminalEnum.headJokerIn)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case headJokerOut:
                 if (parser.smartStep(TerminalEnum.headJokerOut)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case imports:
                 if (parser.smartStep(TerminalEnum.imports)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case paramkw:
                 if (parser.smartStep(TerminalEnum.paramkw)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case blank:
                 if (parser.smartStep(TerminalEnum.blank)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case name:
                 if (parser.smartStep(TerminalEnum.name)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case type:
                 if (parser.smartStep(TerminalEnum.type)==SmartStepReturn.RELEX) {
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
        case semicolon:
                 if (parser.smartStep(TerminalEnum.semicolon)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
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
      case letter: {
          data=dataViewer.view(ToolsProcessor.this.buffer);
             char letter= terminalEvaluator.letter(data);
                 ToolsProcessor.this.buffer.discard();
               stack.push_char(letter);
         return;
      }
      case javaLetter: {
          data=dataViewer.view(ToolsProcessor.this.buffer);
             char javaLetter= terminalEvaluator.javaLetter(data);
                 ToolsProcessor.this.buffer.discard();
               stack.push_char(javaLetter);
         return;
      }
      case jokerIn: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case jokerOut: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case javaJokerIn: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case javaJokerOut: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case headJokerIn: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case headJokerOut: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case imports: {
          data=dataViewer.view(ToolsProcessor.this.buffer);
             terminalEvaluator.imports(data);
                 ToolsProcessor.this.buffer.discard();
               return;
      }
      case paramkw: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case blank: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case name: {
          data=dataViewer.view(ToolsProcessor.this.buffer);
             String name= terminalEvaluator.name(data);
                 ToolsProcessor.this.buffer.discard();
               stack.push_Object(name);
         return;
      }
      case type: {
          data=dataViewer.view(ToolsProcessor.this.buffer);
             String type= terminalEvaluator.type(data);
                 ToolsProcessor.this.buffer.discard();
               stack.push_Object(type);
         return;
      }
      case colon: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case semicolon: {
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
      case start: {
// ast false
// starType NO_STAR
            grammarEvaluator.start();
            return;
      }
      case methodStart: {
// ast false
// starType NO_STAR
            grammarEvaluator.methodStart();
            return;
      }
      case head: {
// ast false
// starType NO_STAR
            grammarEvaluator.head();
            return;
      }
      case headClassDef: {
// ast false
// starType NO_STAR
            grammarEvaluator.headClassDef();
            return;
      }
      case moreParam: {
// ast false
// starType NO_STAR
            return;
      }
      case paramsInit: {
// ast false
// starType NO_STAR
            return;
      }
      case paramDef: {
// ast false
// starType NO_STAR

          String type=(String) stack.pop_Object();

          String name=(String) stack.pop_Object();
            grammarEvaluator.paramDef(name, type);
            return;
      }
      case classDef: {
// ast false
// starType NO_STAR
            grammarEvaluator.classDef();
            return;
      }
      case unit: {
// ast false
// starType NO_STAR
            return;
      }
      case begin: {
// ast false
// starType NO_STAR
            return;
      }
      case wordOutput: {
// ast false
// starType NO_STAR
            grammarEvaluator.wordOutput();
            return;
      }
      case javaExprOuput: {
// ast false
// starType NO_STAR
            grammarEvaluator.javaExprOuput();
            return;
      }
      case javaCodeOutput: {
// ast false
// starType NO_STAR
            return;
      }
      case startOut: {
// ast false
// starType NO_STAR
            grammarEvaluator.startOut();
            return;
      }
      case endOut: {
// ast false
// starType NO_STAR
            grammarEvaluator.endOut();
            return;
      }
      case firstLetter: {
// ast false
// starType NO_STAR

          char letter= stack.pop_char();
            grammarEvaluator.firstLetter(letter);
            return;
      }
      case moreLetter: {
// ast false
// starType NO_STAR

          char letter= stack.pop_char();
            grammarEvaluator.moreLetter(letter);
            return;
      }
      case startJavaWord: {
// ast false
// starType NO_STAR
            return;
      }
      case moreJavaLetter: {
// ast false
// starType NO_STAR

          char javaLetter= stack.pop_char();
            grammarEvaluator.moreJavaLetter(javaLetter);
            return;
      }
    }
    throw new AssertionError("unknown production "+production);
  }

  @SuppressWarnings("unchecked")
  public void accept(NonTerminalEnum nonterminal) {
    switch(nonterminal) {
      case start:
        grammarEvaluator.acceptStart( );

        return;
      default:
    }
    throw new AssertionError("unknown start nonterminal "+nonterminal);
  }

  public void popTerminalOnError(TerminalEnum terminal) {
    switch(terminal) {
      case letter:
        stack.pop_char();
        return;
      case javaLetter:
        stack.pop_char();
        return;
      case jokerIn:
        return;
      case jokerOut:
        return;
      case javaJokerIn:
        return;
      case javaJokerOut:
        return;
      case headJokerIn:
        return;
      case headJokerOut:
        return;
      case imports:
        return;
      case paramkw:
        return;
      case blank:
        return;
      case name:
        stack.pop_Object();
        return;
      case type:
        stack.pop_Object();
        return;
      case colon:
        return;
      case semicolon:
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
      case methodStart:
        return;
      case head:
        return;
      case params:
        return;
      case param:
        return;
      case classDef:
        return;
      case body:
        return;
      case unit:
        return;
      case startOut:
        return;
      case endOut:
        return;
      case word:
        return;
      case javaWord:
        return;
    }
    throw new AssertionError("unknown nonterminal "+nonterminal);
  }
}
