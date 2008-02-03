package fr.umlv.tatoo.cc.lexer.main;

import fr.umlv.tatoo.cc.common.extension.ExtensionBus.DataKey;
import fr.umlv.tatoo.cc.lexer.lexer.RuleFactory;

public class LexerDataKeys {
  public static final DataKey<RuleFactory> ruleFactory=
    new DataKey<RuleFactory>();
}
