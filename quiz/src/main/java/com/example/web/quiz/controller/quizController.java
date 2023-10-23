package com.example.web.quiz.controller;

import com.example.web.quiz.entities.quiz;
import com.example.web.quiz.services.quizService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://your-frontend-app.com")
@RestController
@AllArgsConstructor
@RequestMapping("/quiz")
public class quizController {

    quizService quizService;

    @GetMapping("/retrieve-all-quiz")
    public List<quiz> getquizs() {
        List<quiz> listquizs = quizService.retrieveAllQuizs();
        return listquizs;
    }

    @GetMapping("/retrieve-quiz/{quiz-id}")
    public quiz retrieveQuiz(@PathVariable("quiz-id") Integer Idquiz) {
        return quizService.retrieveQuiz(Idquiz);
    }

    @PostMapping("/add-quiz")
    public quiz addQuiz(@RequestBody quiz e) {
        quiz quiz = quizService.addQuiz(e);
        return quiz;
    }

    @PutMapping("/update-quiz")
    public quiz updateQuiz(@RequestBody quiz e) {
        quiz quiz = quizService.updateQuiz(e);
        return quiz;
    }

    @DeleteMapping("/delete-quiz/{quiz-id}")
    public String deleteQuiz(@PathVariable("quiz-id") Integer Idquiz) {
        quizService.deleteQuiz(Idquiz);
        return "quiz with ID " + Idquiz + " has been deleted successfully";
    }


}
