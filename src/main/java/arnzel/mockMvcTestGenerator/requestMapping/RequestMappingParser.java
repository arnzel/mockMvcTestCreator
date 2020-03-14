package arnzel.mockMvcTestGenerator.requestMapping;

import arnzel.mockMvcTestGenerator.ClassParser;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestMapping;

public class RequestMappingParser {
  
  private final ClassParser classParser;

  public RequestMappingParser() {
    this.classParser = new ClassParser();
  }
  
  public Set<Method> getMethodsAnnotatedWithRequestMapping(Class<?> clazz){
    List<Method> methods =
        classParser.findAnnotatedMethods(clazz,RequestMapping.class);
    return methods
        .stream()
        .collect(Collectors.toSet());
  }
}
