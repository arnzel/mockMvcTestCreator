package arnzel.mockMvcTestGenerator;


import arnzel.mockMvcTestGenerator.exceptions.IllegalClassException;
import java.io.File;
import org.springframework.stereotype.Controller;

public class MockMvcTestGenerator {
  
  private final TestClassGenerator testClassGenerator;

  public MockMvcTestGenerator() {
    this.testClassGenerator = new TestClassGenerator(testClassWriter);
  }

  public File generateMockMvcTest(Class clazz){
    validateClass(clazz);
    return writeTestClass(clazz);
  }
  
  private File writeTestClass(Class clazz){
    return testClassGenerator
        .generateTestClass(clazz);
  }
  
  private void validateClass(Class clazz){
    if(!isClassAnnotatedWith(clazz,Controller.class)){
      throw new IllegalClassException(clazz);
    }
  }
  
  private boolean isClassAnnotatedWith(Class clazz,Class annotationClazz){
    return clazz.isAnnotationPresent(annotationClazz);
  }

}
