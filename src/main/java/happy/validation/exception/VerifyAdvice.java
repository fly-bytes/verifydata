package happy.validation.exception;

import happy.validation.config.bean.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VerifyAdvice {

    @Autowired
    private ResponseBody responseBody;

    @ExceptionHandler
    @org.springframework.web.bind.annotation.ResponseBody
    public ResponseBody verifyException(VerifyException verifyException) {
        responseBody.setMessage(verifyException.getMessage());
        return responseBody;
    }
}
