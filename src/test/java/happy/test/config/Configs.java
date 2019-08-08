package happy.test.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import happy.test.bean.Responses;
import happy.validation.config.bean.ResponseBody;
import happy.validation.exception.VerifyAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.converter.HttpMessageConverter;

@Configuration
public class Configs {

    @Bean("responseBody")
    @Scope(scopeName = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Responses responseBody() {
        return new Responses();
    }

    @Bean
    public HttpMessageConverter httpMessageConverter() {
        return new FastJsonHttpMessageConverter();
    }
}
