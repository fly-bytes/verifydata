package happy.validation.config;

import happy.validation.condition.ResponseCondition;
import happy.validation.config.bean.VerifyErrorMessage;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("happy.validation")
public class Config {

    @Bean
    @Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Conditional(ResponseCondition.class)
    public VerifyErrorMessage responseBody() {
        return new VerifyErrorMessage();
    }

}
