package arnzel.mockMvcTestGenerator.javaPoet;

import com.squareup.javapoet.FieldSpec;
import org.springframework.test.web.servlet.MockMvc;

import javax.lang.model.element.Modifier;

public class FieldSpecUtils {

    public static FieldSpec getPrivateFieldSpec(Class clazz,
                                                String fieldName) {
        return FieldSpec
                .builder(clazz, fieldName)
                .addModifiers(Modifier.PRIVATE)
                .addModifiers(Modifier.FINAL)
                .build();
    }
}
