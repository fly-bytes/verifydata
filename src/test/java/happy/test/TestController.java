package happy.test;

import happy.validation.Validation;
import happy.validation.verify.Verify;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@ComponentScan("happy")
public class TestController {

    @RequestMapping("/get")
    @Validation
    public String test_1(@Verify(notNull = true, message = "用户名不能为空")
                         @RequestParam(name = "name") String name){
        return "welcome " + name;
    }
}
