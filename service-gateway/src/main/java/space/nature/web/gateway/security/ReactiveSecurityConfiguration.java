package space.nature.web.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

/**
 * @author LZx
 * @date 2020/2/7
 */
@EnableWebFluxSecurity
public class ReactiveSecurityConfiguration {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange()
                .pathMatchers(HttpMethod.GET, "/**").permitAll()
                .anyExchange().authenticated()
                .and().csrf().disable()
                .requestCache().disable()
                .httpBasic().disable()
                .formLogin()
                .authenticationEntryPoint(new AjaxAuthenticationEntryPoint())
                .authenticationSuccessHandler(new AjaxAuthenticationSuccessHandler())
                .authenticationFailureHandler(new AjaxAuthenticationFailureHandler())
                .requiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/sessions"))
                .and().logout()
                .requiresLogout(ServerWebExchangeMatchers.pathMatchers(HttpMethod.DELETE, "/sessions"))
                .logoutSuccessHandler(new AjaxLogoutSuccessHandler())
                .logoutHandler(new LogoutExtraHandler()).and().build();
    }

}
