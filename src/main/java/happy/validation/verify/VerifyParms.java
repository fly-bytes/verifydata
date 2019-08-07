package happy.validation.verify;

import happy.validation.filter.VerifyFilterChain;
import happy.validation.util.Methods;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * VerifyParms
 */
public class VerifyParms {
    private final static Log LOGGER = LogFactory.getLog(VerifyParms.class);

    private List<Verify>   list      =   new ArrayList();
    private Object[]       args      =   {};
    private List<String>   parmsType =   new ArrayList();
    private List<String>   parmsName =   new ArrayList<>();
    private String         common    =   ",int,long,double,float,char,boolean,short,java.lang.Integer,java.lang.Boolean,java.lang.Character,java.lang.Long,java.lang.Short,java.lang.Float,java.lang.Double,java.lang.String,";

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
        // 遍历参数 第一类是基础类型，第二类是对象，暂不校验List
        for (int i = 0; i < args.length; i++) {
            LOGGER.info("verify " + parmsName.get(i) + "=[" + args[i] + "]");

            if (list.get(i) != null && (args[i] == null || common.indexOf(parmsType.get(i)) != -1 )) {
                verifyFilterChain.doFilter(args[i], list.get(i));
            } else if (common.indexOf(parmsType.get(i)) == -1) {
                Field[] fields = args[i].getClass().getDeclaredFields();

                for (int j = 0; j < fields.length; j++) {
                    if (fields[j].isAnnotationPresent(Verify.class) && common.indexOf(fields[j].getGenericType().getTypeName()) != -1) {
                        try {
                            // set field access
                            fields[j].setAccessible(true);
                            verifyFilterChain.doFilter(fields[j].get(args[i]), fields[j].getAnnotationsByType(Verify.class)[0]);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        LOGGER.info("verify data success");
    }
}
