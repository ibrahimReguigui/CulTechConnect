package com.example.eventmanagement.Services;


import com.example.eventmanagement.Entities.Event;
import com.example.eventmanagement.Interfaces.IEventService;
import com.example.eventmanagement.Repositories.EventRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventServiceImp implements IEventService {
    @Autowired
    EventRepository eventRepository;

    @Override
    public void addEvent(Event e) {
        eventRepository.save(e);
    }

    @Override
    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);

    }

    @Override
    public void updateEvent(Integer idEvent, Event event) {
        Optional<Event> ev = eventRepository.findById(idEvent);
        if (ev.isPresent()) {
            Event _event = ev.get();
            _event.setDescription(event.getDescription());
            eventRepository.save(_event);
        } else {
            System.out.println("event not found!");
        }
    }

    @Override
    public List<Event> listAllEvents() {
        List<Event> event = eventRepository.findAll();
        return event;
    }
}
