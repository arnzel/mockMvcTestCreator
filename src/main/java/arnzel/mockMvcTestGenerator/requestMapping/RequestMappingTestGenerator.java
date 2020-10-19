package arnzel.mockMvcTestGenerator.requestMapping;

import static java.lang.String.format;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.lang.reflect.Method;
import java.util.Set;
import javax.lang.model.element.Modifier;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.bind.annotation.RequestMapping;

public class RequestMappingTestGenerator {
  
  private final RequestMappingParser requestMappingParser;

  public RequestMappingTestGenerator() {
    this.requestMappingParser = new RequestMappingParser();
  }
  
  public void createTests(TypeSpec.Builder testClassBuilder, Class clazz ){
    Set<Method> methods = 
        requestMappingParser.getMethodsAnnotatedWithRequestMapping(clazz);
    methods
        .stream()
        .forEach(method -> createTestMethod(testClassBuilder,method));
  }
  
  private void createTestMethod(TypeSpec.Builder testClassBuilder, Method method){
    MethodSpec methodSpec = MethodSpec.methodBuilder(method.getName())
        .addModifiers(Modifier.PUBLIC)
        .addAnnotation(Test.class)
        .addStatement(
                getPerformRequestString(method),
                MockMvcRequestBuilders.class,
                MediaType.class,
                MockMvcResultHandlers.class)
        .returns(void.class)
        .addException(Exception.class)
        .build();
    testClassBuilder.addMethod(methodSpec);
  }
  
  private String getPerformRequestString(Method method){
    RequestMapping requestMapping =
        method.getAnnotation(RequestMapping.class);
    return format(
        "mockMvc" +
                ".perform( $T.get(%s).accept($T.APPLICATION_JSON))" +
                ".andDo($T.print())",
        "\"" + requestMapping.value()[0] + "\"");
  }

}
