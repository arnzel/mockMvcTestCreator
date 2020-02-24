package arnzel.mockMvcTestGenerator;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.File;
import java.io.IOException;

public class TestClassWriter {

  private final String TEST_CLASS_PATH = "generated/src/test/java/";

  private final String TEST_CLASS_NAME_POSTFIX = "Test";


  File writeTestClass(Class clazz){
    String testClassName= getTestClassName(clazz);
    String testClassPackageName = getTestClassPackage(clazz);
    TypeSpec testClassTypeSpec = TypeSpec
        .classBuilder(testClassName)
        .build();
    JavaFile javaFile = JavaFile.builder(testClassPackageName, testClassTypeSpec)
        .build();
    File testClassFile = new File(TEST_CLASS_PATH);
    try {
      javaFile.writeTo(testClassFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return testClassFile;
  }

  String getTestClassName(Class clazz){
    return clazz.getSimpleName() + TEST_CLASS_NAME_POSTFIX;
  }

  String getTestClassPackage(Class clazz){
    return clazz.getPackage().getName();
  }
}
