package main.java.com.ykfs.plugins.generate.handler;

import com.intellij.codeInsight.generation.ClassMember;
import com.intellij.codeInsight.generation.ConstructorBodyGenerator;
import com.intellij.codeInsight.generation.GenerateConstructorHandler;
import com.intellij.codeInsight.generation.GenerateMembersUtil;
import com.intellij.codeInsight.generation.GenerationInfo;
import com.intellij.codeInsight.generation.PsiElementClassMember;
import com.intellij.codeInsight.generation.PsiFieldMember;
import com.intellij.codeInsight.generation.PsiGenerationInfo;
import com.intellij.codeInsight.generation.actions.GenerateConstructorAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JVMElementFactories;
import com.intellij.psi.JVMElementFactory;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiSubstitutor;
import com.intellij.psi.util.TypeConversionUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import sun.reflect.misc.ConstructorUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.intellij.openapi.ui.playback.PlaybackRunner.StatusCallback.Type.code;

/**
 * Created by ykfs on 2016/7/12.
 */
public class GenerateConstructorWithoutLineHandler extends GenerateConstructorHandler {
  private ConstructorBodyGenerator constructorBodyGenerator = new ConstructorWithoutLineGenerator();

  @NotNull
  @Override
  protected List<? extends GenerationInfo> generateMemberPrototypes(PsiClass aClass, ClassMember[] members) throws IncorrectOperationException {
    PsiMethod constructor = generateConstructorWithoutLine(aClass, members);
    return Collections.singletonList(new PsiGenerationInfo<PsiMethod>(constructor));
  }

  private PsiMethod generateConstructorWithoutLine(PsiClass psiClass, ClassMember[] members) {
    List<PsiField> fieldsVector = new ArrayList<PsiField>();
    StringBuilder buffer = new StringBuilder();

    buffer.append("public ").append(psiClass.getName()).append("(");
    int iCount = 0;
    for (ClassMember member1 : members) {
      PsiElement member = ((PsiElementClassMember)member1).getElement();
      if (member instanceof PsiField) {
        iCount++;
        PsiField field = (PsiField) member;
        fieldsVector.add(field);
        //why can not add final ?
        buffer.append("final ").append(field.getType().getPresentableText()).append(' ').append(field.getName().replace("_", "")).append(',');
      }
    }
    if (iCount > 0) {
      buffer.delete(buffer.length() - 1, buffer.length());
    }
    buffer.append("){\n");

    for (PsiField field: fieldsVector) {
      String fieldName = field.getName();
      buffer.append(fieldName);
      buffer.append(" = ");
      buffer.append(fieldName.replace("_", ""));
      buffer.append(";\n");
    }
    buffer.append("}\n");

    PsiElementFactory elementFactory = JavaPsiFacade.getElementFactory(psiClass.getProject());
    PsiMethod constructor = elementFactory.createMethodFromText(buffer.toString(), psiClass);
    return constructor;
  }
}
