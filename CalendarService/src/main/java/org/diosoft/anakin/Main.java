package org.diosoft.anakin;

import org.diosoft.anakin.service.CalendarService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;

public class Main {


    public static void main(String[] args) {

        String file1 = "/data/event1.txt";
        String file2 = "/data/event2.txt";

        Main main = new Main();

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        CalendarService service = context.getBean("calendarService", CalendarService.class);

        try {
            service.addEvent(service.createEvent(file1));
            service.addEvent(service.createEvent(file2));
            service.addEvent(service.createEvent("Party", "Peace!Labor!May", "01.05.2015 12:00", "01.05.2015 20:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(service.getAllEvent());
    }


}
