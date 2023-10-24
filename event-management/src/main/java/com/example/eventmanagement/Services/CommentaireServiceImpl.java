package com.example.eventmanagement.Services;


import com.example.eventmanagement.Entities.Commentaire;
import com.example.eventmanagement.Entities.Event;
import com.example.eventmanagement.Interfaces.ICommentaireService;
import com.example.eventmanagement.Repositories.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentaireServiceImpl implements ICommentaireService {

    @Autowired
    CommentaireRepository commentaireRepository;
    @Override
    public void addCommentaire(Commentaire c) {
        commentaireRepository.save(c);

    }

    @Override
    public void deleteCommentaire(Integer id) {
        commentaireRepository.deleteById(id);

    }

    @Override
    public void updateCommentaire(Integer idComm, Commentaire commentaire) {

    }

    @Override
    public List<Commentaire> lisAllCommentaires() {
        List<Commentaire> commentaires = commentaireRepository.findAll();
        return commentaires;
    }
}
