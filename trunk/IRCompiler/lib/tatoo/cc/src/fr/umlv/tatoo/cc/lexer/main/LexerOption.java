package fr.umlv.tatoo.cc.lexer.main;

import java.util.Arrays;
import java.util.List;

import fr.umlv.tatoo.cc.common.main.AbstractSimpleCommand;
import fr.umlv.tatoo.cc.common.main.GeneratorBean;

public class LexerOption  {
  
  private LexerOption() {
    // can't be instantiated
  }
  
  public interface LexerParam {
    public void setLexerType(LexerType lexerType);
  }
  
  public static final AbstractSimpleCommand<LexerParam> charset=
    new AbstractSimpleCommand<LexerParam>("charset") {
      public void execute(String option,LexerParam bean, List<? extends String> args) {
        String charset = args.get(0);
        bean.setLexerType(LexerType.valueOf(charset));
      }
      public String usage(String optionName, int numberOfArgument) {
        return "choose charset in "+Arrays.toString(LexerType.values());
      }
      public String usageArgumentName(String option, int numberOfArgument, int index) {
        return "charset name";
      }
    };

    public static final AbstractSimpleCommand<GeneratorBean> name=
      new AbstractSimpleCommand<GeneratorBean>("name") {
      public void execute(String option,GeneratorBean bean, List<? extends String> args) {
        bean.setTypeName(LexerAliasPrototype.rule,args.get(0));
      }
      public String usage(String optionName, int numberOfArguments) {
        return "generated rule enum class name";
      }
      public String usageArgumentName(String option, int numberOfArgument, int index) {
        return "name";
      }
    };
}
