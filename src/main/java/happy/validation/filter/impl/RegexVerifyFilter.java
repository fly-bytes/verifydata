package happy.validation.filter.impl;

import happy.validation.exception.VerifyException;
import happy.validation.filter.VerifyFilter;
import happy.validation.filter.annotation.FilterStrategy;
import happy.validation.verify.Verify;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@FilterStrategy(type = "regexVerifyFilter")
@Component
public class RegexVerifyFilter implements VerifyFilter {
    @Override
    public void doFilter(Object parms, Verify verify) {
        if (StringUtils.hasText(verify.regex()) && parms != null && !Pattern.matches(verify.regex(), parms.toString())) {
            throw new VerifyException(verify.message());
        }
        if(StringUtils.hasText(verify.regex()) && parms == null  && !Pattern.matches(verify.regex(),"")) {
            throw new VerifyException(verify.message());
        }
    }
}
