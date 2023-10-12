package com.example.web.chatservice.service;

import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class UserSessionRegistry {

    private final Map<String, String> userSessionMap = new ConcurrentHashMap<>();
    private final SimpMessagingTemplate messagingTemplate;


    public void registerUserSession(String userId, String sessionId) {
        userSessionMap.put(userId, sessionId);
        broadcastConnectedUsers();
    }

    public void removeUserSession(String sessionId) {
        userSessionMap.entrySet().removeIf(entry -> entry.getValue().equals(sessionId));
        broadcastConnectedUsers();
    }

    public Map<String, String> getAllConnectedUsers() {
        return userSessionMap;
    }

    public void broadcastConnectedUsers() {
        messagingTemplate.convertAndSend("/connected-users", userSessionMap.keySet());
    }
    public String getSessionIdByUserId(String userId) {
        return userSessionMap.get(userId);
    }
}
