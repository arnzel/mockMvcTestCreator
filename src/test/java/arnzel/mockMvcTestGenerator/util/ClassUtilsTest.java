package arnzel.mockMvcTestGenerator.util;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.FileSystem;

import static org.assertj.core.api.Assertions.assertThat;

public class ClassUtilsTest {


    @Test
    public void testGetClassFromFile() throws Exception {
        // Setup

        final File file = getFile(ClassUtilsTest.class);

        // Run the test
        final Class result = ClassUtils.getClassFromFile(file);

        // Verify the results
        assertThat(result).isEqualTo(ClassUtilsTest.class);
    }

    private File getFile(Class clazz){
        return new File("/Users/211897/IdeaProjects/mockMvcTestCreator/src/test/java/arnzel/mockMvcTestGenerator/util/ClassUtilsTest.java");
    }
}
