package com.example.web.quiz.repository;

import com.example.web.quiz.entities.quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface quizRepository extends CrudRepository<quiz,Integer> {
}
