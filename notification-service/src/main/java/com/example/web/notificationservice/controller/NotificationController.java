package com.example.web.notificationservice.controller;

import com.example.web.notificationservice.interfaces.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
@Slf4j
@AllArgsConstructor
@Controller
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;
    private NotificationService notificationService;

    @MessageMapping("/notification/setSeen")
    public void setSeen(Principal principal) {
        notificationService.setSeen();
        messagingTemplate.convertAndSend("/notification/"+principal.getName(),"up to date");
    }

    @MessageMapping("/notification/getMyNotification")
    public void getMyNotification(Principal principal) {
        log.info(principal.getName());
    }
}
