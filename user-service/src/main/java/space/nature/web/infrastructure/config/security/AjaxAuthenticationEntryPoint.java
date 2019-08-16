/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.infrastructure.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LZx
 * @date 2018年11月29日
 */
@Slf4j
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.info("-->> AJAX request access forbidden: {}", authException.getLocalizedMessage());
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

}
