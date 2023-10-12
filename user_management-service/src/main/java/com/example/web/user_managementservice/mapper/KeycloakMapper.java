package com.example.web.user_managementservice.mapper;

import com.example.web.user_managementservice.entities.User;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
public class KeycloakMapper {
    public User userRepresentationMapToUser(UserRepresentation userRepresentation) {
        User user = new User();
        user.setFirstName(userRepresentation.getFirstName());
        user.setLastName(userRepresentation.getLastName());
        user.setEmail(userRepresentation.getEmail());
        user.setId(userRepresentation.getId());
        return user;
    }
}
