package com.example.echange.service;

import com.example.echange.model.Echange;
import com.example.echange.repo.EchangeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EchangeService {
    private final EchangeRepository echangeRepository;

    @Autowired
    public EchangeService(EchangeRepository echangeRepository) {
        this.echangeRepository = echangeRepository;
    }

    // Create operation
    public Echange createEchange(Echange echange) {
        return echangeRepository.save(echange);
    }

    // Read operation
    public List<Echange> getAllEchanges() {
        return echangeRepository.findAll();
    }

    public Optional<Echange> getEchangeById(Long id) {
        return echangeRepository.findById(id);
    }

    // Update operation
    public Echange updateEchange(Echange echange) {
        return echangeRepository.save(echange);
    }

    // Delete operation
    public void deleteEchange(Long id) {
        echangeRepository.deleteById(id);
    }
    public Echange updateEchangeWithNbParticipants(Long id, int nbParticipants) {
        Optional<Echange> optionalEchange = echangeRepository.findById(id);
        if (optionalEchange.isPresent()) {
            Echange echange = optionalEchange.get();
            echange.setNbparticipant(nbParticipants);
            return echangeRepository.save(echange);
        } else {
            throw new EntityNotFoundException("Echange with ID " + id + " not found.");
        }
    }
    public Echange participateInEchange(Long id) {
        Optional<Echange> echangeOptional = echangeRepository.findById(id);

        if (echangeOptional.isPresent()) {
            Echange echange = echangeOptional.get();
            echange.setNbparticipant(echange.getNbparticipant() + 1);
            return echangeRepository.save(echange);
        } else {
            return null; // Handle not found case appropriately
        }
    }
}
