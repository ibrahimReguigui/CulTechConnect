package com.example.eventmanagement.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Ressources implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idRs;
    String nomRs;
    String descriptionRs;
    @Enumerated(EnumType.STRING)
    TypeRessources typeRessources;
    @ManyToOne
    Event event;
}

