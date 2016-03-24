package main.java.com.ykfs.plugins.generate.handler;

import com.intellij.codeInsight.generation.*;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykf on 2016/3/10.
 */
public class GenerateSetterWithFinalHandler extends GenerateSetterHandler {
  private static final Logger log = Logger.getInstance(GenerateSetterWithFinalHandler.class.getName());

  @Override
  protected GenerationInfo[] generateMemberPrototypes(PsiClass aClass, ClassMember original) throws IncorrectOperationException {
    if (original instanceof PropertyClassMember) {
      final PropertyClassMember propertyClassMember = (PropertyClassMember)original;
      GenerationInfo[] getters = propertyClassMember.generateSetters(aClass);
      generateSetters(getters);
      if (getters != null) {
        return getters;
      }
    }
    else if (original instanceof EncapsulatableClassMember) {
      final EncapsulatableClassMember encapsulatableClassMember = (EncapsulatableClassMember)original;
      final GenerationInfo setter = encapsulatableClassMember.generateSetter();
      if (setter != null) {
        return new GenerationInfo[]{setter};
      }
    }
    return GenerationInfo.EMPTY_ARRAY;
  }

  private void generateSetters(GenerationInfo[] getters) {
//    GenerationInfo[] result = getters;
    for (int i = 0; i < getters.length; i++) {
      PsiMethod psiMethod = (PsiMethod)getters[i].getPsiMember();
      Project project = psiMethod.getProject();
      PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();

      String sMethod = psiMethod.getText();
      sMethod = sMethod.replaceAll("\\(", "fuck(final ");
      getters[i] = new PsiGenerationInfo(factory.createMethodFromText(sMethod, psiMethod));
    }
//    return result;
  }
}
