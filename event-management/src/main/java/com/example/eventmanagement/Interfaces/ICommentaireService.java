package com.example.eventmanagement.Interfaces;

import com.example.eventmanagement.Entities.Commentaire;
import com.example.eventmanagement.Entities.Event;

import java.util.List;

public interface ICommentaireService {
    void addCommentaire(Commentaire c);
    void deleteCommentaire(Integer id);
    void updateCommentaire(Integer idComm,Commentaire commentaire);
    List<Commentaire> lisAllCommentaires();
}
