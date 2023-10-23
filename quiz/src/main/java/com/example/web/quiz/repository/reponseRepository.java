package com.example.web.quiz.repository;

import com.example.web.quiz.entities.reponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface reponseRepository extends CrudRepository<reponse,Integer> {
}
