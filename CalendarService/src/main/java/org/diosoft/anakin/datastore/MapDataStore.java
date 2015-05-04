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
    public List<Event> getEventByTitle(String title) {
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
    public Event getEvent(UUID id) {
        return storage.get(id);
    }

    @Override
    public List<Event> getEventsByDay(GregorianCalendar date) {
        List<Event> list = new ArrayList<Event>();
        for(Map.Entry<UUID, Event> entry : storage.entrySet()) {
            GregorianCalendar testDate = entry.getValue().getStartDate();
            if (testDate.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
                    testDate.get(Calendar.MONTH) == date.get(Calendar.MONTH) &&
                    testDate.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)) {
                list.add(entry.getValue());
            }
        }
        return list;
    }

    @Override
    public Event removeEvent(UUID id) {
        Event event = storage.get(id);
        storage.remove(id);
        return event;
    }

    void addEventForTest(Event event) {
        storage.put(event.getId(), event);
    }

    Event getEventForTest(UUID id) {
        return storage.get(id);
    }
}
