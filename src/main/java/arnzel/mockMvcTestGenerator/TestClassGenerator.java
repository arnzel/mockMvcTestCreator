package arnzel.mockMvcTestGenerator;

import static java.lang.String.format;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.File;
import java.io.IOException;

public class TestClassGenerator {
  
  private final String TEST_CLASS_PATH = "generated/src/test/java/";
  
  private final String TEST_CLASS_NAME_POSTFIX = "Test";

  public File generateTestClass(Class clazz) {
    String testClassName= getTestClassName(clazz);
    String testClassPackageName = getTestClassPackage(clazz);
    System.out.println(format("Generating test class '%s' in package '%s'",
        testClassName,
        testClassPackageName));
    return writeTestClass(testClassName,testClassPackageName);
  }
  
  String getTestClassName(Class clazz){
    return clazz.getSimpleName() + TEST_CLASS_NAME_POSTFIX;
  }

  String getTestClassPackage(Class clazz){
    return clazz.getPackage().getName();
  }
  
  File writeTestClass(String testClassName,String testClassPackageName){
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
  

}
