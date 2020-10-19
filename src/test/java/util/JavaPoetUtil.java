package util;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.lang.reflect.Type;

import static com.squareup.javapoet.TypeSpec.classBuilder;

public class JavaPoetUtil {

    public static String createJavaFileWithField(FieldSpec fieldSpec){
        TypeSpec typeSpec =
                classBuilder("Blub").addField(fieldSpec).build();
        return createJavaFileWithField(typeSpec);
    }

    public static String createJavaFileWithField(TypeSpec typeSpec){
        JavaFile javaFile = JavaFile.builder("dummy", typeSpec)
                .build();
        return javaFile.toString();
    }
}
