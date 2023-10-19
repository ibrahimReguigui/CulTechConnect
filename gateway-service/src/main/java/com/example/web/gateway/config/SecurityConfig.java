package com.example.web.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.cors().disable()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeExchange(authorizeExchange ->
                        authorizeExchange
                                .pathMatchers("/api/user/visitor/**").permitAll()
                                .anyExchange().authenticated()
                )
                .oauth2Login();
        return http.build();
    }

}
