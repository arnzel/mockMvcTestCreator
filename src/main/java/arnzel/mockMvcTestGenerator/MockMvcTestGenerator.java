package arnzel.mockMvcTestGenerator;

import arnzel.mockMvcTestGenerator.exceptions.IllegalClassException;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class MockMvcTestGenerator {
  
  public void generateMockMvcTest(Class clazz){
    if(isClassAnnotatedWith(clazz,Controller.class)){
      
    }else{
      throw new IllegalClassException(clazz);
    }
  }
  
  private boolean isClassAnnotatedWith(Class clazz,Class annotationClazz){
    return clazz.isAnnotationPresent(annotationClazz);
  }

}
