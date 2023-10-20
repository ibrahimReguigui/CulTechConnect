package com.example.eventmanagement.Controllers;


import com.example.eventmanagement.Entities.Event;
import com.example.eventmanagement.Services.EventServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {
    @Autowired
    EventServiceImp eventServiceImp;

    @PostMapping("/add-Event")
    public void ajouterEvent(@RequestBody Event event) {
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


}
