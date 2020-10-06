package arnzel.mockMvcTestGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

import arnzel.mockMvcTestGenerator.exceptions.IllegalClassException;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import fixtures.controllers.MethodMappingController;
import fixtures.otherClasses.NonControllerClass;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MockMvcTestGeneratorClassTest {

  private static final FileSystem FILE_SYSTEM = Jimfs.newFileSystem(Configuration.unix());

  private MockMvcTestGenerator mockMvcTestGeneratorUnderTest;

  private TestClassGenerator testClassGenerator = mock(TestClassGenerator.class);

  @BeforeEach
  void setUp() {
    mockMvcTestGeneratorUnderTest = new MockMvcTestGenerator(testClassGenerator);
  }

  @Test
  void generateTestForMethodMapping() throws IOException {
    // given
    givenClassGeneration(MethodMappingController.class);

    // when
    File testClassFile =
        mockMvcTestGeneratorUnderTest.generateMockMvcTest(MethodMappingController.class);

    // then
    assertThat(testClassFile).isNotNull();
  }
  
  @Test
  void expectErrorForClassNotAnnotatedWithController(){
    assertThatThrownBy(() -> {
      mockMvcTestGeneratorUnderTest.generateMockMvcTest(NonControllerClass.class);
    }).isInstanceOf(IllegalClassException.class)
        .hasMessageContaining("Cannot create mock mvc test for class 'fixtures.otherClasses.NonControllerClass' which is not annotated with @Controller");
 
  }

  private void givenClassGeneration(Class clazz) throws IOException {
    File file = Files.createTempFile("prefix","suffix").toFile();
    Mockito.when(testClassGenerator.generateTestClass(clazz))
            .thenReturn(file);
  }
}
