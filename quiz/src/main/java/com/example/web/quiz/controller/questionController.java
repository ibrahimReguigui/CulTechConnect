package com.example.web.quiz.controller;

import com.example.web.quiz.entities.question;
import com.example.web.quiz.services.questionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/question")

public class questionController {
   questionService questionService;

    @GetMapping("/retrieve-all-question")
    public List<question> getquestions() {
        List<question> listquestions = questionService.retrieveAllquestions();
        return listquestions;
    }

    @GetMapping("/retrieve-question/{question-id}")
    public question retrieveQuestion(@PathVariable("question-id") Integer Idquestion) {
        return questionService.retrieveQuestion(Idquestion);
    }

    @PostMapping("/add-question")
    public question addQuestion(@RequestBody question e) {
        question question = questionService.addQuestion(e);
        return question;
    }

    @PutMapping("/update-question")
    public question updateQuestion(@RequestBody question e) {
        question question = questionService.updateQuestion(e);
        return question;
    }

    @DeleteMapping("/delete-question/{question-id}")
    public String deleteQuestion(@PathVariable("question-id") Integer Idquestion) {
        questionService.deleteQuestion(Idquestion);
        return "question with ID " + Idquestion + " has been deleted successfully";
    }

}
