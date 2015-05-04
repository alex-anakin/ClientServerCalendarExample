package org.diosoft.anakin.service;

import org.diosoft.anakin.datastore.DataStore;
import org.diosoft.anakin.exception.NullCalendarException;
import org.diosoft.anakin.model.Event;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MapCalendarService implements CalendarService {

    private final DataStore dataStore;

    public MapCalendarService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void addEvent(String title, String description, String start, String end, String[] attendees)
            throws ParseException {

        dataStore.addEvent(new Event.Builder()
                .id(makeId())
                .title(title)
                .description(description)
                .startDate(parseDate(start))
                .endDate(parseDate(end))
                .attendees(Arrays.asList(attendees))
                .build());
    }

    @Override
    public void addEvent(String title, String description, String start, String end) throws ParseException {
        String[] attendees = {};
        addEvent(title, description, start, end, attendees);
    }

    @Override
    public void addEvent(String title, String start, String end) throws ParseException {
        String[] attendees = {};
        String description = "";
        addEvent(title, description, start, end, attendees);
    }

    @Override
    public void addEvent(String path) throws ParseException {
        String[] data = readDataFromFile(path).split(", ");
        String[] attendees = Arrays.copyOfRange(data, 4, data.length);
        addEvent(data[0], data[1], data[2], data[3], attendees);
    }

    @Override
    public void addEvent(Event event) {
        dataStore.addEvent(event);
    }

    @Override
    public void addEventAllDay(String title, String description, String date, String[] attendees)
            throws RemoteException, ParseException {

        GregorianCalendar calendar = parseDate(date);

        //work time is between 10:00 and 19:00
        GregorianCalendar start = new GregorianCalendar(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                10, 0);

        GregorianCalendar end = new GregorianCalendar(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                19, 0);

        dataStore.addEvent(new Event.Builder()
                .id(makeId())
                .title(title)
                .description(description)
                .startDate(start)
                .endDate(end)
                .attendees(Arrays.asList(attendees))
                .build());
    }

    @Override
    public List<Event> getEventByTitle(String title) {
        return dataStore.getEventByTitle(title);
    }

    @Override
    public List<Event> getAllEvents() {
        return dataStore.getAllEvents();
    }

    @Override
    public Event getEventById(UUID id) throws RemoteException {
        return dataStore.getEvent(id);
    }

    @Override
    public void setDescription(Event event, String description) {
        Event tempEvent = dataStore.removeEvent(event.getId());
        dataStore.addEvent(new Event.Builder(tempEvent)
                .description(description)
                .build());
    }

    @Override
    public void setAttendees(Event event, String[] attendees) {
        Event tempEvent = dataStore.removeEvent(event.getId());
        dataStore.addEvent(new Event.Builder(tempEvent)
                .attendees(Arrays.asList(attendees))
                .build());
    }

    @Override
    public List<Event> getEventByTimeAndAttendee(String attendee, String date) throws RemoteException, ParseException {
        List<Event> matchedEvents = new ArrayList<Event>();
        List<Event> allEvents = dataStore.getAllEvents();
        GregorianCalendar checkingDate = parseDate(date);

        for(Event event : allEvents) {
            if (checkingDate.compareTo(event.getStartDate()) > 0 &&
                    checkingDate.compareTo(event.getEndDate()) < 0) {
                if (event.getAttendees().contains(attendee)) {
                    matchedEvents.add(event);
                }
            }
        }
        return matchedEvents;
    }

    @Override
    public boolean isAttendeeFreeAtTheTime(String attendee, String date) throws RemoteException, ParseException {
        boolean isFree = true;
        List<Event> allEvents = dataStore.getAllEvents();
        GregorianCalendar checkingDate = parseDate(date);

        for(Event event : allEvents) {
            if (event.getAttendees().contains(attendee)) {
                isFree = checkingDate.compareTo(event.getStartDate()) < 0 &&
                            checkingDate.compareTo(event.getEndDate()) > 0;
            }
        }
        return isFree;
    }

    @Override
    public GregorianCalendar findTimeForEvent(String date, int durationMinutes)
            throws ParseException, NullCalendarException {

        // method finds start time of first free period in the day

        GregorianCalendar calendarDate = parseDate(date);

        System.out.println(calendarDate.getTime());

        //work time is between 10:00 and 19:00
        GregorianCalendar dayStart = new GregorianCalendar(
                calendarDate.get(Calendar.YEAR),
                calendarDate.get(Calendar.MONTH),
                calendarDate.get(Calendar.DAY_OF_MONTH),
                10, 0);

        GregorianCalendar dayEnd = new GregorianCalendar(
                calendarDate.get(Calendar.YEAR),
                calendarDate.get(Calendar.MONTH),
                calendarDate.get(Calendar.DAY_OF_MONTH),
                19, 0);

        GregorianCalendar badDate = new GregorianCalendar(0, 0, 0);
        GregorianCalendar targetDateTime = new GregorianCalendar(0, 0, 0);;

        // set begin of Event at begin of the day
        long startTimeInMillis = dayStart.getTimeInMillis();
        long dayEndTimeInMillis = dayEnd.getTimeInMillis();
        long durationMillis = durationMinutes * 60 * 1000;

        List<Event> eventsOfDay = dataStore.getEventsByDay(calendarDate);
        Map<GregorianCalendar, GregorianCalendar> businessPeriods =
                new TreeMap<GregorianCalendar, GregorianCalendar>();

        for(Event event : eventsOfDay) {
            businessPeriods.put(event.getStartDate(), event.getEndDate());
        }

        if (eventsOfDay.isEmpty()) {
            targetDateTime = dayStart;
        }
        else {
            for(Map.Entry<GregorianCalendar, GregorianCalendar> entry : businessPeriods.entrySet()) {

                if(startTimeInMillis + durationMillis <= entry.getKey().getTimeInMillis()) {
                    targetDateTime.setTimeInMillis(startTimeInMillis);
                    break;
                }
                else {
                    startTimeInMillis = entry.getValue().getTimeInMillis();
                }
            }
            // check free period after last even of the day
            if(targetDateTime.equals(badDate) && startTimeInMillis + durationMillis < dayEndTimeInMillis) {
                targetDateTime.setTimeInMillis(startTimeInMillis);
            }
        }
        if (targetDateTime.equals(badDate)) {
            throw new NullCalendarException();
        }
        else {
            return targetDateTime;
        }
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
        final int NUMBER_OF_SYMBOL_IN_DATE_WITHOUT_TIME = 10;
        SimpleDateFormat sdf;
        if (date.length() > NUMBER_OF_SYMBOL_IN_DATE_WITHOUT_TIME) {
            sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        }
        else {
            sdf = new SimpleDateFormat("dd.MM.yyyy");
        }
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(sdf.parse(date));
        return calendar;
    }

    // internal needs method
    private void printCalendarMap(Map<GregorianCalendar, GregorianCalendar> map) {
        for (Map.Entry<GregorianCalendar, GregorianCalendar> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey().getTime()
                    + " Value : " + entry.getValue().getTime());
        }
    }
}
