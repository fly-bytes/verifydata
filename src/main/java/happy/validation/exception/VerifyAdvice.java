package happy.validation.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VerifyAdvice {

    @ExceptionHandler
    @org.springframework.web.bind.annotation.ResponseBody
    public ResponseBody verifyException(VerifyException verifyException){
        return new ResponseBody(verifyException.getMessage());
    }

    private class ResponseBody{
        private String code = "-1";
        private String message = "error";
        private Object data;

        public ResponseBody(Object data) {
            this.data = data;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public Object getData() {
            return data;
        }
    }
}
