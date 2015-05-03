package org.diosoft.anakin.datastore;

import org.diosoft.anakin.model.Event;

import java.util.*;

public class MapDataStore implements DataStore {

    private Map<UUID, Event> storage;

    public MapDataStore() {
        this.storage  = new HashMap<UUID, Event>();
    }

    @Override
    public void addEvent(Event event) {
        storage.put(event.getId(), event);
    }

    @Override
    public List<Event> searchByTitle(String title) {
        List<Event> list = new ArrayList<Event>();
        for(Map.Entry<UUID, Event> entry : storage.entrySet()) {
            if (entry.getValue().getTitle().equalsIgnoreCase(title)) {
                list.add(entry.getValue());
            }
        }
        return list;
    }

    @Override
    public List<Event> getAllEvents() {
        return new ArrayList<Event>(storage.values());
    }

    @Override
    public Event getEventById(UUID id) {
        return storage.get(id);
    }

    void addEventForTest(Event event) {
        storage.put(event.getId(), event);
    }

    Event getEventForTest(UUID id) {
        return storage.get(id);
    }
}
