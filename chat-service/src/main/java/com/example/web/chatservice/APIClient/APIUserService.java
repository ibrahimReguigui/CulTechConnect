package com.example.web.chatservice.APIClient;

import com.example.web.chatservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@FeignClient("usermanagement-service")
public interface APIUserService {

    @GetMapping("/api/user/client/getUserId")
     String getUserId(@RequestParam String email);
}
