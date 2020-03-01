package arnzel.mockMvcTestGenerator.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

import static java.lang.reflect.Modifier.isStatic;

public class MethodInvocationStringBuilder {

    public String getMethodInvocationString(Method method,String parameterList){
        if(isStatic(method.getModifiers())){
            String className = method.getClass().getSimpleName();
            String methodName = method.getName();
            return  className + "." + methodName + "(" + parameterList + ");";
        }else {
            throw new UnsupportedOperationException("Cannot create method invocation string for non static methods");
        }
    }

}
