package com.exemple.web.reclamation.services;

import com.exemple.web.reclamation.entities.reclamation;
import com.exemple.web.reclamation.repository.ReclamationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor

public class ReclamationService implements ReclamationInterface {
    @Autowired
    ReclamationRepository reclamationRepository;

    @Override
    public List<reclamation> retrieveAllReclamations() {
        Iterable<reclamation> iterable = reclamationRepository.findAll();
        List<reclamation> reclamationList = new ArrayList<>();
        iterable.forEach(reclamationList::add);
        return reclamationList;
    }
    @Override
    public reclamation addReclamation(reclamation e) {
        return reclamationRepository.save(e);
    }

    @Override
    public reclamation updateReclamation(reclamation e) {
        return reclamationRepository.save(e);
    }

    @Override
    public reclamation retrieveReclamation(Integer idReclamation) {
        return reclamationRepository.findById(idReclamation).get();
    }

    @Override
    public void deleteReclamation (Integer idReclamation) {
        reclamationRepository.deleteById(idReclamation);
    }
}
