package org.diosoft.anakin.datastore;

import org.diosoft.anakin.model.Event;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;

public class MapDataStoreTest {

    MapDataStore testClass = new MapDataStore();
    UUID expectedUuidValue;
    Event expectedEventValue;

    @Before
    public void setUp() {

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
                .attenders(Arrays.asList(attenders))
                .build();

        expectedEventValue = event;
        expectedUuidValue = event.getId();
        testClass.storage.put(event.getId(), event);
    }

    @Test
    public void testAddEvent() {
        String title = "Test event";
        String description = "Test description";
        GregorianCalendar startDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 10, 0);
        GregorianCalendar endDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 12, 30);
        String[] attenders = {"test@gmail.com", "mail@ukr.net"};
        Event expectedValue = new Event.Builder()
                .title(title)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .attenders(Arrays.asList(attenders))
                .build();

        testClass.addEvent(expectedValue);
        Event returnedValue = testClass.storage.get(expectedValue.getId());
        assertEquals(expectedValue, returnedValue);
    }

    @Test
    public void testSearchByTitle() {
        String title = "Test event";
        List<Event> events = testClass.searchByTitle(title);
        Event returnedValue = events.get(0);
        assertEquals(expectedEventValue, returnedValue);
    }
}