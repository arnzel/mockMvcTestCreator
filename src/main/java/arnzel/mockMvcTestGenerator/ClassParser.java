package arnzel.mockMvcTestGenerator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
