package com.example.eventmanagement.payload;

import com.example.eventmanagement.Entities.Commentaire;
import com.example.eventmanagement.Entities.Ressources;
import com.example.eventmanagement.Entities.TypeEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    Integer idEvent;
    String nom;
    String description;
    String lieu;
    Date dateDeb;
    Date dateFin;
    String image;
    MultipartFile img;
    @Enumerated(EnumType.STRING)
    TypeEvent type;

}
