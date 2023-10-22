package com.example.echange.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.time.LocalDateTime;
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
    @Transient
    private LocalDateTime currentDate;

    private int nbparticipant;

    @Enumerated(EnumType.STRING)
    private Pays pays;
    private String user;

    @OneToMany(mappedBy = "echange", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Participant> participants;
    public LocalDateTime getCurrentDate() {
        return LocalDateTime.now(); // Obtient la date et l'heure actuelles
    }







}
