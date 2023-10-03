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
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")

public class UserController {
    @Autowired
    private UserServiceImp userService;

    @RolesAllowed({"ROLE_MEMBER", "ROLE_ADMIN", "ROLE_PARTNER", "ROLE_SYSTEMADMIN"})
    @GetMapping("/profile/getUserProfile")
    public ResponseEntity getUserProfile(Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getProfile(principal));
    }

    @RolesAllowed({"ROLE_MEMBER", "ROLE_ADMIN", "ROLE_PARTNER", "ROLE_SYSTEMADMIN"})
    @PostMapping("/profile/updatePassword")
    public ResponseEntity updatePassword(Principal principal, @RequestParam String newPwd) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("response", userService.updatePassword(principal, newPwd)));
    }

    @RolesAllowed({"ROLE_MEMBER", "ROLE_ADMIN", "ROLE_PARTNER", "ROLE_SYSTEMADMIN"})
    @PostMapping("/profile/updateProfile")
    public ResponseEntity updateProfile(Principal principal, @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateProfile(principal, userDto));
    }

    @PostMapping("/visitor/register")
    public ResponseEntity registration(@RequestBody UserDto userDto) {
        System.out.println("register");
        String result = userService.registration(userDto);
        if (result == "User Already Exist")
            return ResponseEntity.unprocessableEntity().body(Map.of("response", result));
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("response", result));
    }
}


/*
    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
                String response = "{\"name\": \"John\", \"email\": \"john@example.com\"}";*/
