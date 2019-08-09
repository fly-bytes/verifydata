package happy.test.bean;

import happy.validation.config.bean.ResponseBody;

public class Responses extends ResponseBody {
    private int status = 500;

    public int getStatus() {
        return status;
    }

    @Override
    public void setCode(String code) {
        super.setCode("500");
    }
}
