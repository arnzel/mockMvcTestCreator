package arnzel.mockMvcTestGenerator.mockMvc;

import static com.squareup.javapoet.MethodSpec.constructorBuilder;
import static com.squareup.javapoet.TypeSpec.classBuilder;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import javax.lang.model.element.Modifier;
import org.springframework.test.web.servlet.MockMvc;

public class MockMvcStandAloneSetupClassGenerator implements MockMvcClassGenerator {

  private final String MOCK_MVC_VARIABLE_NAME = "mockMvc";

  public TypeSpec createTestClass(Class clazz,String testClassName){
    return classBuilder(testClassName)
        .addField(getMockMvcFieldSpec())
        .addField(getControllerFieldSpec(clazz))
        .addMethod(getDefaultConstructorSpec(clazz))
        .build();
  }

  private FieldSpec getMockMvcFieldSpec() {
    return FieldSpec
        .builder(MockMvc.class, MOCK_MVC_VARIABLE_NAME)
        .addModifiers(Modifier.PRIVATE)
        .build();
  }

  private FieldSpec getControllerFieldSpec(Class clazz){
    return FieldSpec
        .builder(clazz, clazz.getSimpleName())
        .addModifiers(Modifier.PRIVATE)
        .build();
  }

  private MethodSpec getDefaultConstructorSpec(Class clazz){
    return constructorBuilder()
        .addModifiers(Modifier.PUBLIC)
        .addStatement("this.$N = new $N()",clazz.getSimpleName(),clazz.getSimpleName())
        .addStatement(generateMockMvcStandAloneSetupStatement(),
            MOCK_MVC_VARIABLE_NAME, clazz.getSimpleName())
        .build();
  }

  private String generateMockMvcStandAloneSetupStatement(){
    return "this.$N = MockMvcBuilders.standaloneSetup(new $N()).build()";
  }

}
