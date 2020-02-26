package arnzel.mockMvcTestGenerator;

import arnzel.mockMvcTestGenerator.mockMvc.MockMvcStandAloneSetupClassGenerator;
import arnzel.mockMvcTestGenerator.mockMvc.MockMvcClassGenerator;
import com.squareup.javapoet.TypeSpec;
import java.io.File;


public class TestClassGenerator {
  
  private final String TEST_CLASS_NAME_POSTFIX = "Test";
  
  private final TestClassWriter testClassWriter;
  
  private final MockMvcClassGenerator
      mockMvcClassGenerator;
  
  public TestClassGenerator() {
    this.testClassWriter = new TestClassWriter();
    this.mockMvcClassGenerator = 
        new MockMvcStandAloneSetupClassGenerator();
  }
  
  public File generateTestClass(Class clazz) {
    TypeSpec typeSpec = mockMvcClassGenerator
        .createTestClass(clazz,getTestClassName(clazz));
    return testClassWriter
        .writeTestClass(clazz,typeSpec);
  }


  private String getTestClassName(Class clazz){
    return clazz.getSimpleName() + TEST_CLASS_NAME_POSTFIX;
  }

}
