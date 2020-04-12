package space.nature.web.gateway.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.nature.core.dto.ResponseFactory;
import org.nature.core.dto.ResponseStatusEnum;
import org.nature.core.util.JsonUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

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
            write = JsonUtils.write(ResponseFactory.create(ResponseStatusEnum.FAIL));
        } catch (JsonProcessingException e) {
            return Mono.error(e);
        }
        DataBuffer dataBuffer = response.bufferFactory().wrap(write.getBytes(StandardCharsets.UTF_8));
        return response.writeAndFlushWith(Mono.just(dataBuffer).map(Mono::just));
    }
}
