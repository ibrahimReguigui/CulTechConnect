package com.example.eventmanagement.Controllers;


import com.example.eventmanagement.Entities.Event;
import com.example.eventmanagement.Entities.Ressources;
import com.example.eventmanagement.Services.EventServiceImp;
import com.example.eventmanagement.Services.RessourcesServiceImp;
import com.example.eventmanagement.payload.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EventController {
    @Autowired
    EventServiceImp eventServiceImp;
    @Autowired
    RessourcesServiceImp ressourcesServiceImp;

    @PostMapping("/add-Event")
    public void ajouterEvent(@RequestBody Event event, @RequestParam Integer idRs) {

        Ressources ressource = ressourcesServiceImp.getRessourceById(idRs);
event.setRessources(ressource);
        eventServiceImp.addEvent(event);
    }

    @DeleteMapping("/deleteEvent/{idEvent}")
    public void deleteEvent(@PathVariable("idEvent") Integer idEvent) {
        eventServiceImp.deleteEvent(idEvent);
    }


    @PutMapping("/updateEvent/{idEvent}")
    public void updateEvent(@PathVariable("idEvent") Integer idEvent, @RequestBody Event event) {
        eventServiceImp.updateEvent(idEvent, event);
    }
    @GetMapping("/getAllEvents")
    public List<Event> getAlldata() {
        return eventServiceImp.listAllEvents();
    }


    @GetMapping("/event-details/{eventId}")
    public ResponseEntity<Event> getEventDetails(@PathVariable Integer eventId) {
        Event event = eventServiceImp.getEventDetails(eventId);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/createEvent")
    public ResponseEntity<?> createEvent(@ModelAttribute EventDTO eventdto) throws IOException {
       Event event=new Event();
        event.setLieu(eventdto.getLieu());
       // event.setDateDeb(eventdto.getDateDeb());
       // event.setDateFin(eventdto.getDateFin());
        event.setDescription(eventdto.getDescription());
        event.setNom(eventdto.getNom());
       // event.setType(eventdto.getType());
        Event idEvent = eventServiceImp.addEvent(event);
       eventServiceImp.uploadImageToFileSystem(idEvent.getIdEvent(), eventdto.getImg());
         return ResponseEntity.status(HttpStatus.OK)
                .body(event);
    }


}
