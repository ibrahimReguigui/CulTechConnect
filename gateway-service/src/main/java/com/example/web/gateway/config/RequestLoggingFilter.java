/*
package com.example.web.gateway.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class RequestLoggingFilter {

    @Bean
    public GlobalFilter logRequestFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            Route route = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

            // Log request headers
            System.out.println("Request Headers for Route: " + route.getId());
            request.getHeaders().forEach((key, value) -> System.out.println(key + ": " + value));

            // Continue with the request chain
            return chain.filter(exchange);
        };
    }
}

*/
