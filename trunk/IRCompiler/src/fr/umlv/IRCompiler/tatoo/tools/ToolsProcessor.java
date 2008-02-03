package fr.umlv.IRCompiler.tatoo.tools;

import fr.umlv.IRCompiler.tatoo.lexer.RuleEnum;
import fr.umlv.IRCompiler.tatoo.parser.TerminalEnum;
import fr.umlv.IRCompiler.tatoo.parser.NonTerminalEnum;
import fr.umlv.IRCompiler.tatoo.parser.ProductionEnum;
import fr.umlv.IRCompiler.tatoo.tools.TerminalEvaluator;
import fr.umlv.IRCompiler.tatoo.tools.GrammarEvaluator;

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
        case val:
                 if (parser.smartStep(TerminalEnum.val)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case or:
                 if (parser.smartStep(TerminalEnum.or)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case and:
                 if (parser.smartStep(TerminalEnum.and)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case not:
                 if (parser.smartStep(TerminalEnum.not)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case open_brace:
                 if (parser.smartStep(TerminalEnum.open_brace)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case close_brace:
                 if (parser.smartStep(TerminalEnum.close_brace)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case space:
                 if (parser.smartStep(TerminalEnum.space)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case letter:
                 if (parser.smartStep(TerminalEnum.letter)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case blk:
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
      case val: {
          data=dataViewer.view(ToolsProcessor.this.buffer);
             boolean val= terminalEvaluator.val(data);
                 ToolsProcessor.this.buffer.discard();
               stack.push_boolean(val);
         return;
      }
      case or: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case and: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case not: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case open_brace: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case close_brace: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case space: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case letter: {
          data=dataViewer.view(ToolsProcessor.this.buffer);
             char letter= terminalEvaluator.letter(data);
                 ToolsProcessor.this.buffer.discard();
               stack.push_char(letter);
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
      case start__expr: {
// ast false
// starType NO_STAR

          boolean expr= stack.pop_boolean();
            grammarEvaluator.start__expr(expr);
            return;
      }
      case and: {
// ast false
// starType NO_STAR

          boolean expr2= stack.pop_boolean();

          boolean expr= stack.pop_boolean();
          stack.push_boolean(grammarEvaluator.and(expr, expr2));
          return;
      }
      case or: {
// ast false
// starType NO_STAR

          boolean expr2= stack.pop_boolean();

          boolean expr= stack.pop_boolean();
          stack.push_boolean(grammarEvaluator.or(expr, expr2));
          return;
      }
      case not: {
// ast false
// starType NO_STAR

          boolean expr= stack.pop_boolean();
          stack.push_boolean(grammarEvaluator.not(expr));
          return;
      }
      case parenthesis: {
// ast false
// starType NO_STAR

          boolean expr= stack.pop_boolean();
          stack.push_boolean(grammarEvaluator.parenthesis(expr));
          return;
      }
      case value: {
// ast false
// starType NO_STAR

          boolean val= stack.pop_boolean();
          stack.push_boolean(grammarEvaluator.value(val));
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
      case val:
        stack.pop_boolean();
        return;
      case or:
        return;
      case and:
        return;
      case not:
        return;
      case open_brace:
        return;
      case close_brace:
        return;
      case space:
        return;
      case letter:
        stack.pop_char();
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
      case expr:
        stack.pop_boolean();
        return;
    }
    throw new AssertionError("unknown nonterminal "+nonterminal);
  }
}
