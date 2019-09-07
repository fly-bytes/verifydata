package happy.test;

import happy.test.bean.User;
import happy.validation.Validation;
import happy.validation.util.VerifyType;
import happy.validation.verify.Verify;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SpringBootApplication
@RestController
@ComponentScan("happy")
public class TestController {

    @RequestMapping("/get")
    @Validation
    @ResponseBody
    public String test_1(@Verify(regexType = VerifyType.IDCARD , message = "age wrong...")
                         @RequestParam(name = "names", required = false) String names,
                         @Verify(minLength = -1, message = "age wrong...")
                         @RequestParam(name = "b") boolean b){
        return "welcome " + names;
    }

    @RequestMapping("/gets/{name}/end")
    @Validation
    @ResponseBody
    public String test_2(@Verify(maxLength = 3, message = "name max length 3...")
                         @PathVariable("name") String name){
        return "welcome " + name;
    }

    @RequestMapping("/postJson")
    @Validation({User.TestAdd.class})
    @ResponseBody
    public List<User> postJson(@Verify @RequestBody List<User> users){
        return  users;
    }
}
