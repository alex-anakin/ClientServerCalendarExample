package org.diosoft.anakin.service;

import org.diosoft.anakin.datastore.DataStore;
import org.diosoft.anakin.model.Event;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public class MapCalendarService implements CalendarService {

    private final DataStore dataStore;

    public MapCalendarService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void addEvent(String title, String description, String start, String end, String[] attenders)
            throws ParseException {

        dataStore.addEvent(new Event.Builder()
                .id(makeId())
                .title(title)
                .description(description)
                .startDate(parseDate(start))
                .endDate(parseDate(end))
                .attenders(Arrays.asList(attenders))
                .build());
    }

    @Override
    public void addEvent(String title, String description, String start, String end) throws ParseException {
        String[] attenders = {};
        addEvent(title, description, start, end, attenders);
    }

    @Override
    public void addEvent(String title, String start, String end) throws ParseException {
        String[] attenders = {};
        String description = "";
        addEvent(title, description, start, end, attenders);
    }

    @Override
    public void addEvent(String path) throws ParseException {
        String[] data = readDataFromFile(path).split(", ");
        String[] attenders = Arrays.copyOfRange(data, 4, data.length);
        addEvent(data[0], data[1], data[2], data[3], attenders);
    }

    @Override
    public void addEvent(Event event) {
        dataStore.addEvent(event);
    }

    @Override
    public List<Event> searchByTitle(String title) {
        return dataStore.searchByTitle(title);
    }

    @Override
    public List<Event> getAllEvents() {
        return dataStore.getAllEvents();
    }

    @Override
    public Event getEventById(UUID id) throws RemoteException {
        return dataStore.getEventById(id);
    }

    @Override
    public Event setDescription(Event event, String description) {
        return null;
    }

    @Override
    public Event setAttenders(Event event, String[] attenders) {
        return null;
    }

    public UUID makeId() {
        return UUID.randomUUID();
    }

    public String readDataFromFile(String path) {
        URL resourceUrl = getClass().getResource(path);
        byte[] content = {};
        try {
            content = Files.readAllBytes(Paths.get(resourceUrl.toURI()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(content);
    }

    public GregorianCalendar parseDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(sdf.parse(date));
        return calendar;
    }
}
