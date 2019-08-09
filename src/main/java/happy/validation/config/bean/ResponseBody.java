package happy.validation.config.bean;

public class ResponseBody {
    private String code = "-1";
    private String message = "error";
    private Object data;

    public ResponseBody() {
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
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

    public ResponseBody setMessage(String message) {
        this.message = message;
        return this;
    }
}