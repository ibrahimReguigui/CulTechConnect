package com.example.web.quiz.services;

import com.example.web.quiz.entities.quiz;

import java.util.List;

public interface quizInterface {
    List<quiz> retrieveAllQuizs();

    quiz addQuiz(quiz e);

    quiz updateQuiz(quiz e);

    quiz retrieveQuiz(Integer idQuiz);

    void deleteQuiz (Integer idQuiz);
}
