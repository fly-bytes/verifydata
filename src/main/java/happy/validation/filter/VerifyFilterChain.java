package happy.validation.filter;

import happy.validation.filter.factory.VerifyFactory;
import happy.validation.verify.Verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class VerifyFilterChain {
    private static List<String> order = Arrays.asList("lengthVerifyFilter", "notNullVerifyFilter", "regexVerifyFilter");

    @Autowired
    private VerifyFactory verifyFactory;

    public void doFilter(Object parms, Verify verify){
        for (String type : order) {
            verifyFactory.getVerifyFilterByType(type).doFilter(parms, verify);
        }
    }

    public static void main(String[] args) {
        for (String s : order) {
            System.out.println(s);
        }
    }
}
