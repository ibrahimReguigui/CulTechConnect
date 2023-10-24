package com.example.web.quiz.services;

import com.example.web.quiz.entities.question;

import com.example.web.quiz.repository.questionRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class questionService  implements questionInterface {
    @Autowired
    questionRepository questionRepository;

    @Override
    public List<question> retrieveAllquestions() {
        Iterable<question> iterable = questionRepository.findAll();
        List<question> questionList = new ArrayList<>();
        iterable.forEach(questionList::add);
        return questionList;
    }

    @Override
    public question addQuestion(question e) {
        return questionRepository.save(e);
    }

    @Override
    public question updateQuestion(question e) {
        return questionRepository.save(e);
    }

    @Override
    public question retrieveQuestion(Integer idquestion) {
        return questionRepository.findById(idquestion).get();
    }

    @Override
    public void deleteQuestion(Integer idquestion) {
        questionRepository.deleteById(idquestion);
    }


}
