package org.diosoft.anakin.datastore;

import org.diosoft.anakin.model.Event;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public interface DataStore {

    void addEvent(Event event);

    List<Event> getEventByTitle(String title);

    List<Event> getAllEvents();

    List<Event> getEventsByDay(GregorianCalendar date);

    Event getEvent(UUID id);

    Event removeEvent(UUID id);

}
