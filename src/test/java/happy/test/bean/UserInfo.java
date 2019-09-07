package happy.test.bean;

import happy.validation.verify.Verify;

public class UserInfo {
    @Verify(maxLength = 1, message = "id最长长度1", groups = {User.TestAdd.class})
    private String id;
    private int age;

    public UserInfo(String id, int age) {
        this.id = id;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
