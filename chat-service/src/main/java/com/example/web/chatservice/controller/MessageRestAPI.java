package com.example.web.chatservice.controller;

import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/messages")
public class MessageRestAPI {
    @RolesAllowed({"ROLE_Test"})
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        String response = "{\"name\": \"John\", \"email\": \"john@example.com\"}";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
