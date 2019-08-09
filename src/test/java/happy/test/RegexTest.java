package happy.test;

import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
        // 非负整数
        System.out.println(Pattern.matches( "^\\d+$", "0"));
        // 正整数
        System.out.println(Pattern.matches( "^[0-9]*[1-9][0-9]*$", "2000000000009787732"));
    }
}
