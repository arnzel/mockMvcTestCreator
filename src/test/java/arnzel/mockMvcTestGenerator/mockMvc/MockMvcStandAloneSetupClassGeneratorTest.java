package arnzel.mockMvcTestGenerator.mockMvc;

import com.squareup.javapoet.TypeSpec;
import fixtures.controllers.MethodMappingController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.JavaPoetUtil;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static util.ResourcesUtil.getResourceContent;

class MockMvcStandAloneSetupClassGeneratorTest {

    private MockMvcStandAloneSetupClassGenerator mockMvcStandAloneSetupClassGeneratorUnderTest;

    @BeforeEach
    void setUp() {
        mockMvcStandAloneSetupClassGeneratorUnderTest = new MockMvcStandAloneSetupClassGenerator();
    }

    @Test
    void testCreateTestClass() throws IOException, URISyntaxException {
        // Setup

        // Run the test
        final TypeSpec.Builder result = mockMvcStandAloneSetupClassGeneratorUnderTest
                .createTestClass(MethodMappingController.class, "testClassName");

        // Verify the results
        String javaFile =
                JavaPoetUtil.createJavaFileWithField(result.build());

        assertThat(javaFile).isEqualTo(getResourceContent("/classWithMockMvcInstance.txt"));
    }
}
