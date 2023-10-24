package com.example.web.quiz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "Resultat")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResult;

    private int score;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private quiz quiz;

}
