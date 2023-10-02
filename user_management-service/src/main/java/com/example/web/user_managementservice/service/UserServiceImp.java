package com.example.web.user_managementservice.service;

import com.example.web.user_managementservice.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
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

    public UserResource getRessource(Principal principal) {
        return keycloak.realm("CulTechConnect").users().get(getKeycloakProfile(principal).getId());
    }
    public UserDto getProfile(Principal principal) {
        UserDto userDto = UserDto.builder().email(getKeycloakProfile(principal).getEmail())
                .phoneNumber(getKeycloakProfile(principal).getAttributes().get("phoneNumber").get(0) != null ?
                        getKeycloakProfile(principal).getAttributes().get("phoneNumber").get(0) : null)
                .image(getKeycloakProfile(principal).getAttributes().get("image").get(0) != null ?
                        getKeycloakProfile(principal).getAttributes().get("image").get(0) : null)
                .firstName(getKeycloakProfile(principal).getFirstName())
                .lastName(getKeycloakProfile(principal).getLastName())
                .build();
        return userDto;
    }

    public String updatePassword(Principal principal, String newPwd) {
        UserResource userResource = getRessource(principal);
        CredentialRepresentation newCredential = new CredentialRepresentation();
        newCredential.setType(CredentialRepresentation.PASSWORD);
        newCredential.setValue(newPwd);
        userResource.resetPassword(newCredential);
        return "Password changed successfully";
    }

    public UserRepresentation updateProfile(Principal principal,UserDto userDto) {

        UserResource userResource = getRessource(principal);
        UserRepresentation updatedUser = userResource.toRepresentation();
        updatedUser.setFirstName(userDto.getFirstName());
        updatedUser.setLastName(userDto.getLastName());
        updatedUser.getAttributes().put("image", Arrays.asList((userDto.getImage() != null ? userDto.getImage() :
                updatedUser.getAttributes().get("image").get(0))));
        updatedUser.getAttributes().put("phoneNumber", Arrays.asList((userDto.getImage() != null ? String.valueOf(userDto.getPhoneNumber()) :
                updatedUser.getAttributes().get("phoneNumber").get(0))));
        userResource.update(updatedUser);

        return updatedUser;
    }

}
