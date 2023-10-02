package com.example.web.user_managementservice.service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakService {

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080/")
                .realm("CulTechConnect")
                .clientId("microservice-auth")
                .username("systemadmin@mail.com")
                .password("systemadmin@mail.com")
                .build();
    }
}

