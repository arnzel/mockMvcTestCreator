package arnzel.mockMvcTestGenerator;

import arnzel.mockMvcTestGenerator.mockMvc.MockMvcClassGenerator;
import arnzel.mockMvcTestGenerator.requestMapping.RequestMappingTestGenerator;
import com.squareup.javapoet.TypeSpec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.squareup.javapoet.TypeSpec.classBuilder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestClassGeneratorTest {

    private TestClassGenerator testClassGenerator;

    private TestClassWriter testClassWriter = mock(TestClassWriter.class);

    private MockMvcClassGenerator mockMvcClassGenerator = mock(MockMvcClassGenerator.class);

    private RequestMappingTestGenerator requestMappingTestGenerator = mock(RequestMappingTestGenerator.class);



    @BeforeEach
    public void before(){
        testClassGenerator = new TestClassGenerator(testClassWriter,
                mockMvcClassGenerator,
                requestMappingTestGenerator
                );
    }

    @Test
    void generateTestClass() {
        // given
        Class clazz = Object.class;
        TypeSpec.Builder builder = givenClassGeneration(clazz,"ObjectTest");

        // when
        testClassGenerator.generateTestClass(clazz);

        // then
        verifyClassWriting(clazz,builder.build());

    }

    private TypeSpec.Builder givenClassGeneration(Class clazz,String classNameTest){
        TypeSpec.Builder builder = classBuilder(clazz.getName());
        when(mockMvcClassGenerator
                .createTestClass(clazz,classNameTest))
                .thenReturn(builder);
        return builder;
    }

    private void verifyClassWriting(Class clazz,TypeSpec typeSpec){
        Mockito.verify(testClassWriter).writeTestClass(clazz,typeSpec);
    }
}