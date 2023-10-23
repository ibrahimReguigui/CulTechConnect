package com.example.eventmanagement.Controllers;

import com.example.eventmanagement.Entities.Event;
import com.example.eventmanagement.Repositories.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "*")
public class FileRessource {

    private final EventRepository eventRepository;

    public FileRessource(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles,
                                         @RequestParam("nom") String nom,
                                         @RequestParam("lieu") String lieu,
                                         @RequestParam("description") String description,
                                         @RequestParam("num") String num,
                                         @RequestParam("prix") String prix,
                                         @RequestParam("nomprop") String nomprop) {

        try {

            List<String> filenames = new ArrayList<>();
            for (MultipartFile file : multipartFiles) {
                String filename = StringUtils.cleanPath(file.getOriginalFilename());
                Event event = new Event();
                event.setNom(nom);
                event.setDescription(description);
                event.setLieu(lieu);
                event.setNumTel(num);
                event.setPrixTicket(prix);
                event.setNomProprietaire(nomprop);
                event.setImage(file.getBytes());
                eventRepository.save(event);
                filenames.add(filename);
            }

            return ResponseEntity.ok().body(filenames);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du traitement des fichiers.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite : " + ex.getMessage());
        }
    }
}
