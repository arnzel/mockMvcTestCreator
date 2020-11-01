package arnzel.mockMvcTestGenerator.util;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import net.openhft.compiler.CompilerUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ClassUtils {

    public static Class getClassFromFile(File file) throws Exception {
        String fullClassName = getPackageName(file) +"." + getClassName(file);
        String fileContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        return CompilerUtils.CACHED_COMPILER.loadFromJava(fullClassName,fileContent);
    }

    private static String getClassName(File file)  {
        String fileName = file.getName();
        return fileName.substring(0,fileName.indexOf('.'));
    }

    private static String getPackageName(File file) throws IOException {
        CompilationUnit cu = new JavaParser()
                .parse(file)
                .getResult()
                .get();
        return  cu
                .getPackageDeclaration()
                .get()
                .getName()
                .asString();
    }
}
