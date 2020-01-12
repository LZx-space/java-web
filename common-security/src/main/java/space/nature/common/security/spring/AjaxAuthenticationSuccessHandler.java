/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.security.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import space.nature.common.core.dto.ResponseFactory;
import space.nature.common.core.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author LZx
 */
@Slf4j
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenHandler tokenHandler;

    public AjaxAuthenticationSuccessHandler(JwtTokenHandler tokenHandler) {
        this.tokenHandler = tokenHandler;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("-->> authenticate success through AJAX");
        String token = tokenHandler.create(authentication);

        Cookie cookie = new Cookie(JwtTokenConstants.COOKIE_TOKEN_NAME, token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(tokenHandler.getTokenTimeout() * 60);
        response.addCookie(cookie);

        try (PrintWriter writer = response.getWriter()) {
            String json = JsonUtils.write(ResponseFactory.success());
            writer.write(json);
            writer.flush();
        }
    }

}
