package arnzel.mockMvcTestGenerator;

import static com.squareup.javapoet.MethodSpec.constructorBuilder;
import static com.squareup.javapoet.TypeSpec.classBuilder;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.io.File;
import java.lang.reflect.Field;
import javax.lang.model.element.Modifier;
import org.springframework.test.web.servlet.MockMvc;

public class TestClassGenerator {
  
  private final String TEST_CLASS_NAME_POSTFIX = "Test";
  
  private final String MOCK_MVC_VARIABLE_NAME = "mockMvc";
  
  private final TestClassWriter testClassWriter;
  
  public TestClassGenerator() {
    this.testClassWriter = new TestClassWriter();
  }
  
  public File generateTestClass(Class clazz) {
    TypeSpec typeSpec = getTypeSpec(clazz);
    return testClassWriter
        .writeTestClass(clazz,typeSpec);
  }

  TypeSpec getTypeSpec(Class clazz){
    return classBuilder(getTestClassName(clazz))
        .addField(getMockMvcFieldSpec())
        .addField(getControllerFieldSpec(clazz))
        .addMethod(getDefaultConstructorSpec(clazz))
        .build();
  }
  
  private MethodSpec getDefaultConstructorSpec(Class clazz){ 
    //TODO Initialize mockMvc Variable correctly
    return constructorBuilder()
        .addModifiers(Modifier.PUBLIC)
        .addStatement("this.$N = new $N()",clazz.getSimpleName(),clazz.getSimpleName())
        .addStatement("this.$N = $N", MOCK_MVC_VARIABLE_NAME, "null")
        .build();
  }
  
/*  private String generateMockMvcInitialisationStatement(){
    
  }*/
  
  private FieldSpec getControllerFieldSpec(Class clazz){
    return FieldSpec
        .builder(clazz, clazz.getSimpleName())
        .addModifiers(Modifier.PRIVATE)
        .build();
  }

  private FieldSpec getMockMvcFieldSpec() {
    return FieldSpec
        .builder(MockMvc.class, MOCK_MVC_VARIABLE_NAME)
        .addModifiers(Modifier.PRIVATE)
        .build();
  }

  String getTestClassName(Class clazz){
    return clazz.getSimpleName() + TEST_CLASS_NAME_POSTFIX;
  }

}
