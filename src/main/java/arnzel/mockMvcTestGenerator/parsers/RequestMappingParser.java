package arnzel.mockMvcTestGenerator.parsers;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestMapping;

public class RequestMappingParser {
  
  private final ClassParser classParser;

  public RequestMappingParser() {
    this.classParser = new ClassParser();
  }
  
  public List<RequestMapping> getRequestMappings(Class<?> clazz){
    List<Method> methods =
        classParser.findAnnotatedMethods(clazz,RequestMapping.class);
    return methods
        .stream()
        .map(method -> method.getAnnotation(RequestMapping.class))
        .collect(Collectors.toList());
  }
}
