package space.nature.common.security.spring;

import lombok.extern.slf4j.Slf4j;
import org.nature.core.dto.ResponseFactory;
import org.nature.core.util.JsonUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author LZx
 */
@Slf4j
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("-->> authenticate success through AJAX");
        try (PrintWriter writer = response.getWriter()) {
            String json = JsonUtils.write(ResponseFactory.success());
            writer.write(json);
            writer.flush();
        }
    }

}
