/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.security.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import space.nature.common.core.dto.ResponseFactory;
import space.nature.common.core.dto.ResponseStatusEnum;
import space.nature.common.util.JsonUtils;

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
        String result;
        if (authentication == null || !authentication.isAuthenticated()) {
            result = JsonUtils.write(ResponseFactory.create(ResponseStatusEnum.FAIL));
        } else {
            result = JsonUtils.write(ResponseFactory.success());
        }
        try (PrintWriter writer = response.getWriter()) {
            writer.write(result);
            writer.flush();
        }
    }
}
