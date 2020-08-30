package com.nirmal.cricketergateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/cricketers*")
                        .filters(f -> f.circuitBreaker(c -> c.setName("cricketerFb")
                            .setFallbackUri("forward:/cricketer-failover")
                                .setRouteId("cricketer-failover")
                        ))
                        .uri("lb://cricketer-service")
                        .id("cricketer-service"))
                .route(r -> r.path("/cricketer-failover*")
                        .uri("lb://cricketer-failover-service")
                        .id("cricketer-failover-service"))
                .build();
    }
}
