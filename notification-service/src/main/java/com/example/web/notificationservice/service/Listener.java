package com.example.web.notificationservice.service;

import com.example.web.notificationservice.entitie.Notification;
import com.example.web.notificationservice.interfaces.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class Listener {

    private SimpMessagingTemplate messagingTemplate;
    private NotificationService notificationService;

    @KafkaListener(topics = "notification", groupId = "notificationId")
    public void handleNotification(@Payload Notification notification) {
        log.info(notification.toString());
        log.info(notification.getContent());
        log.info(notification.getTime().toString());
        log.info(notification.getUserId());
        notification=notificationService.saveNotification(notification);
        messagingTemplate.convertAndSend("/notification", notification);
    }

}
