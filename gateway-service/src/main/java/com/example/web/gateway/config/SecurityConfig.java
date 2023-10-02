package com.example.web.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()// Enable CORS
                .authorizeExchange(authorizeExchange ->
                        authorizeExchange
                                .pathMatchers("/api/user/visitor/**").permitAll()// Allow all requests for simplicity (customize as needed)
                                .anyExchange().authenticated()
                                //.and().cors()
                                .and().csrf().disable()
                                .formLogin().disable()
                                .httpBasic().disable()
                )
                .oauth2Login();

        return http.build();
    }

}
