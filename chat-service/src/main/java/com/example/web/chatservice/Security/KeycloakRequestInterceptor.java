package com.example.web.chatservice.Security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class KeycloakRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication instanceof KeycloakAuthenticationToken) {
            KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) authentication;
            String token = keycloakAuthenticationToken.getAccount().getKeycloakSecurityContext().getTokenString();
            template.header("Authorization", "Bearer " + token);
        }
    }

}

