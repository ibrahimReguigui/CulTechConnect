package com.example.web.quiz.services;

import com.example.web.quiz.entities.reponse;
import com.example.web.quiz.repository.reponseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class reponseService {
    @Autowired
    private reponseRepository reponseRepository;

    public void saveReponse(reponse reponse) {

        reponseRepository.save(reponse);
    }
}
