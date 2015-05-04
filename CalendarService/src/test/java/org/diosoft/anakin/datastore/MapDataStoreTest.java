package org.diosoft.anakin.datastore;

import org.diosoft.anakin.model.Event;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;

public class MapDataStoreTest {

    private MapDataStore testClass = new MapDataStore();
    private Event expectedValue;

    @Before
    public void setUp() {

        String title = "Test event";
        String description = "Test description";
        UUID id = UUID.randomUUID();
        GregorianCalendar startDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 10, 0);
        GregorianCalendar endDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 12, 30);
        String[] attendees = {"test@gmail.com", "mail@ukr.net"};
        expectedValue = new Event.Builder()
                .title(title)
                .description(description)
                .id(id)
                .startDate(startDate)
                .endDate(endDate)
                .attendees(Arrays.asList(attendees))
                .build();

        testClass.addEventForTest(expectedValue);
    }

    @Test
    public void testAddEvent() {
        String title = "Test event";
        String description = "Test description";
        UUID id = UUID.randomUUID();
        GregorianCalendar startDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 10, 0);
        GregorianCalendar endDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 12, 30);
        String[] attendees = {"test@gmail.com", "mail@ukr.net"};
        Event expectedValue = new Event.Builder()
                .title(title)
                .description(description)
                .id(id)
                .startDate(startDate)
                .endDate(endDate)
                .attendees(Arrays.asList(attendees))
                .build();

        testClass.addEvent(expectedValue);
        Event returnedValue = testClass.getEventForTest(expectedValue.getId());
        assertEquals(expectedValue, returnedValue);
    }

    @Test
    public void testSearchByTitle() {
        String title = "Test event";
        List<Event> events = testClass.getEventByTitle(title);
        Event returnedValue = events.get(0);
        assertEquals(expectedValue, returnedValue);
    }

    @Test
    public void testGetAllEvents() {
        String title = "Another test event";
        String description = "Another Test description";
        UUID id = UUID.randomUUID();
        GregorianCalendar startDate = new GregorianCalendar(2015, Calendar.JUNE, 30, 10, 0);
        GregorianCalendar endDate = new GregorianCalendar(2015, Calendar.JUNE, 30, 12, 30);
        String[] attendees = {"one@gmail.com", "mail@bigmir.net"};
        Event event = new Event.Builder()
                .title(title)
                .description(description)
                .id(id)
                .startDate(startDate)
                .endDate(endDate)
                .attendees(Arrays.asList(attendees))
                .build();
        // number of events in storage at the moment:
        int expectedValue = 2;

        testClass.addEventForTest(event);
        int returnedValue = testClass.getAllEvents().size();
        assertEquals(expectedValue, returnedValue);
    }

    @Test
    public void testGetEventById() {
        Event returnedValue = testClass.getEvent(expectedValue.getId());
        assertEquals(expectedValue, returnedValue);
    }
}