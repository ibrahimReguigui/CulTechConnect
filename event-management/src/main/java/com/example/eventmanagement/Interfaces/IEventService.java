package com.example.eventmanagement.Interfaces;

import com.example.eventmanagement.Entities.Event;

import java.util.List;

public interface IEventService {
    void addEvent(Event e);
    void deleteEvent(Integer id);
    void updateEvent(Integer idEvent,Event event);
    List<Event> listAllEvents();
    Event getEventDetails(Integer eventId);
}
