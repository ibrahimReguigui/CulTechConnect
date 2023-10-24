package com.exemple.web.reclamation.repository;

import com.exemple.web.reclamation.entities.reclamation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamationRepository extends CrudRepository<reclamation,Integer> {
}
