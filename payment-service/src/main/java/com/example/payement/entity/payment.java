package com.example.payement.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

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
    private String Name_on_Card;
    private String Card_Number;
    private String MM;
    private String YY;
    private String CVV;
    //relation enum with country
    @Enumerated(EnumType.STRING)
    private Country Country;
    //relation enum with type
    @Enumerated(EnumType.STRING)
    private StatusType StatusType;
    private Long user;
}
