package arnzel.mockMvcTestGenerator.javaPoet;

import arnzel.mockMvcTestGenerator.requestMapping.RequestMappingParser;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import util.ResourcesUtil;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.squareup.javapoet.TypeSpec.classBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static util.ResourcesUtil.getResourceContent;


class FieldSpecUtilsTest {

    @Test
    void testGetPrivateFieldSpec() throws IOException, URISyntaxException {

        // Run the test
        final FieldSpec result = FieldSpecUtils.getPrivateFieldSpec(Object.class, "classParser");

        // Verify the results
        String string = createJavaFileWithField(result);

        assertThat(string).isEqualTo(getResourceContent("/classWithField.txt"));

    }

    private String createJavaFileWithField(FieldSpec fieldSpec){
        TypeSpec typeSpec =
                classBuilder("Blub").addField(fieldSpec).build();
        JavaFile javaFile = JavaFile.builder("dummy", typeSpec)
                .build();
        return javaFile.toString();
    }


}
