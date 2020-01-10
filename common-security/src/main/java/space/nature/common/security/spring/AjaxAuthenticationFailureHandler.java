/*
 * Copyright (c) 2019, LZx
 */

package space.nature.common.security.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import space.nature.common.core.dto.ResponseFactory;
import space.nature.common.core.dto.ResponseStatusEnum;
import space.nature.common.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String localizedMessage = exception.getLocalizedMessage();
        log.info("-->> authenticate failed through AJAX：{}", localizedMessage);
        // TODO if remove token
        try (PrintWriter writer = response.getWriter()) {
            String json = JsonUtils.write(ResponseFactory.create(ResponseStatusEnum.FAIL));
            writer.write(json);
            writer.flush();
        }
    }

}
