package arnzel.mockMvcTestGenerator.mockMvc;

import com.squareup.javapoet.TypeSpec;

public interface MockMvcClassGenerator {

  public TypeSpec.Builder createTestClass(Class clazz,String testClassName);
}
