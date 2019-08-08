package happy.test;

import happy.validation.util.VerifyCostant;

import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
        System.out.println(Pattern.matches(VerifyCostant.EMAIL, "1@wewqq.com"));
        System.out.println(Pattern.matches(VerifyCostant.IDCARD, "41138119850922791X"));
        System.out.println(Pattern.matches(VerifyCostant.PHONE, "19234536787"));
        System.out.println(Pattern.matches(VerifyCostant.MOBILE, "19234536787"));
        System.out.println(Pattern.matches(VerifyCostant.AGE, "-1"));
    }
}
