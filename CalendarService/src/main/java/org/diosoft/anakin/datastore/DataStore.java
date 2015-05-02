package org.diosoft.anakin.datastore;

import org.diosoft.anakin.model.Event;

import java.util.List;

public interface DataStore {

    void addEvent(Event event);

    List<Event> searchByTitle(String title);

    List<Event> getAllEvent();

}
