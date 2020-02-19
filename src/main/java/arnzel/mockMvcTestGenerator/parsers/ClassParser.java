package arnzel.mockMvcTestGenerator.parsers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestMapping;

public class ClassParser {
  
  public List<Method> findAnnotatedMethods(Class<?> clazz, 
      Class<? extends Annotation> annotationClass) {
    Method[] methods = clazz.getMethods();
    List<Method> annotatedMethods = new ArrayList<Method>(methods.length);
    for (Method method : methods) {
      if( method.isAnnotationPresent(annotationClass)){
        annotatedMethods.add(method);
      }
    }
    return annotatedMethods;
  }

  

}
