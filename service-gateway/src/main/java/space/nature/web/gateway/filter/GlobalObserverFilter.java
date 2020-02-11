package space.nature.web.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * GlobalFilter的意义与yml中Default-filter有重叠<br>
 * 1.所有Filter都是作用于{@link ServerWebExchange}，所有的请求都能用到<br>
 * 2.有Default-filter可配置给所有routes<br>
 *
 * @author LZx
 * @date 2020/2/8
 */
@Slf4j
public class GlobalObserverFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("-------------------->[{}] now request[{}]", "all routes pass", exchange.getRequest().getURI().getRawPath());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
