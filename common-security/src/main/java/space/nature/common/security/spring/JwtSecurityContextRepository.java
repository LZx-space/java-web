/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.security.spring;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JwtSecurityContextRepository implements SecurityContextRepository {

    private final JwtTokenHandler tokenHandler;

    public JwtSecurityContextRepository(JwtTokenHandler tokenHandler) {
        this.tokenHandler = tokenHandler;
    }

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        log.info("loadContext");
        HttpServletRequest request = requestResponseHolder.getRequest();
        String token = request.getHeader(JwtTokenConstants.HEADER_TOKEN_NAME);
        if (StringUtils.isEmpty(token)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (JwtTokenConstants.COOKIE_TOKEN_NAME.equals(cookie.getName())) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }
        }
        if (StringUtils.isEmpty(token)) {
            return SecurityContextHolder.createEmptyContext();
        }
        try {
            // TODO 刷新expiration
            Authentication authentication = tokenHandler.parse(token);
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authentication);
            return securityContext;
        } catch (ExpiredJwtException e) {
            log.info("token expired");
            return SecurityContextHolder.createEmptyContext();
        } catch (Exception e) {
            log.info("parse token exception", e);
            return SecurityContextHolder.createEmptyContext();
        }
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        log.info("saveContext");
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie e : cookies) {
                if (JwtTokenConstants.COOKIE_TOKEN_NAME.equals(e.getName())) {
                    return true;
                }
            }
        }
        return StringUtils.isNotEmpty(request.getHeader(JwtTokenConstants.COOKIE_TOKEN_NAME));
    }
}
