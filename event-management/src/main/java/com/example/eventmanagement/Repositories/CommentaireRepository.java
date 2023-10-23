package com.example.eventmanagement.Repositories;

import com.example.eventmanagement.Entities.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {
}
