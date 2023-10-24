package com.example.echange.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user;
    @Transient
    private LocalDateTime currentDate;
    private Status statut;
    private String message ;
    @ManyToOne
    @JoinColumn(name = "echange_id")
    private Echange echange;;




    public LocalDateTime getCurrentDate() {
        return LocalDateTime.now(); // Obtient la date et l'heure actuelles
    }


}
