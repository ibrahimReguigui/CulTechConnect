package com.example.eventmanagement.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;


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


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ressources")
    private Set<Event> Event;

    public Ressources(Integer idRs) {
        this.idRs = idRs;
    }
}

