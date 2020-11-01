package arnzel.mockMvcTestGenerator;

import com.squareup.javapoet.TypeSpec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestClassWriterTest {

    private TestClassWriter testClassWriterUnderTest;

    @BeforeEach
    void setUp() {
        testClassWriterUnderTest = new TestClassWriter();
    }

    @Test
    void testWriteTestClass() throws IOException {
        // Setup
        final TypeSpec testClassTypeSpec = TypeSpec.classBuilder("Blub").build();

        // Run the test
        final File result = testClassWriterUnderTest
                .writeTestClass(Object.class, testClassTypeSpec);

        // Verify the results
        assertEquals("src/test/java/java/lang/Blub.java", result.getPath());
    }

    @Test
    void testGetTestClassPackage() {
        // Setup

        // Run the test
        final String result = testClassWriterUnderTest.getTestClassPackage(Object.class);

        // Verify the results
        assertEquals("java.lang", result);
    }
}
