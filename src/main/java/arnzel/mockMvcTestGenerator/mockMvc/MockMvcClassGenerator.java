package arnzel.mockMvcTestGenerator.mockMvc;

import com.squareup.javapoet.TypeSpec;

public interface MockMvcClassGenerator {

  public TypeSpec createTestClass(Class clazz,String testClassName);
}
