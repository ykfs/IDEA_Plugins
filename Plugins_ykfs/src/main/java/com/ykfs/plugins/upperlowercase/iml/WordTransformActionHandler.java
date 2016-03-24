package main.java.com.ykfs.plugins.upperlowercase.iml;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;

/**
 * Created by ykfs on 2016/3/23.
 */
public class WordTransformActionHandler extends EditorWriteActionHandler {
  private CaseTransformer _transformer;

  public WordTransformActionHandler(CaseTransformer transformer) {
    _transformer = transformer;
  }

  @Override
  public void executeWriteAction(Editor editor, Caret caret, DataContext dataContext) {
    if (editor != null) {
      SelectionModel selectionModel = editor.getSelectionModel();
      if (selectionModel != null && selectionModel.hasSelection()) {
        handleSelection(editor, selectionModel);
      }
    }
  }

  private void handleSelection(Editor editor, SelectionModel selectionModel) {
    Document doc = editor.getDocument();
    if(doc != null) {
      int start = selectionModel.getSelectionStart();
      int end = selectionModel.getSelectionEnd();
//      char[] allChars = doc.getChars();
//      StringBuffer selection = new StringBuffer();
//      selection.append(allChars, start, end - start);
//      this.m_transformer.transform(selection);
      StringBuffer selection = new StringBuffer(selectionModel.getSelectedText());
      _transformer.transform(selection);
      doc.replaceString(start, end, selection.toString());
//      selectionModel.setSelection(start, start + selection.length());
    }
  }
}
