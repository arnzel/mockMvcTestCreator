package arnzel.mockMvcTestGenerator.parsers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import fixtures.controllers.MethodMappingController;
import fixtures.otherClasses.NonControllerClass;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestMapping;

class RequestMappingParserTest {

  private RequestMappingParser requestMappingParser;

  @BeforeEach
  void setUp() {
    requestMappingParser = new RequestMappingParser();
  }

  @Test
  void classHasMethodWithAnnotation() {

    // Run the test
    final List<RequestMapping> result = requestMappingParser.getRequestMappings(
        MethodMappingController.class);

    // Verify the results
    assertThat(result.size()).isEqualTo(1);
  }

  @Test
  void classHasNotMethodWithAnnotation() {

    // Run the test
    final List<RequestMapping> result = requestMappingParser.getRequestMappings(
        NonControllerClass.class);

    // Verify the results
    assertThat(result.size()).isEqualTo(0);
  }
}
