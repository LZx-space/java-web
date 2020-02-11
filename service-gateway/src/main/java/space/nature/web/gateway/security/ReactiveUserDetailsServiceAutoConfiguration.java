package space.nature.web.gateway.security;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author LZx
 * @date 2020/2/10
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration.class)
public class ReactiveUserDetailsServiceAutoConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ReactiveUserDetailsService jdbcReactiveUserDetailsService(PasswordEncoder passwordEncoder) {
        return new JdbcReactiveUserDetailService(passwordEncoder);
    }

}
