package happy.validation.config;

import happy.validation.condition.ResponseCondition;
import happy.validation.config.bean.ResponseBody;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("happy.validation")
public class Config {

    @Bean
    @Scope(scopeName = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Conditional(ResponseCondition.class)
    public ResponseBody responseBody() {
        return new ResponseBody();
    }
}
