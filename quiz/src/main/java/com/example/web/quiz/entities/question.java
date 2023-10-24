package com.example.web.quiz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table( name = "Question")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idQuestion;
    private String texte;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private quiz quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<reponse> reponses;
}
