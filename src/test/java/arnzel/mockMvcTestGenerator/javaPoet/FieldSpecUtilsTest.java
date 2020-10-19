package arnzel.mockMvcTestGenerator.javaPoet;

import arnzel.mockMvcTestGenerator.requestMapping.RequestMappingParser;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import util.JavaPoetUtil;
import util.ResourcesUtil;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static util.JavaPoetUtil.createJavaFileWithField;
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




}
