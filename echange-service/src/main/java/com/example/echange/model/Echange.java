package com.example.echange.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Echange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;

    private Date dateDebut;
    private Date dateFin;

    private int nbparticipant;

    @Enumerated(EnumType.STRING)
    private Pays pays;












}
