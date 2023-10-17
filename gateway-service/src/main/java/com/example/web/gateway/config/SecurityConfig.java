package com.example.web.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
<<<<<<< HEAD
        http.cors().disable()
=======
        http
>>>>>>> 332a21e9070e8b8cd285951f651f77b048bf49fc
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
