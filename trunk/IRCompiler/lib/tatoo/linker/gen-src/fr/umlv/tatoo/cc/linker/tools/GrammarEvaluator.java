package fr.umlv.tatoo.cc.linker.tools;


/** 
 *  This class is generated - please do not edit it 
 */
public interface GrammarEvaluator {
  /** This methods is called after the reduction of the non terminal start
   *  by the grammar production start_def.
   *  <code>start := 'grammar' 'colon' grammarDef_plus 'root' 'colon' 'identifier' 'link' 'colon' linkDef_plus </code>
   */
  public  void start_def(String identifier);
  /** This methods is called after the reduction of the non terminal grammarDef
   *  by the grammar production grammar_def.
   *  <code>grammarDef := 'identifier' 'assign' 'quotedstring' </code>
   */
  public  void grammar_def(String identifier, String quotedstring);
  /** This methods is called after the reduction of the non terminal linkDef
   *  by the grammar production link_def.
   *  <code>linkDef := 'identifier' 'dot' 'identifier' 'assign' 'identifier' linkStart_opt_optional0 linkVersion_opt_optional1 </code>
   */
  public  void link_def(String identifier, String identifier2, String identifier3, String linkStart_opt_optional0, String linkVersion_opt_optional1);
  /** This methods is called after the reduction of the non terminal linkStart
   *  by the grammar production linkStart_def.
   *  <code>linkStart := 'dot' 'identifier' </code>
   */
  public  String linkStart_def(String identifier);
  /** This methods is called after the reduction of the non terminal linkVersion
   *  by the grammar production linkVersion_def.
   *  <code>linkVersion := 'lbracket' 'identifier' 'rbracket' </code>
   */
  public  String linkVersion_def(String identifier);

}
