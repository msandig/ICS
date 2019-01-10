package de.dhbw.ics.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class IcsApplication {

    public static void main(String[] args) {
        SpringApplication.run(IcsApplication.class, args);
    }
}
