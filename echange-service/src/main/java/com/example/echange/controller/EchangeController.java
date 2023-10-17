package com.example.echange.controller;

import com.example.echange.model.Echange;
import com.example.echange.service.EchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/echanges")
@CrossOrigin(origins = "http://localhost:59297/")

public class EchangeController {
    private final EchangeService echangeService;

    @Autowired
    public EchangeController(EchangeService echangeService) {
        this.echangeService = echangeService;
    }

    @PostMapping
    public Echange createEchange(@RequestBody Echange echange) {
        return echangeService.createEchange(echange);
    }

    @GetMapping
    public List<Echange> getAllEchanges() {
        return echangeService.getAllEchanges();
    }

    @GetMapping("/{id}")
    public Echange getEchangeById(@PathVariable Long id) {
        return echangeService.getEchangeById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Echange updateEchange(@PathVariable Long id, @RequestBody Echange echange) {
        echange.setId(id);
        return echangeService.updateEchange(echange);
    }

    @DeleteMapping("/{id}")
    public void deleteEchange(@PathVariable Long id) {
        echangeService.deleteEchange(id);
    }
    @PutMapping("/{id}/nbparticipants")
    public Echange updateNbParticipants(@PathVariable Long id, @RequestParam int nbParticipants) {
        return echangeService.updateEchangeWithNbParticipants(id, nbParticipants);
    }
    @PostMapping("/participate/{id}")
    public ResponseEntity<Echange> participateInEchange(@PathVariable Long id) {
        Echange updatedEchange = echangeService.participateInEchange(id);
        return ResponseEntity.ok(updatedEchange);
    }
}
