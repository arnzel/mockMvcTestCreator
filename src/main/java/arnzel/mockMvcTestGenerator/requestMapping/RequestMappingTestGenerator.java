package arnzel.mockMvcTestGenerator.requestMapping;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.lang.reflect.Method;
import java.util.Set;
import javax.lang.model.element.Modifier;

public class RequestMappingTestGenerator {
  
  private final RequestMappingParser requestMappingParser;

  public RequestMappingTestGenerator() {
    this.requestMappingParser = new RequestMappingParser();
  }
  
  public void createTests(TypeSpec.Builder testClassBuilder, Class clazz ){
    Set<Method> methods = 
        requestMappingParser.getRequestMappings(clazz);
    methods
        .stream()
        .forEach(method -> createTest(testClassBuilder,method));
  }
  
  private void createTest(TypeSpec.Builder testClassBuilder, Method method){
    MethodSpec methodSpec = MethodSpec.methodBuilder(method.getName())
        .addModifiers(Modifier.PUBLIC)
        .returns(void.class)
        .build();
    testClassBuilder.addMethod(methodSpec);
  }

}
