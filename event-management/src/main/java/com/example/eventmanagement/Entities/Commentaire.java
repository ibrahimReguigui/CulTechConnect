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
public class Commentaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idComm;
    String contenu;
}
