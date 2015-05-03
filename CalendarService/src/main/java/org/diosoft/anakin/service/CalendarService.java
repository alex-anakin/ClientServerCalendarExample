package org.diosoft.anakin.service;

import org.diosoft.anakin.model.Event;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

public interface CalendarService extends Remote {

    void addEvent(String title, String description, String start, String end, String[] attenders)
            throws RemoteException, ParseException;

    void addEvent(String title, String description, String start, String end)
            throws RemoteException, ParseException;

    void addEvent(String title, String start, String end) throws RemoteException, ParseException;

    void addEvent(String path) throws RemoteException, ParseException;

    void addEvent(Event event) throws RemoteException;

    Event setDescription(Event event, String description) throws RemoteException;

    Event setAttenders(Event event, String[] attenders) throws RemoteException;

    List<Event> searchByTitle(String title) throws RemoteException;

    List<Event> getAllEvents() throws RemoteException;

    Event getEventById(UUID id) throws RemoteException;

}
