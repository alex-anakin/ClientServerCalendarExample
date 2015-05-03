package org.diosoft.anakin.datastore;

import org.diosoft.anakin.model.Event;

import java.util.List;
import java.util.UUID;

public interface DataStore {

    void addEvent(Event event);

    List<Event> searchByTitle(String title);

    List<Event> getAllEvents();

    Event getEventById(UUID id);

}
