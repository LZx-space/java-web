package space.nature.web.gateway.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutHandler;
import reactor.core.publisher.Mono;

/**
 * @author LZx
 * @date 2020/2/8
 */
@Slf4j
public class LogoutExtraHandler implements ServerLogoutHandler {

    @Override
    public Mono<Void> logout(WebFilterExchange exchange, Authentication authentication) {
        if (log.isDebugEnabled()) {
            log.debug("--> log out extra handler");
        }
        return Mono.empty();
    }
}
