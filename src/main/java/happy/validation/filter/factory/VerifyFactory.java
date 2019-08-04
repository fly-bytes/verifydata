package happy.validation.filter.factory;

import happy.validation.filter.VerifyFilter;
import happy.validation.filter.annotation.FilterStrategy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class VerifyFactory implements ApplicationContextAware {

    private Map<String, VerifyFilter> handlerStrategyMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Collection<VerifyFilter> filters = applicationContext.getBeansOfType(VerifyFilter.class).values();
        filters.forEach(v -> handlerStrategyMap.put(v.getClass().getAnnotation(FilterStrategy.class).type(), v));
    }


    public VerifyFilter getVerifyFilterByType(String type) {
        return handlerStrategyMap.get(type);
    }
}
