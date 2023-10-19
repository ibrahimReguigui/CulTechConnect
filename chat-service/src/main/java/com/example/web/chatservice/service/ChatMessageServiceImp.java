package com.example.web.chatservice.service;

import com.example.web.chatservice.APIClient.APIUserService;
import com.example.web.chatservice.dto.UserDto;
import com.example.web.chatservice.interfaces.ChatMessageService;
import com.example.web.chatservice.model.ChatMessage;
import com.example.web.chatservice.repository.ChatMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatMessageServiceImp implements ChatMessageService {

    private ChatMessageRepository chatMessageRepository;
    private APIUserService apiUserService;

    @Override
    public ChatMessage saveChatMessage(ChatMessage chatMessage) {
        chatMessage.setTime(new Date());
        chatMessage.setSeen(false);
        System.out.println(chatMessage);
        return chatMessageRepository.save(chatMessage);
    }

    @Override
    public List<ChatMessage> getAllMyMessages(Principal principal) {
        String id=apiUserService.getUserId(principal.getName());
        return chatMessageRepository.getAllBySenderOrRecipient(id,id).orElse(null);
    }

    @Override
    public void setMessageSeen(Long id) {
        chatMessageRepository.findById(id).ifPresent(e->{e.setSeen(true);chatMessageRepository.save(e);});
    }

}
