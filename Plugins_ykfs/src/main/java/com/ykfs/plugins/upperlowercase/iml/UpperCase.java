package main.java.com.ykfs.plugins.upperlowercase.iml;

/**
 * Created by ykfs on 2016/3/23.
 */
public class UpperCase implements CaseTransformer {
  public void transform(StringBuffer buffer) {
    int length = buffer.length();

    for(int i = 0; i < length; i++) {
      buffer.setCharAt(i, Character.toUpperCase(buffer.charAt(i)));
    }
  }
}
