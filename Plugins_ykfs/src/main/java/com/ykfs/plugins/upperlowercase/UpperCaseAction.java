package main.java.com.ykfs.plugins.upperlowercase;

import com.intellij.openapi.editor.actionSystem.EditorAction;
import main.java.com.ykfs.plugins.upperlowercase.iml.UpperCase;
import main.java.com.ykfs.plugins.upperlowercase.iml.WordTransformActionHandler;

/**
 * Created by ykfs on 2016/3/23.
 */
public class UpperCaseAction extends EditorAction {
  public UpperCaseAction() {
    super(new WordTransformActionHandler(new UpperCase()));
  }
}
