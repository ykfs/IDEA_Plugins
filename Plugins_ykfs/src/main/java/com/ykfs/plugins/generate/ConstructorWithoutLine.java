package main.java.com.ykfs.plugins.generate;

import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.generation.GenerateConstructorHandler;
import com.intellij.codeInsight.generation.actions.BaseGenerateAction;
import main.java.com.ykfs.plugins.generate.handler.GenerateConstructorWithoutLineHandler;

/**
 * Created by ykfs on 2016/7/12.
 */
public class ConstructorWithoutLine extends BaseGenerateAction {
  public ConstructorWithoutLine() {
    super(new GenerateConstructorWithoutLineHandler());
  }
}
