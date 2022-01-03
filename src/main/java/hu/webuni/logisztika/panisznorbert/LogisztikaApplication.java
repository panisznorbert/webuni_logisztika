package hu.webuni.logisztika.panisznorbert;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogisztikaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LogisztikaApplication.class, args);}

    @Override
    public void run(String... args) throws Exception {

        System.out.format("start");
    }
}
