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

    // Verify List
    private List<Verify> list = new ArrayList();
    // parms List
    private Object[] args = {};
    // parms type
    private List<String> parmsType = new ArrayList();
    // parms name
    private List<String> parmsName = new ArrayList<>();
    private String common = ",int,long,double,float,char,boolean,short,java.lang.Integer,java.lang.Boolean,java.lang.Character,java.lang.Long,java.lang.Short,java.lang.Float,java.lang.Double,java.lang.String,";

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
        // traversing parm
        for (int i = 0; i < args.length; i++) {
            LOGGER.info("verify " + parmsName.get(i) + "=[" + args[i] + "]");
            if ((args[i] == null && list.get(i) != null) ||
                    (common.indexOf(parmsType.get(i)) != -1 && list.get(i) != null)) {
                verifyFilterChain.doFilter(args[i], list.get(i));
            } else if (common.indexOf(parmsType.get(i)) == -1) {
                // verify class
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
