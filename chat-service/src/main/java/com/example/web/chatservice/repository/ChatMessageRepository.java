package com.example.web.chatservice.repository;

import com.example.web.chatservice.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
