package com.example.web.quiz.controller;

import com.example.web.quiz.entities.reponse;
import com.example.web.quiz.services.reponseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/reponse")

public class reponseController {
    @Autowired
    reponseService reponseService;

    @PostMapping("/addreponse")
    public ResponseEntity<String> submitResponse(@RequestBody reponse response) {

        reponseService.saveReponse(response);
        return ResponseEntity.ok("Response submitted successfully");
    }

}
