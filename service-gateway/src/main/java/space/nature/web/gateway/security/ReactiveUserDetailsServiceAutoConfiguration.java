package space.nature.web.gateway.security;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;

/**
 * @author LZx
 * @date 2020/2/10
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration.class)
public class ReactiveUserDetailsServiceAutoConfiguration {

    @Bean
    public ReactiveUserDetailsService jdbcReactiveUserDetailsService() {
        return new JdbcReactiveUserDetailServiceImpl();
    }

}
