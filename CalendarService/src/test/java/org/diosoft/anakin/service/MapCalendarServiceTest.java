package org.diosoft.anakin.service;

import org.diosoft.anakin.datastore.MapDataStore;
import org.diosoft.anakin.model.Event;
import org.junit.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

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
        ParseException expectedValue = new ParseException("", 1);
        MapCalendarService testClass = new MapCalendarService(new MapDataStore());
        try {
            testClass.parseDate(date);
            fail("Exception should be thrown");
        } catch (Exception returnedValue) {
            assertSame(expectedValue.getClass(), returnedValue.getClass());
        }
    }

    @Test
    public void testCreateEvent() throws Exception {
        String title = "Test event";
        String description = "Test description";
        GregorianCalendar startDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 10, 0);
        GregorianCalendar endDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 12, 30);
        String start = "30.04.2015 10:00";
        String end = "30.04.2015 12:30";
        String[] attenders = {"test@gmail.com", "mail@ukr.net"};
        Event expectedValue = new Event.Builder()
                .title(title)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .attenders(Arrays.asList(attenders))
                .build();

        MapCalendarService testClass = new MapCalendarService(new MapDataStore());
        Event returnedValue = testClass.createEvent(title, description, start, end, attenders);
        assertNotNull(returnedValue);
    }




}