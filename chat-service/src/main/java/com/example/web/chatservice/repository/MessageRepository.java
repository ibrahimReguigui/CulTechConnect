package com.example.web.chatservice.repository;

import com.example.web.chatservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository  extends JpaRepository<Message, Long> {
@Query("select m from Message  m where m.sender=:name")
    List<Message> findAllBySender(@Param("name") String sender);
}
