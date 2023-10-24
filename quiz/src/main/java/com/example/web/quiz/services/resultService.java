package com.example.web.quiz.services;

import com.example.web.quiz.entities.result;
import com.example.web.quiz.repository.resultRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class resultService implements resultInterface {
    @Autowired
    resultRepository resultRepository;

    public result getResultForQuiz(Integer idQuiz) {
return null;
       // return resultRepository.findByQuizId(idQuiz);
    }
}
