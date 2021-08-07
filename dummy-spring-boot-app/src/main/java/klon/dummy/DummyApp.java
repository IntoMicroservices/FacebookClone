package klon.dummy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SuppressWarnings("all")//disable sonar for the class
public class DummyApp {
    public static void main(String[] args) {
        SpringApplication.run(DummyApp.class, args);
    }

}
