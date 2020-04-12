package space.nature.web.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author LZx
 */
@RestController
@SpringBootApplication
@EnableRedisWebSession
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @GetMapping("/hello")
    public Mono<String> hello(ServerHttpRequest request) {
        MultiValueMap<String, String> stringStringMultiValueMap = request.getQueryParams();
        System.out.println(stringStringMultiValueMap);
        return Mono.just("hello reactor");
    }

}
