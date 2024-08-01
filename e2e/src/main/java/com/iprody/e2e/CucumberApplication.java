package com.iprody.e2e;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the e2e application.
 * Initializes and runs the Spring Boot application using SpringApplication.
 */

@SpringBootApplication
public class CucumberApplication {

    public static void main(String[] args) {
        SpringApplication.run(CucumberApplication.class, args);
    }

}
