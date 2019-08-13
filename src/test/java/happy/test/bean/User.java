package happy.test.bean;

import happy.validation.verify.Verify;

import java.util.Date;

public class User {
    @Verify(maxLength = 20, message = "姓名最长为20")
    private String name;
    private int userid;
    @Verify(notNull = true, message = "日期不能为空")
    private Date date;
    private Responses responses;

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

    public Responses getResponses() {
        return responses;
    }

    public void setResponses(Responses responses) {
        this.responses = responses;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userid=" + userid +
                ", date=" + date +
                '}';
    }
}
