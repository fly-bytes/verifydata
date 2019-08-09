package happy.validation.filter.impl;

import happy.validation.exception.VerifyException;
import happy.validation.filter.VerifyFilter;
import happy.validation.filter.annotation.FilterStrategy;
import happy.validation.verify.Verify;
import org.springframework.stereotype.Component;

@Component
@FilterStrategy(type = "lengthVerifyFilter")
public class LengthVerifyFilter implements VerifyFilter {
    @Override
    public void doFilter(Object parms, Verify verify) {
        if(parms != null &&  (parms.toString().length() < verify.minLength()
                        || parms.toString().length() > verify.maxLength())) {
            throw new VerifyException(verify.message());
        }
    }
}
