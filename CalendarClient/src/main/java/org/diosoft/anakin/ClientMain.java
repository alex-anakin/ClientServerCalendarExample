package org.diosoft.anakin;

import org.diosoft.anakin.service.CalendarService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.GregorianCalendar;

public class ClientMain /*extends Application*/ {

    public static void main(String[] args) throws RemoteException {
       // launch(args);

        String file1 = "/data/event1.txt";
        String file2 = "/data/event2.txt";

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/clientApplicationContext.xml");
        CalendarService service = context.getBean("calendarService", CalendarService.class);

        ClientMain main = new ClientMain();

        try {
            service.addEvent(file1);
            service.addEvent(file2);
            service.addEvent("Party", "Peace!Labor!May", "30.04.2015 17:00", "30.04.2015 22:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(service.getAllEvents());

        try {
            GregorianCalendar beginTimeForEvent = service.findTimeForEvent("30.04.2015", 120);
            System.out.println(beginTimeForEvent.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /*@Override
    public void start(Stage stage) throws Exception {
        String fxmlFile = "/fxml/hello.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setTitle("JavaFX and Maven");
        stage.setScene(new Scene(root));
        stage.show();
    }*/
}
