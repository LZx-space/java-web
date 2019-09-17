/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.security.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityProperties properties;

    private final JwtTokenHandler tokenHandler;

    public SecurityConfig(SecurityProperties properties) {
        this.properties = properties;
        this.tokenHandler = new JwtTokenHandler(properties.getJwt().getTimeout());
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private SecurityContextRepository contextRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.eraseCredentials(true).userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
         * 前后端分离下CSRF分析：
         * 1.用户能跨站攻击则知道用户的sessionId，则能知道其他请求信息，如cookie、页面里的显示的CSRFToken
         * 2.传统的后端渲染页面每次请求后重新渲染页面能保证CSRFToken更新，大大减小被攻击的可能
         * 3.异步的修改状态类的HTTP请求，不能随意更新CSRFToken，此举会出现并发问题
         * 4.即便用户将CSRFToken放在内存中，但是请求的时候依然会通过Header或者parameters传输而遭到泄露
         * 5.HTTPS只能保证传输过程中的数据不会被劫持修改
         * 综合：前后端分离下不能保证不被CSRF攻击，服务端渲染时带Token能提高发起攻击的代价，需要特定脚本处理返回数据
         *
         */
        http.csrf().ignoringAntMatchers(new String[]{"/**"})
                .and().exceptionHandling()
                .accessDeniedHandler(new AjaxAccessDeniedHandler())
                .authenticationEntryPoint(new AjaxAuthenticationEntryPoint())
                .and().authorizeRequests()
                .antMatchers("/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().requestCache().disable()
                .securityContext().securityContextRepository(contextRepository)
                .and().formLogin()
                .loginPage(properties.getLoginPageUrl()).permitAll()
                .loginProcessingUrl("/login")
                .successHandler(successHandler)
                .failureHandler(new AjaxAuthenticationFailureHandler())
                .and().logout().logoutSuccessHandler(new AjaxLogoutSuccessHandler())
                .addLogoutHandler(new ExtraLogoutHandler())
                .and().rememberMe().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
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

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new AjaxAuthenticationSuccessHandler(tokenHandler);
    }

    @Bean
    public SecurityContextRepository contextRepository() {
        return new JwtSecurityContextRepository(tokenHandler);
    }

}
