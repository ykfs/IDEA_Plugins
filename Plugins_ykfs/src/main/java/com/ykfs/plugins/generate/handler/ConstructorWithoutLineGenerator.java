package main.java.com.ykfs.plugins.generate.handler;

import com.intellij.codeInsight.generation.JavaConstructorBodyWithSuperCallGenerator;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiParameter;
import org.jetbrains.annotations.NotNull;

/**
 * Created by ykfs on 2016/7/12.
 */
public class ConstructorWithoutLineGenerator extends JavaConstructorBodyWithSuperCallGenerator {
  @Override
  public StringBuilder start(StringBuilder buffer, @NotNull String name, @NotNull PsiParameter[] parameters) {
    buffer.append("public ").append(name).append("(");
    for (PsiParameter parameter : parameters) {
      //去除私有成员前的下划线
      buffer.append(parameter.getType().getPresentableText()).append(' ').append(parameter.getName().replace("_", "")).append(',');
    }
    if (parameters.length > 0) {
      buffer.delete(buffer.length() - 1, buffer.length());
    }
    buffer.append("){\n");
    return buffer;
  }
}
