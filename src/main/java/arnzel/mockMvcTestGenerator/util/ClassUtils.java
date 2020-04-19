package arnzel.mockMvcTestGenerator.util;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassUtils {

    public static Class getClassFromFile(File file) throws Exception {
        URL url =file.toURI().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[] {url});
        return loader.loadClass(getPackageName(file)
                + "."
                + getClassName(file));
    }

    private static String getClassName(File file) throws FileNotFoundException {
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
