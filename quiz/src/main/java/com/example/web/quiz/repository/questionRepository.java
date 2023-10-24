package com.example.web.quiz.repository;

import com.example.web.quiz.entities.question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface questionRepository extends CrudRepository<question,Integer> {
}
