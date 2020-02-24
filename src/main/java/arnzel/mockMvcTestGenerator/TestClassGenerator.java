package arnzel.mockMvcTestGenerator;

import static com.squareup.javapoet.MethodSpec.constructorBuilder;
import static com.squareup.javapoet.TypeSpec.classBuilder;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.io.File;
import javax.lang.model.element.Modifier;
import org.springframework.test.web.servlet.MockMvc;

public class TestClassGenerator {
  
  private final String TEST_CLASS_NAME_POSTFIX = "Test";
  
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
        .addMethod(getDefaultConstructorSpec())
        .build();
  }
  
  private MethodSpec getDefaultConstructorSpec(){ 
    return constructorBuilder()
        .addModifiers(Modifier.PUBLIC)
        .build();
  }

  private FieldSpec getMockMvcFieldSpec() {
    return FieldSpec
        .builder(MockMvc.class, "mockMvc")
        .addModifiers(Modifier.PRIVATE)
        .build();
  }

  String getTestClassName(Class clazz){
    return clazz.getSimpleName() + TEST_CLASS_NAME_POSTFIX;
  }

}
