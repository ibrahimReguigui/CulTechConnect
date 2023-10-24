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
    @Lob
    @Column(length = 1048576)
    private byte[] image;
    String numTel;
    String prixTicket;
    String nomProprietaire;

    @Enumerated(EnumType.STRING)
    TypeEvent type;

    @ManyToOne
    Ressources ressources;


    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return this.image;
    }


}
