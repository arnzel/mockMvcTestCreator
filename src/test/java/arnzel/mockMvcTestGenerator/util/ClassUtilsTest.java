package arnzel.mockMvcTestGenerator.util;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class ClassUtilsTest {


    @Test
    public void testGetClassFromFile() throws Exception {
        // Setup

        final File file = getFile("/Blub.txt");

        // Run the test
        final Class result = ClassUtils.getClassFromFile(file);

        // Verify the results
        assertThat(result.getName()).isEqualTo("dummy.Blub");
    }

    private File getFile(String path){
        return new File(ClassUtils.class.getResource(path).getFile());
    }
}
