package space.nature.web.gateway.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import reactor.core.publisher.Mono;
import space.nature.common.core.dto.ResponseFactory;
import space.nature.common.core.dto.ResponseStatusEnum;
import space.nature.common.core.util.JsonUtils;

/**
 * @author LZx
 * @date 2020/2/8
 */
@Slf4j
public class AjaxAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        if (log.isDebugEnabled()) {
            log.debug("-->> authenticate failed through AJAX：{}", exception.getLocalizedMessage());
        }
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        response.setStatusCode(HttpStatus.OK);
        String write;
        try {
            write = JsonUtils.write(ResponseFactory.success(ResponseStatusEnum.FAIL));
        } catch (JsonProcessingException e) {
            return Mono.error(e);
        }
        return response.writeAndFlushWith(Mono.just(new DefaultDataBufferFactory().wrap(write.getBytes())).map(Mono::just));
    }
}
