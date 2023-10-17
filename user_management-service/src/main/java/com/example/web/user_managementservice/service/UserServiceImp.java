package com.example.web.user_managementservice.service;

import com.example.web.user_managementservice.Enum.Role;

import com.example.web.user_managementservice.Interface.UserService;
import com.example.web.user_managementservice.entities.User;
import com.example.web.user_managementservice.mapper.KeycloakMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {

    private final Keycloak keycloak;
    private final KafkaTemplate<String,String> kafkaTemplate;
    private KeycloakMapper keycloakMapper;


    public UserRepresentation getKeycloakProfile(Principal principal) {
        return keycloak.realm("CulTechConnect").users().search(principal.getName()).get(0);
    }

    public UserResource getRessource(Principal principal) {
        return keycloak.realm("CulTechConnect").users().get(getKeycloakProfile(principal).getId());
    }
    public User getProfile(Principal principal) {
        User user = User.builder().email(getKeycloakProfile(principal).getEmail())
                .phoneNumber(getKeycloakProfile(principal).getAttributes().get("phoneNumber").get(0) != null ?
                        getKeycloakProfile(principal).getAttributes().get("phoneNumber").get(0) : null)
                .image(getKeycloakProfile(principal).getAttributes().get("image").get(0) != null ?
                        getKeycloakProfile(principal).getAttributes().get("image").get(0) : null)
                .firstName(getKeycloakProfile(principal).getFirstName())
                .lastName(getKeycloakProfile(principal).getLastName())
                .build();
        return user;
    }
    public String getIdByEmail(String email) {
        return keycloak.realm("CulTechConnect").users().search(email).get(0).getId();
    }

    public String updatePassword(Principal principal, String newPwd) {
        UserResource userResource = getRessource(principal);
        CredentialRepresentation newCredential = new CredentialRepresentation();
        newCredential.setType(CredentialRepresentation.PASSWORD);
        newCredential.setTemporary(false);
        newCredential.setValue(newPwd);
        userResource.resetPassword(newCredential);
        kafkaTemplate.send("notification","test notif");
        return "Password changed successfully";
    }

    public UserRepresentation updateProfile(Principal principal, User user) {

        UserResource userResource = getRessource(principal);
        UserRepresentation updatedUser = userResource.toRepresentation();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.getAttributes().put("image", Arrays.asList((user.getImage() != null ? user.getImage() :
                updatedUser.getAttributes().get("image").get(0))));
        updatedUser.getAttributes().put("phoneNumber", Arrays.asList((user.getImage() != null ? String.valueOf(user.getPhoneNumber()) :
                updatedUser.getAttributes().get("phoneNumber").get(0))));
        userResource.update(updatedUser);

        return updatedUser;
    }

    public Boolean userExistByEmailKeycloak(String email) {
        List<UserRepresentation> userRepresentations = keycloak.realm("CulTechConnect").users().search(email);
        if (userRepresentations.size() == 0)
            return false;
        return true;
    }
    public String registration(User userDto) {
        if (userExistByEmailKeycloak(userDto.getEmail()))
            return "User Already Exist";

        //CREATE THE USER IMAGE
        UserRepresentation user = new UserRepresentation();

        user.setUsername(userDto.getEmail());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        //ATRIBUTES
        HashMap<String, List<String>> attributes = new HashMap<>();
        attributes.put("phoneNumber", Collections.singletonList(String.valueOf(userDto.getPhoneNumber())));
        attributes.put("image", Collections.singletonList((userDto.getImage() != null ? userDto.getImage() : "null")));
        attributes.put("info", Collections.singletonList((userDto.getInfo() != null ? userDto.getInfo() : "null")));
        attributes.put("adress", Collections.singletonList((userDto.getAdress() != null ? userDto.getAdress() : "null")));
        if (userDto.getRole() == Role.PARTNER) {
            attributes.put("societyName", Collections.singletonList(userDto.getSocietyName()));
        } else
            attributes.put("societyName", Collections.singletonList("null"));


        user.setAttributes(attributes);
        //ACOUNT ACTIVATION
        user.setEnabled(true);
        //PASSWORD
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userDto.getPassword());
        user.setCredentials(Arrays.asList(passwordCred));
        //ADD THE USER IN KEYCLOAK
        UsersResource usersResource = keycloak.realm("CulTechConnect").users();
        Response response = usersResource.create(user);
        String createdUserId = CreatedResponseUtil.getCreatedId(response);
        //UPDATE ROlE
        UserResource userResource = keycloak.realm("CulTechConnect").users().get(createdUserId);
        RoleRepresentation role = keycloak.realm("CulTechConnect").roles().get(userDto.getRole().toString()).toRepresentation();
        List<RoleRepresentation> roles = userResource.roles().realmLevel().listEffective();
        roles.add(role);
        userResource.roles().realmLevel().add(roles);
        return userResource.toString();
    }
    public List<User> getAllUsers(Principal principal) {
        UsersResource usersResource = keycloak.realm("CulTechConnect").users();
        List<UserRepresentation> userRepresentations = usersResource.list();

        List<User> users = userRepresentations.stream().filter(e->!e.getEmail().equals("systemadmin@mail.com")&&
                        !e.getEmail().equals(principal.getName()))
                .map(e->keycloakMapper.userRepresentationMapToUser(e))
                .collect(Collectors.toList());

        return users;
    }
}
