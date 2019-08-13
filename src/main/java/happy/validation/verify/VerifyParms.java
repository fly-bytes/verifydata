package happy.validation.verify;

import happy.validation.filter.VerifyFilterChain;
import happy.validation.util.Methods;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * VerifyParms
 */
public class VerifyParms {
    private final static Log LOGGER = LogFactory.getLog(VerifyParms.class);

    private List<Verify> list = new ArrayList();
    private Object[] args;
    private List<String> parmsType = new ArrayList();
    private List<String> parmsName = new ArrayList<>();
    private List<String> baseType = Arrays.asList("int", "long", "double", "float", "char", "boolean", "short"
            , "java.lang.Integer", "java.lang.Long", "java.lang.Double", "java.lang.Short", "java.lang.Float", " java.lang.Boolean", "java.lang.Character", "java.lang.String");
    private String listType = "java.util.List";

    public VerifyParms(JoinPoint joinPoint) {
        args = joinPoint.getArgs();
        // runing menthod
        Method method = Methods.getMethodByName(joinPoint.getSignature().getDeclaringType(),
                joinPoint.getSignature().getName());
        // set verify rule
        Methods.addVerify(list, method, parmsType, parmsName);
    }

    /**
     * 校验start
     *
     * @param verifyFilterChain
     */
    public void verify(VerifyFilterChain verifyFilterChain) {
        if (args.length == 0) return;

        // 遍历参数 第一类是基础类型，第二类是对象，第三类是List
        for (int i = 0; i < args.length; i++) {
            LOGGER.info("verify " + parmsName.get(i) + "=" + args[i] + "，parmsType=" + parmsType);

            if (list.get(i) != null && (args[i] == null || baseType.contains(parmsType.get(i)))) {
                verifyFilterChain.doFilter(args[i], list.get(i));
            } else if (!baseType.contains(parmsType.get(i)) && !parmsType.get(i).equals(listType)) {
                veridyBean(verifyFilterChain, args[i]);
            } else if (parmsType.get(i).equals(listType) && !baseType.contains(parmsType.get(i))) {
                Iterator it = ((List) args[i]).iterator();
                while (it.hasNext()) {
                    veridyBean(verifyFilterChain, it.next());
                }
            }
        }

        LOGGER.info("verify data success");
    }

    /**
     * 校验对象
     *
     * @param verifyFilterChain 检验链
     * @param obj               对象
     */
    private void veridyBean(VerifyFilterChain verifyFilterChain, Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (int j = 0; j < fields.length; j++) {
            if (fields[j].isAnnotationPresent(Verify.class) && baseType.contains(fields[j].getGenericType().getTypeName())) {
                try {
                    // set field access
                    fields[j].setAccessible(true);
                    verifyFilterChain.doFilter(fields[j].get(obj), fields[j].getAnnotationsByType(Verify.class)[0]);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
