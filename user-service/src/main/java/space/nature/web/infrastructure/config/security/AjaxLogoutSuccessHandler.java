/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.infrastructure.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import space.nature.core.responseobj.CommonResponseFactory;
import space.nature.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("-->> log out success");
        try (PrintWriter writer = response.getWriter()) {
            String json = JsonUtils.fromObject(CommonResponseFactory.success());
            writer.write(json);
            writer.flush();
        }
    }
}
