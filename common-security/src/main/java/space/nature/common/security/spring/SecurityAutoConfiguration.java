package space.nature.common.security.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author LZx
 */
@EnableWebSecurity
public class SecurityAutoConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.eraseCredentials(true).userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().exceptionHandling()
                .accessDeniedHandler(new AjaxAccessDeniedHandler())
                .authenticationEntryPoint(new AjaxAuthenticationEntryPoint())
                .and().authorizeRequests()
                .anyRequest().permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // TODO 是否HttpFirewall接口需要一个处理HttpServletResponse的方法以满足AJAX请求，提代码
        web.ignoring().regexMatchers("^(.*\\.[A-Za-z]+)$");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
