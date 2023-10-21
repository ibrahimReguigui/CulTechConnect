package com.example.eventmanagement.Interfaces;

import com.example.eventmanagement.Entities.Event;
import com.example.eventmanagement.Entities.Ressources;

import java.util.List;

public interface IRessourcesService {
    void addRessource(Ressources r);
    void deleteRessource(Integer id);
    void updateRessource(Integer idRs,Ressources ressources);
    List<Ressources> listAllRessources();

    Ressources getRessourceById(Integer id);

}
