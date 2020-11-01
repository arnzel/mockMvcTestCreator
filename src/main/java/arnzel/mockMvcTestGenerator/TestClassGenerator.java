package arnzel.mockMvcTestGenerator;

import arnzel.mockMvcTestGenerator.mockMvc.MockMvcStandAloneSetupClassGenerator;
import arnzel.mockMvcTestGenerator.mockMvc.MockMvcClassGenerator;
import arnzel.mockMvcTestGenerator.requestMapping.RequestMappingTestGenerator;
import com.squareup.javapoet.TypeSpec;
import java.io.File;


public class TestClassGenerator {
  
  private final String TEST_CLASS_NAME_POSTFIX = "Test";
  
  private final TestClassWriter testClassWriter;
  
  private final MockMvcClassGenerator mockMvcClassGenerator;
  
  private final RequestMappingTestGenerator requestMappingTestGenerator;
  
  public TestClassGenerator(TestClassWriter testClassWriter,
                            MockMvcClassGenerator mockMvcClassGenerator,
                            RequestMappingTestGenerator requestMappingTestGenerator) {
    this.testClassWriter = testClassWriter;
    this.mockMvcClassGenerator =
            mockMvcClassGenerator;
    this.requestMappingTestGenerator = requestMappingTestGenerator;
  }
  
  public File generateTestClass(Class clazz) {
    TypeSpec.Builder testClassBuilder = mockMvcClassGenerator
        .createTestClass(clazz,getTestClassName(clazz));
    requestMappingTestGenerator
        .createTests(testClassBuilder,clazz);
    return testClassWriter
        .writeTestClass(clazz,testClassBuilder.build());
  }


  private String getTestClassName(Class clazz){
    return clazz.getSimpleName() + TEST_CLASS_NAME_POSTFIX;
  }

}
