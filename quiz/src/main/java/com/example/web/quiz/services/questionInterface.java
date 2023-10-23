package com.example.web.quiz.services;

import com.example.web.quiz.entities.question;
import java.util.List;

public interface questionInterface {

    List<question> retrieveAllquestions();

    question addQuestion(question e);

    question updateQuestion(question e);

    question retrieveQuestion(Integer idQuestion);

    void deleteQuestion(Integer idQuestion);
}
