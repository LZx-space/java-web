/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import space.nature.web.gateway.infrastructure.filters.JsonExceptionHandler;

@SpringBootApplication(exclude = ErrorWebFluxAutoConfiguration.class)
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/users")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://localhost:8081/users"))
                .route(p -> p
                        .path("/blogs")
                        .uri("http://localhost:8082/blogs"))
                .build();
    }

}
