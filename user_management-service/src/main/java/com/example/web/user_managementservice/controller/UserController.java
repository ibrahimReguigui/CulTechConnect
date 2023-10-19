package com.example.web.user_managementservice.controller;

import com.example.web.user_managementservice.entities.User;
import com.example.web.user_managementservice.service.UserServiceImp;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImp userService;


    @GetMapping("/client/getUserId")
    public ResponseEntity getUserId(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getIdByEmail(email));
    }

    @RolesAllowed({"ROLE_MEMBER", "ROLE_ADMIN", "ROLE_PARTNER", "ROLE_SYSTEMADMIN"})
    @GetMapping("/profile/getUserProfile")
    public ResponseEntity getUserProfile(Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getProfile(principal));
    }

    @RolesAllowed({"ROLE_MEMBER","ROLE_ADMIN", "ROLE_PARTNER", "ROLE_SYSTEMADMIN"})
    @PostMapping("/profile/updatePassword")
    public ResponseEntity updatePassword(Principal principal, @RequestParam String newPwd) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("response", userService.updatePassword(principal, newPwd)));
    }

    @RolesAllowed({"ROLE_MEMBER", "ROLE_ADMIN", "ROLE_PARTNER", "ROLE_SYSTEMADMIN"})
    @PostMapping("/profile/updateProfile")
    public ResponseEntity updateProfile(Principal principal, @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateProfile(principal, user));
    }

    @CrossOrigin
    @PostMapping("/visitor/register")
    public ResponseEntity registration(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> validationErrors = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity
                    .badRequest()
                    .body(validationErrors);
        }

        String result = userService.registration(user);
        if (result == "User Already Exist")
            return ResponseEntity.unprocessableEntity().body(Map.of("response", result));
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("response", result));
    }

    @RolesAllowed({"ROLE_MEMBER", "ROLE_ADMIN", "ROLE_PARTNER", "ROLE_SYSTEMADMIN"})
    @GetMapping("/getAllUser")
    public ResponseEntity getAllUser(Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers(principal));
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        return ResponseEntity.status(HttpStatus.OK).body("test sucessfull");
    }

}
