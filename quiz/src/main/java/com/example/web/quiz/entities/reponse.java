package com.example.web.quiz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "Reponse")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReponse;
    private String texte;
    private boolean answer;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private question question;
}
