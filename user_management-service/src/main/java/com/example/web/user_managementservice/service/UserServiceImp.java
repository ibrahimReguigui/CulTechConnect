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

    @Override
    public UserRepresentation getProfile(String email) {
        return keycloak.realm("CulTechConnect").users().search(email).get(0);
    }

    public UserRepresentation updateProfile(UserDto userDto,String id) {

        UserResource userResource = keycloak.realm("pidev").users().get(id);
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
