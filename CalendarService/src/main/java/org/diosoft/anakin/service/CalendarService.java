package org.diosoft.anakin.service;

import org.diosoft.anakin.model.Event;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;

public interface CalendarService extends Remote {

    Event createEvent(String title, String description, String start, String end, String[] attenders)
            throws RemoteException, ParseException;

    Event createEvent(String title, String description, String start, String end)
            throws RemoteException, ParseException;

    Event createEvent(String title, String start, String end) throws RemoteException, ParseException;

    Event createEvent(String path) throws RemoteException, ParseException;

    Event setDescription(String description) throws RemoteException;

    Event setAttenders(String[] attenders) throws RemoteException;

    void addEvent(Event event) throws RemoteException;

    List<Event> searchByTitle(String title) throws RemoteException;

    List<Event> getAllEvent() throws RemoteException;

}
