package arnzel.mockMvcTestGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import arnzel.mockMvcTestGenerator.exceptions.IllegalClassException;
import fixtures.controllers.MethodMappingController;
import fixtures.otherClasses.NonControllerClass;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MockMvcTestGeneratorTest {

  private MockMvcTestGenerator mockMvcTestGeneratorUnderTest;

  @BeforeEach
  void setUp() {
    mockMvcTestGeneratorUnderTest = new MockMvcTestGenerator();
  }

  @Test
  void generateTestForMethodMapping(){
    File testClassFile =
        mockMvcTestGeneratorUnderTest.generateMockMvcTest(MethodMappingController.class);
    assertThat(testClassFile).isNotNull();
  }
  
  @Test
  void expectErrorForClassNotAnnotatedWithController(){
    assertThatThrownBy(() -> {
      mockMvcTestGeneratorUnderTest.generateMockMvcTest(NonControllerClass.class);
    }).isInstanceOf(IllegalClassException.class)
        .hasMessageContaining("Cannot create mock mvc test for class 'fixtures.otherClasses.NonControllerClass' which is not annotated with @Controller");
 
  }
}
