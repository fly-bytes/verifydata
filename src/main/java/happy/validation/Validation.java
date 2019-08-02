package happy.validation;

import java.lang.annotation.*;

/**
 * 需要校验的方法或者方法参数
 */
@Documented
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validation {}
