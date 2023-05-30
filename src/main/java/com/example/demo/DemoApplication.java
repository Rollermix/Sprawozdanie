package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@SpringBootApplication
public class DemoApplication {

	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static void main(String[] args) {
		String firstName = "Piotr";
		String lastName = "Czajka";
		Date runDate = new Date(System.currentTimeMillis());
		LogManager logManager = LogManager.getLogManager();
		Logger log = logManager.getLogger(Logger.GLOBAL_LOGGER_NAME);
		log.info(firstName + " " + lastName );
		log.info("Server started at: " + runDate.toString());
		SpringApplication.run(DemoApplication.class, args);
	}

}
