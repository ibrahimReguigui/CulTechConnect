package com.example.web.quiz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.util.List;

@Entity
@Table( name = "Quiz")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idQuiz;
    private String titre;
    private String description;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<question> questions;
}
