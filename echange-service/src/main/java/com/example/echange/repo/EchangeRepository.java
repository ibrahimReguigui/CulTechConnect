package com.example.echange.repo;

import com.example.echange.model.Echange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EchangeRepository extends JpaRepository<Echange, Long> {
}