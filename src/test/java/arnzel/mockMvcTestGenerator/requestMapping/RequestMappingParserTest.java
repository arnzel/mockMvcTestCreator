package arnzel.mockMvcTestGenerator.requestMapping;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import fixtures.controllers.RequestMappingController;
import fixtures.otherClasses.NonControllerClass;
import java.lang.reflect.Method;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RequestMappingParserTest {

  private RequestMappingParser requestMappingParser;

  @BeforeEach
  void setUp() {
    requestMappingParser = new RequestMappingParser();
  }

  @Test
  void classHasMethodWithAnnotation() {

    // Run the test
    final Collection<Method> result = requestMappingParser.getMethodsAnnotatedWithRequestMapping(
        RequestMappingController.class);

    // Verify the results
    assertThat(result.size()).isEqualTo(1);
  }

  @Test
  void classHasNotMethodWithAnnotation() {

    // Run the test
    final Collection<Method> result = requestMappingParser.getMethodsAnnotatedWithRequestMapping(
        NonControllerClass.class);

    // Verify the results
    assertThat(result.size()).isEqualTo(0);
  }
}
