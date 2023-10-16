package com.example.eventmanagement.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idEvent;
    String nom;
    String description;
    String lieu;
    Date dateDeb;
    Date dateFin;

    @Enumerated(EnumType.STRING)
    TypeEvent type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Ressources> Ressourcess;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Commentaire> commentaires;




}
