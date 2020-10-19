package arnzel.mockMvcTestGenerator.requestMapping;

import com.squareup.javapoet.TypeSpec;
import fixtures.controllers.RequestMappingController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RequestMappingTestGeneratorTest {

    private RequestMappingTestGenerator requestMappingTestGenerator;

    @BeforeEach
    public void before(){
        requestMappingTestGenerator = new RequestMappingTestGenerator();
    }

    @Test
    void createTests() {

        //given
        TypeSpec.Builder typeSpecBuilder =
                TypeSpec.classBuilder("blub");
        // when
        requestMappingTestGenerator.createTests(typeSpecBuilder,
                RequestMappingController.class);

        // then
        TypeSpec typeSpec = typeSpecBuilder.build();
        assertThat(typeSpec.methodSpecs.size()).isEqualTo(1);

    }
}