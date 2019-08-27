/*
 * Copyright (c) 2019, LZx
 */

package space.nature.spring.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AJAX访问请求的拒绝后的处理
 *
 * @author LZx
 * @date 2018年9月14日
 */
@Slf4j
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String localizedMessage = accessDeniedException.getLocalizedMessage();
        log.info("-->> AJAX request access denied: {}", localizedMessage);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

}
