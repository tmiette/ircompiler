package fr.umlv.tatoo.motocity.tools;


/** 
 *  This class is generated - please do not edit it 
 */
public interface GrammarEvaluator {
  /** This methods is called after the reduction of the non terminal start
   *  by the grammar production start.
   *  <code>start := head methodStart body </code>
   */
  public  void start();
  /** This methods is called after the reduction of the non terminal methodStart
   *  by the grammar production methodStart.
   *  <code>methodStart := </code>
   */
  public  void methodStart();
  /** This methods is called after the reduction of the non terminal head
   *  by the grammar production head.
   *  <code>head := 'headJokerIn' 'imports' params classDef javaWord 'headJokerOut' </code>
   */
  public  void head();
  /** This methods is called after the reduction of the non terminal head
   *  by the grammar production headClassDef.
   *  <code>head := classDef </code>
   */
  public  void headClassDef();
  /** This methods is called after the reduction of the non terminal param
   *  by the grammar production paramDef.
   *  <code>param := 'paramkw' 'blank' 'name' 'colon' 'type' 'semicolon' </code>
   */
  public  void paramDef(String name, String type);
  /** This methods is called after the reduction of the non terminal classDef
   *  by the grammar production classDef.
   *  <code>classDef := </code>
   */
  public  void classDef();
  /** This methods is called after the reduction of the non terminal unit
   *  by the grammar production wordOutput.
   *  <code>unit := word </code>
   */
  public  void wordOutput();
  /** This methods is called after the reduction of the non terminal unit
   *  by the grammar production javaExprOuput.
   *  <code>unit := 'jokerIn' startOut javaWord endOut 'jokerOut' </code>
   */
  public  void javaExprOuput();
  /** This methods is called after the reduction of the non terminal startOut
   *  by the grammar production startOut.
   *  <code>startOut := </code>
   */
  public  void startOut();
  /** This methods is called after the reduction of the non terminal endOut
   *  by the grammar production endOut.
   *  <code>endOut := </code>
   */
  public  void endOut();
  /** This methods is called after the reduction of the non terminal word
   *  by the grammar production firstLetter.
   *  <code>word := 'letter' </code>
   */
  public  void firstLetter(char letter);
  /** This methods is called after the reduction of the non terminal word
   *  by the grammar production moreLetter.
   *  <code>word := word 'letter' </code>
   */
  public  void moreLetter(char letter);
  /** This methods is called after the reduction of the non terminal javaWord
   *  by the grammar production moreJavaLetter.
   *  <code>javaWord := javaWord 'javaLetter' </code>
   */
  public  void moreJavaLetter(char javaLetter);

  /** Accepts the non-terminal start 
   */
  public void acceptStart();

}
