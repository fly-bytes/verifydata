package happy.validation.util;


import happy.validation.Validation;
import happy.validation.verify.Verify;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

public class Methods {
    public static Method getMethodByName(Class clazz, String methodName) {
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Validation.class)
                && m.getName().equals(methodName)) {
                return  m;
            }
        }
        return null;
    }

    public static void addVerify(List<Verify> list, Method method) {
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(Verify.class)) list.add(i, parameters[i].getAnnotationsByType(Verify.class)[0]);
            else list.add(null);
        }
    }

}
