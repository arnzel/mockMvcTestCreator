package arnzel.mockMvcTestGenerator;

import java.io.File;

public class TestClassGenerator {
  
  private final TestClassWriter testClassWriter;
  
  public TestClassGenerator() {
    this.testClassWriter = new TestClassWriter();
  }
  
  public File generateTestClass(Class clazz) {
    return testClassWriter
        .writeTestClass(clazz);
  }

}
