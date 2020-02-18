package arnzel.mockMvcTestGenerator.exceptions;

import static java.lang.String.format;

public class IllegalClassException extends IllegalArgumentException {

  public IllegalClassException(Class clazz){
    super(getError(clazz));
  }
  
  private static String getError(Class clazz){
    return
        format("Cannot create mock mvc test for class '%s' " 
            + "which is not annotated with @Controller",clazz.getName());
  }
  
}
