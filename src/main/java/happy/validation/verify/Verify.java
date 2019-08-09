package happy.validation.verify;

import happy.validation.util.VerifyType;

import java.lang.annotation.*;

/**
 * 校验属性
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Verify {
    // 最大长度
    int maxLength() default Integer.MAX_VALUE;

    // 最小长度
    int minLength() default -1;

    // 是否为空
    boolean notNull() default false;

    // 正则校验
    String regex() default "";

    // 常用正则
    VerifyType regexType() default VerifyType.DEFAULT;

    // 校验错误返回信息
    String message() default "数据格式错误";
}
