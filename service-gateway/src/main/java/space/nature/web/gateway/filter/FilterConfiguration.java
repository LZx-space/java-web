package space.nature.web.gateway.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author LZx
 * @date 2020/2/8
 */
@Component
public class FilterConfiguration {

    @Bean
    public GlobalFilter globalObserverFilter() {
        return new GlobalObserverFilter();
    }

}
