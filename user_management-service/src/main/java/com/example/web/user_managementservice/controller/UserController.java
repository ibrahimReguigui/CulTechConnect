package com.example.web.user_managementservice.controller;

import com.example.web.user_managementservice.dto.UserDto;
import com.example.web.user_managementservice.service.UserService;
import com.example.web.user_managementservice.service.UserServiceImp;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
public class UserController {

    private UserServiceImp userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RolesAllowed({"ROLE_MEMBER"})
    @GetMapping("/getUserProfile")
    public ResponseEntity<String> getUserProfile(Principal principal) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String response = "{\"name\": \"John\", \"email\": \"john@example.com\"}";

        ///

        System.out.println( principal.getName());
        System.out.println(userService.getProfile(principal.getName()));
        System.out.println(userService.getProfile(principal.getName()).getId());
        UserDto userDto=UserDto.builder().email(userService.getProfile(principal.getName()).getEmail())
                .image()
        ///
        return  ResponseEntity.status(HttpStatus.OK).body());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RolesAllowed({"ROLE_MEMBER"})
    @GetMapping("/updateProfile")
    public ResponseEntity updateProfile(@RequestBody UserDto userDto ,Principal principal) {
        return  ResponseEntity.status(HttpStatus.OK).body(userService.updateProfile(userDto,userService.getProfile(principal.getName()).getId()));
    }
}

