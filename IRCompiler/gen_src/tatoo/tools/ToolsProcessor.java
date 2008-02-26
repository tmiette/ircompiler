package tatoo.tools;

import tatoo.tools.Another_Arg;
import tatoo.tools.Another_Arg_Star;
import tatoo.tools.Another_Parameter;
import tatoo.tools.Another_Parameter_Star;
import tatoo.tools.Function_Declaration;
import tatoo.tools.IArg_List;
import tatoo.tools.IElse_Statement;
import tatoo.tools.IExpression;
import tatoo.tools.IInstruction;
import tatoo.tools.IPackage_Name;
import tatoo.tools.IParameter_List;
import tatoo.tools.IStatement;
import tatoo.tools.IType;
import tatoo.tools.IVariable_Declaration;
import tatoo.tools.Import_Statement;
import tatoo.tools.Import_Statement_Star;
import tatoo.tools.Instruction_Star;
import tatoo.tools.Multiple_Statement;
import tatoo.tools.Parameter;
import tatoo.tools.Start;
import tatoo.tools.Statement_Star;
import tatoo.tools.Variable_Assignment;
import tatoo.lexer.RuleEnum;
import tatoo.parser.TerminalEnum;
import tatoo.parser.NonTerminalEnum;
import tatoo.parser.ProductionEnum;
import tatoo.tools.TerminalEvaluator;
import tatoo.tools.GrammarEvaluator;

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
        case dot_:
                 if (parser.smartStep(TerminalEnum.dot_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case semicolon_:
                 if (parser.smartStep(TerminalEnum.semicolon_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case lpar_:
                 if (parser.smartStep(TerminalEnum.lpar_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case rpar_:
                 if (parser.smartStep(TerminalEnum.rpar_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case comma_:
                 if (parser.smartStep(TerminalEnum.comma_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case plus_:
                 if (parser.smartStep(TerminalEnum.plus_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case minus_:
                 if (parser.smartStep(TerminalEnum.minus_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case mult_:
                 if (parser.smartStep(TerminalEnum.mult_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case div_:
                 if (parser.smartStep(TerminalEnum.div_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case equal_:
                 if (parser.smartStep(TerminalEnum.equal_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case inequal_:
                 if (parser.smartStep(TerminalEnum.inequal_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case affect_:
                 if (parser.smartStep(TerminalEnum.affect_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case pow_:
                 if (parser.smartStep(TerminalEnum.pow_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case import_:
                 if (parser.smartStep(TerminalEnum.import_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case function_:
                 if (parser.smartStep(TerminalEnum.function_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case end_function_:
                 if (parser.smartStep(TerminalEnum.end_function_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case if_:
                 if (parser.smartStep(TerminalEnum.if_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case then_:
                 if (parser.smartStep(TerminalEnum.then_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case else_:
                 if (parser.smartStep(TerminalEnum.else_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case fi_:
                 if (parser.smartStep(TerminalEnum.fi_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case return_:
                 if (parser.smartStep(TerminalEnum.return_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case not_:
                 if (parser.smartStep(TerminalEnum.not_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case print_:
                 if (parser.smartStep(TerminalEnum.print_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case foreach_:
                 if (parser.smartStep(TerminalEnum.foreach_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case in_:
                 if (parser.smartStep(TerminalEnum.in_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case do_:
                 if (parser.smartStep(TerminalEnum.do_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case done_:
                 if (parser.smartStep(TerminalEnum.done_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case void_type_:
                 if (parser.smartStep(TerminalEnum.void_type_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case integer_type_:
                 if (parser.smartStep(TerminalEnum.integer_type_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case boolean_type_:
                 if (parser.smartStep(TerminalEnum.boolean_type_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case float_type_:
                 if (parser.smartStep(TerminalEnum.float_type_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case double_type_:
                 if (parser.smartStep(TerminalEnum.double_type_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case string_type_:
                 if (parser.smartStep(TerminalEnum.string_type_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case comment_:
                 if (parser.smartStep(TerminalEnum.comment_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case boolean_:
                 if (parser.smartStep(TerminalEnum.boolean_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case number_:
                 if (parser.smartStep(TerminalEnum.number_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case float_:
                 if (parser.smartStep(TerminalEnum.float_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case double_:
                 if (parser.smartStep(TerminalEnum.double_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case identifier_:
                 if (parser.smartStep(TerminalEnum.identifier_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case string_:
                 if (parser.smartStep(TerminalEnum.string_)==SmartStepReturn.RELEX) {
            buffer.restart();
            return;
          }
             return;
        case blk_:
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
      case dot_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case semicolon_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case lpar_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case rpar_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case comma_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case plus_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case minus_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case mult_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case div_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case equal_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case inequal_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case affect_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case pow_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case import_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case function_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case end_function_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case if_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case then_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case else_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case fi_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case return_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case not_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case print_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case foreach_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case in_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case do_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case done_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case void_type_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case integer_type_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case boolean_type_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case float_type_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case double_type_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case string_type_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case comment_: {
                      ToolsProcessor.this.buffer.discard();
               return;
      }
      case boolean_: {
          data=dataViewer.view(ToolsProcessor.this.buffer);
             Boolean boolean_= terminalEvaluator.boolean_(data);
                 ToolsProcessor.this.buffer.discard();
               stack.push_Object(boolean_);
         return;
      }
      case number_: {
          data=dataViewer.view(ToolsProcessor.this.buffer);
             Integer number_= terminalEvaluator.number_(data);
                 ToolsProcessor.this.buffer.discard();
               stack.push_Object(number_);
         return;
      }
      case float_: {
          data=dataViewer.view(ToolsProcessor.this.buffer);
             Float float_= terminalEvaluator.float_(data);
                 ToolsProcessor.this.buffer.discard();
               stack.push_Object(float_);
         return;
      }
      case double_: {
          data=dataViewer.view(ToolsProcessor.this.buffer);
             Double double_= terminalEvaluator.double_(data);
                 ToolsProcessor.this.buffer.discard();
               stack.push_Object(double_);
         return;
      }
      case identifier_: {
          data=dataViewer.view(ToolsProcessor.this.buffer);
             String identifier_= terminalEvaluator.identifier_(data);
                 ToolsProcessor.this.buffer.discard();
               stack.push_Object(identifier_);
         return;
      }
      case string_: {
          data=dataViewer.view(ToolsProcessor.this.buffer);
             String string_= terminalEvaluator.string_(data);
                 ToolsProcessor.this.buffer.discard();
               stack.push_Object(string_);
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
      case import_statement_star_empty: {
// ast true
// starType STAR_EMPTY
          // star empty tatoo.tools.Import_Statement_Star
          stack.push_Object(new Import_Statement_Star());
          return;
      }
      case import_statement_star_rec: {
// ast true
// starType STAR_RECURSIVE_LEFT
          // star recursive left
          Import_Statement import_statement=(Import_Statement)stack.pop_Object();
          Import_Statement_Star import_statement_star=(Import_Statement_Star)stack.pop_Object();
          import_statement_star.nodeList().add(import_statement);
          stack.push_Object(import_statement_star);
          return;
      }
      case instruction_star_empty: {
// ast true
// starType STAR_EMPTY
          // star empty tatoo.tools.Instruction_Star
          stack.push_Object(new Instruction_Star());
          return;
      }
      case instruction_star_rec: {
// ast true
// starType STAR_RECURSIVE_LEFT
          // star recursive left
          IInstruction instruction=(IInstruction)stack.pop_Object();
          Instruction_Star instruction_star=(Instruction_Star)stack.pop_Object();
          instruction_star.nodeList().add(instruction);
          stack.push_Object(instruction_star);
          return;
      }
      case start: {
// ast false
// starType NO_STAR

          Instruction_Star instruction_star=(Instruction_Star) stack.pop_Object();

          Import_Statement_Star import_statement_star=(Import_Statement_Star) stack.pop_Object();
          stack.push_Object(grammarEvaluator.start(import_statement_star, instruction_star));
          return;
      }
      case import_statement: {
// ast false
// starType NO_STAR

          IPackage_Name package_name=(IPackage_Name) stack.pop_Object();
          stack.push_Object(grammarEvaluator.import_statement(package_name));
          return;
      }
      case single_package_name: {
// ast false
// starType NO_STAR

          String identifier_=(String) stack.pop_Object();
          stack.push_Object(grammarEvaluator.single_package_name(identifier_));
          return;
      }
      case multiple_package_name: {
// ast false
// starType NO_STAR

          String identifier_=(String) stack.pop_Object();

          IPackage_Name package_name=(IPackage_Name) stack.pop_Object();
          stack.push_Object(grammarEvaluator.multiple_package_name(package_name, identifier_));
          return;
      }
      case instruction: {
// ast false
// starType NO_STAR

          Function_Declaration function_declaration=(Function_Declaration) stack.pop_Object();
          stack.push_Object(grammarEvaluator.instruction(function_declaration));
          return;
      }
      case statement: {
// ast false
// starType NO_STAR

          IStatement statement=(IStatement) stack.pop_Object();
          stack.push_Object(grammarEvaluator.statement(statement));
          return;
      }
      case function_declaration: {
// ast false
// starType NO_STAR

          Multiple_Statement multiple_statement=(Multiple_Statement) stack.pop_Object();

          IParameter_List parameter_list=(IParameter_List) stack.pop_Object();

          String identifier_=(String) stack.pop_Object();

          IType type=(IType) stack.pop_Object();
          stack.push_Object(grammarEvaluator.function_declaration(type, identifier_, parameter_list, multiple_statement));
          return;
      }
      case empty_parameter_list: {
// ast false
// starType NO_STAR
          stack.push_Object(grammarEvaluator.empty_parameter_list());
          return;
      }
      case another_parameter_star_empty: {
// ast true
// starType STAR_EMPTY
          // star empty tatoo.tools.Another_Parameter_Star
          stack.push_Object(new Another_Parameter_Star());
          return;
      }
      case another_parameter_star_rec: {
// ast true
// starType STAR_RECURSIVE_LEFT
          // star recursive left
          Another_Parameter another_parameter=(Another_Parameter)stack.pop_Object();
          Another_Parameter_Star another_parameter_star=(Another_Parameter_Star)stack.pop_Object();
          another_parameter_star.nodeList().add(another_parameter);
          stack.push_Object(another_parameter_star);
          return;
      }
      case parameter_list: {
// ast false
// starType NO_STAR

          Another_Parameter_Star another_parameter_star=(Another_Parameter_Star) stack.pop_Object();

          Parameter parameter=(Parameter) stack.pop_Object();
          stack.push_Object(grammarEvaluator.parameter_list(parameter, another_parameter_star));
          return;
      }
      case parameter: {
// ast false
// starType NO_STAR

          String identifier_=(String) stack.pop_Object();

          IType type=(IType) stack.pop_Object();
          stack.push_Object(grammarEvaluator.parameter(type, identifier_));
          return;
      }
      case another_parameter: {
// ast false
// starType NO_STAR

          Parameter parameter=(Parameter) stack.pop_Object();
          stack.push_Object(grammarEvaluator.another_parameter(parameter));
          return;
      }
      case statement_star_empty: {
// ast true
// starType STAR_EMPTY
          // star empty tatoo.tools.Statement_Star
          stack.push_Object(new Statement_Star());
          return;
      }
      case statement_star_rec: {
// ast true
// starType STAR_RECURSIVE_LEFT
          // star recursive left
          IStatement statement=(IStatement)stack.pop_Object();
          Statement_Star statement_star=(Statement_Star)stack.pop_Object();
          statement_star.nodeList().add(statement);
          stack.push_Object(statement_star);
          return;
      }
      case multiple_statement: {
// ast false
// starType NO_STAR

          Statement_Star statement_star=(Statement_Star) stack.pop_Object();
          stack.push_Object(grammarEvaluator.multiple_statement(statement_star));
          return;
      }
      case variable_declaration_statement: {
// ast false
// starType NO_STAR

          IVariable_Declaration variable_declaration=(IVariable_Declaration) stack.pop_Object();
          stack.push_Object(grammarEvaluator.variable_declaration_statement(variable_declaration));
          return;
      }
      case variable_assignment_statement: {
// ast false
// starType NO_STAR

          Variable_Assignment variable_assignment=(Variable_Assignment) stack.pop_Object();
          stack.push_Object(grammarEvaluator.variable_assignment_statement(variable_assignment));
          return;
      }
      case comment_statement: {
// ast false
// starType NO_STAR
          stack.push_Object(grammarEvaluator.comment_statement());
          return;
      }
      case print_statement: {
// ast false
// starType NO_STAR

          IExpression expression=(IExpression) stack.pop_Object();
          stack.push_Object(grammarEvaluator.print_statement(expression));
          return;
      }
      case expression_statement: {
// ast false
// starType NO_STAR

          IExpression expression=(IExpression) stack.pop_Object();
          stack.push_Object(grammarEvaluator.expression_statement(expression));
          return;
      }
      case return_statement: {
// ast false
// starType NO_STAR

          IExpression expression=(IExpression) stack.pop_Object();
          stack.push_Object(grammarEvaluator.return_statement(expression));
          return;
      }
      case return_statement_empty: {
// ast false
// starType NO_STAR
          stack.push_Object(grammarEvaluator.return_statement_empty());
          return;
      }
      case conditional_statement: {
// ast false
// starType NO_STAR

          IElse_Statement else_statement=(IElse_Statement) stack.pop_Object();

          Multiple_Statement multiple_statement=(Multiple_Statement) stack.pop_Object();

          IExpression expression=(IExpression) stack.pop_Object();
          stack.push_Object(grammarEvaluator.conditional_statement(expression, multiple_statement, else_statement));
          return;
      }
      case foreach_statement: {
// ast false
// starType NO_STAR

          Multiple_Statement multiple_statement=(Multiple_Statement) stack.pop_Object();

          IExpression expression=(IExpression) stack.pop_Object();

          String identifier_=(String) stack.pop_Object();
          stack.push_Object(grammarEvaluator.foreach_statement(identifier_, expression, multiple_statement));
          return;
      }
      case foreach_statement_with_declaration: {
// ast false
// starType NO_STAR

          Multiple_Statement multiple_statement=(Multiple_Statement) stack.pop_Object();

          IExpression expression=(IExpression) stack.pop_Object();

          String identifier_=(String) stack.pop_Object();

          IType type=(IType) stack.pop_Object();
          stack.push_Object(grammarEvaluator.foreach_statement_with_declaration(type, identifier_, expression, multiple_statement));
          return;
      }
      case empty_statement: {
// ast false
// starType NO_STAR
          stack.push_Object(grammarEvaluator.empty_statement());
          return;
      }
      case empty_else_statement: {
// ast false
// starType NO_STAR
          stack.push_Object(grammarEvaluator.empty_else_statement());
          return;
      }
      case else_statement: {
// ast false
// starType NO_STAR

          Multiple_Statement multiple_statement=(Multiple_Statement) stack.pop_Object();
          stack.push_Object(grammarEvaluator.else_statement(multiple_statement));
          return;
      }
      case variable_declaration_with_assignment: {
// ast false
// starType NO_STAR

          IExpression expression=(IExpression) stack.pop_Object();

          String identifier_=(String) stack.pop_Object();

          IType type=(IType) stack.pop_Object();
          stack.push_Object(grammarEvaluator.variable_declaration_with_assignment(type, identifier_, expression));
          return;
      }
      case variable_declaration_without_assignment: {
// ast false
// starType NO_STAR

          String identifier_=(String) stack.pop_Object();

          IType type=(IType) stack.pop_Object();
          stack.push_Object(grammarEvaluator.variable_declaration_without_assignment(type, identifier_));
          return;
      }
      case variable_assignment: {
// ast false
// starType NO_STAR

          IExpression expression=(IExpression) stack.pop_Object();

          String identifier_=(String) stack.pop_Object();
          stack.push_Object(grammarEvaluator.variable_assignment(identifier_, expression));
          return;
      }
      case plus_expression: {
// ast false
// starType NO_STAR

          IExpression expression2=(IExpression) stack.pop_Object();

          IExpression expression=(IExpression) stack.pop_Object();
          stack.push_Object(grammarEvaluator.plus_expression(expression, expression2));
          return;
      }
      case minus_expression: {
// ast false
// starType NO_STAR

          IExpression expression2=(IExpression) stack.pop_Object();

          IExpression expression=(IExpression) stack.pop_Object();
          stack.push_Object(grammarEvaluator.minus_expression(expression, expression2));
          return;
      }
      case mult_expression: {
// ast false
// starType NO_STAR

          IExpression expression2=(IExpression) stack.pop_Object();

          IExpression expression=(IExpression) stack.pop_Object();
          stack.push_Object(grammarEvaluator.mult_expression(expression, expression2));
          return;
      }
      case div_expression: {
// ast false
// starType NO_STAR

          IExpression expression2=(IExpression) stack.pop_Object();

          IExpression expression=(IExpression) stack.pop_Object();
          stack.push_Object(grammarEvaluator.div_expression(expression, expression2));
          return;
      }
      case pow_expression: {
// ast false
// starType NO_STAR

          IExpression expression2=(IExpression) stack.pop_Object();

          IExpression expression=(IExpression) stack.pop_Object();
          stack.push_Object(grammarEvaluator.pow_expression(expression, expression2));
          return;
      }
      case not_expression: {
// ast false
// starType NO_STAR

          IExpression expression=(IExpression) stack.pop_Object();
          stack.push_Object(grammarEvaluator.not_expression(expression));
          return;
      }
      case minus_simple_expression: {
// ast false
// starType NO_STAR

          IExpression expression=(IExpression) stack.pop_Object();
          stack.push_Object(grammarEvaluator.minus_simple_expression(expression));
          return;
      }
      case equal_expression: {
// ast false
// starType NO_STAR

          IExpression expression2=(IExpression) stack.pop_Object();

          IExpression expression=(IExpression) stack.pop_Object();
          stack.push_Object(grammarEvaluator.equal_expression(expression, expression2));
          return;
      }
      case inequal_expression: {
// ast false
// starType NO_STAR

          IExpression expression2=(IExpression) stack.pop_Object();

          IExpression expression=(IExpression) stack.pop_Object();
          stack.push_Object(grammarEvaluator.inequal_expression(expression, expression2));
          return;
      }
      case par_expression: {
// ast false
// starType NO_STAR

          IExpression expression=(IExpression) stack.pop_Object();
          stack.push_Object(grammarEvaluator.par_expression(expression));
          return;
      }
      case function_call_expression: {
// ast false
// starType NO_STAR

          IArg_List arg_list=(IArg_List) stack.pop_Object();

          String identifier_=(String) stack.pop_Object();
          stack.push_Object(grammarEvaluator.function_call_expression(identifier_, arg_list));
          return;
      }
      case method_call_expression: {
// ast false
// starType NO_STAR

          IArg_List arg_list=(IArg_List) stack.pop_Object();

          String identifier_2=(String) stack.pop_Object();

          String identifier_=(String) stack.pop_Object();
          stack.push_Object(grammarEvaluator.method_call_expression(identifier_, identifier_2, arg_list));
          return;
      }
      case identifier_expression: {
// ast false
// starType NO_STAR

          String identifier_=(String) stack.pop_Object();
          stack.push_Object(grammarEvaluator.identifier_expression(identifier_));
          return;
      }
      case number_expression: {
// ast false
// starType NO_STAR

          Integer number_=(Integer) stack.pop_Object();
          stack.push_Object(grammarEvaluator.number_expression(number_));
          return;
      }
      case float_expression: {
// ast false
// starType NO_STAR

          Float float_=(Float) stack.pop_Object();
          stack.push_Object(grammarEvaluator.float_expression(float_));
          return;
      }
      case double_expression: {
// ast false
// starType NO_STAR

          Double double_=(Double) stack.pop_Object();
          stack.push_Object(grammarEvaluator.double_expression(double_));
          return;
      }
      case string_expression: {
// ast false
// starType NO_STAR

          String string_=(String) stack.pop_Object();
          stack.push_Object(grammarEvaluator.string_expression(string_));
          return;
      }
      case boolean_expression: {
// ast false
// starType NO_STAR

          Boolean boolean_=(Boolean) stack.pop_Object();
          stack.push_Object(grammarEvaluator.boolean_expression(boolean_));
          return;
      }
      case empty_arg_list: {
// ast false
// starType NO_STAR
          stack.push_Object(grammarEvaluator.empty_arg_list());
          return;
      }
      case another_arg_star_empty: {
// ast true
// starType STAR_EMPTY
          // star empty tatoo.tools.Another_Arg_Star
          stack.push_Object(new Another_Arg_Star());
          return;
      }
      case another_arg_star_rec: {
// ast true
// starType STAR_RECURSIVE_LEFT
          // star recursive left
          Another_Arg another_arg=(Another_Arg)stack.pop_Object();
          Another_Arg_Star another_arg_star=(Another_Arg_Star)stack.pop_Object();
          another_arg_star.nodeList().add(another_arg);
          stack.push_Object(another_arg_star);
          return;
      }
      case args_list: {
// ast false
// starType NO_STAR

          Another_Arg_Star another_arg_star=(Another_Arg_Star) stack.pop_Object();

          IExpression expression=(IExpression) stack.pop_Object();
          stack.push_Object(grammarEvaluator.args_list(expression, another_arg_star));
          return;
      }
      case another_arg: {
// ast false
// starType NO_STAR

          IExpression expression=(IExpression) stack.pop_Object();
          stack.push_Object(grammarEvaluator.another_arg(expression));
          return;
      }
      case integer_type: {
// ast false
// starType NO_STAR
          stack.push_Object(grammarEvaluator.integer_type());
          return;
      }
      case float_type: {
// ast false
// starType NO_STAR
          stack.push_Object(grammarEvaluator.float_type());
          return;
      }
      case double_type: {
// ast false
// starType NO_STAR
          stack.push_Object(grammarEvaluator.double_type());
          return;
      }
      case boolean_type: {
// ast false
// starType NO_STAR
          stack.push_Object(grammarEvaluator.boolean_type());
          return;
      }
      case string_type: {
// ast false
// starType NO_STAR
          stack.push_Object(grammarEvaluator.string_type());
          return;
      }
      case void_type: {
// ast false
// starType NO_STAR
          stack.push_Object(grammarEvaluator.void_type());
          return;
      }
      case class_type: {
// ast false
// starType NO_STAR

          String identifier_=(String) stack.pop_Object();
          stack.push_Object(grammarEvaluator.class_type(identifier_));
          return;
      }
    }
    throw new AssertionError("unknown production "+production);
  }

  @SuppressWarnings("unchecked")
  public void accept(NonTerminalEnum nonterminal) {
    switch(nonterminal) {
      case start:
        grammarEvaluator.acceptStart((Start) stack.pop_Object());

        return;
      default:
    }
    throw new AssertionError("unknown start nonterminal "+nonterminal);
  }

  public void popTerminalOnError(TerminalEnum terminal) {
    switch(terminal) {
      case dot_:
        return;
      case semicolon_:
        return;
      case lpar_:
        return;
      case rpar_:
        return;
      case comma_:
        return;
      case plus_:
        return;
      case minus_:
        return;
      case mult_:
        return;
      case div_:
        return;
      case equal_:
        return;
      case inequal_:
        return;
      case affect_:
        return;
      case pow_:
        return;
      case import_:
        return;
      case function_:
        return;
      case end_function_:
        return;
      case if_:
        return;
      case then_:
        return;
      case else_:
        return;
      case fi_:
        return;
      case return_:
        return;
      case not_:
        return;
      case print_:
        return;
      case foreach_:
        return;
      case in_:
        return;
      case do_:
        return;
      case done_:
        return;
      case void_type_:
        return;
      case integer_type_:
        return;
      case boolean_type_:
        return;
      case float_type_:
        return;
      case double_type_:
        return;
      case string_type_:
        return;
      case comment_:
        return;
      case boolean_:
        stack.pop_Object();
        return;
      case number_:
        stack.pop_Object();
        return;
      case float_:
        stack.pop_Object();
        return;
      case double_:
        stack.pop_Object();
        return;
      case identifier_:
        stack.pop_Object();
        return;
      case string_:
        stack.pop_Object();
        return;
      case __eof__:
        return;
    }
    throw new AssertionError("unknown terminal "+terminal);
  }

  public void popNonTerminalOnError(NonTerminalEnum nonterminal) {
    switch(nonterminal) {
      case start:
        stack.pop_Object();
        return;
      case import_statement:
        stack.pop_Object();
        return;
      case package_name:
        stack.pop_Object();
        return;
      case instruction:
        stack.pop_Object();
        return;
      case function_declaration:
        stack.pop_Object();
        return;
      case parameter_list:
        stack.pop_Object();
        return;
      case parameter:
        stack.pop_Object();
        return;
      case another_parameter:
        stack.pop_Object();
        return;
      case multiple_statement:
        stack.pop_Object();
        return;
      case statement:
        stack.pop_Object();
        return;
      case else_statement:
        stack.pop_Object();
        return;
      case variable_declaration:
        stack.pop_Object();
        return;
      case variable_assignment:
        stack.pop_Object();
        return;
      case expression:
        stack.pop_Object();
        return;
      case arg_list:
        stack.pop_Object();
        return;
      case another_arg:
        stack.pop_Object();
        return;
      case type:
        stack.pop_Object();
        return;
      case import_statement_star:
        stack.pop_Object();
        return;
      case instruction_star:
        stack.pop_Object();
        return;
      case another_parameter_star:
        stack.pop_Object();
        return;
      case statement_star:
        stack.pop_Object();
        return;
      case another_arg_star:
        stack.pop_Object();
        return;
    }
    throw new AssertionError("unknown nonterminal "+nonterminal);
  }
}
