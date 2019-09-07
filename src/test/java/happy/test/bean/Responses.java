package happy.test.bean;

import happy.validation.config.bean.VerifyErrorMessage;

public class Responses extends VerifyErrorMessage {
    private int status = 500;

    public int getStatus() {
        return status;
    }

    @Override
    public void setCode(String code) {
        super.setCode("500");
    }
}
