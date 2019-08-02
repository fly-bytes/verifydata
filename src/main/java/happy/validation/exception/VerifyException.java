package happy.validation.exception;

/**
 * 校验失败异常类,项目捕捉这个异常抛到前端就行了
 */
public class VerifyException extends RuntimeException {
    private String message;

    public VerifyException(String message) {
        super(message);
    }
}
