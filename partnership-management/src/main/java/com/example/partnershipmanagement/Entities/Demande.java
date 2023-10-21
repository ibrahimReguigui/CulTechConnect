package com.example.partnershipmanagement.Entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Demande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idDemande;
    String objet;
    String description;
    Date dateDemande;
    Boolean etat;
    @Enumerated(EnumType.STRING)
    TypePartenariat typePartenariat;


}
