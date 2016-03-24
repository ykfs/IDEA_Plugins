package main.java.com.ykfs.plugins.generate.handler;

import com.intellij.codeInsight.generation.JavaConstructorBodyWithSuperCallGenerator;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.util.TypeConversionUtil;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Administrator on 2016/3/10.
 */
public class JavaConstructorBodyWithPreconditionsNotNullGenerator extends JavaConstructorBodyWithSuperCallGenerator {
  public JavaConstructorBodyWithPreconditionsNotNullGenerator() {
  }

  @Override
  public void generateFieldInitialization(@NotNull StringBuilder buffer, @NotNull PsiField[] fields, @NotNull PsiParameter[] parameters) {
    int i = 0;

    for(int length = fields.length; i < length; ++i) {
      PsiField field = fields[i];
      String fieldName = field.getName();
      String paramName = parameters[i].getName();
      if(fieldName.equals(paramName)) {
        buffer.append("this.");
      }

      buffer.append(fieldName).append("= ");
      if(!TypeConversionUtil.isPrimitiveAndNotNull(field.getType())) {
        buffer.append("checkNotNull(").append(paramName).append(")");
      } else {
        buffer.append(paramName);
      }

      buffer.append(";\n");
    }

  }
}
