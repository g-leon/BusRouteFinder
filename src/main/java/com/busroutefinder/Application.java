package com.busroutefinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class Application {
    final static Logger LOG = Logger.getLogger(String.valueOf(Application.class));

    public static void main(String[] args) {
        LOG.info("Starting server...");
        SpringApplication.run(Application.class, args);
    }

}
