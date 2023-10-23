package com.example.web.chatservice.controller;

import com.example.web.chatservice.interfaces.ChatMessageService;
import com.example.web.chatservice.model.ChatMessage;
import com.example.web.chatservice.service.UserSessionRegistry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final UserSessionRegistry userSessionRegistry;
    private ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    @SendTo("/message")
    public ChatMessage sendMessage(ChatMessage message) {
        String recipientUserId = message.getRecipient();
        String senderUserId = message.getSender();
        ChatMessage savedMessage=chatMessageService.saveChatMessage(message);
        if (recipientUserId != null && !recipientUserId.isEmpty()) {
            String recipientSessionId = userSessionRegistry.getSessionIdByUserId(recipientUserId);
            SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create();
            headerAccessor.setSessionId(recipientSessionId);
            headerAccessor.setLeaveMutable(true);

            messagingTemplate.convertAndSend("/message/" + recipientUserId, savedMessage, headerAccessor.getMessageHeaders());
            messagingTemplate.convertAndSend("/message/" + senderUserId, savedMessage, headerAccessor.getMessageHeaders());
            messagingTemplate.convertAndSend("/message/notification" + recipientUserId, 5, headerAccessor.getMessageHeaders());

            return null;
        }
        return message;
    }

    @GetMapping("/chat/getAllMyMessage")
    public ResponseEntity getAllMyMessage(Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(chatMessageService.getAllMyMessages(principal));
    }

    @PostMapping("/chat/setMessageSeen")
    public ResponseEntity setMessageSeen(@RequestParam Long id){
        chatMessageService.setMessageSeen(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(id, "Done"));
    }

    @GetMapping("/chat/getConnectedUsers")
    public ResponseEntity getConnectedUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userSessionRegistry.getAllConnectedUsers().keySet());
    }

}
