package arnzel.mockMvcTestGenerator.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class ClassUtilsTest {


    @Test
    public void testGetClassFromFile() throws Exception {
        // Setup
        final File file = new File("src\\test\\java\\arnzel\\mockMvcTestGenerator\\util\\ClassUtilsTest.java");

        // Run the test
        final Class result = ClassUtils.getClassFromFile(file);

        // Verify the results
        assertThat(result).isEqualTo(ClassUtilsTest.class);
    }
}
