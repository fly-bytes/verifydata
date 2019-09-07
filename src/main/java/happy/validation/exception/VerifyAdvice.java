package happy.validation.exception;

import happy.validation.config.bean.VerifyErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VerifyAdvice {

    @Autowired
    private VerifyErrorMessage verifyErrorMessage;

    @ExceptionHandler(VerifyException.class)
    @ResponseBody
    public VerifyErrorMessage verifyException(VerifyException verifyException) {
        return verifyErrorMessage.setMessage(verifyException.getMessage());
    }
}
