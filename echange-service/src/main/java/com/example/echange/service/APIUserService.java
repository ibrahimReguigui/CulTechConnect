package com.example.echange.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("usermanagement-service")
public interface APIUserService {

    @GetMapping("/api/user/client/getUserId")
    String getUserId(@RequestParam String email);
}