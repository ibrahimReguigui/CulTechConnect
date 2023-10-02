package com.example.web.gateway.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                //.cors()
                //.and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()// Enable CORS
                .authorizeExchange(authorizeExchange ->
                        authorizeExchange
                                .pathMatchers("/**").permitAll()// Allow all requests for simplicity (customize as needed)
                                .anyExchange().authenticated()
                                //.and().cors()
                                .and().csrf().disable()
                                .formLogin().disable()
                                .httpBasic().disable()
                )
                .oauth2Login(Customizer.withDefaults());

        return http.build();
    }

/*    @Bean
    public CorsWebFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:4200/*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("origin");
        config.addAllowedHeader("content-type");
        config.addAllowedHeader("accept");
        config.addAllowedHeader("authorization");
        config.addAllowedHeader("cookie");
        config.addAllowedHeader("Access-Control-Allow-Origin");
        config.addAllowedHeader("Keep-Alive");
        config.addAllowedHeader("Connection");
        config.addAllowedHeader("Vary");
        config.setAllowCredentials(true);


        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }

    @Bean
    public GlobalFilter addCustomResponseHeader() {
        return (exchange, chain) -> {
            // Add your custom header to the response
            ServerHttpResponse response = exchange.getResponse();
            HttpHeaders headers = response.getHeaders();
            headers.add("Access-Control-Allow-Origin", "*");

            // Continue with the filter chain
            return chain.filter(exchange);
        };
    }*/
}
