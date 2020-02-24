package arnzel.mockMvcTestGenerator;

import static com.squareup.javapoet.JavaFile.builder;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.File;
import java.io.IOException;

public class TestClassWriter {

  private final String TEST_CLASS_PATH = "generated/src/test/java/";
  
  File writeTestClass(Class clazz,TypeSpec testClassTypeSpec){
    String testClassPackageName = getTestClassPackage(clazz);
    JavaFile javaFile = builder(testClassPackageName, testClassTypeSpec)
        .build();
    File testClassFile = new File(TEST_CLASS_PATH);
    try {
      javaFile.writeTo(testClassFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return testClassFile;
  }

  String getTestClassPackage(Class clazz){
    return clazz.getPackage().getName();
  }
}
