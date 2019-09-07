package happy.validation.util;


import happy.validation.Validation;
import happy.validation.verify.Verify;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
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

    public static void addVerify(List<Verify> list, Method method, List<String> parmsType, List<String> parmsName, List<Object> verifyGroup) {
        // 校验组
        verifyGroup.addAll(Arrays.asList(method.getAnnotationsByType(Validation.class)[0].value()));
        // 设置参数名
        DefaultParameterNameDiscoverer defaultParameterNameDiscoverer = new DefaultParameterNameDiscoverer();
        parmsName.addAll(Arrays.asList(defaultParameterNameDiscoverer.getParameterNames(method)));

        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            parmsType.add(parameters[i].getType().getName());
            if (parameters[i].isAnnotationPresent(Verify.class)) list.add(i, parameters[i].getAnnotationsByType(Verify.class)[0]);
            else list.add(null);
        }
    }

}
