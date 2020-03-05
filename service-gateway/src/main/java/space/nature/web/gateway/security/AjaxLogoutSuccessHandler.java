package space.nature.web.gateway.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import reactor.core.publisher.Mono;
import space.nature.common.core.dto.ResponseFactory;
import space.nature.common.core.util.JsonUtils;

/**
 * @author LZx
 * @date 2020/2/8
 */
@Slf4j
public class AjaxLogoutSuccessHandler implements ServerLogoutSuccessHandler {

    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
        if (log.isDebugEnabled()) {
            log.debug("--> log out");
        }
        ServerHttpResponse response = exchange.getExchange().getResponse();
        response.setStatusCode(HttpStatus.OK);
        String write;
        try {
            write = JsonUtils.write(ResponseFactory.success());
        } catch (JsonProcessingException e) {
            return Mono.error(e);
        }
        return response.writeAndFlushWith(Mono.just(new DefaultDataBufferFactory().wrap(write.getBytes())).map(Mono::just));
    }
}