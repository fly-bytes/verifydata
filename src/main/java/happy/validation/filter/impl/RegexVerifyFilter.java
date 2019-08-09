package happy.validation.filter.impl;

import happy.validation.exception.VerifyException;
import happy.validation.filter.VerifyFilter;
import happy.validation.filter.annotation.FilterStrategy;
import happy.validation.util.VerifyType;
import happy.validation.verify.Verify;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@Component
@FilterStrategy(type = "regexVerifyFilter")
public class RegexVerifyFilter implements VerifyFilter {
    @Override
    public void doFilter(Object parms, Verify verify) {
        String regex = verify.regexType() != VerifyType.DEFAULT
                ? verify.regexType().value() : verify.regex();

        if (StringUtils.hasText(regex) && !Pattern.matches(regex, parms == null ? "" : parms.toString())) {
            throw new VerifyException(verify.message());
        }
    }
}
