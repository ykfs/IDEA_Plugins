package main.java.com.ykfs.plugins.generate;

import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.generation.actions.BaseGenerateAction;
import main.java.com.ykfs.plugins.generate.handler.GenerateSetterWithFinalHandler;

/**
 * Created by ykf on 2016/3/10.
 */
public class GenerateSetterWithFinal extends BaseGenerateAction {
  public GenerateSetterWithFinal() {
    super(new GenerateSetterWithFinalHandler());
  }
}
