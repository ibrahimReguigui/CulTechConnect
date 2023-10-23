package com.exemple.web.reclamation.services;

import com.exemple.web.reclamation.entities.reclamation;

import java.util.List;

public interface ReclamationInterface {
    List<reclamation> retrieveAllReclamations();

    reclamation addReclamation(reclamation e);

    reclamation updateReclamation(reclamation e);

    reclamation retrieveReclamation(Integer idReclamation);

    void deleteReclamation (Integer idReclamation);
}
