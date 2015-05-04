package org.diosoft.anakin.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventTest {

    @Test
    public void testBuildingInstanceTitleCheck() {

        String title = "Test event";
        String description = "Test description";
        GregorianCalendar startDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 10, 0);
        GregorianCalendar endDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 12, 30);
        String[] attenders = {"test@gmail.com", "mail@ukr.net"};
        Event event = new Event.Builder()
                .title(title)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .attendees(Arrays.asList(attenders))
                .build();

        String expectedValue = title;
        String returnedValue = event.getTitle();
        assertEquals(expectedValue, returnedValue);
    }

    @Test
    public void testBuildingInstanceDescriptionCheck() {

        String title = "Test event";
        String description = "Test description";
        GregorianCalendar startDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 10, 0);
        GregorianCalendar endDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 12, 30);
        String[] attenders = {"test@gmail.com", "mail@ukr.net"};
        Event event = new Event.Builder()
                .title(title)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .attendees(Arrays.asList(attenders))
                .build();

        String expectedValue = description;
        String returnedValue = event.getDescription();
        assertEquals(expectedValue, returnedValue);
    }

    @Test
    public void testBuildingInstanceStartDateCheck() {

        String title = "Test event";
        String description = "Test description";
        GregorianCalendar startDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 10, 0);
        GregorianCalendar endDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 12, 30);
        String[] attenders = {"test@gmail.com", "mail@ukr.net"};
        Event event = new Event.Builder()
                .title(title)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .attendees(Arrays.asList(attenders))
                .build();

        GregorianCalendar expectedValue = startDate;
        GregorianCalendar returnedValue = event.getStartDate();
        assertEquals(expectedValue, returnedValue);
    }

    @Test
    public void testBuildingInstanceEndDateCheck() {

        String title = "Test event";
        String description = "Test description";
        GregorianCalendar startDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 10, 0);
        GregorianCalendar endDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 12, 30);
        String[] attenders = {"test@gmail.com", "mail@ukr.net"};
        Event event = new Event.Builder()
                .title(title)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .attendees(Arrays.asList(attenders))
                .build();

        GregorianCalendar expectedValue = endDate;
        GregorianCalendar returnedValue = event.getEndDate();
        assertEquals(expectedValue, returnedValue);
    }

    @Test
    public void testBuildingInstanceAttendeesCheck() {

        String title = "Test event";
        String description = "Test description";
        GregorianCalendar startDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 10, 0);
        GregorianCalendar endDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 12, 30);
        String[] attenders = {"test@gmail.com", "mail@ukr.net"};
        Event event = new Event.Builder()
                .title(title)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .attendees(Arrays.asList(attenders))
                .build();

        List <String> expectedValue = Arrays.asList(attenders);
        List <String> returnedValue = event.getAttendees();
        assertEquals(expectedValue, returnedValue);
    }
}