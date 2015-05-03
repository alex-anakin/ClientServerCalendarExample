package org.diosoft.anakin;

import org.diosoft.anakin.service.CalendarService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;
import java.text.ParseException;

public class ClientMain /*extends Application*/ {

    public static void main(String[] args) throws RemoteException {
       // launch(args);

        String file1 = "/data/event1.txt";
        String file2 = "/data/event2.txt";

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/clientApplicationContext.xml");
        CalendarService service = context.getBean("calendarService", CalendarService.class);

        ClientMain main = new ClientMain();

        try {
            service.addEvent(service.addEvent(file1));
            service.addEvent(service.addEvent(file2));
            service.addEvent(service.addEvent("Party", "Peace!Labor!May", "01.05.2015 12:00", "01.05.2015 20:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(service.getAllEvents());

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
