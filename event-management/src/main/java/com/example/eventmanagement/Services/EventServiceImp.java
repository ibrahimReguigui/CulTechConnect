package com.example.eventmanagement.Services;


import com.example.eventmanagement.Entities.Event;
import com.example.eventmanagement.Entities.Ressources;
import com.example.eventmanagement.Interfaces.IEventService;
import com.example.eventmanagement.Repositories.EventRepository;
import com.example.eventmanagement.Repositories.RessourcesRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EventServiceImp implements IEventService {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    RessourcesRepository ressourcesRepository;

    private final String FOLDER_PATH = "D:/Projects/CultechConnect/CulTechConnectFront/CulTechConnectFrontOffice/src/assets/img/event/";

    @Override
    public Event addEvent(Event e) {

        return eventRepository.save(e);
    }

    @Override
    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);

    }

    @Override
    public void updateEvent(Integer idEvent, Event event) {
        Optional<Event> ev = eventRepository.findById(idEvent);
      //  if (ev.isPresent()) {
            Event _event = ev.get();
            _event.setDescription(event.getDescription());
        _event.setNom(event.getNom());
            eventRepository.save(_event);
      //  } else {
       //     System.out.println("event not found!");
        //}



    }

    @Override
    public List<Event> listAllEvents() {
        List<Event> event = eventRepository.findAll();
        return event;



    }


    @Override
    public Event getEventDetails(Integer eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        return event.orElse(null);
    }

    @Override
    public void affecterRessourceAEvent(int idRs, int idEvent) {
        Ressources ressource = ressourcesRepository.findById((int) idRs).get();
        Event event = eventRepository.findById((int) idEvent).get();

        event.setRessources(ressource);

         ressource.getEvent().add(event);

         eventRepository.save(event);
    }

    @Override
    public String uploadImageToFileSystem(int idEvent, MultipartFile file) {
        String fileName= file.getOriginalFilename();
        String randomID= UUID.randomUUID().toString();
        String fileName1= randomID.concat(fileName.substring(fileName.lastIndexOf(".")));
        String filePath = FOLDER_PATH +fileName1;
        Event e1 = eventRepository.findById(idEvent).get();
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (file != null) {
            e1.setImage(fileName1);
            eventRepository.save(e1);
            return "file uploaded successfully : " + filePath;
        }
        return null;

    }
}
