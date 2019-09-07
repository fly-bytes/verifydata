package happy.validation.verify;

import happy.validation.filter.VerifyFilterChain;
import happy.validation.util.Methods;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * VerifyParms
 */
public class VerifyParms {
    private final static Log LOGGER = LogFactory.getLog(VerifyParms.class);

    private List<Verify> list = new ArrayList(); // 校验注解的集合
    private List<Object> verifyGroup = new ArrayList<>(); // 校验组
    private Object[] args; // 当前校验的方法
    private List<String> parmsType = new ArrayList();// 参数类型
    private List<String> parmsName = new ArrayList<>();   // 参数名称
    private List<String> baseType = Arrays.asList("int", "long", "double", "float", "char", "boolean", "short"
            , "java.lang.Integer", "java.lang.Long", "java.lang.Double", "java.lang.Short", "java.lang.Float"
            , " java.lang.Boolean", "java.lang.Character", "java.lang.String", "java.util.Date"); // 校验的参数基本数据类型
    private String listType = "java.util.List";// 校验的集合数据类型

    public VerifyParms(JoinPoint joinPoint) {
        args = joinPoint.getArgs();// 表单传递的参数
        Method method = Methods.getMethodByName(joinPoint.getSignature().getDeclaringType(),
                joinPoint.getSignature().getName());// 当前运行的方法
        Methods.addVerify(list, method, parmsType, parmsName, verifyGroup); // 设置对应的校验规则
    }

    /**
     * 校验开始
     *
     * @param verifyFilterChain 检验链
     */
    public void verify(VerifyFilterChain verifyFilterChain) {
        if (args.length == 0) return;

        for (int i = 0; i < args.length; i++) {// 遍历参数 第一类是基础类型，第二类是对象，第三类是List
            if (list.get(i) == null) continue;// 参数前没有注解不校验

            LOGGER.info("verify " + parmsName.get(i) + "=" + args[i] + "，parmsType=" + parmsType.get(i) + "，verifyGroup=" + verifyGroup);

            if (list.get(i) != null && (args[i] == null || baseType.contains(parmsType.get(i)))) {
                verifyFilterChain.doFilter(args[i], list.get(i));
            } else if (!baseType.contains(parmsType.get(i)) && !parmsType.get(i).equals(listType)) {
                veridyBean(verifyFilterChain, args[i]);
            } else if (parmsType.get(i).equals(listType)) {
                Iterator it = ((List) args[i]).iterator();
                while (it.hasNext()) {
                    veridyBean(verifyFilterChain, it.next());
                }
            }
        }

        LOGGER.info("verify data success");
    }

    /**
     * 校验对象,对象里面嵌套对象、集合
     * 如果对象里面的属性上面不存在@Verify不校验，如果存在校验组需要相同或者@Validation的校验组为空才会校验
     * @param verifyFilterChain 检验链
     * @param obj               对象
     */
    private void veridyBean(VerifyFilterChain verifyFilterChain, Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (int j = 0; j < fields.length; j++) {
            if (!fields[j].isAnnotationPresent(Verify.class)) continue;// 如果属性上不存在注解Verify，则跳过该属性
            Verify[] verifys = fields[j].getAnnotationsByType(Verify.class);// 属性上的注解数组
            if(!isContain(verifys[0])) continue;// 如果校验组不匹配，跳过该属性

            final Type genericType = fields[j].getGenericType();
            String typeName = genericType.getTypeName();

            Object o = null;
            try {
                fields[j].setAccessible(true);
                o = fields[j].get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (baseType.contains(typeName)) { // 基础类型
                verifyFilterChain.doFilter(o, verifys[0]);
            } else {
                if (o == null) continue;// 校验对象里面的集合、对象,如果本身为空，则不校验

                if (genericType instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType) genericType;
                    Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0]; //得到泛型里的class类型对象
                    typeName = genericClazz.getTypeName();
                }

                if (baseType.contains(typeName) && o instanceof java.util.List) { // 校验集合,泛型是基础类型
                    Iterator it = ((List) o).iterator();
                    while (it.hasNext()) {
                        verifyFilterChain.doFilter(it.next(), verifys[0]);
                    }
                } else if (!baseType.contains(typeName) && o instanceof java.util.List) { // 校验集合，泛型是对象
                    Iterator it = ((List) o).iterator();
                    while (it.hasNext()) {
                        veridyBean(verifyFilterChain, it.next());
                    }
                } else {
                    veridyBean(verifyFilterChain, o); // 校验对象
                }
            }
        }
    }

    /**
     * 判断校验组是否有交集
     *
     * @param verify
     * @return
     */
    private boolean isContain(Verify verify) {
        Object[] groups = verify.groups();// 得到参数的校验组
        if (verifyGroup == null || verifyGroup.isEmpty()) return true; // 校验组为空，校验所有

        boolean isContains = false; // 数组是否有交集
        for (int i = 0; i < verifyGroup.size(); i++) {
            for (int j = 0; j < groups.length; j++) {
                if (verifyGroup.get(i) == groups[j]) {
                    isContains = true;
                    break;
                }
            }
        }
        return isContains;
    }
}
