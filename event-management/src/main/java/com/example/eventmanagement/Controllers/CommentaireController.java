package com.example.eventmanagement.Controllers;


import com.example.eventmanagement.Entities.Commentaire;
import com.example.eventmanagement.Entities.Event;
import com.example.eventmanagement.Services.CommentaireServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentaireController {

    @Autowired
    CommentaireServiceImpl commentaireService;

    @PostMapping("/add-Comment")
    public void ajouterComment(@RequestBody Commentaire comment) {
        commentaireService.addCommentaire(comment);
    }

    @DeleteMapping("/deleteComment/{idComm}")
    public void deleteComment(@PathVariable("idComm") Integer idComm) {
        commentaireService.deleteCommentaire(idComm);
    }


    @GetMapping("/getAllComments")
    public List<Commentaire> getAllcomments() {
        return commentaireService.lisAllCommentaires();
    }
}
