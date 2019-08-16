/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.infrastructure.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import space.nature.core.responseobj.CommonResponseFactory;
import space.nature.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author LZx
 * @date 2018年9月20日
 */
@Component
@Slf4j
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenHandler tokenHandler;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("-->> authenticate success through AJAX");
        String token = tokenHandler.create(authentication);

        Cookie cookie = new Cookie(JwtTokenConstants.COOKIE_TOKEN_NAME, token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60);
        response.addCookie(cookie);

        try (PrintWriter writer = response.getWriter()) {
            String json = JsonUtils.fromObject(CommonResponseFactory.success());
            writer.write(json);
            writer.flush();
        }
    }

}
