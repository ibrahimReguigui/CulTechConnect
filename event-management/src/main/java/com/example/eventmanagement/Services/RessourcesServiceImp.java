package com.example.eventmanagement.Services;


import com.example.eventmanagement.Entities.Event;
import com.example.eventmanagement.Entities.Ressources;
import com.example.eventmanagement.Interfaces.IRessourcesService;
import com.example.eventmanagement.Repositories.EventRepository;
import com.example.eventmanagement.Repositories.RessourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RessourcesServiceImp implements IRessourcesService {

    @Autowired
    RessourcesRepository ressourcesRepository;

    @Autowired
    EventRepository eventRepository;
    @Override
    public void addRessource(Ressources r) {
        ressourcesRepository.save(r);

    }

    @Override
    public void deleteRessource(Integer id) {
        ressourcesRepository.deleteById(id);
    }

    @Override
    public void updateRessource(Integer idRs, Ressources ressources) {
        Optional<Ressources> re = ressourcesRepository.findById(idRs);
        if (re.isPresent()) {
            Ressources res = re.get();
            res.setDescriptionRs(ressources.getDescriptionRs());
            res.setNomRs(ressources.getNomRs());

            res.setTypeRessources(ressources.getTypeRessources());
            ressourcesRepository.save(res);
        } else {
            System.out.println("ressource not found!");
        }
    }

    @Override
    public List<Ressources> listAllRessources() {
        List<Ressources> ressources = ressourcesRepository.findAll();
        return ressources;
    }

    @Override
    public Ressources getRessourceById(Integer id) {
        return ressourcesRepository.findById(id).orElse(null);
    }


}
