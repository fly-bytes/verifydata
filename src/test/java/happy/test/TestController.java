package happy.test;

import happy.validation.Validation;
import happy.validation.verify.Verify;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@ComponentScan("happy")
public class TestController {

    @RequestMapping("/get")
    @Validation
    @ResponseBody
    public String test_1(@Verify(notNull = true, message = "name cat'n null")
                         @RequestParam(name = "name", required = false) String name){
        return "welcome " + name;
    }
}
