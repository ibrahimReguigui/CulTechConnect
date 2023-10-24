package com.example.eventmanagement.Repositories;

import com.example.eventmanagement.Entities.Ressources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RessourcesRepository extends JpaRepository<Ressources, Integer> {
}
