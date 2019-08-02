package happy.test;

import happy.validation.ValidationAspect;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoApplicationTests {

    @Test
    public void injection() {
        try (AnnotationConfigApplicationContext context = new  AnnotationConfigApplicationContext(ValidationAspect.class)) {
            System.out.println(context.getBean("validationAspect"));
        }
    }


}
