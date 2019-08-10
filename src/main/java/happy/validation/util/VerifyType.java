package happy.validation.util;

public enum VerifyType {
    DEFAULT("*"),
    EMAIL("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"),
    PHONE("(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)"),
    MOBILE("(\\+\\d+)?1[345789]\\d{9}$"),
    AGE("^(?:[1-9][0-9]?|1[01][0-9]|120)$"),
    // 非零正整数
    POSITIVEINTEGER("^[1-9]\\d*$"),
    // 非零负整数
    NOPOSITIVEINTEGER("^-[1-9]\\d*$"),
    // 身份证
    IDCARD("(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)");

    private String regex;

    VerifyType(String regex) {
        this.regex = regex;
    }

    public String value() {
        return this.regex;
    }
}
