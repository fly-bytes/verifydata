package happy.validation.filter.impl;

import happy.validation.exception.VerifyException;
import happy.validation.filter.VerifyFilter;
import happy.validation.filter.annotation.FilterStrategy;
import happy.validation.verify.Verify;
import org.springframework.stereotype.Component;

@Component
@FilterStrategy(type = "notNullVerifyFilter")
public class NotNullVerifyFilter implements VerifyFilter {
    @Override
    public void doFilter(Object parms, Verify verify) {
        if (verify.notNull() && parms == null) throw new VerifyException(verify.message());
    }
}
