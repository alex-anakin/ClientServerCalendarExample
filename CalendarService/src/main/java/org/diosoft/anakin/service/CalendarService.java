package org.diosoft.anakin.service;

import org.diosoft.anakin.model.Event;

import java.text.ParseException;
import java.util.List;

public interface CalendarService {

    Event createEvent(String title, String description, String start, String end, String[] attenders)
            throws ParseException;

    Event createEvent(String title, String description, String start, String end) throws ParseException;

    Event createEvent(String title, String start, String end) throws ParseException;

    Event createEvent(String path) throws ParseException;

    Event setDescription(String description);

    Event setAttenders(String[] attenders);

    void addEvent(Event event);

    List<Event> searchByTitle(String title);

    List<Event> getAllEvent();

}
