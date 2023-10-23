package com.example.eventmanagement.Controllers;


import com.example.eventmanagement.Entities.Event;
import com.example.eventmanagement.Entities.Ressources;
import com.example.eventmanagement.Services.RessourcesServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RessourcesController {
    @Autowired
    RessourcesServiceImp ressourcesServiceImp;

    @PostMapping("/add-Ress")
    public void ajouterRessource(@RequestBody Ressources ressources) {
        ressourcesServiceImp.addRessource(ressources);
    }


    @DeleteMapping("/deleteRes/{idRs}")
    public void deleteRess(@PathVariable("idRs") Integer idRs) {
        ressourcesServiceImp.deleteRessource(idRs);
    }

    @PutMapping("/updateRes/{idRs}")
    public void updateRes(@PathVariable("idRs") Integer idRs, @RequestBody Ressources ressources) {
        ressourcesServiceImp.updateRessource(idRs, ressources);
    }
    @GetMapping("/getAllRessources")
    public List<Ressources> getAlldata() {
        return ressourcesServiceImp.listAllRessources();
    }



}
