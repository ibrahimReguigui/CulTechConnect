package com.exemple.web.reclamation.controller;

import com.exemple.web.reclamation.entities.reclamation;
import com.exemple.web.reclamation.services.ReclamationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Slf4j
@CrossOrigin(origins = "*")
/*@CrossOrigin(origins = "http://your-frontend-app.com")  */
@RestController
@AllArgsConstructor
@RequestMapping("/Reclamation")

public class reclamationController {
    ReclamationService reclamationService;

    @GetMapping("/retrieve-all-reclamations")
    public List<reclamation> getReclamations() {
        List<reclamation> listReclamations = reclamationService.retrieveAllReclamations();
        return listReclamations;
    }

    @GetMapping("/retrieve-reclamation/{reclamation-id}")
    public reclamation retrieveReclamation(@PathVariable("reclamation-id") Integer Idreclamation) {
        return reclamationService.retrieveReclamation(Idreclamation);
    }

    @PostMapping("/add-reclamation")
    public reclamation addReclamation (@RequestBody reclamation e) {
        reclamation reclamation = reclamationService.addReclamation(e);
        return reclamation;
    }

    @PutMapping("/update-reclamation")
    public reclamation updateReclamation(@RequestBody reclamation e) {
        reclamation reclamation = reclamationService.updateReclamation(e);
        return reclamation;
    }

    @DeleteMapping("/delete-reclamation/{reclamation-id}")
    public String deleteReclamation(@PathVariable("reclamation-id") Integer Idreclamation) {
        reclamationService.deleteReclamation(Idreclamation);
        return "Reclamation with ID " + Idreclamation + " has been deleted successfully";
    }


}
