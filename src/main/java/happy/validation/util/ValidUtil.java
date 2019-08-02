package happy.validation.util;

import happy.validation.exception.VerifyException;
import happy.validation.verify.Verify;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

/**
 * valid content
 */
public class ValidUtil {

    public static void valid(Object parms, Verify verify) {
        String message = verify.message();
        int maxLength = verify.maxLength();
        int minLength = verify.minLength();
        boolean notNull = verify.notNull();
        String regex = verify.regex();

        if (notNull && parms == null) throw new VerifyException(message);
        if (parms.toString().length() > maxLength) throw new VerifyException(message);
        if (parms.toString().length() < minLength) throw new VerifyException(message);
        if (StringUtils.hasText(regex) && parms != null && !Pattern.matches(regex, parms.toString())) {
            throw new VerifyException(message);
        }
    }

    public static void valid(Object arg) {
        Field[] fields = arg.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(Verify.class)) {
                try {
                    // set field access
                    fields[i].setAccessible(true);
                    valid(fields[i].get(arg), fields[i].getAnnotationsByType(Verify.class)[0]);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
