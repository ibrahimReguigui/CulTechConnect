package com.example.web.user_managementservice.service;

import com.example.web.user_managementservice.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {

    private final Keycloak keycloak;

    public UserRepresentation getKeycloakProfile(Principal principal) {
        return keycloak.realm("CulTechConnect").users().search(principal.getName()).get(0);
    }
    public UserResource getKeycloakProfileRessource(Principal principal) {
        return keycloak.realm("CulTechConnect").users().get(principal.getName());
    }
    public UserDto getProfile(Principal principal){
        UserDto userDto=UserDto.builder().email(getKeycloakProfile(principal).getEmail())
                .phoneNumber(getKeycloakProfile(principal).getAttributes().get("phoneNumber").get(0) != null ?
                        getKeycloakProfile(principal).getAttributes().get("phoneNumber").get(0) :null)
                .image(getKeycloakProfile(principal).getAttributes().get("image").get(0) != null ?
                        getKeycloakProfile(principal).getAttributes().get("image").get(0) :null)
                .firstName(getKeycloakProfile(principal).getFirstName())
                .lastName(getKeycloakProfile(principal).getLastName())
                .build();
        return userDto;
    }
}
