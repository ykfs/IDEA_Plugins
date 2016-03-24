package main.java.com.ykfs.plugins.generate;

import com.intellij.codeInsight.generation.actions.BaseGenerateAction;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;
import com.siyeh.ig.psiutils.ImportUtils;
import main.java.com.ykfs.plugins.generate.handler.OverrideMethodsWithFinalHandler;

import org.jetbrains.annotations.NotNull;


/**
 * Created by ykf on 2016/3/10.
 */
public class OverrideMethodsWithFinal extends BaseGenerateAction {
  public OverrideMethodsWithFinal() {
    super(new OverrideMethodsWithFinalHandler());
  }

  @Override
  protected boolean isValidForClass(PsiClass targetClass) {
    return super.isValidForClass(targetClass) && !(targetClass instanceof PsiAnonymousClass);
  }

  @Override
  public void actionPerformedImpl(@NotNull Project project, Editor editor) {
    super.actionPerformedImpl(project, editor);
    PsiFile psiFile = PsiUtilBase.getPsiFileInEditor(editor, project);
    PsiClass psiClass = (PsiClass) PsiTreeUtil.getChildOfType(psiFile, PsiClass.class);
//    final PsiField psiField = psiClass.getFields()[0];
//    WriteCommandAction.runWriteCommandAction(project, new Runnable() {
//      public void run() {
//        ImportUtils.addStaticImport("com.google.common.base.Preconditions", "checkNotNull", psiField);
//      }
//    });
  }
}