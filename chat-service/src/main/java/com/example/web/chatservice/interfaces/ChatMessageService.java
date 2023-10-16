package com.example.web.chatservice.interfaces;

import com.example.web.chatservice.model.ChatMessage;

import java.security.Principal;
import java.util.List;

public interface ChatMessageService {
    ChatMessage saveChatMessage(ChatMessage chatMessage);
    List<ChatMessage> getAllMyMessages(Principal principal);
    void setMessageSeen(Long id);
}
