package com.example.web.chatservice.controller;

import com.example.web.chatservice.model.ChatMessage;
import com.example.web.chatservice.service.UserSessionRegistry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final UserSessionRegistry userSessionRegistry;

    @MessageMapping("/chat")
    @SendTo("/message")
    public ChatMessage sendMessage(ChatMessage message) {
        String recipientUserId = message.getRecipient();
        String senderUserId = message.getSender();
        if (recipientUserId != null && !recipientUserId.isEmpty()) {
            String recipientSessionId = userSessionRegistry.getSessionIdByUserId(recipientUserId);
            SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create();
            headerAccessor.setSessionId(recipientSessionId);
            headerAccessor.setLeaveMutable(true);

            messagingTemplate.convertAndSend("/message/" + recipientUserId, message, headerAccessor.getMessageHeaders());
            messagingTemplate.convertAndSend("/message/" + senderUserId, message, headerAccessor.getMessageHeaders());

            return null;
        }
        return message;
    }
}
