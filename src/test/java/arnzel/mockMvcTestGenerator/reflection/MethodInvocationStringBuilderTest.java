package arnzel.mockMvcTestGenerator.reflection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class MethodInvocationStringBuilderTest {

  private MethodInvocationStringBuilder methodInvocationStringBuilderUnderTest;

  @BeforeEach
  void setUp() {
    
    methodInvocationStringBuilderUnderTest = new MethodInvocationStringBuilder();
  }

  @Test
  void testGetMethodInvocationString() throws NoSuchMethodException {
    // Setup
    Class[] cArg = new Class[1];
    cArg[0] = Object[].class;
    final Method method = MockMvcBuilders.class.getMethod("standaloneSetup",cArg);

    // Run the test
    final String result = methodInvocationStringBuilderUnderTest
        .getMethodInvocationString(method, "controller");

    // Verify the results
    assertThat(result).isEqualTo("Method.standaloneSetup(controller);");
  }
}
