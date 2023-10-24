package com.example.web.quiz.controller;

import com.example.web.quiz.entities.result;
import com.example.web.quiz.services.resultService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;

@RestController
@AllArgsConstructor
@RequestMapping("/result")

public class resultController {
    @Autowired
     resultService resultService;

    @GetMapping("/{quizId}")
    public ResponseEntity<result> getResultForQuiz(@PathVariable Integer quizId) {
        result result = resultService.getResultForQuiz(quizId);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
