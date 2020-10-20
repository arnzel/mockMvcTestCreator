package arnzel.mockMvcTestGenerator.mockMvc;

import static arnzel.mockMvcTestGenerator.javaPoet.FieldSpecUtils.getPrivateFieldSpec;
import static com.squareup.javapoet.MethodSpec.constructorBuilder;
import static com.squareup.javapoet.TypeSpec.classBuilder;
import static java.beans.Introspector.decapitalize;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import javax.lang.model.element.Modifier;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.beans.Introspector;

public class MockMvcStandAloneSetupClassGenerator implements MockMvcClassGenerator {

  private final String MOCK_MVC_VARIABLE_NAME = "mockMvc";

  public TypeSpec.Builder createTestClass(Class clazz,String testClassName){
    return classBuilder(testClassName)
            .addModifiers(Modifier.PUBLIC)
        .addField(getMockMvcFieldSpec())
        .addField(getControllerFieldSpec(clazz))
        .addMethod(getDefaultConstructorSpec(clazz));
  }

  private FieldSpec getMockMvcFieldSpec() {
    return getPrivateFieldSpec(
            MockMvc.class, MOCK_MVC_VARIABLE_NAME);
  }

  private FieldSpec getControllerFieldSpec(Class clazz){
    return getPrivateFieldSpec(
            clazz, getClassVariableName(clazz));
  }

  private MethodSpec getDefaultConstructorSpec(Class clazz){
    return constructorBuilder()
        .addModifiers(Modifier.PUBLIC)
        .addStatement("this.$N = new $N()",getClassVariableName(clazz),clazz.getSimpleName())
        .addStatement(generateMockMvcStandAloneSetupStatement(),
                MockMvcBuilders.class,
                clazz)
        .build();
  }

  private String getClassVariableName(Class clazz){
    return decapitalize(clazz.getSimpleName());
  }

  private String generateMockMvcStandAloneSetupStatement(){
    return "this." + MOCK_MVC_VARIABLE_NAME +" = $T.standaloneSetup(new $T()).build()";
  }

}
