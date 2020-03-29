package com.unideb.qsa.calculator.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * QSA.
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    /**
     * Main entry point to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
