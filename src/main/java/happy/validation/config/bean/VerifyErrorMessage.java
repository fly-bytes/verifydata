package happy.validation.config.bean;

public class VerifyErrorMessage {
    private String code = "-1";
    private String message = "error";
    private Object data;

    public VerifyErrorMessage() {
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public VerifyErrorMessage(Object data) {
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

    public VerifyErrorMessage setMessage(String message) {
        this.message = message;
        return this;
    }
}