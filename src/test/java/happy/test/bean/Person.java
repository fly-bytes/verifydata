package happy.test.bean;

import happy.validation.util.VerifyType;
import happy.validation.verify.Verify;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @Author:LiuBingXu
 * @Description:
 * @Date: 2019/8/4.
 * @Modified by
 */
public class Person {
    @Verify(minLength = 1, message = "姓名最少一位")
    private String name;
    @Verify(minLength = 1, maxLength = 3, message = "长度范围1-3")
    private int age;
    @Verify(maxLength = 4, message = "最长为4")
    private Double monery;
    @Verify(maxLength = 2, message = "最长为2")
    private List<String> a;
    @Verify(notNull = true, message = "b不能为空")
    private double b;
    @Verify(regexType = VerifyType.IDCARD, message = "身份证格式错误")
    private String idCards;

    public String getIdCards() {
        return idCards;
    }

    public void setIdCards(String idCards) {
        this.idCards = idCards;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public List<String> getA() {
        return a;
    }

    public void setA(List<String> a) {
        this.a = a;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getMonery() {
        return monery;
    }

    public void setMonery(Double monery) {
        this.monery = monery;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", monery=" + monery +
                ", a=" + a +
                ", b=" + b +
                ", idCards='" + idCards + '\'' +
                '}';
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.example.demo.test.Person");
        for (Field field : aClass.getDeclaredFields()) {
            System.out.println(field.getGenericType().getTypeName());
        }
    }
}
