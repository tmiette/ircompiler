/*
 * Created on 18 juin 2003
 */
package fr.umlv.tatoo.runtime.lexer.rules;

import java.util.Map;

import fr.umlv.tatoo.runtime.buffer.LexerBuffer;
import fr.umlv.tatoo.runtime.lexer.LexerTable;
import fr.umlv.tatoo.runtime.util.IntArrayList;

/**
 * @param <R> type of rules.
 * 
 * @author Julien
 */
public class ActionProcessor<R> {
  
  /**
   * Creates a new lexer process.
   * 
   * @param lexerTable the rule tables for this process
   */
  @SuppressWarnings("unchecked")
  public ActionProcessor(LexerTable<R> lexerTable) {
    int length=lexerTable.getRuleDataMap().size();
    actions=(Action<R>[])new Action<?>[length];
    for(int i=0;i<length;i++)
      actions[i]=new Action<R>();
    
    priorities=new IntArrayList();
    this.lexerTable=lexerTable;
  }

  /** Reset the action processor.
   */
  public void reset() {
    // actions array cells are not nulled because
    // rules are accessible by lexerTable
    priorities.clear();
  }

  private void initProcesses(boolean newLine,Iterable<? extends R> rules) {
    maxVal = -1;
    minPriority = Integer.MAX_VALUE;
    priorities.clear();
    int i = 0;
    Map<R,RuleData> ruleDataMap = lexerTable.getRuleDataMap();
    for (R r : rules) {
      RuleData ruleData = ruleDataMap.get(r);
      if (!newLine && ruleData.beginningOfLineRequired())
        continue;
      // System.out.println(r+" added");
      actions[i].reset(r,ruleData);
      priorities.add(i);
      i++;
    }
  }

  private void removeProcess(int i) {
    // System.out.println("removing "+actions.get(i).getRule());
    int max = priorities.size() - 1;
    if (i != max) {
      Action<R> tmp = actions[i];
      actions[i] = actions[max];
      actions[max] = tmp;
      priorities.set(i, priorities.get(max));
    }
    priorities.removeLast(1);
  }
  
  private void updateWinner(int i) {
    Action<R> action = actions[i];
    int lastMatch = action.lastMatch();
    if (lastMatch == -1)
      return;
    int priority = priorities.get(i);
    if (lastMatch > maxVal || (lastMatch == maxVal && priority < minPriority)) {
      maxVal = lastMatch;
      minPriority = priority;
      winningAction = action;
    }
  }
  
  private void stepProcesses(int step) {
    for (int i = 0; i < priorities.size();) {
      Action<R> action = actions[i];
      if (!action.step(step)) {
        updateWinner(i);
        removeProcess(i);
      } else {
        i++;
      }
    }
  }

  /**
   * Processes available characters from the input stream.
   * @param buffer the lexer buffer.
   * @return {@link ProcessReturn#MORE MORE} if more characters are needed to perform the match,
   * {@link ProcessReturn#ERROR ERROR}, if an error occurred
   * and {@link ProcessReturn#TOKEN TOKEN} if a new token is spawned.
   */
  public ProcessReturn step(LexerBuffer buffer,Iterable<? extends R> rules) {
    if (processFinished()) {
      initProcesses(buffer.previousWasNewLine(),rules);
    }
    while(buffer.hasRemaining()) {
      int step = buffer.next();
      stepProcesses(step);      
      
      if (priorities.isEmpty()) {
        if (maxVal == -1) {
            return ProcessReturn.ERROR;
        } else {
            return ProcessReturn.TOKEN;
         }
      }
    }
    return ProcessReturn.MORE;
  }
  
  /**
   * This method is called after {@link #step} has returned
   * {@link ProcessReturn#MORE MORE} and end-of-file is reached
   * @return {@link ProcessReturn#NOTHING NOTHING} if no new token is available,
   * {@link ProcessReturn#ERROR ERROR}, if an error occurred
   * and {@link ProcessReturn#TOKEN TOKEN} if a new token is spawned. In this case;
   * if characters are back available in the buffer, {@link #step} must be
   * called again until it returns {@link ProcessReturn#MORE MORE}, and then
   * this method has to be called again */
  public ProcessReturn stepClose() {
    if (processFinished())
      return ProcessReturn.NOTHING;
    for (int i = 0; i < priorities.size();i++) {
      updateWinner(i);
    }
    priorities.clear();
    if (maxVal == -1) {
        return ProcessReturn.ERROR;
    } else {
        return ProcessReturn.TOKEN;
    }
  }
  
  boolean processFinished() {
    return priorities.isEmpty();
  }
  
  /**
   * Returns the rule that matched, upon successful analyze. This method
   * must be called only after method {@link #step} or {@link #stepClose} has returned
   * {@link ProcessReturn#TOKEN}
   * @return the rule that matched
   */
  public R winningRule() {
    if (!processFinished() || maxVal==-1)
      throw new IllegalStateException("This method must be called only after a succesfull match");
    return winningAction.getRule();
  }

  /**
   * Returns the token length, upon successful analyze. This method
   * must be called only after method {@link #step} or {@link #stepClose} has returned
   * {@link ProcessReturn#TOKEN}
   * @return the token length
   */
  public int tokenLength() {
    if (!processFinished() || maxVal==-1)
      throw new IllegalStateException("This method must be called only after a succesfull match");
    return maxVal;
  }
  
  /**
   * Returns the rule tables for this process
   * @return the rule tables for this process
   */
  public LexerTable<R> getLexerTable() {
    return lexerTable;
  }
  
  int maxVal;
  private int minPriority;
  private Action<R> winningAction;
  
  private final IntArrayList priorities;
  private final Action<R>[] actions;
  
  private final LexerTable<R> lexerTable;
}
