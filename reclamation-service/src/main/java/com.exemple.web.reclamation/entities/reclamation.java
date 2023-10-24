package com.exemple.web.reclamation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table( name = "Reclamation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class reclamation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idReclamation")
    private Integer idReclamation;
    private String Name ;
    private String details ;

    private String Status  ;


}
