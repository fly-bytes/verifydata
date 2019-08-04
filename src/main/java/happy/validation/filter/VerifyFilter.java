package happy.validation.filter;

import happy.validation.verify.Verify;

public interface VerifyFilter {
    void doFilter(Object parms, Verify verify);
}
