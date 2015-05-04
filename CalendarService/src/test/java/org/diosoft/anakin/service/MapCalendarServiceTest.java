package org.diosoft.anakin.service;

import org.diosoft.anakin.datastore.DataStore;
import org.diosoft.anakin.datastore.MapDataStore;
import org.diosoft.anakin.model.Event;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MapCalendarServiceTest {

    @Test
    public void testReadDataFromFile() throws Exception {
        String path = "/data/event1.txt";
        String expectedValue = "Meeting, Self-education questions, 30.04.2015 11:30, " +
                "30.04.2015 13:30, ivan@gmail.com, artem@gmail.com, alex@gmail.com";
        MapCalendarService testClass = new MapCalendarService(new MapDataStore());

        String returnedValue = testClass.readDataFromFile(path);
        assertEquals("Read data from file is failed", expectedValue, returnedValue);
    }

    @Test
    public void testParseDate() throws Exception {
        String date = "01.05.2015 11:00";
        GregorianCalendar expectedValue = new GregorianCalendar(2015, Calendar.MAY, 1, 11, 0);
        MapCalendarService testClass = new MapCalendarService(new MapDataStore());

        GregorianCalendar returnedValue = testClass.parseDate(date);
        assertEquals(expectedValue, returnedValue);

    }

    @Test
    public void testParseDateException() throws Exception {
        String date = "any wrong text";
        MapCalendarService testClass = new MapCalendarService(new MapDataStore());
        String expectedValue = "Unparseable date: \"any wrong text\"";

        try {
            testClass.parseDate(date);
            fail("Exception should be thrown");
        } catch (Exception exception) {
            assertEquals(expectedValue, exception.getMessage());
        }
    }

    @Test
    public void testAddEventAllProperties() throws Exception {
        // initialize variable inputs
        String title = "Test event";
        String description = "Test description";
        GregorianCalendar startDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 10, 0);
        GregorianCalendar endDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 12, 30);
        String start = "30.04.2015 10:00";
        String end = "30.04.2015 12:30";
        String[] attenders = {"test@gmail.com", "mail@ukr.net"};

        // initialize mocks
        DataStore dataStore = mock(DataStore.class);

        // initialize class to test
        CalendarService service = new MapCalendarService(dataStore);

        // invoke method on class to test
        service.addEvent(title, description, start, end, attenders);

        // assert return value

        // verify mock expectations

        // Use "anyObject" because every new Event has unique UUID
        verify(dataStore).addEvent(Matchers.<Event>anyObject());
    }

    @Test
    public void testAddEventWithoutAttendees() throws Exception {
        // initialize variable inputs
        String title = "Test event";
        String description = "Test description";
        String start = "30.04.2015 10:00";
        String end = "30.04.2015 12:30";

        // initialize mocks
        DataStore dataStore = mock(DataStore.class);

        // initialize class to test
        CalendarService service = new MapCalendarService(dataStore);

        // invoke method on class to test
        service.addEvent(title, description, start, end);

        // assert return value

        // verify mock expectations
        verify(dataStore).addEvent(Matchers.<Event>anyObject());
    }

    @Test
    public void testAddEventWithoutAttendeesDescription() throws Exception {
        // initialize variable inputs
        String title = "Test event";
        String start = "30.04.2015 10:00";
        String end = "30.04.2015 12:30";

        // initialize mocks
        DataStore dataStore = mock(DataStore.class);

        // initialize class to test
        CalendarService service = new MapCalendarService(dataStore);

        // invoke method on class to test
        service.addEvent(title, start, end);

        // assert return value

        // verify mock expectations
        verify(dataStore).addEvent(Matchers.<Event>anyObject());
    }

    @Test
    public void testAddEventReturnedException() throws Exception {
        String title = "Test event";
        String description = "Test description";
        String start = "any wrong text";
        String end = "30.04.2015 12:30";
        String[] attenders = {"test@gmail.com", "mail@ukr.net"};
        String expectedValue = "Unparseable date: \"any wrong text\"";
        MapCalendarService testClass = new MapCalendarService(new MapDataStore());

        try {
            testClass.addEvent(title, description, start, end, attenders);
            fail("Exception should be thrown");
        } catch (Exception exception) {
            assertEquals(expectedValue, exception.getMessage());
        }
    }

    @Test
    public void testAddEventAsEvent() throws Exception {
        // initialize variable inputs
        String title = "Test event";
        String description = "Test description";
        GregorianCalendar startDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 10, 0);
        GregorianCalendar endDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 12, 30);
        String[] attenders = {"test@gmail.com", "mail@ukr.net"};

        Event expectedEvent = new Event.Builder()
                .title(title)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .attendees(Arrays.asList(attenders))
                .build();

        // initialize mocks
        DataStore dataStore = mock(DataStore.class);

        // initialize class to test
        CalendarService service = new MapCalendarService(dataStore);

        // invoke method on class to test
        service.addEvent(expectedEvent);

        // assert return value

        // verify mock expectations
        verify(dataStore).addEvent(expectedEvent);
    }

    @Test
    public void testSearchByTitle() throws Exception {
        // initialize variable inputs
        String title = "some title";
        List<Event> expectedValue = new ArrayList<Event>();

        // initialize mocks
        DataStore dataStore = mock(DataStore.class);
        when(dataStore.getEventByTitle(title)).thenReturn(expectedValue);

        // initialize class to test
        CalendarService service = new MapCalendarService(dataStore);

        // invoke method on class to test
        List<Event> returnedValue = service.getEventByTitle(title);

        // assert return value
        assertEquals(expectedValue, returnedValue);
    }

    @Test
    public void testGetAllEvents() throws Exception {
        // initialize variable inputs
        List<Event> expectedValue = new ArrayList<Event>();

        // initialize mocks
        DataStore dataStore = mock(DataStore.class);
        when(dataStore.getAllEvents()).thenReturn(expectedValue);

        // initialize class to test
        CalendarService service = new MapCalendarService(dataStore);

        // invoke method on class to test
        List<Event> returnedValue = service.getAllEvents();

        // assert return value
        assertEquals(expectedValue, returnedValue);
    }



    // initialize variable inputs

    // initialize mocks

    // initialize class to test

    // invoke method on class to test

    // assert return value

    // verify mock expectations


}