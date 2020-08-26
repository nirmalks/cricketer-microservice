package com.nirmal.cricketergateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class  LocalhostConfig {
    @Bean
    public RouteLocator localhostRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/cricketers*")
                    .uri("http://localhost:8081")
                        .id("cricketer-service"))
                .build();
    }
}
