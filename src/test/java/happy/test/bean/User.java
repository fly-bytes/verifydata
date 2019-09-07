package happy.test.bean;

import happy.validation.verify.Verify;

import java.util.Date;
import java.util.List;

public class User {
    @Verify(maxLength = 2, message = "姓名最长为2", groups = TestAdd.class)
    private String name;
    private int userid;
    @Verify(notNull = true, message = "日期不能为空")
    private Date date;

    public User(String name, int userid, Date date) {
        this.name = name;
        this.userid = userid;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userid=" + userid +
                ", date=" + date +
                '}';
    }

    public class TestAdd{}
    public class TestDel{}
}
