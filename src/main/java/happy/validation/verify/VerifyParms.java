package happy.validation.verify;

import happy.validation.util.Methods;
import happy.validation.util.ValidUtil;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * VerifyParms
 */
public class VerifyParms {
    // Verify List
    private List<Verify> list = new ArrayList();
    // parms List
    private Object[] args = {};
    private String common = ",java.lang.Integer,java.lang.Boolean,java.lang.Character,java.lang.Long,java.lang.Short,java.lang.Float,java.lang.Double,java.lang.String,";

    public VerifyParms(JoinPoint joinPoint) {
        args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        // runing menthod
        Method method = Methods.getMethodByName(joinPoint.getSignature().getDeclaringType(),
                joinPoint.getSignature().getName());
        // set verify rule
        Methods.addVerify(list, method);
    }

    /**
     * 校验start
     */
    public void verify() {
        if (args.length == 0) return;

        // traversing parm
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null) {
                ValidUtil.valid(args[i], list.get(i));
            } else if (common.indexOf(args[i].getClass().getName()) != -1 && list.get(i) != null) {
                ValidUtil.valid(args[i], list.get(i));
            } else if (common.indexOf(args[i].getClass().getName()) == -1){
                // verify class
                ValidUtil.valid(args[i]);
            }
        }
    }
}
