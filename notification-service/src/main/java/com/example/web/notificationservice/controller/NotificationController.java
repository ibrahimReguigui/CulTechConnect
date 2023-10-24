package com.example.web.notificationservice.controller;


import com.example.web.notificationservice.entitie.Notification;
import com.example.web.notificationservice.interfaces.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/notification")
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;
    private NotificationService notificationService;

    @GetMapping("/setSeen")
    public void setSeen(Principal principal) {
        notificationService.setSeen();
        }

    @GetMapping("/getMyNotification")
    public ResponseEntity getMyNotification(Principal principal) {
        System.out.println(notificationService.getAllNotif(principal));
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.getAllNotif(principal));

    }
}
