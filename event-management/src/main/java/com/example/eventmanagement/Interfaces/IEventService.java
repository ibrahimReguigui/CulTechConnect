package com.example.eventmanagement.Interfaces;

import com.example.eventmanagement.Entities.Event;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEventService {
    Event addEvent(Event e);
    void deleteEvent(Integer id);
    void updateEvent(Integer idEvent,Event event);
    List<Event> listAllEvents();
    Event getEventDetails(Integer eventId);
    void affecterRessourceAEvent(int idRs, int idEvent);

    //String uploadImageToFileSystem(int idEvent, MultipartFile file);
}
