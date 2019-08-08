package happy.validation.config.bean;

public class ResponseBody {
    private String code = "-1";
    private String message = "error";
    private Object data;

    public ResponseBody() {
    }

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

    public void setMessage(String message) {
        this.message = message;
    }
}