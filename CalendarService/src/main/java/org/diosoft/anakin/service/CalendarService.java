package org.diosoft.anakin.service;

import org.diosoft.anakin.exception.NullCalendarException;
import org.diosoft.anakin.model.Event;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public interface CalendarService extends Remote {

    void addEvent(String title, String description, String start, String end, String[] attendees)
            throws RemoteException, ParseException;

    void addEvent(String title, String description, String start, String end)
            throws RemoteException, ParseException;

    void addEvent(String title, String start, String end) throws RemoteException, ParseException;

    void addEvent(String path) throws RemoteException, ParseException;

    void addEvent(Event event) throws RemoteException;

    void addEventAllDay(String title, String description, String date, String[] attendees)
            throws RemoteException, ParseException;

    void setDescription(Event event, String description) throws RemoteException;

    void setAttendees(Event event, String[] attendees) throws RemoteException;

    List<Event> getEventByTitle(String title) throws RemoteException;

    List<Event> getAllEvents() throws RemoteException;

    Event getEventById(UUID id) throws RemoteException;

    List<Event> getEventByTimeAndAttendee(String attendee, String date) throws RemoteException, ParseException;

    boolean isAttendeeFreeAtTheTime(String attendee, String date) throws RemoteException, ParseException;

    GregorianCalendar findTimeForEvent(String date, int durationMinutes) throws RemoteException, ParseException, NullCalendarException;



}
