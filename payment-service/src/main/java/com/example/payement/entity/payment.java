package com.example.payement.entity;


import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicUpdate
@ToString
public class payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id_payment;
    private String name_on_Card;
    private String card_Number;
    private String mm;
    private String yy;
    private String cvv;
    private float montant;
    //relation enum with country
    @Enumerated(EnumType.STRING)
    private Country country;
    //relation enum with type
    @Enumerated(EnumType.STRING)
    private StatusType statusType;
    private Long user;
}
