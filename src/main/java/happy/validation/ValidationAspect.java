package happy.validation;

import happy.validation.filter.VerifyFilterChain;
import happy.validation.verify.VerifyParms;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    @Autowired
    private VerifyFilterChain verifyFilterChain;

    @Pointcut("@annotation(happy.validation.Validation)")
    public void validation() {
    }

    @Before("validation()")
    public void validations(JoinPoint joinPoint) {
        new VerifyParms(joinPoint).verify(verifyFilterChain);
    }

}
