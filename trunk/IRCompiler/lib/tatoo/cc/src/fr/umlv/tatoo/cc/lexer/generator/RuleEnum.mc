<![HEAD[
import fr.umlv.tatoo.cc.lexer.lexer.RuleDecl;
import fr.umlv.tatoo.cc.common.generator.Type;
import java.util.*;
%param rules:List<? extends RuleDecl>;
%param ruleEnum:Type;
]]>
package <[|ruleEnum.getPackageName()|]>;

/** 
 *  This class is generated - please do not edit it 
 */
public enum <[|ruleEnum.getSimpleName()|]> {
<![JAVA[for(Iterator<? extends RuleDecl> r=rules.iterator();r.hasNext();) {
RuleDecl rule = r.next();
out.print(rule.getId());
if (r.hasNext())
  out.println(',');
}]]>;
}