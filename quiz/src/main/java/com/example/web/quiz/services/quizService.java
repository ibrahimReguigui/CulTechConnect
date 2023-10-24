package com.example.web.quiz.services;

import com.example.web.quiz.entities.quiz;
import com.example.web.quiz.repository.quizRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor

public class quizService implements quizInterface {
        @Autowired
        quizRepository quizRepository;

        @Override
        public List<quiz> retrieveAllQuizs() {
            Iterable<quiz> iterable = quizRepository.findAll();
            List<quiz> quizList = new ArrayList<>();
            iterable.forEach(quizList::add);
            return quizList;
        }

        @Override
        public quiz addQuiz(quiz e) {
            return quizRepository.save(e);
        }

        @Override
        public quiz updateQuiz(quiz e) {
            return quizRepository.save(e);
        }

        @Override
        public quiz retrieveQuiz(Integer idQuiz) {
            return quizRepository.findById(idQuiz).get();
        }

        @Override
        public void deleteQuiz(Integer idQuiz) {
            quizRepository.deleteById(idQuiz);
        }

}
