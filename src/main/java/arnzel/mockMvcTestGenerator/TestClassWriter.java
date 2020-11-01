package arnzel.mockMvcTestGenerator;

import static com.squareup.javapoet.JavaFile.builder;
import static java.lang.String.format;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.File;
import java.io.IOException;

public class TestClassWriter {

  private final String TEST_CLASS_PATH = "src/test/java/";
  
  File writeTestClass(Class clazz,TypeSpec testClassTypeSpec){
    String testClassPackageName = getTestClassPackage(clazz);
    JavaFile javaFile = builder(testClassPackageName, testClassTypeSpec)
        .build();
    File testClassPath = new File(TEST_CLASS_PATH);
    try {
      return javaFile.writeToPath(testClassPath.toPath()).toFile();
    } catch (IOException e) {
      String error = format("Cannot write class '%s' to path '%s'",clazz.getName(),testClassPath.getPath());
      throw new RuntimeException(error);
    }
  }

  String getTestClassPackage(Class clazz){
    return clazz.getPackage().getName();
  }
}
