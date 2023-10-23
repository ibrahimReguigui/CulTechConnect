package com.example.web.chatservice.config;

import com.example.web.chatservice.service.UserSessionRegistry;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private final UserSessionRegistry userSessionRegistry;

    public WebSocketEventListener(UserSessionRegistry userSessionRegistry) {
        this.userSessionRegistry = userSessionRegistry;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        System.out.println("connected");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();
        String userId = (String) headerAccessor.getNativeHeader("userId").get(0);
        userSessionRegistry.registerUserSession(userId, sessionId);
        userSessionRegistry.broadcastConnectedUsers();
        System.out.println(userSessionRegistry.getAllConnectedUsers());
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        System.out.println("disconnected");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();
        userSessionRegistry.removeUserSession(sessionId);
        userSessionRegistry.broadcastConnectedUsers();
        System.out.println(userSessionRegistry.getAllConnectedUsers());
    }
}

