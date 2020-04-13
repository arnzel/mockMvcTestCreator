package arnzel.mockMvcTestGenerator.mockMvc;

import static arnzel.mockMvcTestGenerator.javaPoet.FieldSpecUtils.getPrivateFieldSpec;
import static com.squareup.javapoet.MethodSpec.constructorBuilder;
import static com.squareup.javapoet.TypeSpec.classBuilder;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import javax.lang.model.element.Modifier;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class MockMvcStandAloneSetupClassGenerator implements MockMvcClassGenerator {

  private final String MOCK_MVC_VARIABLE_NAME = "mockMvc";

  private final String MOCK_MVC_BUILDERS_VARIABLE_NAME = "mockMvcBuilders";

  private final String MOCK_MVC_REQUEST_BUILDERS_VARIABLE_NAME = "mockMvcRequestBuilders";

  public TypeSpec.Builder createTestClass(Class clazz,String testClassName){
    return classBuilder(testClassName)
        .addField(getMockMvcFieldSpec())
        .addField(getControllerFieldSpec(clazz))
            .addField(getMockMvcBuildersFieldSpec())
            .addField(getMockMvcRequestBuildersFieldSpec())
        .addMethod(getDefaultConstructorSpec(clazz));
  }

  private FieldSpec getMockMvcRequestBuildersFieldSpec() {
    return getPrivateFieldSpec(
            MockMvcRequestBuilders.class,
            MOCK_MVC_REQUEST_BUILDERS_VARIABLE_NAME);

  }
  private FieldSpec getMockMvcBuildersFieldSpec() {
    return getPrivateFieldSpec(
            MockMvcBuilders.class,
            MOCK_MVC_BUILDERS_VARIABLE_NAME);
  }


  private FieldSpec getMockMvcFieldSpec() {
    return getPrivateFieldSpec(
            MockMvc.class, MOCK_MVC_VARIABLE_NAME);
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
            MOCK_MVC_VARIABLE_NAME,
                MOCK_MVC_BUILDERS_VARIABLE_NAME,
                clazz.getSimpleName())
        .build();
  }

  private String generateMockMvcStandAloneSetupStatement(){
    return "this.$N = $N.standaloneSetup(new $N()).build()";
  }

}
